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
import com.unisys.aos.view.messageprocessor.entity.AIRC;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsAircraftTypeDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAircraftType;
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
 * Aircraft code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("AIRC")
@Slf4j
public class AircProcessor implements Processor {
    private final RdmsAircraftTypeDAO rdmsAircraftTypeDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public AircProcessor(RdmsAircraftTypeDAO rdmsAircraftTypeDAO, ReferenceProperties referenceProperties) {
        this.rdmsAircraftTypeDAO = rdmsAircraftTypeDAO;
        this.referenceProperties = referenceProperties;
    }

    /**
     * Process the message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        AIRC parsedObj = objectMapper.readValue(msg, AIRC.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<AIRC.AircraftTypeData> aircraftTypes = parsedObj.getAircs();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsAircraftType> currentRecords = rdmsAircraftTypeDAO.findAll();
        ConcurrentMap<String, RdmsAircraftType> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsAircraftType::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (AIRC.AircraftTypeData record : aircraftTypes) {
            log.debug("processing AircraftType record code [{}]", record.getITAT());

            // create empty domain aircraft type entity and map properties
            RdmsAircraftType entity = new RdmsAircraftType();
            entity.setIataCode(record.getITAT());
            entity.setIcaoCode(record.getICAT());
            entity.setDescription(record.getDESC());
            entity.setLocalDescription(record.getCDSC());
            entity.setSizeCategory(record.getCHAP());
            entity.setMaxPax(record.getMAXP());
            entity.setMaxFreightWeight(record.getMFWT());
            entity.setMaxTakeoffWeight(record.getMTWT());
            entity.setAircraftLength(record.getALEN());
            entity.setWingSpan(record.getWSPN());
            entity.setMinHandlingTime(record.getMHTM());
            entity.setMaxAirbridge(record.getMABR());

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            RdmsAircraftType currentRecord = null;
            String itatCode = record.getITAT();
            if (null != itatCode) {
                String identity = itatCode + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }
            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsAircraftTypeDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity.setCreateTime(new Date());
                    entity = rdmsAircraftTypeDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsAircraftTypeDAO.entityEqual(entity, currentRecord)) {
                        rdmsAircraftTypeDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getIataCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsAircraftType record : currentRecords) {
                rdmsAircraftTypeDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
