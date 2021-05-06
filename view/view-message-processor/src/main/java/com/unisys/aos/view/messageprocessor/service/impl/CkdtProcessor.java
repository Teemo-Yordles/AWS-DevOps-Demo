/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unisys.aos.view.common.util.ListUtil;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsCheckinDesk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * flight checkinDesk type change message processor
 *
 * @author jianglushuang
 * @since 2020/11/18 17:00
 */
@Service("FLOP_CKDT")
@Slf4j
public class CkdtProcessor extends AbsProcessor implements Processor {
    private final FlightDAO flightDAO;

    @Autowired
    public CkdtProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the CKDT message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {

        List<FLOP.FlopData> flops = parserXml(msg);
        if (null == flops || flops.isEmpty()) {
            log.error("There is no FLOP section in CKDT message. Message text [" + msg + "]");
            return;
        }
        // loop through all XML records
        // if flight already exists then UPDATE otherwise return error
        for (FLOP.FlopData record : flops) {
            if (null == record.getFLID()) {
                log.error("There is no FFID tag in CKDT message. Message text [" + msg + "]");
                return;
            }
            log.debug("processing FLOP record id [{}]", record.getFLID());

            // find flight in redis or DB
            FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
            if (null == flightDTO) {
                log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                return;
            }

            // check checkinDesk tag
            List<FmsCheckinDesk> checkinDesks = null;
            try {
                checkinDesks = parseCounters(record.getCkdts(),record.getFLID());
                if (null == checkinDesks || checkinDesks.isEmpty()) {
                    log.error("CKDT cannot be empty in CKDT message. Message text [" + msg + "]");
                    return;
                }
            } catch (ParseException e) {
                log.error("AODB message cannot be converted to DB Date");
            }
            // if same, no update is made.
            if (ListUtil.isListEqual(checkinDesks, flightDTO.getCheckinDesks())) {
                log.info("CKDT is same so nothing changed.");
                return;
            }
            // do actual update
            flightDAO.updateAdditionFlightInfo(flightDTO, checkinDesks, FmsCheckinDesk.class);

            FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(record.getFLID())
                    .addUpdateFields("checkinDesks",checkinDesks);
            publishToRedis(dto);
        }
    }
}
