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
 * flight handling agent change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/26 15:12
 */
@Service("FLOP_HNAG")
@Slf4j
public class HnagProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public HnagProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the HNAG message.
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
                    log.error("There is no FFID tag in HNAG message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }

                // check flight handling agent tag
                Integer newFieldAgent = record.getFHAG();
                Integer newPassengerAgent = record.getPHAG();
                Integer newMaintenanceAgent = record.getMHAG();

                Integer oldFieldAgent = flightDTO.getFlight().getFieldAgent();
                Integer oldPassengerAgent = flightDTO.getFlight().getPaxAgent();
                Integer oldMaintenanceAgent = flightDTO.getFlight().getMaintenanceAgent();

                FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
                if (null == newFlightDTO || null == newFlightDTO.getFlight()) {
                    log.error("Failed to clone the old FlightDTO object.");
                    return;
                }

                boolean isUpdated = false;

                if (null == newFieldAgent) {
                    log.info("The existing field handling agent value is being deleted. Message text [" + msg + "]");
                    if (null != oldFieldAgent) {
                        newFlightDTO.getFlight().setFieldAgent(null);
                        isUpdated = true;
                    }
                } else {
                    if (!newFieldAgent.equals(oldFieldAgent)) {
                        newFlightDTO.getFlight().setFieldAgent(newFieldAgent);
                        isUpdated = true;
                    }
                }

                if (null == newPassengerAgent) {
                    if (null != oldPassengerAgent) {
                        newFlightDTO.getFlight().setPaxAgent(null);
                        isUpdated = true;
                    }
                } else {
                    if (!newPassengerAgent.equals(oldPassengerAgent)) {
                        newFlightDTO.getFlight().setPaxAgent(newPassengerAgent);
                        isUpdated = true;
                    }
                }

                if (null == newMaintenanceAgent) {
                    if (null != oldMaintenanceAgent) {
                        newFlightDTO.getFlight().setMaintenanceAgent(null);
                        isUpdated = true;
                    }
                } else {
                    if (!newMaintenanceAgent.equals(oldMaintenanceAgent)) {
                        newFlightDTO.getFlight().setMaintenanceAgent(newMaintenanceAgent);
                        isUpdated = true;
                    }
                }

                if (isUpdated) {
                    // do update
                    flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

                    FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                            .addUpdateFields("fieldAgent",newFieldAgent)
                            .addUpdateFields("maintenanceAgent",newMaintenanceAgent)
                            .addUpdateFields("passengerAgent",newPassengerAgent);
                    publishToRedis(dto);
                } else {
                    // if same, no update is made.
                    log.info("HNAG, PHAG and MHAG are same so nothing changed. Organization id of field handling agent=[{}], Organization id of passenger handling agent=[{}], Organization id of maintenance handling agent=[{}].", newFieldAgent, newPassengerAgent, newMaintenanceAgent);
                }
            }
        } else {
            log.error("There is no FLOP section in HNAG message. Message text [" + msg + "]");
        }
    }
}
