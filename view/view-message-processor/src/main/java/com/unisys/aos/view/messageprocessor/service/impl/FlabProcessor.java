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
import com.unisys.aos.view.messageprocessor.entity.FLAB;
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
 * flight landing aborted change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/19 11:23
 */
@Service("FLOP_FLAB")
@Slf4j
public class FlabProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public FlabProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the FLAB message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {
        String landingAbortIndicatorRegex = "([OG])";

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
                    log.error("There is no FFID tag in FLAB message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check flight landing aborted tag
                FLAB newFlab = record.getFlab();
                if (null == newFlab) {
                    log.info("The existing flight landing aborted indicator value is being unset. Message text [" + msg + "]");
                    if (null == flightDTO.getFlight().getLandingAbortIndicator() && null == flightDTO.getFlight().getLandingAbortReason()) {
                        return;
                    }
                    updateFlab(flightDTO, null, null);
                    return;
                }
                Character newLandingAbortIndicator = newFlab.getARES();
                String newLandingAbortReason = newFlab.getValue();
                if (null == newLandingAbortIndicator) {
                    log.error("ARES cannot be empty in non-empty FLAB message. Message text [" + msg + "]");
                    return;
                }
                if (!newLandingAbortIndicator.toString().matches(landingAbortIndicatorRegex)) {
                    log.error("Landing abort indicator in FLAB message is invalid, valid value is O (Overshot indicator) or G (Go Around indicator). Message text [" + msg + "]");
                    return;
                }

                // if same, no update is made.
                if (newLandingAbortIndicator.toString().equals(flightDTO.getFlight().getLandingAbortIndicator())) {
                    if (null == newLandingAbortReason) {
                        log.info("Reason for flight landing abort is empty. Message text [" + msg + "]");
                        if (null == flightDTO.getFlight().getLandingAbortReason()) {
                            log.info("FLAB is same so nothing changed. Landing abort indicator=[{}], landing abort reason=[{}]", newLandingAbortIndicator, newLandingAbortReason);
                        } else {
                            updateFlab(flightDTO, newLandingAbortIndicator.toString(), newLandingAbortReason);
                        }
                    } else {
                        if (newLandingAbortReason.equals(flightDTO.getFlight().getLandingAbortReason())) {
                            log.info("FLAB is same so nothing changed. Landing abort indicator=[{}], landing abort reason=[{}]", newLandingAbortIndicator, newLandingAbortReason);
                        } else {
                            updateFlab(flightDTO, newLandingAbortIndicator.toString(), newLandingAbortReason);
                        }
                    }
                } else {
                    updateFlab(flightDTO, newLandingAbortIndicator.toString(), newLandingAbortReason);
                }
            }
        } else {
            log.error("There is no FLOP section in FLAB message. Message text [" + msg + "]");
        }
    }

    /**
     * do actual update
     *
     * @param flightDTO             FlightDTO that needs to be updated.
     * @param landingAbortIndicator landing abort indicator, O (Overshot indicator) or G (Go Around indicator)
     * @param landingAbortReason    landing abort reason
     */
    private void updateFlab(FlightDTO flightDTO, String landingAbortIndicator, String landingAbortReason) {
        FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
        if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
            newFlightDTO.getFlight().setLandingAbortIndicator(landingAbortIndicator);
            newFlightDTO.getFlight().setLandingAbortReason(landingAbortReason);
            flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

            FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                    .addUpdateFields("landingAbortIndicator",landingAbortIndicator)
                    .addUpdateFields("landingAbortReason",landingAbortReason);
            publishToRedis(dto);
        } else {
            log.error("Failed to clone the old FlightDTO object.");
        }
    }
}
