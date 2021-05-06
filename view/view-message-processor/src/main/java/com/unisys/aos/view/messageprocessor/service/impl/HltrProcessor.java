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
import com.unisys.aos.view.messageprocessor.entity.HLTR;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsHandlingAgentDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsHandlingAgent;
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
 * Handling transaction code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("HLTR")
@Slf4j
public class HltrProcessor implements Processor {
    private final RdmsHandlingAgentDAO rdmsHandlingAgentDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public HltrProcessor(RdmsHandlingAgentDAO rdmsHandlingAgentDAO, ReferenceProperties referenceProperties) {
        this.rdmsHandlingAgentDAO = rdmsHandlingAgentDAO;
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
        HLTR parsedObj = objectMapper.readValue(msg, HLTR.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<HLTR.HandlingAgentData> newHandlingAgents = parsedObj.getHltrs();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsHandlingAgent> oldHandlingAgents = rdmsHandlingAgentDAO.findAll();
        ConcurrentMap<String, RdmsHandlingAgent> handlingAgentMap = oldHandlingAgents.stream()
                .collect(Collectors.toConcurrentMap(RdmsHandlingAgent::getIdentity, (p) -> p));

        // loop through all XML records
        // if HandlingAgent Code already exists then UPDATE otherwise INSERT
        if (null != newHandlingAgents) {
            for (HLTR.HandlingAgentData record : newHandlingAgents) {
                log.debug("processing HandlingAgent record code [{}]", record.getHLTC());

                // create empty domain handling agent entity and map properties
                RdmsHandlingAgent newHandlingAgent = new RdmsHandlingAgent();
                newHandlingAgent.setHandlingAgentCode(record.getHLTC());
                newHandlingAgent.setDescription(record.getHLTD());
                newHandlingAgent.setLocalDescription(record.getHTDC());
                newHandlingAgent.setHandlingAgentCategory(record.getHLTT());
                newHandlingAgent.setUnitMeasure(record.getUMNM());

                newHandlingAgent.setInspireTime(defaultInspireTime);
                newHandlingAgent.setExpireTime(defaultExpireTime);

                RdmsHandlingAgent oldHandlingAgent = null;
                String handlingAgentCode = record.getHLTC();
                if (null != handlingAgentCode) {
                    String identity = handlingAgentCode + newHandlingAgent.getInspireTime() + newHandlingAgent.getExpireTime();
                    oldHandlingAgent = handlingAgentMap.get(identity);
                }
                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldHandlingAgent) {
                        rdmsHandlingAgentDAO.deleteInNewTx(oldHandlingAgent.getId());
                    }
                } else {
                    if (null == oldHandlingAgent) {
                        // new handlingAgent code then create new record
                        newHandlingAgent.setCreateTime(new Date());
                        newHandlingAgent = rdmsHandlingAgentDAO.insertInNewTx(newHandlingAgent);
                        // update the map to show latest status
                        handlingAgentMap.put(newHandlingAgent.getIdentity(), newHandlingAgent);
                    } else {
                        // not new then update record. If same ignore.
                        newHandlingAgent.setCreateTime(oldHandlingAgent.getCreateTime());
                        newHandlingAgent.setUpdateTime(new Date());
                        newHandlingAgent.setId(oldHandlingAgent.getId());
                        if (!rdmsHandlingAgentDAO.entityEqual(newHandlingAgent, oldHandlingAgent)) {
                            rdmsHandlingAgentDAO.updateInNewTx(newHandlingAgent, oldHandlingAgent);
                            // update the map to show latest status
                            handlingAgentMap.replace(newHandlingAgent.getIdentity(), newHandlingAgent);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newHandlingAgent.getHandlingAgentCode());
                        }
                        oldHandlingAgents.remove(oldHandlingAgent);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain HLTR record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsHandlingAgent record : oldHandlingAgents) {
                rdmsHandlingAgentDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
