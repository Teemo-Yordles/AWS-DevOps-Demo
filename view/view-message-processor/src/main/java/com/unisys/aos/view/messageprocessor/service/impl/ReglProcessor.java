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
import com.unisys.aos.view.messageprocessor.entity.REGL;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsRegionDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsRegion;
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
 * Region code message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("REGL")
@Slf4j
public class ReglProcessor implements Processor {
    private final RdmsRegionDAO rdmsRegionDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public ReglProcessor(RdmsRegionDAO rdmsRegionDAO, ReferenceProperties referenceProperties) {
        this.rdmsRegionDAO = rdmsRegionDAO;
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
        REGL parsedObj = objectMapper.readValue(msg, REGL.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<REGL.RegionData> newRegions = parsedObj.getRegls();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsRegion> oldRegions = rdmsRegionDAO.findAll();
        ConcurrentMap<String, RdmsRegion> regionMap = oldRegions.stream()
                .collect(Collectors.toConcurrentMap(RdmsRegion::getIdentity, (p) -> p));

        // loop through all XML records
        // if Region Code already exists then UPDATE otherwise INSERT
        if (null != newRegions) {
            for (REGL.RegionData record : newRegions) {
                log.debug("processing Region record code [{}]", record.getREGC());

                // create empty domain region entity and map properties
                RdmsRegion newRegion = new RdmsRegion();
                newRegion.setRegionCode(record.getREGC());
                newRegion.setDescription(record.getREGN());
                newRegion.setLocalDescription(record.getRGNC());

                newRegion.setInspireTime(defaultInspireTime);
                newRegion.setExpireTime(defaultExpireTime);

                RdmsRegion oldRegion = null;
                String regionCode = record.getREGC();
                if (null != regionCode) {
                    String identity = regionCode + newRegion.getInspireTime() + newRegion.getExpireTime();
                    oldRegion = regionMap.get(identity);
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldRegion) {
                        rdmsRegionDAO.deleteInNewTx(oldRegion.getId());
                    }
                } else {
                    if (null == oldRegion) {
                        // new Region code then create new record
                        newRegion.setCreateTime(new Date());
                        newRegion = rdmsRegionDAO.insertInNewTx(newRegion);
                        // update the map to show latest status
                        regionMap.put(newRegion.getIdentity(), newRegion);
                    } else {
                        // not new then update record. If same ignore.
                        newRegion.setCreateTime(oldRegion.getCreateTime());
                        newRegion.setUpdateTime(new Date());
                        newRegion.setId(oldRegion.getId());
                        if (!rdmsRegionDAO.entityEqual(newRegion, oldRegion)) {
                            rdmsRegionDAO.updateInNewTx(newRegion, oldRegion);
                            // update the map to show latest status
                            regionMap.replace(newRegion.getIdentity(), newRegion);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newRegion.getRegionCode());
                        }
                        oldRegions.remove(oldRegion);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain REGL record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsRegion region : oldRegions) {
                rdmsRegionDAO.deleteInNewTx(region.getId());
            }
        }
    }
}
