/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unisys.aos.view.common.util.ObjectUtil;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * flight eobt type change message processor
 *
 * @author jianglushuang
 * @since 2020/11/18 17:00
 */
@Service("FLOP_EOBT")
@Slf4j
public class EobtProcessor extends AbsProcessor implements Processor {
    private final FlightDAO flightDAO;

    @Autowired
    public EobtProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the EOBT message.
     *
     * @param msg - Message to be processed
     */
    @Override
    public void process(String msg) throws JsonProcessingException {

        List<FLOP.FlopData> flops = parserXml(msg);
        if (null == flops || flops.isEmpty()) {
            log.error("There is no FLOP section in EOBT message. Message text [" + msg + "]");
            return;
        }
        // loop through all XML records
        // if flight already exists then UPDATE otherwise return error
        for (FLOP.FlopData record : flops) {
            if (null == record.getFLID()) {
                log.error("There is no FFID tag in EOBT message. Message text [" + msg + "]");
                return;
            }
            log.debug("processing FLOP record id [{}]", record.getFLID());

            // find flight in redis or DB
            FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
            if (null == flightDTO) {
                log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                return;
            }
            // check EOBT tag
            String eobt = record.getEOBT();
            Date eobtCode = null;
            if(eobt == null){
                if (flightDTO.getFlight().getEstimatedOffblock() == null){
                    log.info("EOBT is same so nothing changed. EobtCode=null");
                    return;
                }
            }else{
                try {
                    eobtCode = ThreadLocalDateUtil.parseXmlDate(eobt);
                } catch (ParseException e) {
                    log.error("Parser EOBT xml date error.Message text [" + msg + "]" );
                    return;
                }
                // if same, no update is made.
                if (eobtCode.equals(flightDTO.getFlight().getEstimatedOffblock())) {
                    log.info("EOBT is same so nothing changed. EobtCode=[{}]", eobtCode);
                    return;
                }
            }

            // do actual update
            FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
            if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
                newFlightDTO.getFlight().setEstimatedOffblock(eobtCode);
                flightDAO.updateFmsFlightInNewTx(newFlightDTO, flightDTO.getFlight());
                FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(record.getFLID())
                        .addUpdateFields("eobtCode",eobtCode);
                publishToRedis(dto);
            } else {
                log.error("Failed to clone the old FlightDTO object.");
            }
        }
    }
}
