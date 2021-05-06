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
import com.unisys.aos.view.messageprocessor.entity.ABNR;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsAbnormalStatusDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAbnormalStatus;
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
 * Abnormal reason code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("ABNR")
@Slf4j
public class AbnrProcessor implements Processor {
    private final RdmsAbnormalStatusDAO rdmsAbnormalStatusDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public AbnrProcessor(RdmsAbnormalStatusDAO rdmsAbnormalStatusDAO, ReferenceProperties referenceProperties) {
        this.rdmsAbnormalStatusDAO = rdmsAbnormalStatusDAO;
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
        ABNR parsedObj = objectMapper.readValue(msg, ABNR.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<ABNR.AbnormalStatusData> newAbnormalStatuses = parsedObj.getAbnrs();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsAbnormalStatus> oldAbnormalStatuses = rdmsAbnormalStatusDAO.findAll();
        ConcurrentMap<String, RdmsAbnormalStatus> abnormalStatusMap = oldAbnormalStatuses.stream()
                .collect(Collectors.toConcurrentMap(RdmsAbnormalStatus::getIdentity, (p) -> p));

        // loop through all XML records
        // if AbnormalStatus Code already exists then UPDATE otherwise INSERT
        if (null != newAbnormalStatuses) {
            for (ABNR.AbnormalStatusData record : newAbnormalStatuses) {
                log.debug("processing AbnormalStatus record code [{}]", record.getANRC());

                // create empty domain abnormal reason entity and map properties
                RdmsAbnormalStatus newAbnormalStatus = new RdmsAbnormalStatus();
                newAbnormalStatus.setAbnormalReasonCode(record.getANRC());
                newAbnormalStatus.setDescription(record.getANRD());
                newAbnormalStatus.setLocalDescription(record.getARDC());

                newAbnormalStatus.setInspireTime(defaultInspireTime);
                newAbnormalStatus.setExpireTime(defaultExpireTime);

                RdmsAbnormalStatus oldAbnormalStatus = null;
                String abnormalReasonCode = record.getANRC();
                if (null != abnormalReasonCode) {
                    String identity = abnormalReasonCode + newAbnormalStatus.getInspireTime() + newAbnormalStatus.getExpireTime();
                    oldAbnormalStatus = abnormalStatusMap.get(identity);
                }
                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldAbnormalStatus) {
                        rdmsAbnormalStatusDAO.deleteInNewTx(oldAbnormalStatus.getId());
                    }
                } else {
                    if (null == oldAbnormalStatus) {
                        // new abnormal status code then create new record
                        newAbnormalStatus.setCreateTime(new Date());
                        newAbnormalStatus = rdmsAbnormalStatusDAO.insertInNewTx(newAbnormalStatus);
                        // update the map to show latest status
                        abnormalStatusMap.put(newAbnormalStatus.getIdentity(), newAbnormalStatus);
                    } else {
                        // not new then update record. If same ignore.
                        newAbnormalStatus.setCreateTime(oldAbnormalStatus.getCreateTime());
                        newAbnormalStatus.setUpdateTime(new Date());
                        newAbnormalStatus.setId(oldAbnormalStatus.getId());
                        if (!rdmsAbnormalStatusDAO.entityEqual(newAbnormalStatus, oldAbnormalStatus)) {
                            rdmsAbnormalStatusDAO.updateInNewTx(newAbnormalStatus, oldAbnormalStatus);
                            // update the map to show latest status
                            abnormalStatusMap.replace(newAbnormalStatus.getIdentity(), newAbnormalStatus);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newAbnormalStatus.getAbnormalReasonCode());
                        }
                        oldAbnormalStatuses.remove(oldAbnormalStatus);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain ABNR record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsAbnormalStatus record : oldAbnormalStatuses) {
                rdmsAbnormalStatusDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
