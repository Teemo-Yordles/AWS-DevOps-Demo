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
import com.unisys.aos.view.messageprocessor.entity.STTS;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.messageprocessor.util.ProcessorUtil;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsStatusDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsStatus;
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
 * Flight status message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("STTS")
@Slf4j
public class SttsProcessor implements Processor {
    private final RdmsStatusDAO rdmsStatusDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public SttsProcessor(RdmsStatusDAO rdmsStatusDAO, ReferenceProperties referenceProperties) {
        this.rdmsStatusDAO = rdmsStatusDAO;
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
        STTS parsedObj = objectMapper.readValue(msg, STTS.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<STTS.StatusData> newStatuses = parsedObj.getSttss();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsStatus> oldStatuses = rdmsStatusDAO.findAll();
        ConcurrentMap<String, RdmsStatus> statusMap = oldStatuses.stream()
                .collect(Collectors.toConcurrentMap(RdmsStatus::getIdentity, (p) -> p));

        // loop through all XML records
        // if Status Code already exists then UPDATE otherwise INSERT
        if (null != newStatuses) {
            for (STTS.StatusData record : newStatuses) {
                log.debug("processing Status record code [{}]", record.getSTTC());

                // create empty domain status entity and map properties
                RdmsStatus newStatus = new RdmsStatus();
                newStatus.setStatusCode(record.getSTTC());
                newStatus.setDescription(record.getSTTD());
                newStatus.setLocalDescription(record.getSTDC());
                newStatus.setAbnormal(ProcessorUtil.stringToBoolean(record.getABNS()));

                newStatus.setInspireTime(defaultInspireTime);
                newStatus.setExpireTime(defaultExpireTime);

                RdmsStatus oldStatus = null;
                String statusCode = record.getSTTC();
                if (null != statusCode) {
                    String identity = statusCode + newStatus.getInspireTime() + newStatus.getExpireTime();
                    oldStatus = statusMap.get(identity);
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldStatus) {
                        rdmsStatusDAO.deleteInNewTx(oldStatus.getId());
                    }
                } else {
                    if (null == oldStatus) {
                        // new status code then create new record
                        newStatus.setCreateTime(new Date());
                        newStatus = rdmsStatusDAO.insertInNewTx(newStatus);
                        // update the map to show latest status
                        statusMap.put(newStatus.getIdentity(), newStatus);
                    } else {
                        // not new then update record. If same ignore.
                        newStatus.setCreateTime(oldStatus.getCreateTime());
                        newStatus.setUpdateTime(new Date());
                        newStatus.setId(oldStatus.getId());
                        if (!rdmsStatusDAO.entityEqual(newStatus, oldStatus)) {
                            rdmsStatusDAO.updateInNewTx(newStatus, oldStatus);
                            // update the map to show latest status
                            statusMap.replace(newStatus.getIdentity(), newStatus);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newStatus.getStatusCode());
                        }
                        oldStatuses.remove(oldStatus);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain STTS record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsStatus record : oldStatuses) {
                rdmsStatusDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
