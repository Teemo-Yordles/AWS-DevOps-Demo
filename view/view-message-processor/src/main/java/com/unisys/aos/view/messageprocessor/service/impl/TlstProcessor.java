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
import com.unisys.aos.view.messageprocessor.entity.TLST;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.messageprocessor.util.ProcessorUtil;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsTerminalDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsTerminal;
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
 * Terminal message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("TLST")
@Slf4j
public class TlstProcessor implements Processor {
    private final RdmsTerminalDAO rdmsTerminalDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public TlstProcessor(RdmsTerminalDAO rdmsTerminalDAO, ReferenceProperties referenceProperties) {
        this.rdmsTerminalDAO = rdmsTerminalDAO;
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
        TLST parsedObj = objectMapper.readValue(msg, TLST.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<TLST.TerminalData> newTerminals = parsedObj.getTlsts();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsTerminal> oldTerminals = rdmsTerminalDAO.findAll();
        ConcurrentMap<String, RdmsTerminal> terminalMap = oldTerminals.stream()
                .collect(Collectors.toConcurrentMap(RdmsTerminal::getIdentity, (p) -> p));

        // loop through all XML records
        // if Terminal Code already exists then UPDATE otherwise INSERT
        if (null != newTerminals) {
            for (TLST.TerminalData record : newTerminals) {
                log.debug("processing Terminal record code [{}]", record.getTCOD());

                // create empty domain aircraft type entity and map properties
                RdmsTerminal newTerminal = new RdmsTerminal();
                newTerminal.setTerminalCode(record.getTCOD());
                newTerminal.setDescription(record.getTNAM());
                newTerminal.setLocalDescription(record.getTNMC());
                newTerminal.setTerminalCategory(record.getTCAT());
                newTerminal.setPassenger(ProcessorUtil.stringToBoolean(record.getTPAX()));

                newTerminal.setInspireTime(defaultInspireTime);
                newTerminal.setExpireTime(defaultExpireTime);

                RdmsTerminal oldTerminal = null;
                String terminalCode = record.getTCOD();
                if (null != terminalCode) {
                    String identity = terminalCode + newTerminal.getInspireTime() + newTerminal.getExpireTime();
                    oldTerminal = terminalMap.get(identity);
                }
                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldTerminal) {
                        rdmsTerminalDAO.deleteInNewTx(oldTerminal.getId());
                    }
                } else {
                    if (null == oldTerminal) {
                        // new registration code then create new record
                        newTerminal.setCreateTime(new Date());
                        newTerminal = rdmsTerminalDAO.insertInNewTx(newTerminal);
                        // update the map to show latest status
                        terminalMap.put(newTerminal.getIdentity(), newTerminal);
                    } else {
                        // not new then update record. If same ignore.
                        newTerminal.setCreateTime(oldTerminal.getCreateTime());
                        newTerminal.setUpdateTime(new Date());
                        newTerminal.setId(oldTerminal.getId());
                        if (!rdmsTerminalDAO.entityEqual(newTerminal, oldTerminal)) {
                            rdmsTerminalDAO.updateInNewTx(newTerminal, oldTerminal);
                            // update the map to show latest status
                            terminalMap.replace(newTerminal.getIdentity(), newTerminal);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newTerminal.getTerminalCode());
                        }
                        oldTerminals.remove(oldTerminal);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain TLST record content from XML.");
        }


        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsTerminal record : oldTerminals) {
                rdmsTerminalDAO.deleteInNewTx(record.getId());
            }
        }
    }
}
