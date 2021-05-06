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
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * flight aircraft type change message processor
 *
 * @author Liu Jie
 * @since 2020/11/13 17:00
 */
@Service("FLOP_ACFT")
@Slf4j
public class AcftProcessor extends AbsProcessor implements Processor {
    private final FlightDAO flightDAO;

    @Autowired
    public AcftProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the ACFT message.
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
                    log.error("There is no FFID tag in ACFT message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check aircraft tag
                String aircraftTypeCode = record.getACFT();
                if (null == aircraftTypeCode) {
                    log.error("ACFT cannot be empty in ACFT message. Message text [" + msg + "]");
                    return;
                }
                // if same, no update is made.
                if (aircraftTypeCode.equals(flightDTO.getFlight().getAircraftType())) {
                    log.info("ACFT is same so nothing changed. AircraftTypeCode=[{}]", aircraftTypeCode);
                    return;
                }
                // do actual update
                FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
                if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
                    newFlightDTO.getFlight().setAircraftType(aircraftTypeCode);
                    flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());
                    FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(record.getFLID())
                            .addUpdateFields("aircraftTypeCode",aircraftTypeCode);
                    publishToRedis(dto);
                } else {
                    log.error("Failed to clone the old FlightDTO object.");
                }
            }
        } else {
            log.error("There is no FLOP section in ACFT message. Message text [" + msg + "]");
        }
    }
}
