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
import com.unisys.aos.view.messageprocessor.entity.CHLT;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsChuteDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsChute;
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
 * Chute code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("CHLT")
@Slf4j
public class ChltProcessor implements Processor {

    private final RdmsChuteDAO rdmsChuteDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public ChltProcessor(RdmsChuteDAO rdmsChuteDAO, ReferenceProperties referenceProperties) {
        this.rdmsChuteDAO = rdmsChuteDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        CHLT parsedObj = objectMapper.readValue(msg, CHLT.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<CHLT.ChuteData> msgs = parsedObj.getChutes();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsChute> currentRecords = rdmsChuteDAO.findAll();
        ConcurrentMap<String, RdmsChute> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsChute::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (CHLT.ChuteData record : msgs) {
            log.debug("processing chute record code [{}]", record.getCCOD());

            // create empty domain chute entity and map properties
            RdmsChute entity = new RdmsChute();
            entity.setChuteCode(record.getCCOD());
            entity.setDescription(record.getCHNM());
            entity.setLocalDescription(record.getCNMC());
            entity.setChuteCategory(record.getCCAT());
            entity.setTerminalCode(record.getCTML());

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsChute currentRecord = null;
            String ccod = record.getCCOD();
            if (null != ccod) {
                String identity = ccod + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }
            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsChuteDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsChuteDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsChuteDAO.entityEqual(entity, currentRecord)) {
                        rdmsChuteDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getChuteCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsChute record : currentRecords) {
                rdmsChuteDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
