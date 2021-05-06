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
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * flight engine start request change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/17 10:10
 */
@Service("FLOP_EGSR")
@Slf4j
public class EgsrProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public EgsrProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the EGSR message.
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
                    log.error("There is no FFID tag in EGSR message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check flight engine start request tag
                String engineStartRequestStr = record.getEGSR();
                if (null == engineStartRequestStr || engineStartRequestStr.isEmpty()) {
                    log.info("The existing engine start request time value is being deleted. Message text [" + msg + "]");
                    if (null == flightDTO.getFlight().getEngineStartRequest()) {
                        return;
                    }
                    updateEgsr(flightDTO, null);
                    return;
                }
                try {
                    Date engineStartRequest = ThreadLocalDateUtil.parseXmlDate(engineStartRequestStr);
                    if (null == engineStartRequest) {
                        log.error("The format of EGSR is wrong, Message text [" + msg + "], EGSR text [" + engineStartRequestStr + "]");
                        return;
                    }
                    // if same, no update is made.
                    Date oldEngineStartRequest = flightDTO.getFlight().getEngineStartRequest();
                    if (null != oldEngineStartRequest && engineStartRequest.compareTo(oldEngineStartRequest) == 0) {
                        log.info("EGSR is same so nothing changed. Engine start request for departures = [{}]", engineStartRequest);
                    } else {
                        // do actual update
                        updateEgsr(flightDTO, engineStartRequest);
                    }
                } catch (ParseException e) {
                    log.error("The format of EGSR is wrong, Message text [" + msg + "], EGSR text [" + engineStartRequestStr + "]");
                    return;
                }
            }
        } else {
            log.error("There is no FLOP section in EGSR message. Message text [" + msg + "]");
        }
    }

    /**
     * do actual update
     *
     * @param flightDTO          FlightDTO that needs to be updated.
     * @param engineStartRequest engine start request time.
     */
    private void updateEgsr(FlightDTO flightDTO, Date engineStartRequest) {
        FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
        if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
            newFlightDTO.getFlight().setEngineStartRequest(engineStartRequest);
            flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

            FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                    .addUpdateFields("engineStartRequest",engineStartRequest);
            publishToRedis(dto);
        } else {
            log.error("Failed to clone the old FlightDTO object.");
        }
    }
}
