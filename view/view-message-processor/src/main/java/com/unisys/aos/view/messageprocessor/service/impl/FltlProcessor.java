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
import com.unisys.aos.view.messageprocessor.entity.FLTL;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsFlightTypeDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsFlightType;
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
 * FlightType code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("FLTL")
@Slf4j
public class FltlProcessor implements Processor {

    private final RdmsFlightTypeDAO rdmsFlightTypeDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public FltlProcessor(RdmsFlightTypeDAO rdmsFlightTypeDAO, ReferenceProperties referenceProperties) {
        this.rdmsFlightTypeDAO = rdmsFlightTypeDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        FLTL parsedObj = objectMapper.readValue(msg, FLTL.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<FLTL.FlightTypeData> msgs = parsedObj.getFlightTypes();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsFlightType> currentRecords = rdmsFlightTypeDAO.findAll();
        ConcurrentMap<String, RdmsFlightType> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsFlightType::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (FLTL.FlightTypeData record : msgs) {
            log.debug("processing FlightType record code [{}]", record.getFTYP());

            // create empty domain flight type entity and map properties
            RdmsFlightType entity = new RdmsFlightType();
            entity.setFlightTypeCode(record.getFTYP());
            entity.setFlightType(record.getCTYP());
            entity.setDescription(record.getFDES());
            entity.setLocalDescription(record.getFDSC());
            entity.setCommercial("Y".equalsIgnoreCase(record.getFCML()));

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsFlightType currentRecord = null;
            String ftyp = record.getFTYP();
            if (null != ftyp) {
                String identity = ftyp + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }

            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsFlightTypeDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsFlightTypeDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsFlightTypeDAO.entityEqual(entity, currentRecord)) {
                        rdmsFlightTypeDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getFlightTypeCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsFlightType record : currentRecords) {
                rdmsFlightTypeDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
