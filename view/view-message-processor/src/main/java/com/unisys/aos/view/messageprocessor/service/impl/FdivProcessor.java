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
import com.unisys.aos.view.messageprocessor.entity.FDIV;
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
 * flight diverted change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/19 11:23
 */
@Service("FLOP_FDIV")
@Slf4j
public class FdivProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public FdivProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the FDIV message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {
        String divDirectionValueRegex = "(TO|FROM)";

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
                    log.error("There is no FFID tag in FDIV message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check flight divert tag
                FDIV newFdiv = record.getFdiv();
                if (null == newFdiv) {
                    log.info("The content of FDIV tag is null. Message text [" + msg + "]");
                    if (null == flightDTO.getFlight().getDivAirport() && null == flightDTO.getFlight().getDivDirection() && null == flightDTO.getFlight().getDivReason()) {
                        return;
                    }
                    updateFdiv(flightDTO, null, null, null);
                    return;
                }
                String newDivAirport = newFdiv.getDDES();
                String newDivDivDirection = newFdiv.getDDIR();
                String newDivReason = newFdiv.getValue();
                if (null == newDivAirport || newDivAirport.isEmpty()) {
                    log.info("The existing flight diverted indicator value is being unset. Message text [" + msg + "]");
                    if (null != flightDTO.getFlight().getDivAirport() || null != flightDTO.getFlight().getDivReason() || null != flightDTO.getFlight().getDivDirection()) {
                        updateFdiv(flightDTO, null, null, null);
                    }
                    return;
                }
                if (null == newDivDivDirection || newDivDivDirection.isEmpty()) {
                    log.error("Divert direction cannot be empty in non-empty FDIV message. Message text [" + msg + "]");
                    return;
                }
                if (!newDivDivDirection.matches(divDirectionValueRegex)) {
                    log.error("Divert direction value in FDIV message is invalid, valid value is TO or FROM. Message text [" + msg + "]");
                    return;
                }

                // if same, no update is made.
                if (newDivAirport.equals(flightDTO.getFlight().getDivAirport()) && newDivDivDirection.equals(flightDTO.getFlight().getDivDirection())) {
                    if (null == newDivReason) {
                        log.info("Reason for flight diverted is empty. Message text [" + msg + "]");
                        if (null == flightDTO.getFlight().getDivReason()) {
                            log.info("FDIV is same so nothing changed. Divert airport=[{}], divert direction=[{}], divert reason is empty.", newDivAirport, newDivDivDirection);
                            return;
                        } else {
                            updateFdiv(flightDTO, newDivAirport, newDivDivDirection, newDivReason);
                        }
                    } else {
                        if (newDivReason.equals(flightDTO.getFlight().getDivReason())) {
                            log.info("FDIV is same so nothing changed. Divert airport=[{}], divert direction=[{}], divert reason=[{}].", newDivAirport, newDivDivDirection, newDivReason);
                        } else {
                            updateFdiv(flightDTO, newDivAirport, newDivDivDirection, newDivReason);
                        }
                    }
                } else {
                    updateFdiv(flightDTO, newDivAirport, newDivDivDirection, newDivReason);
                }
            }
        } else {
            log.error("There is no FLOP section in FDIV message. Message text [" + msg + "]");
        }
    }

    /**
     * do actual update
     *
     * @param flightDTO    FlightDTO that needs to be updated.
     * @param divAirport   divert airport, IATA code
     * @param divDirection divert direction, TO or FROM
     * @param divReason    divert reason
     */
    private void updateFdiv(FlightDTO flightDTO, String divAirport, String divDirection, String divReason) {
        FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
        if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
            newFlightDTO.getFlight().setDivAirport(divAirport);
            newFlightDTO.getFlight().setDivDirection(divDirection);
            newFlightDTO.getFlight().setDivReason(divReason);
            flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

            FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                    .addUpdateFields("divAirport",divAirport)
                    .addUpdateFields("divDirection",divDirection)
                    .addUpdateFields("divReason",divReason);
            publishToRedis(dto);
        } else {
            log.error("Failed to clone the old FlightDTO object.");
        }
    }
}
