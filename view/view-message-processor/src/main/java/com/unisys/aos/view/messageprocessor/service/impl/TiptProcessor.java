/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.entity.TIPT;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsStand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * flight externalStatus type change message processor
 *
 * @author jianglushuang
 * @since 2020/11/18 17:00
 */
@Service("FLOP_TIPT")
@Slf4j
public class TiptProcessor extends AbsProcessor implements Processor {
    private final FlightDAO flightDAO;

    @Autowired
    public TiptProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the TIPT message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {

        List<FLOP.FlopData> flops = parserXml(msg);
        if (null == flops || flops.isEmpty()) {
            log.error("There is no FLOP section in TIPT message. Message text [" + msg + "]");
            return;
        }
        // loop through all XML records
        // if flight already exists then UPDATE otherwise return error
        for (FLOP.FlopData record : flops) {
            if (null == record.getFLID()) {
                log.error("There is no FFID tag in TIPT message. Message text [" + msg + "]");
                return;
            }
            log.debug("processing FLOP record id [{}]", record.getFLID());

            // find flight in redis or DB
            FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
            if (null == flightDTO || null == flightDTO.getStands()) {
                log.error("Cannot find the stand of flight.  flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                return;
            }

            Map<String, FmsStand> standUpdates = new HashMap<>();
            try {
                List<FmsStand> stands = parseStandTractorUpdate(record.getTipts(), record.getFLID());
                for (FmsStand stand: stands){
                    standUpdates.put(stand.getStandCode(),stand);
                }
            } catch (ParseException e) {
                log.error("AODB message cannot be converted to DB Date");
                return;
            }
            List<FmsStand> stands = new LinkedList<>();
            // check TIPT tag
            for (FmsStand stand : flightDTO.getStands()){
                FmsStand standUpdate = standUpdates.get(stand.getStandCode());
                if (standUpdate == null) continue;
                if (null == standUpdate.getTractorTime()){
                    if(null == stand.getTractorTime()){
                        continue;
                    }
                }else if (standUpdate.getTractorTime().equals(stand.getTractorTime())) {
                    continue;
                }
                stand.setTractorTime(standUpdate.getTractorTime());
                stand.setUpdateTime(new Date());
                stands.add(stand);
            }

            // do actual update
            if(stands.size()!=0) {
                flightDAO.updateFmsStands(stands);
                List<FlightDTO> dtos = new ArrayList<>();
                dtos.add(flightDTO);
                flightDAO.cacheAll(dtos);

                FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(record.getFLID())
                        .addUpdateFields("standTractorTime", stands);
                publishToRedis(dto);
            }
        }
    }

}
