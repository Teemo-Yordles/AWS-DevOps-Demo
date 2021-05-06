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
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * flight VIP passengers change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/26 15:12
 */
@Service("FLOP_VIPP")
@Slf4j
public class VippProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public VippProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the VIPP message.
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
                    log.error("There is no FFID tag in VIPP message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }

                // check flight VIP passenger tag
                Short newVipCount = record.getVIPP();
                Byte newVipFlag = record.getVIPR();

                Short oldVipCount = flightDTO.getFlight().getVipCount();
                Byte oldVipFlag = flightDTO.getFlight().getVipFlag();

                FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
                if (null == newFlightDTO || null == newFlightDTO.getFlight()) {
                    log.error("Failed to clone the old FlightDTO object.");
                    return;
                }

                boolean isUpdated = false;

                if (null == newVipCount) {
                    log.info("The existing VIP passenger count value is being deleted. Message text [" + msg + "]");
                    if (null != oldVipCount) {
                        newFlightDTO.getFlight().setVipCount(null);
                        isUpdated = true;
                    }
                } else {
                    if (!newVipCount.equals(oldVipCount)) {
                        newFlightDTO.getFlight().setVipCount(newVipCount);
                        isUpdated = true;
                    }
                }

                if (null == newVipFlag) {
                    log.info("The existing VIP rating value is being deleted. Message text [" + msg + "]");
                    if (null != oldVipFlag) {
                        newFlightDTO.getFlight().setVipFlag(null);
                        isUpdated = true;
                    }
                } else {
                    if (!newVipFlag.equals(oldVipFlag)) {
                        newFlightDTO.getFlight().setVipFlag(newVipFlag);
                        isUpdated = true;
                    }
                }

                if (isUpdated) {
                    // do update
                    flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

                    FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                            .addUpdateFields("vipCount", newVipCount)
                            .addUpdateFields("vipFlag",newVipFlag);
                    publishToRedis(dto);
                } else {
                    // if same, no update is made.
                    log.info("VIPP and VIPR are same so nothing changed. VIP count=[{}], VIP flag=[{}].", newVipCount, newVipFlag);
                }
            }
        } else {
            log.error("There is no FLOP section in VIPP message. Message text [" + msg + "]");
        }
    }
}
