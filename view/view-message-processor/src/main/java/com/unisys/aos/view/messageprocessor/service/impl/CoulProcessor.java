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
import com.unisys.aos.view.messageprocessor.entity.COUL;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsCountryDAO;
import com.unisys.aos.view.viewentity.dao.RdmsRegionCountryAssociationDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCountry;
import com.unisys.aos.view.viewentity.entity.reference.RdmsRegionCountryAssociation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Country code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("COUL")
@Slf4j
public class CoulProcessor implements Processor {
    private final RdmsCountryDAO rdmsCountryDAO;
    private final RdmsRegionCountryAssociationDAO regionCountryAssociationDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public CoulProcessor(RdmsCountryDAO rdmsCountryDAO, RdmsRegionCountryAssociationDAO regionCountryAssociationDAO, ReferenceProperties referenceProperties) {
        this.rdmsCountryDAO = rdmsCountryDAO;
        this.regionCountryAssociationDAO = regionCountryAssociationDAO;
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
        COUL parsedObj = objectMapper.readValue(msg, COUL.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<COUL.CountryData> newCountries = parsedObj.getCouls();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current country entities
        Collection<RdmsCountry> oldCountries = rdmsCountryDAO.findAll();
        ConcurrentMap<String, RdmsCountry> countryMap = oldCountries.stream()
                .collect(Collectors.toConcurrentMap(RdmsCountry::getIdentity, (p) -> p));

        // loop through all XML records
        // if Country Code already exists then UPDATE otherwise INSERT
        if (null != newCountries) {
            for (COUL.CountryData record : newCountries) {
                log.debug("processing Country record code [{}]", record.getCOUC());

                // create empty domain country entity and map properties
                RdmsCountry newCountry = new RdmsCountry();
                newCountry.setCountryCode(record.getCOUC());
                newCountry.setDescription(record.getCOUN());
                newCountry.setLocalDescription(record.getCNMC());

                newCountry.setInspireTime(defaultInspireTime);
                newCountry.setExpireTime(defaultExpireTime);

                RdmsCountry oldCountry = null;
                String countryCode = record.getCOUC();
                if (null != countryCode) {
                    String identity = countryCode + newCountry.getInspireTime() + newCountry.getExpireTime();
                    oldCountry = countryMap.get(identity);
                }

                List<String> newRegionList = record.getREGC();
                newRegionList.removeIf(""::equals);
                Collection<RdmsRegionCountryAssociation> oldAssociations = regionCountryAssociationDAO.selectByCountryCode(countryCode, defaultInspireTime, defaultInspireTime);
                ConcurrentMap<String, RdmsRegionCountryAssociation> associationMap = new ConcurrentHashMap<>();
                if (null != oldAssociations) {
                    associationMap = oldAssociations.stream().collect(Collectors.toConcurrentMap(RdmsRegionCountryAssociation::getRegionCode, (p) -> p));
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldCountry) {
                        rdmsCountryDAO.deleteInNewTx(oldCountry.getId());
                        regionCountryAssociationDAO.deleteByCountryInNexTx(oldCountry.getCountryCode(), defaultInspireTime, defaultExpireTime);
                    }
                } else {

                    if (null == oldCountry) {
                        // new country code then create new record
                        newCountry.setCreateTime(new Date());
                        newCountry = rdmsCountryDAO.insertInNewTx(newCountry);
                        // update the map to show latest status - country
                        countryMap.put(newCountry.getIdentity(), newCountry);
                        // new association then create new record
                        regionCountryAssociationDAO.deleteByCountryInNexTx(newCountry.getCountryCode(), defaultInspireTime, defaultExpireTime);
                        for (String region : newRegionList) {
                            RdmsRegionCountryAssociation newAssociation = new RdmsRegionCountryAssociation();
                            newAssociation.setCountryCode(record.getCOUC());
                            newAssociation.setRegionCode(region);

                            newAssociation.setCountryInspireTime(defaultInspireTime);
                            newAssociation.setCountryExpireTime(defaultExpireTime);

                            newAssociation.setCreateTime(new Date());
                            regionCountryAssociationDAO.insertInNewTx(newAssociation);
                            // update the map to show latest status - region-country association
                            associationMap.put(region, newAssociation);
                        }
                    } else {
                        // not new then update record. If same ignore.
                        newCountry.setCreateTime(oldCountry.getCreateTime());
                        newCountry.setUpdateTime(new Date());
                        newCountry.setId(oldCountry.getId());
                        if (!rdmsCountryDAO.entityEqual(newCountry, oldCountry)) {
                            rdmsCountryDAO.updateInNewTx(newCountry, oldCountry);
                            // update the map to show latest status
                            countryMap.replace(oldCountry.getIdentity(), newCountry);
                            // if their region code are different, update association table. If same ignore.
                            updateAssociation(associationMap, newRegionList, record, false);
                        } else {
                            if (null != oldAssociations && oldAssociations.size() != 0) {
                                updateAssociation(associationMap, newRegionList, record, true);
                            } else {
                                if (newRegionList.size() != 0) {
                                    for (String regionCode : newRegionList) {
                                        RdmsRegionCountryAssociation association = new RdmsRegionCountryAssociation();
                                        association.setCountryCode(record.getCOUC());
                                        association.setRegionCode(regionCode);
                                        association.setCountryInspireTime(defaultInspireTime);
                                        association.setCountryExpireTime(defaultExpireTime);
                                        association.setCreateTime(new Date());
                                        regionCountryAssociationDAO.insertInNewTx(association);
                                        associationMap.put(regionCode, association);
                                    }
                                } else {
                                    log.info("Identical entity found for code [{}] so skipped processing it", newCountry.getCountryCode());
                                }
                            }
                        }
                        oldCountries.remove(oldCountry);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain COUL record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsCountry record : oldCountries) {
                rdmsCountryDAO.deleteInNewTx(record.getId());
                regionCountryAssociationDAO.deleteByCountryInNexTx(record.getCountryCode(), defaultInspireTime, defaultExpireTime);
            }
        }
    }

    /**
     * Update associations table
     *
     * @param associationMap All RdmsRegionCountryAssociation obtained through country code, key: country code, value: RdmsRegionCountryAssociation
     * @param newRegionList  Region code list
     * @param record         Record
     * @param doLog          Whether to log
     */
    public void updateAssociation(ConcurrentMap<String, RdmsRegionCountryAssociation> associationMap, List<String> newRegionList, COUL.CountryData record, boolean doLog) {
        if (null == associationMap || null == record) {
            return;
        }
        RdmsRegionCountryAssociation newAssociation = new RdmsRegionCountryAssociation();
        newAssociation.setCountryCode(record.getCOUC());
        if (null != newRegionList && newRegionList.size() != 0) {
            ConcurrentMap<String, RdmsRegionCountryAssociation> newAssociationMap = new ConcurrentHashMap<>();
            boolean hasNew = false;
            for (String newRegion : newRegionList) {
                RdmsRegionCountryAssociation association = associationMap.get(newRegion);
                if (null != association) {
                    newAssociationMap.put(newRegion, association);
                    associationMap.remove(newRegion);
                } else {
                    hasNew = true;
                    newAssociation.setRegionCode(newRegion);
                    newAssociation.setCountryInspireTime(referenceProperties.getDefaultInspireTime());
                    newAssociation.setCountryExpireTime(referenceProperties.getDefaultExpireTime());
                    newAssociation.setCreateTime(new Date());
                    regionCountryAssociationDAO.insertInNewTx(newAssociation);
                    newAssociationMap.put(newRegion, newAssociation);
                }
            }
            if (hasNew) {
                if (!associationMap.isEmpty()) {
                    for (Map.Entry<String, RdmsRegionCountryAssociation> entry : associationMap.entrySet()) {
                        regionCountryAssociationDAO.deleteInNewTx(entry.getValue().getId());
                    }
                }
            } else {
                if (associationMap.isEmpty() && doLog) {
                    log.info("Identical entity found for code [{}] so skipped processing it", record.getCOUC());
                } else {
                    for (Map.Entry<String, RdmsRegionCountryAssociation> entry : associationMap.entrySet()) {
                        regionCountryAssociationDAO.deleteInNewTx(entry.getValue().getId());
                    }
                }
            }
            associationMap.clear();
            associationMap.putAll(newAssociationMap);
        } else {
            regionCountryAssociationDAO.deleteByCountryInNexTx(record.getCOUC(), referenceProperties.getDefaultInspireTime(), referenceProperties.getDefaultExpireTime());
            associationMap.clear();
        }
    }

}
