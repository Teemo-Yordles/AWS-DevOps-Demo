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
import com.unisys.aos.view.viewentity.entity.flight.FmsFlight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * flight turnaround change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/18 10:31
 */
@Service("FLOP_TAOP")
@Slf4j
public class TaopProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public TaopProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the TAOP message.
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
                    log.error("There is no FFID tag in TAOP message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check flight turnaround
                String turnaroundOperator = record.getTAOP();
                String turnaroundFlightNumber = record.getTAFL();
                Long turnaroundFlightId = record.getTAID();
                String oldTurnaroundOperator = flightDTO.getFlight().getLinkedAirlineCode();
                String oldTurnaroundFlightNumber = flightDTO.getFlight().getLinkedFlightNumber();
                Long oldTurnaroundFlightId = flightDTO.getFlight().getLinkedAodbId();

                if (null == turnaroundOperator || turnaroundOperator.isEmpty() || null == turnaroundFlightNumber || turnaroundFlightNumber.isEmpty() || null == turnaroundFlightId) {
                    log.info("The existing turnaround link for an arrival is broken and the arrival is no more linked to any departure. Message text [" + msg + "]");
                    FmsFlight flight = flightDTO.getFlight();
                    if (null == flight.getLinkedAirlineCode() && null == flight.getLinkedFlightNumber() && null == flight.getLinkedAodbId()) {
                        return;
                    }
                    updateTaop(flightDTO, null, null, null);
                    return;
                }

                // if same, no update is made.
                if (turnaroundOperator.equals(oldTurnaroundOperator) && turnaroundFlightNumber.equals(oldTurnaroundFlightNumber) && turnaroundFlightId.equals(oldTurnaroundFlightId)) {
                    log.info("TAOP is same so nothing changed. Turnaround operator=[{}], turnaround flight number=[{}], turnaround flight ID=[{}]", turnaroundOperator, turnaroundFlightNumber, turnaroundFlightId);
                } else {
                    // do actual update
                    updateTaop(flightDTO, turnaroundOperator, turnaroundFlightNumber, turnaroundFlightId);
                }
            }
        } else {
            log.error("There is no FLOP section in TAOP message. Message text [" + msg + "]");
        }
    }

    /**
     * do actual update
     *
     * @param flightDTO              FlightDTO that needs to be updated.
     * @param turnaroundOperator     turnaround operator, IATA code.
     * @param turnaroundFlightNumber turnaround flight number.
     * @param turnaroundFlightId     turnaround flight id in AODB.
     */
    private void updateTaop(FlightDTO flightDTO, String turnaroundOperator, String turnaroundFlightNumber, Long turnaroundFlightId) {
        FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
        if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
            newFlightDTO.getFlight().setLinkedAirlineCode(turnaroundOperator);
            newFlightDTO.getFlight().setLinkedFlightNumber(turnaroundFlightNumber);
            newFlightDTO.getFlight().setLinkedAodbId(turnaroundFlightId);
            flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

            FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                    .addUpdateFields("turnaroundOperator",turnaroundOperator)
                    .addUpdateFields("turnaroundFlightNumber",turnaroundFlightNumber)
                    .addUpdateFields("turnaroundFlightId",turnaroundFlightId);
            publishToRedis(dto);
        } else {
            log.error("Failed to clone the old FlightDTO object.");
        }
    }
}
