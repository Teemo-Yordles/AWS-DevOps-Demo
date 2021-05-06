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
import com.unisys.aos.view.messageprocessor.entity.GLST;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsGateDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsGate;
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
 * Gate code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("GLST")
@Slf4j
public class GlstProcessor implements Processor {
    private final RdmsGateDAO rdmsGateDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public GlstProcessor(RdmsGateDAO rdmsGateDAO, ReferenceProperties referenceProperties) {
        this.rdmsGateDAO = rdmsGateDAO;
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
        GLST parsedObj = objectMapper.readValue(msg, GLST.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<GLST.GateData> newGates = parsedObj.getGlsts();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current gate entities
        Collection<RdmsGate> oldGates = rdmsGateDAO.findAll();
        ConcurrentMap<String, RdmsGate> gateMap = oldGates.stream()
                .collect(Collectors.toConcurrentMap(RdmsGate::getIdentity, (p) -> p));

        // loop through all XML records
        // if Gate Code already exists then UPDATE otherwise INSERT
        if (null != newGates) {
            for (GLST.GateData record : newGates) {
                log.debug("processing Gate record code [{}]", record.getGCOD());

                // create empty domain gate entity and map properties
                RdmsGate newGate = new RdmsGate();
                newGate.setGateCode(record.getGCOD());
                newGate.setDescription(record.getGTNM());
                newGate.setLocalDescription(record.getGNMC());
                newGate.setGateCategory(record.getGCAT());
                newGate.setTerminalCode(record.getGTML());
                newGate.setPierCode(record.getPIER());

                newGate.setInspireTime(defaultInspireTime);
                newGate.setExpireTime(defaultExpireTime);

                RdmsGate oldGate = null;
                String gateCode = record.getGCOD();
                if (null != gateCode) {
                    String identity = gateCode + newGate.getInspireTime() + newGate.getExpireTime();
                    oldGate = gateMap.get(identity);
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldGate) {
                        rdmsGateDAO.deleteInNewTx(oldGate.getId());
                    }
                } else {
                    if (null == oldGate) {
                        // new gate code then create new record
                        newGate.setCreateTime(new Date());
                        newGate = rdmsGateDAO.insertInNewTx(newGate);
                        // update the map to show latest status - gate
                        gateMap.put(newGate.getIdentity(), newGate);
                    } else {
                        // not new then update record. If same ignore.
                        newGate.setCreateTime(oldGate.getCreateTime());
                        newGate.setUpdateTime(new Date());
                        newGate.setId(oldGate.getId());
                        if (!rdmsGateDAO.entityEqual(newGate, oldGate)) {
                            rdmsGateDAO.updateInNewTx(newGate, oldGate);
                            // update the map to show latest status
                            gateMap.replace(oldGate.getIdentity(), newGate);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newGate.getGateCode());
                        }
                        oldGates.remove(oldGate);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain GLST record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsGate record : oldGates) {
                rdmsGateDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
