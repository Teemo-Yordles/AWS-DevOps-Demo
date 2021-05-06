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
import com.unisys.aos.view.messageprocessor.entity.DLYL;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsDelayTypeDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsDelayType;
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
 * Flight delay code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("DLYL")
@Slf4j
public class DlylProcessor implements Processor {
    private final RdmsDelayTypeDAO rdmsDelayTypeDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public DlylProcessor(RdmsDelayTypeDAO rdmsDelayTypeDAO, ReferenceProperties referenceProperties) {
        this.rdmsDelayTypeDAO = rdmsDelayTypeDAO;
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
        DLYL parsedObj = objectMapper.readValue(msg, DLYL.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<DLYL.DelayTypeData> newDelayTypes = parsedObj.getDlyls();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsDelayType> oldDelayTypes = rdmsDelayTypeDAO.findAll();
        ConcurrentMap<String, RdmsDelayType> delayTypeMap = oldDelayTypes.stream()
                .collect(Collectors.toConcurrentMap(RdmsDelayType::getIdentity, (p) -> p));

        // loop through all XML records
        // if DelayType Code already exists then UPDATE otherwise INSERT
        if (null != newDelayTypes) {
            for (DLYL.DelayTypeData record : newDelayTypes) {
                log.debug("processing DelayType record code [{}]", record.getDCOD());

                // create empty domain delay code entity and map properties
                RdmsDelayType newDelayType = new RdmsDelayType();
                newDelayType.setDelayTypeCode(record.getDCOD());
                newDelayType.setDelayTypeCodeNumber(record.getDCDN());
                newDelayType.setDescription(record.getDDES());
                newDelayType.setLocalDescription(record.getDDSC());

                newDelayType.setInspireTime(defaultInspireTime);
                newDelayType.setExpireTime(defaultExpireTime);

                RdmsDelayType oldDelayType = null;
                String delayTypeCode = record.getDCOD();
                if (null != delayTypeCode) {
                    String identity = delayTypeCode + newDelayType.getInspireTime() + newDelayType.getExpireTime();
                    oldDelayType = delayTypeMap.get(identity);
                }
                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldDelayType) {
                        rdmsDelayTypeDAO.deleteInNewTx(oldDelayType.getId());
                    }
                } else {
                    if (null == oldDelayType) {
                        // new delayType code then create new record
                        newDelayType.setCreateTime(new Date());
                        newDelayType = rdmsDelayTypeDAO.insertInNewTx(newDelayType);
                        // update the map to show latest status
                        delayTypeMap.put(newDelayType.getIdentity(), newDelayType);
                    } else {
                        // not new then update record. If same ignore.
                        newDelayType.setCreateTime(oldDelayType.getCreateTime());
                        newDelayType.setUpdateTime(new Date());
                        newDelayType.setId(oldDelayType.getId());
                        if (!rdmsDelayTypeDAO.entityEqual(newDelayType, oldDelayType)) {
                            rdmsDelayTypeDAO.updateInNewTx(newDelayType, oldDelayType);
                            // update the map to show latest status
                            delayTypeMap.replace(newDelayType.getIdentity(), newDelayType);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newDelayType.getDelayTypeCode());
                        }
                        oldDelayTypes.remove(oldDelayType);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain DLYL record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsDelayType record : oldDelayTypes) {
                rdmsDelayTypeDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
