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
import com.unisys.aos.view.messageprocessor.entity.CITY;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsCityDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCity;
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
 * City code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("CITY")
@Slf4j
public class CityProcessor implements Processor {

    private final RdmsCityDAO rdmsCityDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public CityProcessor(RdmsCityDAO rdmsCityDAO, ReferenceProperties referenceProperties) {
        this.rdmsCityDAO = rdmsCityDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        CITY parsedObj = objectMapper.readValue(msg, CITY.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<CITY.CityData> msgs = parsedObj.getCitys();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsCity> currentRecords = rdmsCityDAO.findAll();
        ConcurrentMap<String, RdmsCity> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsCity::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (CITY.CityData record : msgs) {
            log.debug("processing city record code [{}]", record.getITCD());

            // create empty domain city entity and map properties
            RdmsCity entity = new RdmsCity();
            entity.setIataCityCode(record.getITCD());
            entity.setIcaoCityCode(record.getICCD());
            entity.setDescription(record.getCNAM());
            entity.setLocalDescription(record.getCNMC());
            entity.setCountryCode(record.getCTRY());
            List<CITY.AbbrData> abbrs = record.getAbbrs();
            if (null != abbrs && abbrs.size() != 0) {
                for (CITY.AbbrData abbr : abbrs) {
                    switch (abbr.getCODE()) {
                        case "ABR1":
                            entity.setAbbreviation1(abbr.getABBR());
                            break;
                        case "ABR2":
                            entity.setAbbreviation2(abbr.getABBR());
                            break;
                        case "ABR3":
                            entity.setAbbreviation3(abbr.getABBR());
                            break;
                        case "ABR4":
                            entity.setAbbreviation4(abbr.getABBR());
                            break;
                        case "ABR5":
                            entity.setAbbreviation5(abbr.getABBR());
                            break;
                        case "ABR6":
                            entity.setAbbreviation6(abbr.getABBR());
                            break;
                        default:
                            break;
                    }
                }
            }

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsCity currentRecord = null;
            String itcd = record.getITCD();
            if (null != itcd) {
                String identity = itcd + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }

            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsCityDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsCityDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsCityDAO.entityEqual(entity, currentRecord)) {
                        rdmsCityDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getIataCityCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsCity record : currentRecords) {
                rdmsCityDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
