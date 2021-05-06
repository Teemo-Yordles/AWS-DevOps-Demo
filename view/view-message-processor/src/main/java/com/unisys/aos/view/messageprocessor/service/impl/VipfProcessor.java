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
import com.unisys.aos.view.common.util.ObjectUtil;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.entity.VIPF;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsVip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * VIP information change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/12/17 11:31
 */
@Service("FLOP_VIPF")
@Slf4j
public class VipfProcessor implements Processor {
    private final FlightDAO flightDAO;

    @Autowired
    public VipfProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the VIPF message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {
        // Parse the XML to object
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        FLOP parsedObj = objectMapper.readValue(msg, FLOP.class);
        List<FLOP.FlopData> flops = parsedObj.getFlops();

        // loop through all XML records
        // if flight already exists then UPDATE otherwise return error
        if (null != flops && !flops.isEmpty()) {
            for (FLOP.FlopData record : flops) {
                if (null == record.getFLID()) {
                    log.error("There is no FFID tag in VIPF message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check vip information
                List<FmsVip> fmsVipList = flightDTO.getVips();
                ConcurrentMap<String, FmsVip> fmsVipMap = new ConcurrentHashMap<>();
                if(null != fmsVipList && !fmsVipList.isEmpty()){
                    fmsVipMap = fmsVipList.stream()
                            .collect(Collectors.toConcurrentMap(FmsVip::getVipCode, (p) -> p));
                }
                List<VIPF> vipfs = record.getVipfs();
                boolean hasChanged = false;
                if(null != vipfs && !vipfs.isEmpty()){
                    for (VIPF vipf: vipfs) {
                        String vipCode = vipf.getVPCD();
                        Short entourageSize = vipf.getVFES();
                        if(null != vipCode && !vipCode.isEmpty()){
                            FmsVip fmsVip = fmsVipMap.get(vipCode);
                            if(null != fmsVip){
                                Short oldEntourageSize = fmsVip.getEntourageSize();
                                // Already exists, update it if it is not equal.
                                if(null != oldEntourageSize){
                                    if(!entourageSize.equals(oldEntourageSize)){
                                        FmsVip newVip = new FmsVip();
                                        newVip.setId(fmsVip.getId());
                                        newVip.setFlightId(fmsVip.getFlightId());
                                        newVip.setVipCode(fmsVip.getVipCode());
                                        newVip.setEntourageSize(entourageSize);
                                        newVip.setServiceCode(fmsVip.getServiceCode());
                                        newVip.setServiceQuantity(fmsVip.getServiceQuantity());
                                        newVip.setServiceStartTime(fmsVip.getServiceStartTime());
                                        newVip.setServiceEndTime(fmsVip.getServiceEndTime());
                                        newVip.setCreateTime(fmsVip.getCreateTime());
                                        newVip.setUpdateTime(new Date());
                                        fmsVipMap.put(vipCode, newVip);
                                        hasChanged = true;
                                    }
                                }
                            }else{
                                // Insert if it does not exist.
                                FmsVip newVip = new FmsVip();
                                newVip.setFlightId(record.getFLID());
                                newVip.setVipCode(vipCode);
                                newVip.setEntourageSize(entourageSize);
                                newVip.setCreateTime(new Date());
                                fmsVipMap.put(vipCode, newVip);
                                hasChanged = true;
                            }
                        }
                    }
                }
                if(hasChanged){
                    // do actual update
                    List<FmsVip> newVipList = new ArrayList<>(fmsVipMap.values());
                    FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
                    if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
                        newFlightDTO.setVips(newVipList);
                        flightDAO.updateInNewTx(newFlightDTO, flightDTO);
                    } else {
                        log.error("Failed to clone the old FlightDTO object.");
                    }
                }
            }
        } else {
            log.error("There is no FLOP section in VIPF message. Message text [" + msg + "]");
        }
    }
}
