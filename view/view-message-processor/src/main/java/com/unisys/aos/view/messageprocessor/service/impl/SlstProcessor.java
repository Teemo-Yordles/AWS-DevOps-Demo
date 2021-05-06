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
import com.unisys.aos.view.messageprocessor.entity.SLST;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.messageprocessor.util.ProcessorUtil;
import com.unisys.aos.view.viewentity.config.ReferenceProperties;
import com.unisys.aos.view.viewentity.dao.RdmsStandDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsStand;
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
 * Stand message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/10/21 23:50
 */
@Service("SLST")
@Slf4j
public class SlstProcessor implements Processor {
    private final RdmsStandDAO rdmsStandDAO;
    private final ReferenceProperties referenceProperties;

    @Autowired
    public SlstProcessor(RdmsStandDAO rdmsStandDAO, ReferenceProperties referenceProperties) {
        this.rdmsStandDAO = rdmsStandDAO;
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
        SLST parsedObj = objectMapper.readValue(msg, SLST.class);
        String subType = parsedObj.getMeta().getSTYP();
        List<SLST.StandData> newStands = parsedObj.getSlsts();

        Long defaultInspireTime = referenceProperties.getDefaultInspireTime();
        Long defaultExpireTime = referenceProperties.getDefaultExpireTime();

        // query DB for current stand entities
        Collection<RdmsStand> oldStands = rdmsStandDAO.findAll();
        ConcurrentMap<String, RdmsStand> standMap = oldStands.stream()
                .collect(Collectors.toConcurrentMap(RdmsStand::getIdentity, (p) -> p));

        // loop through all XML records
        // if Stand Code already exists then UPDATE otherwise INSERT
        if (null != newStands) {
            for (SLST.StandData record : newStands) {
                log.debug("processing Stand record code [{}]", record.getSCOD());

                // create empty domain stand entity and map properties
                RdmsStand newStand = new RdmsStand();
                newStand.setStandCode(record.getSCOD());
                newStand.setDescription(record.getSTNM());
                newStand.setLocalDescription(record.getSNMC());
                newStand.setAllocateBigAircraft(record.getSATC());
                newStand.setStandGroupCode(record.getSTGP());
                newStand.setWidth(record.getSWID());
                newStand.setLength(record.getSHGT());
                newStand.setTerminalCode(record.getSTML());
                newStand.setAttacheMaxAirbridge(record.getMABS());
                newStand.setInternationalGate(record.getDIGT());
                newStand.setDomesticGate(record.getDDGT());
                newStand.setStandType(record.getREMT());
                newStand.setFixedElectric(ProcessorUtil.stringToBoolean(record.getFEPU()));

                newStand.setInspireTime(defaultInspireTime);
                newStand.setExpireTime(defaultExpireTime);

                RdmsStand oldStand = null;
                String standCode = record.getSCOD();
                if (null != standCode) {
                    String identity = standCode + newStand.getInspireTime() + newStand.getExpireTime();
                    oldStand = standMap.get(identity);
                }

                if (Processor.DEL.equalsIgnoreCase(subType)) {
                    // if not found then nothing happens
                    if (null != oldStand) {
                        rdmsStandDAO.deleteInNewTx(oldStand.getId());
                    }
                } else {
                    if (null == oldStand) {
                        // new stand code then create new record
                        newStand.setCreateTime(new Date());
                        newStand = rdmsStandDAO.insertInNewTx(newStand);
                        // update the map to show latest status - stand
                        standMap.put(newStand.getIdentity(), newStand);
                    } else {
                        // not new then update record. If same ignore.
                        newStand.setCreateTime(oldStand.getCreateTime());
                        newStand.setUpdateTime(new Date());
                        newStand.setId(oldStand.getId());
                        if (!rdmsStandDAO.entityEqual(newStand, oldStand)) {
                            rdmsStandDAO.updateInNewTx(newStand, oldStand);
                            // update the map to show latest status
                            standMap.replace(oldStand.getIdentity(), newStand);
                        } else {
                            log.info("Identical entity found for code [{}] so skipped processing it", newStand.getStandCode());
                        }
                        oldStands.remove(oldStand);
                    }
                }
            }
        } else {
            log.warn("Parsing failed, failed to obtain STSL record content from XML.");
        }

        // Delete the extra record not in XML but in DB
        if (Processor.DNLD.equalsIgnoreCase(subType)
                || Processor.RESP.equalsIgnoreCase(subType)) {
            for (RdmsStand record : oldStands) {
                rdmsStandDAO.deleteInNewTx(record.getId());
            }
        }
    }

}
