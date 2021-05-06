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
import com.unisys.aos.view.messageprocessor.entity.PLST;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsPierDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsPier;
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
 * Pier code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("PLST")
@Slf4j
public class PlstProcessor implements Processor {

    private final RdmsPierDAO rdmsPierDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public PlstProcessor(RdmsPierDAO rdmsPierDAO, ReferenceProperties referenceProperties) {
        this.rdmsPierDAO = rdmsPierDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        PLST parsedObj = objectMapper.readValue(msg, PLST.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<PLST.PierData> msgs = parsedObj.getPiers();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsPier> currentRecords = rdmsPierDAO.findAll();
        ConcurrentMap<String, RdmsPier> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsPier::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (PLST.PierData record : msgs) {
            log.debug("processing pier record code [{}]", record.getPCOD());

            // create empty domain pier entity and map properties
            RdmsPier entity = new RdmsPier();
            entity.setPierCode(record.getPCOD());
            entity.setDescription(record.getPRNM());
            entity.setLocalDescription(record.getPNMC());
            entity.setPierCategory(record.getPCAT());
            entity.setTerminalCode(record.getPTML());

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsPier currentRecord = null;
            String pcod = record.getPCOD();
            if (pcod != null) {
                String identity = pcod + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }

            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsPierDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsPierDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsPierDAO.entityEqual(entity, currentRecord)) {
                        rdmsPierDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getPierCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsPier record : currentRecords) {
                rdmsPierDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
