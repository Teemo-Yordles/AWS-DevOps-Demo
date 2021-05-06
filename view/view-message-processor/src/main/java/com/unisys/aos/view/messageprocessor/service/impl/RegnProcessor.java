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
import com.unisys.aos.view.messageprocessor.entity.REGN;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsRegistrationDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsRegistration;
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
 * Registration code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("REGN")
@Slf4j
public class RegnProcessor implements Processor {
    private final RdmsRegistrationDAO rdmsRegistrationDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public RegnProcessor(RdmsRegistrationDAO rdmsRegistrationDAO, ReferenceProperties referenceProperties) {
        this.rdmsRegistrationDAO = rdmsRegistrationDAO;
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
        REGN parsedObj = objectMapper.readValue(msg, REGN.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<REGN.RegistrationData> newRegistrations = parsedObj.getRegns();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsRegistration> oldRegistrations = rdmsRegistrationDAO.findAll();
        ConcurrentMap<String, RdmsRegistration> registrationMap = oldRegistrations.stream()
                .collect(Collectors.toConcurrentMap(RdmsRegistration::getIdentity, (p) -> p));

        // loop through all XML records
        // if Registration Code already exists then UPDATE otherwise INSERT
        if (null != newRegistrations) {
            for (REGN.RegistrationData record : newRegistrations) {
                log.debug("processing Registration record code [{}]", record.getRNUM());

                // create empty domain registration entity and map properties
                RdmsRegistration newRegistration = new RdmsRegistration();
                newRegistration.setRegistrationCode(record.getRNUM());
                newRegistration.setAircraftTypeCode(record.getITAT());
                newRegistration.setOrganizationId(record.getOWID());
                newRegistration.setAirlineCode(record.getACAL());
                newRegistration.setAirlineSubcompanyCode(record.getALSC());
                newRegistration.setMaxPax(record.getMAXP());
                newRegistration.setMaxFreightWeight(record.getMFWT());
                newRegistration.setMaxTakeoffWeight(record.getMTWT());

                newRegistration.setInspireTime(defaultInspireTime);
                newRegistration.setExpireTime(defaultExpireTime);

                RdmsRegistration oldRegistration = null;
                String registrationNumber = record.getRNUM();
                if (null != registrationNumber) {
                    String identity = registrationNumber + newRegistration.getInspireTime() + newRegistration.getExpireTime();
                    oldRegistration = registrationMap.get(identity);
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldRegistration) {
                        rdmsRegistrationDAO.deleteInNewTx(oldRegistration.getId());
                    }
                } else {
                    if (null == oldRegistration) {
                        // new registration code then create new record
                        newRegistration.setCreateTime(new Date());
                        newRegistration = rdmsRegistrationDAO.insertInNewTx(newRegistration);
                        // update the map to show latest status
                        registrationMap.put(newRegistration.getIdentity(), newRegistration);
                    } else {
                        // not new then update record. If same ignore.
                        newRegistration.setCreateTime(oldRegistration.getCreateTime());
                        newRegistration.setUpdateTime(new Date());
                        newRegistration.setId(oldRegistration.getId());
                        if (!rdmsRegistrationDAO.entityEqual(newRegistration, oldRegistration)) {
                            rdmsRegistrationDAO.updateInNewTx(newRegistration, oldRegistration);
                            // update the map to show latest status
                            registrationMap.replace(newRegistration.getIdentity(), newRegistration);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newRegistration.getRegistrationCode());
                        }
                        oldRegistrations.remove(oldRegistration);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain REGN record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsRegistration record : oldRegistrations) {
                rdmsRegistrationDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
