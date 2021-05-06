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
 * flight finals time change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/17 10:10
 */
@Service("FLOP_FINT")
@Slf4j
public class FintProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public FintProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the FINT message.
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
                    log.error("There is no FFID tag in FINT message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check flight finals time tag
                String finalTimeStr = record.getFINT();
                if (null == finalTimeStr || finalTimeStr.isEmpty()) {
                    log.info("The existing finals time value is being deleted. Message text [" + msg + "]");
                    if (null == flightDTO.getFlight().getFinalTime()) {
                        return;
                    }
                    updateFint(flightDTO, null);
                    return;
                }
                try {
                    Date finalTime = ThreadLocalDateUtil.parseXmlDate(finalTimeStr);
                    if (null == finalTime) {
                        log.error("The format of FINT is wrong, Message text [" + msg + "], FINT text [" + finalTimeStr + "]");
                        return;
                    }
                    // if same, no update is made.
                    Date oldFinalTime = flightDTO.getFlight().getFinalTime();
                    if (null != oldFinalTime && finalTime.compareTo(oldFinalTime) == 0) {
                        log.info("FINT is same so nothing changed. Final time=[{}]", finalTime);
                    } else {
                        // do actual update
                        updateFint(flightDTO, finalTime);
                    }
                } catch (ParseException e) {
                    log.error("The format of FINT is wrong, Message text [" + msg + "], FINT text [" + finalTimeStr + "]");
                    return;
                }
            }
        } else {
            log.error("There is no FLOP section in FINT message. Message text [" + msg + "]");
        }
    }

    /**
     * do actual update
     *
     * @param flightDTO FlightDTO that needs to be updated.
     * @param finalTime finals time.
     */
    private void updateFint(FlightDTO flightDTO, Date finalTime) {
        FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
        if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
            newFlightDTO.getFlight().setFinalTime(finalTime);
            flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());

            FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                    .addUpdateFields("finalTim",finalTime);
            publishToRedis(dto);
        } else {
            log.error("Failed to clone the old FlightDTO object.");
        }
    }
}
