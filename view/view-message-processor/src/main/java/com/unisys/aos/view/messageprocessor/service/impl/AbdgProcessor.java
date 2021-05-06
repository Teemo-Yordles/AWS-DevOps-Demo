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
import com.unisys.aos.view.messageprocessor.entity.ABDG;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.messageprocessor.util.ProcessorUtil;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsAirbridgeDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAirbridge;
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
 * Airbridge code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("ABDG")
@Slf4j
public class AbdgProcessor implements Processor {
    private final RdmsAirbridgeDAO rdmsAirbridgeDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public AbdgProcessor(RdmsAirbridgeDAO rdmsAirbridgeDAO, ReferenceProperties referenceProperties) {
        this.rdmsAirbridgeDAO = rdmsAirbridgeDAO;
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
        ABDG parsedObj = objectMapper.readValue(msg, ABDG.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<ABDG.AirbridgeData> newAirbridges = parsedObj.getAbdgs();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current airbridge entities
        Collection<RdmsAirbridge> oldAirbridges = rdmsAirbridgeDAO.findAll();
        ConcurrentMap<String, RdmsAirbridge> airbridgeMap = oldAirbridges.stream()
                .collect(Collectors.toConcurrentMap(RdmsAirbridge::getIdentity, (p) -> p));

        // loop through all XML records
        // if Airbridge Code already exists then UPDATE otherwise INSERT
        if (null != newAirbridges) {
            for (ABDG.AirbridgeData record : newAirbridges) {
                log.debug("processing Airbridge record code [{}]", record.getABCD());

                // create empty domain airbridge entity and map properties
                RdmsAirbridge newAirbridge = new RdmsAirbridge();
                newAirbridge.setAirbridgeCode(record.getABCD());
                newAirbridge.setDescription(record.getDESC());
                newAirbridge.setLocalDescription(record.getCDSC());
                newAirbridge.setMaxHeight(record.getAHGT());
                newAirbridge.setTerminalCode(record.getATML());
                newAirbridge.setSupportA380(ProcessorUtil.stringToBoolean(record.getATHE()));

                newAirbridge.setInspireTime(defaultInspireTime);
                newAirbridge.setExpireTime(defaultExpireTime);

                List<String> standList = record.getSTND();
                if (null != standList) {
                    standList.removeIf(""::equals);
                    newAirbridge.setAssociatedStands(standList.toString());
                }
                List<String> gateList = record.getGATE();
                if (null != gateList) {
                    gateList.removeIf(""::equals);
                    newAirbridge.setAssociatedGates(gateList.toString());
                }

                newAirbridge.setArmNumber(record.getAMNO());

                RdmsAirbridge oldAirbridge = null;
                String airbridgeCode = record.getABCD();
                if (null != airbridgeCode) {
                    String identity = airbridgeCode + newAirbridge.getInspireTime() + newAirbridge.getExpireTime();
                    oldAirbridge = airbridgeMap.get(identity);
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldAirbridge) {
                        rdmsAirbridgeDAO.deleteInNewTx(oldAirbridge.getId());
                    }
                } else {
                    if (null == oldAirbridge) {
                        // new airbridge code then create new record
                        newAirbridge.setCreateTime(new Date());
                        newAirbridge = rdmsAirbridgeDAO.insertInNewTx(newAirbridge);
                        // update the map to show latest status - airbridge
                        airbridgeMap.put(newAirbridge.getIdentity(), newAirbridge);
                    } else {
                        // not new then update record. If same ignore.
                        newAirbridge.setCreateTime(oldAirbridge.getCreateTime());
                        newAirbridge.setUpdateTime(new Date());
                        newAirbridge.setId(oldAirbridge.getId());
                        if (!rdmsAirbridgeDAO.entityEqual(newAirbridge, oldAirbridge)) {
                            rdmsAirbridgeDAO.updateInNewTx(newAirbridge, oldAirbridge);
                            // update the map to show latest status
                            airbridgeMap.replace(oldAirbridge.getIdentity(), newAirbridge);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newAirbridge.getAirbridgeCode());
                        }
                        oldAirbridges.remove(oldAirbridge);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain ABDG record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsAirbridge record : oldAirbridges) {
                rdmsAirbridgeDAO.deleteInNewTx(record.getId());
            }
        }
    }
}
