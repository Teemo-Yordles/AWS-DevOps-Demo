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
import com.unisys.aos.view.messageprocessor.entity.BLST;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsCarouselDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsCarousel;
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
 * Carousel code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("BLST")
@Slf4j
public class BlstProcessor implements Processor {

    private final RdmsCarouselDAO rdmsCarouselDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public BlstProcessor(RdmsCarouselDAO rdmsCarouselDAO, ReferenceProperties referenceProperties) {
        this.rdmsCarouselDAO = rdmsCarouselDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        BLST parsedObj = objectMapper.readValue(msg, BLST.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<BLST.CarouselData> msgs = parsedObj.getCarousels();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsCarousel> currentRecords = rdmsCarouselDAO.findAll();
        ConcurrentMap<String, RdmsCarousel> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsCarousel::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (BLST.CarouselData record : msgs) {
            log.debug("processing carousel record code [{}]", record.getBCOD());

            // create empty domain carousel entity and map properties
            RdmsCarousel entity = new RdmsCarousel();
            entity.setCarouselCode(record.getBCOD());
            entity.setDescription(record.getBTNM());
            entity.setLocalDescription(record.getBNMC());
            entity.setCarouselCategory(record.getBCAT());
            entity.setTerminalCode(record.getBTML());

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsCarousel currentRecord = null;
            String bcod = record.getBCOD();
            if (null != bcod) {
                String identity = bcod + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }

            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsCarouselDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsCarouselDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsCarouselDAO.entityEqual(entity, currentRecord)) {
                        rdmsCarouselDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getCarouselCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsCarousel record : currentRecords) {
                rdmsCarouselDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
