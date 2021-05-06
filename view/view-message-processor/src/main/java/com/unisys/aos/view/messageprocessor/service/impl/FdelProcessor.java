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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * flight delete message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/19 11:23
 */
@Service("FLOP_FDEL")
@Slf4j
public class FdelProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public FdelProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the FDEL message.
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
                Long flightId = record.getFLID();
                if (null == flightId) {
                    log.error("There is no FFID tag in FDEL message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", flightId);

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + flightId + "] FFID=[" + record.getFFID() + "]");
                    return;
                }
                // check flight delete tag
                String deleteEvent = record.getFDEL();
                if (null != deleteEvent && !deleteEvent.isEmpty()) {
                    log.error("FDEL must be empty in FDEL message. Message text [" + msg + "]");
                    return;
                }
                // delete flight
                flightDAO.deleteInNewTx(flightId);

                // When deleting a flight, it is necessary to maintain the "codeshare" field of master flight if this is a code share flight.
                FmsFlight flight = flightDTO.getFlight();
                if(null != flight){
                    Long masterAodbId = flight.getMasterAodbId();
                    if(null != masterAodbId){
                        FlightDTO masterFlightDto = flightDAO.findByAodbId(masterAodbId);
                        if(null != masterFlightDto){
                            List<String> codeShareFlightNumberList = flightDAO.getCodeShareFlightNumberList(masterFlightDto.getFlight());
                            String flightNumberString = null;
                            if(null != codeShareFlightNumberList && !codeShareFlightNumberList.isEmpty()){
                                flightNumberString = String.join(", ", codeShareFlightNumberList);
                            }
                            FlightDTO newMasterFlightDTO = (FlightDTO) ObjectUtil.deepClone(masterFlightDto);
                            if (null != newMasterFlightDTO && null != newMasterFlightDTO.getFlight()) {
                                newMasterFlightDTO.setCodeShareFlightList(flightNumberString);
                                flightDAO.updateInNewTx(newMasterFlightDTO, masterFlightDto);
                                FlightNotificationDTO flightNotificationDTO = FlightNotificationDTO.buildUpdateNotification(masterAodbId)
                                        .addUpdateFields("codeShareFlightList", flightNumberString);
                                publishToRedis(flightNotificationDTO);

                            }else {
                                log.error("Failed to clone the old master FlightDTO object.");
                            }
                        }
                    }
                }

                FlightNotificationDTO dto = FlightNotificationDTO.buildDeleteNotification(flightId);
                publishToRedis(dto);
            }
        } else {
            log.error("There is no FLOP section in FDEL message. Message text [" + msg + "]");
        }
    }
}
