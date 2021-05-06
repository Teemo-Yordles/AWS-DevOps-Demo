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
import com.unisys.aos.view.messageprocessor.entity.VIPS;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsVipServiceDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsVipService;
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
 * VipService code message processor
 *
 * @author jianglushuang
 * @since 2020/9/23 3:31 下午
 */
@Service("VIPS")
@Slf4j
public class VipsProcessor implements Processor {

    private final RdmsVipServiceDAO rdmsVipServiceDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public VipsProcessor(RdmsVipServiceDAO rdmsVipServiceDAO, ReferenceProperties referenceProperties) {
        this.rdmsVipServiceDAO = rdmsVipServiceDAO;
        this.referenceProperties = referenceProperties;
    }

    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        VIPS parsedObj = objectMapper.readValue(msg, VIPS.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<VIPS.VipServiceData> msgs = parsedObj.getVipServices();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current entities
        Collection<RdmsVipService> currentRecords = rdmsVipServiceDAO.findAll();
        ConcurrentMap<String, RdmsVipService> recordMap = currentRecords.stream()
                .collect(Collectors.toConcurrentMap(RdmsVipService::getIdentity, (p) -> p));

        // loop through all XML records
        // if IATACode already exists then UPDATE otherwise INSERT
        for (VIPS.VipServiceData record : msgs) {
            log.debug("processing vip service record code [{}]", record.getVSCD());

            // create empty domain vip service entity and map properties
            RdmsVipService entity = new RdmsVipService();
            entity.setVipServiceCode(record.getVSCD());
            entity.setDescription(record.getVSDS());
            entity.setLocalDescription(record.getVSDC());
            entity.setVipServiceQtyortimCode(record.getVSQC());
            entity.setVipServiceUnitmeasCodeSingle(record.getVSUC());

            entity.setInspireTime(defaultInspireTime);
            entity.setExpireTime(defaultExpireTime);

            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            RdmsVipService currentRecord = null;
            String vscd = record.getVSCD();
            if (null != vscd) {
                String identity = vscd + entity.getInspireTime() + entity.getExpireTime();
                currentRecord = recordMap.get(identity);
            }

            if (Processor.DEL.equalsIgnoreCase(subType)) {
                // if not found then nothing happens
                if (null != currentRecord) {
                    rdmsVipServiceDAO.deleteInNewTx(currentRecord.getId());
                }
            } else {
                if (null == currentRecord) {
                    // new IATA code then create new record
                    entity = rdmsVipServiceDAO.insertInNewTx(entity);
                    // update the map to show latest status
                    recordMap.put(entity.getIdentity(), entity);
                } else {
                    // not new then update record. If same ignore.
                    entity.setCreateTime(currentRecord.getCreateTime());
                    entity.setUpdateTime(new Date());
                    entity.setId(currentRecord.getId());
                    if (!rdmsVipServiceDAO.entityEqual(entity, currentRecord)) {
                        rdmsVipServiceDAO.updateInNewTx(entity, currentRecord);
                        // update the map to show latest status
                        recordMap.replace(entity.getIdentity(), entity);
                    } else {
                        log.info("Identical entity found for code [{}] so skipped processing it", entity.getVipServiceCode());
                    }
                    currentRecords.remove(currentRecord);
                }
            }
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsVipService record : currentRecords) {
                rdmsVipServiceDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
