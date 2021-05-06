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
import com.unisys.aos.view.messageprocessor.entity.FRET;
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
 * flight returned message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/19 11:23
 */
@Service("FLOP_FRET")
@Slf4j
public class FretProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public FretProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the FRET message.
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
                    log.error("There is no FFID tag in FRET message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check flight returned tag
                FRET newFret = record.getFret();
                if (null == newFret) {
                    log.info("the existing flight returned indicator value is being unset. Message text [" + msg + "]");
                    if (null != flightDTO.getFlight().getRetRsn() || null != flightDTO.getFlight().getRetTyp()) {
                        updateFret(flightDTO, null, null);
                    }
                    return;
                }
                Character newReturnType = newFret.getREID();
                String newReturnReason = newFret.getValue();
                if (null == newReturnType) {
                    log.error("REID cannot be empty in non-empty FRET message. Message text [" + msg + "]");
                    return;
                }
                String returnTypeValueRegex = "([AG])";
                if (!newReturnType.toString().matches(returnTypeValueRegex)) {
                    log.error("Flight return type in FDIV message is invalid, valid value is A (Air return) or G (Ground return). Message text [" + msg + "]");
                    return;
                }

                // if same, no update is made.
                if (newReturnType.toString().equals(flightDTO.getFlight().getRetTyp())) {
                    if (null == newReturnReason || newReturnReason.isEmpty()) {
                        log.info("Reason for flight return is empty. Message text [" + msg + "]");
                        if (null == flightDTO.getFlight().getRetRsn()) {
                            log.info("FRET is same so nothing changed. Return type=[{}], return reason=[{}]", newReturnType.toString(), newReturnReason);
                        } else {
                            updateFret(flightDTO, newReturnReason, newReturnType.toString());
                        }
                    } else {
                        if (newReturnReason.equals(flightDTO.getFlight().getRetRsn())) {
                            log.info("FRET is same so nothing changed. Return type=[{}], return reason=[{}]", newReturnType.toString(), newReturnReason);
                        } else {
                            updateFret(flightDTO, newReturnReason, newReturnType.toString());
                        }
                    }
                } else {
                    updateFret(flightDTO, newReturnReason, newReturnType.toString());
                }
            }
        } else {
            log.error("There is no FLOP section in FRET message. Message text [" + msg + "]");
        }
    }

    /**
     * do actual update
     *
     * @param flightDTO    FlightDTO that needs to be updated.
     * @param returnReason flight return reason
     * @param returnType   flight return type, G (Ground return) or A (Air return)
     */
    private void updateFret(FlightDTO flightDTO, String returnReason, String returnType) {
        FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
        if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
            newFlightDTO.getFlight().setRetRsn(returnReason);
            newFlightDTO.getFlight().setRetTyp(returnType);
            flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

            FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                    .addUpdateFields("returnReason",returnReason)
                    .addUpdateFields("returnType",returnType);
            publishToRedis(dto);
        } else {
            log.error("Failed to clone the old FlightDTO object.");
        }
    }
}
