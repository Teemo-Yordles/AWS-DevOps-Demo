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
import com.unisys.aos.view.messageprocessor.entity.VIPL;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsVipPersonnalDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsVipPersonnal;
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
 * Vip personnal code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("VIPL")
@Slf4j
public class ViplProcessor implements Processor {
    private final RdmsVipPersonnalDAO rdmsVipPersonnalDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public ViplProcessor(RdmsVipPersonnalDAO rdmsVipPersonnalDAO, ReferenceProperties referenceProperties) {
        this.rdmsVipPersonnalDAO = rdmsVipPersonnalDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        VIPL parsedObj = objectMapper.readValue(msg, VIPL.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<VIPL.VipPersonnalData> msgs = parsedObj.getVipPersonnals();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsVipPersonnal> currentRecords = rdmsVipPersonnalDAO.findAll();
        ConcurrentMap<String, RdmsVipPersonnal> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsVipPersonnal::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (VIPL.VipPersonnalData record : msgs) {
            log.debug("processing vip personnal record code [{}]", record.getVPCD());

            // create empty domain vip personnal entity and map properties
            RdmsVipPersonnal entity = new RdmsVipPersonnal();
            entity.setVipPersonCode(record.getVPCD());
            entity.setVipPersonNumber(record.getVPNC());
            entity.setVipPersonFirstname(record.getVFNM());
            entity.setVipPersonLastname(record.getVLNM());
            entity.setVipPersonPosition(record.getVPON());
            entity.setVipPersonRanking(record.getVRNK());
            entity.setDescription(record.getVDES());
            entity.setLocalDescription(record.getVDSC());
            entity.setVipContactPerson(record.getVPTC());
            entity.setVipContactTelephoneNumber(record.getVCTN());
            entity.setVipContactMobileNumber(record.getVCMN());
            entity.setVipPersonWorkUnit(record.getVPWU());
            entity.setVipPersonRemarks(record.getVPPS());

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsVipPersonnal currentRecord = null;
            String vpcd = record.getVPCD();
            if (null != vpcd) {
                String identity = vpcd + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }

            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsVipPersonnalDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsVipPersonnalDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsVipPersonnalDAO.entityEqual(entity, currentRecord)) {
                        rdmsVipPersonnalDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getVipPersonCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsVipPersonnal record : currentRecords) {
                rdmsVipPersonnalDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
