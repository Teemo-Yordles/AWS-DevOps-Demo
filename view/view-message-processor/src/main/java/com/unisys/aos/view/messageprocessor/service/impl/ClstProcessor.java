/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.unisys.aos.view.messageprocessor.entity.CLST;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsCheckinDeskDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCheckinDesk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * CheckinDesk code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("CLST")
@Slf4j
public class ClstProcessor implements Processor {

    private final RdmsCheckinDeskDAO rdmsCheckinDeskDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public ClstProcessor(RdmsCheckinDeskDAO rdmsCheckinDeskDAO, ReferenceProperties referenceProperties) {
        this.rdmsCheckinDeskDAO = rdmsCheckinDeskDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        CLST parsedObj = objectMapper.readValue(msg, CLST.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<CLST.CheckinDeskData> msgs = parsedObj.getCheckinDesks();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsCheckinDesk> currentRecords = rdmsCheckinDeskDAO.findAll();
        ConcurrentMap<String, RdmsCheckinDesk> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsCheckinDesk::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (CLST.CheckinDeskData record : msgs) {
            log.debug("processing checkin desk record code [{}]", record.getCCOD());

            // create empty domain checkin desk entity and map properties
            RdmsCheckinDesk entity = new RdmsCheckinDesk();
            entity.setCheckinDeskCode(record.getCCOD());
            entity.setDescription(record.getCTNM());
            entity.setLocalDescription(record.getCNMC());
            entity.setCheckinDeskCategory(record.getCCAT());
            entity.setTerminalCode(record.getCTML());
            entity.setTransfer("Y".equalsIgnoreCase(record.getCTRA()));

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsCheckinDesk currentRecord = null;
            String ccod = record.getCCOD();
            if (null != ccod) {
                String identity = ccod + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }

            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsCheckinDeskDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsCheckinDeskDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsCheckinDeskDAO.entityEqual(entity, currentRecord)) {
                        rdmsCheckinDeskDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getCheckinDeskCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsCheckinDesk record : currentRecords) {
                rdmsCheckinDeskDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
