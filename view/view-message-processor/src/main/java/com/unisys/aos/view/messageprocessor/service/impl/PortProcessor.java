/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unisys.aos.view.common.util.ObjectUtil;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * flight externalStatus type change message processor
 *
 * @author jianglushuang
 * @since 2020/11/18 17:00
 */
@Service("FLOP_PORT")
@Slf4j
public class PortProcessor extends AbsProcessor implements Processor {
    private static final Character CLOSE_PORT_TAG = 'C';
    private static final Character OPEN_PORT_TAG = 'O';
    private static final String CLOSE_PORT_FIELD_NAME = "portCloseTime";
    private static final String OPEN_PORT_FIELD_NAME = "portOpenTime";
    private final FlightDAO flightDAO;

    @Autowired
    public PortProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the PORT message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {

        List<FLOP.FlopData> flops = parserXml(msg);
        if (null == flops || flops.isEmpty()) {
            log.error("There is no FLOP section in PORT message. Message text [" + msg + "]");
            return;
        }
        // loop through all XML records
        // if flight already exists then UPDATE otherwise return error
        for (FLOP.FlopData record : flops) {
            Long flightId = record.getFLID();
            if (null == flightId) {
                log.error("There is no FFID tag in PORT message. Message text [" + msg + "]");
                return;
            }
            log.debug("processing FLOP record id [{}]", flightId);

            // find flight in redis or DB
            FlightDTO flightDTO = flightDAO.findByAodbId(flightId);
            if (null == flightDTO) {
                log.error("Cannot find flight ID=[" + flightId + "] FFID=[" + record.getFFID() + "]");
                return;
            }
            // check PORT tag
            String portTime = record.getPORT().getPOTM();
            Character portIndicator = record.getPORT().getPOID();
            Date portTimeD = null;

            if (null == portIndicator) {
                log.error("There is no POID tag in PORT message. Message text [" + msg + "]");
                return;
            }

            // do deep clone
            FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
            if (null == newFlightDTO || null == newFlightDTO.getFlight()) {
                log.error("Failed to clone the old FlightDTO object.");
                return;
            }

            // if potm is null , empty poid
            if (null == portTime || portTime.isEmpty()) {
                if (CLOSE_PORT_TAG.equals(portIndicator)) {
                    if (null == flightDTO.getFlight().getPortCloseTime()) {
                        log.info("PORT is same so nothing changed. Port close time = null");
                        return;
                    } else {
                        log.info("The existing port close time value is being deleted.");
                        newFlightDTO.getFlight().setPortCloseTime(null);
                        publishToRedis(flightId, CLOSE_PORT_FIELD_NAME, null);
                    }
                }
                if (OPEN_PORT_TAG.equals(portIndicator)) {
                    if (null == flightDTO.getFlight().getPortOpenTime()) {
                        log.info("PORT is same so nothing changed. Port open time = null");
                        return;
                    } else {
                        log.info("The existing port open time value is being deleted.");
                        newFlightDTO.getFlight().setPortOpenTime(null);
                        publishToRedis(flightId, OPEN_PORT_FIELD_NAME, null);
                    }
                }
            } else {
                try {
                    portTimeD = ThreadLocalDateUtil.parseXmlDate(portTime);
                } catch (ParseException e) {
                    log.error("Parser POTM xml date error.Message text [" + msg + "]");
                    e.printStackTrace();
                    return;
                }
                if (CLOSE_PORT_TAG.equals(portIndicator)) {
                    // if same, no update is made.
                    if (portTimeD.equals(flightDTO.getFlight().getPortCloseTime())) {
                        log.info("PORT is same so nothing changed. Port close time=[{}]", portTime);
                        return;
                    } else {
                        newFlightDTO.getFlight().setPortCloseTime(portTimeD);
                        publishToRedis(flightId, CLOSE_PORT_FIELD_NAME, portTimeD);
                    }
                }
                if (OPEN_PORT_TAG.equals(portIndicator)) {
                    // if same, no update is made.
                    if (portTimeD.equals(flightDTO.getFlight().getPortOpenTime())) {
                        log.info("PORT is same so nothing changed. Port open time=[{}]", portTime);
                        return;
                    } else {
                        newFlightDTO.getFlight().setPortOpenTime(portTimeD);
                        publishToRedis(flightId, OPEN_PORT_FIELD_NAME, portTimeD);
                    }
                }
            }

            // do actual update
            flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

        }
    }

    /***
     * Publish to redis.
     * @param flightId Flight id.
     * @param fieldName Modify field name.
     * @param portTime Port time.
     */
    private void publishToRedis(Long flightId, String fieldName, Date portTime) {
        FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightId)
                .addUpdateFields(fieldName, portTime);
        publishToRedis(dto);
    }
}
