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
import com.unisys.aos.view.messageprocessor.entity.AIRL;
import com.unisys.aos.view.messageprocessor.entity.AirlineSubcompanyData;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsAirlineDAO;
import com.unisys.aos.view.viewentity.dao.RdmsAirlineSubcompanyDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAirline;
import com.unisys.aos.view.viewentity.entity.reference.RdmsAirlineSubcompany;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Airline code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("AIRL")
@Slf4j
public class AirlProcessor implements Processor {
    private final RdmsAirlineDAO rdmsAirlineDAO;
    private final RdmsAirlineSubcompanyDAO rdmsAirlineSubcompanyDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public AirlProcessor(RdmsAirlineDAO rdmsAirlineDAO, RdmsAirlineSubcompanyDAO rdmsAirlineSubcompanyDAO, ReferenceProperties referenceProperties) {
        this.rdmsAirlineDAO = rdmsAirlineDAO;
        this.rdmsAirlineSubcompanyDAO = rdmsAirlineSubcompanyDAO;
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
        AIRL parsedObj = objectMapper.readValue(msg, AIRL.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<AIRL.AirlineData> newAirlines = parsedObj.getAirls();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current airline entities
        Collection<RdmsAirline> oldAirlines = rdmsAirlineDAO.findAll();
        ConcurrentMap<String, RdmsAirline> airlineMap = oldAirlines.stream()
                .collect(Collectors.toConcurrentMap(RdmsAirline::getIdentity, (p) -> p));

        // loop through all XML records
        // if Airline Code already exists then UPDATE otherwise INSERT
        if (null != newAirlines) {
            for (AIRL.AirlineData record : newAirlines) {
                log.debug("processing Airline record code [{}]", record.getITOP());

                // create empty domain airline entity and map properties
                RdmsAirline newAirline = new RdmsAirline();
                List<RdmsAirlineSubcompany> newSubcompanyList = new ArrayList<>();

                newAirline.setIataOperatorCode(record.getITOP());
                newAirline.setIcaoOperatorCode(record.getICOP());
                newAirline.setDescription(record.getONAM());
                newAirline.setLocalDescription(record.getONMC());
                newAirline.setCountryCode(record.getCTRY());

                newAirline.setInspireTime(defaultInspireTime);
                newAirline.setExpireTime(defaultExpireTime);

                AIRL.Terminal terminal = record.getTerminal();
                if (null != terminal) {
                    newAirline.setDefaultTerminal(terminal.getTRML());
                    newAirline.setDefaultTerminalFlin(terminal.getFLIN());
                    newAirline.setDefaultTerminalMvin(terminal.getMVIN());
                }
                List<String> operatorGroupList = record.getOGRP();
                if (null != operatorGroupList) {
                    operatorGroupList.removeIf(""::equals);
                    newAirline.setOperatorGroups(operatorGroupList.toString());
                }
                newAirline.setAirlineCategory(record.getDORI());

                RdmsAirline oldAirline = null;
                String iataCode = record.getITOP();
                if (null != iataCode) {
                    String identity = iataCode + newAirline.getInspireTime() + newAirline.getExpireTime();
                    oldAirline = airlineMap.get(identity);
                }
                List<AirlineSubcompanyData> subcompanyDataList = record.getSUBC();
                Collection<RdmsAirlineSubcompany> oldSubcompanies = rdmsAirlineSubcompanyDAO.selectByAirlineCode(record.getITOP(), newAirline.getInspireTime(), newAirline.getExpireTime());
                ConcurrentMap<String, RdmsAirlineSubcompany> subcompanyMap = new ConcurrentHashMap<>();
                if (null != oldSubcompanies) {
                    subcompanyMap = oldSubcompanies.stream().collect(Collectors.toConcurrentMap(RdmsAirlineSubcompany::getIdentity, (p) -> p));
                }

                if (null != subcompanyDataList) {
                    for (AirlineSubcompanyData subcompanyData : subcompanyDataList) {
                        RdmsAirlineSubcompany newSubcompany = new RdmsAirlineSubcompany();
                        newSubcompany.setAirlineIataOperatorCode(record.getITOP());
                        newSubcompany.setAirlineSubcompanyCode(subcompanyData.getCODE());
                        newSubcompany.setDescription(subcompanyData.getNAME());
                        newSubcompany.setLocalDescription(subcompanyData.getNAMC());
                        newSubcompany.setParentCompanyInspireTime(newAirline.getInspireTime());
                        newSubcompany.setParentCompanyExpireTime(newAirline.getExpireTime());
                        newSubcompanyList.add(newSubcompany);
                    }
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldAirline) {
                        rdmsAirlineDAO.deleteInNewTx(oldAirline.getId());
                        rdmsAirlineSubcompanyDAO.deleteByAirlineCodeInNexTx(oldAirline.getIataOperatorCode(), newAirline.getInspireTime(), newAirline.getExpireTime());
                    }
                } else {
                    if (null == oldAirline) {
                        // new airline code then create new record
                        newAirline.setCreateTime(new Date());
                        newAirline = rdmsAirlineDAO.insertInNewTx(newAirline);
                        // update the map to show latest status
                        airlineMap.put(newAirline.getIdentity(), newAirline);
                        // new subcompany code then create new record
                        rdmsAirlineSubcompanyDAO.deleteByAirlineCodeInNexTx(newAirline.getIataOperatorCode(), newAirline.getInspireTime(), newAirline.getExpireTime());
                        if (newSubcompanyList.size() != 0) {
                            for (RdmsAirlineSubcompany subcompany : newSubcompanyList) {
                                subcompany.setCreateTime(new Date());
                                RdmsAirlineSubcompany tempSubcompany = rdmsAirlineSubcompanyDAO.insertInNewTx(subcompany);
                                subcompanyMap.put(subcompany.getIdentity(), tempSubcompany);
                            }
                        }
                    } else {
                        // not new then update record. If same ignore.
                        newAirline.setCreateTime(oldAirline.getCreateTime());
                        newAirline.setUpdateTime(new Date());
                        newAirline.setId(oldAirline.getId());
                        if (!rdmsAirlineDAO.entityEqual(newAirline, oldAirline)) {
                            rdmsAirlineDAO.updateInNewTx(newAirline, oldAirline);
                            // update the map to show latest status
                            airlineMap.replace(oldAirline.getIdentity(), newAirline);
                            // if their subcompanies code are different, update subcompany table. If same ignore.
                            updateSubcompany(subcompanyMap, newSubcompanyList, record, newAirline, false);
                        } else {
                            if (null != oldSubcompanies && oldSubcompanies.size() != 0) {
                                updateSubcompany(subcompanyMap, newSubcompanyList, record, newAirline, true);
                            } else {
                                if (newSubcompanyList.size() != 0) {
                                    for (RdmsAirlineSubcompany subcompany : newSubcompanyList) {
                                        subcompany.setCreateTime(new Date());
                                        rdmsAirlineSubcompanyDAO.insertInNewTx(subcompany);
                                        subcompanyMap.put(subcompany.getIdentity(), subcompany);
                                    }
                                } else {
                                    log.info("Identical entity found for code [{}] so skipped processing it", record.getITOP());
                                }
                            }
                        }
                        oldAirlines.remove(oldAirline);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain AIRL record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsAirline record : oldAirlines) {
                rdmsAirlineDAO.deleteInNewTx(record.getId());
                rdmsAirlineSubcompanyDAO.deleteByAirlineCodeInNexTx(record.getIataOperatorCode(), record.getInspireTime(), record.getExpireTime());
            }
        }
    }

    /***
     * Update airline subcompany table
     * @param subcompanyMap All RdmsAirlineSubcompany obtained through airline IATA operator code, key: subcompany code, value: RdmsAirlineSubcompany
     * @param newSubcompanyList Airline subcompany list
     * @param record Record
     * @param rdmsAirline airline
     * @param doLog Whether to log
     */
    public void updateSubcompany(ConcurrentMap<String, RdmsAirlineSubcompany> subcompanyMap, List<RdmsAirlineSubcompany> newSubcompanyList, AIRL.AirlineData record, RdmsAirline rdmsAirline, boolean doLog) {
        if (null == subcompanyMap || null == record) {
            return;
        }
        if (null != newSubcompanyList && newSubcompanyList.size() != 0) {
            ConcurrentMap<String, RdmsAirlineSubcompany> newSubcompanyMap = new ConcurrentHashMap<>();
            boolean hasNewSubcompany = false;
            for (RdmsAirlineSubcompany subcompany : newSubcompanyList) {
                RdmsAirlineSubcompany oldSubcompany = subcompanyMap.get(subcompany.getIdentity());
                if (null != oldSubcompany) {
                    if (!rdmsAirlineSubcompanyDAO.entityEqual(subcompany, oldSubcompany)) {
                        hasNewSubcompany = true;
                        subcompany.setId(oldSubcompany.getId());
                        subcompany.setCreateTime(oldSubcompany.getCreateTime());
                        subcompany.setUpdateTime(new Date());
                        rdmsAirlineSubcompanyDAO.updateInNewTx(subcompany, oldSubcompany);
                    }
                    newSubcompanyMap.put(oldSubcompany.getIdentity(), subcompany);
                    subcompanyMap.remove(oldSubcompany.getIdentity());
                } else {
                    hasNewSubcompany = true;
                    subcompany.setCreateTime(new Date());
                    rdmsAirlineSubcompanyDAO.insertInNewTx(subcompany);
                    newSubcompanyMap.put(subcompany.getIdentity(), subcompany);
                }
            }
            if (hasNewSubcompany) {
                if (!subcompanyMap.isEmpty()) {
                    for (Map.Entry<String, RdmsAirlineSubcompany> entry : subcompanyMap.entrySet()) {
                        rdmsAirlineSubcompanyDAO.deleteInNewTx(entry.getValue().getId());
                    }
                }
            } else {
                if (subcompanyMap.isEmpty()) {
                    if (doLog) {
                        log.info("Identical entity found for code [{}] so skipped processing it", record.getITOP());
                    }
                } else {
                    for (Map.Entry<String, RdmsAirlineSubcompany> entry : subcompanyMap.entrySet()) {
                        rdmsAirlineSubcompanyDAO.deleteInNewTx(entry.getValue().getId());
                    }
                }
            }
            subcompanyMap.clear();
            subcompanyMap.putAll(newSubcompanyMap);
        } else {
            rdmsAirlineSubcompanyDAO.deleteByAirlineCodeInNexTx(record.getITOP(), rdmsAirline.getInspireTime(), rdmsAirline.getExpireTime());
            subcompanyMap.clear();
        }
    }
}
