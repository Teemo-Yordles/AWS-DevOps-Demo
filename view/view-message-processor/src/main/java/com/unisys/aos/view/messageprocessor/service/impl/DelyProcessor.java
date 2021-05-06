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
import com.unisys.aos.view.common.util.ListUtil;
import com.unisys.aos.view.common.util.ObjectUtil;
import com.unisys.aos.view.common.util.ThreadLocalDateUtil;
import com.unisys.aos.view.messageprocessor.entity.DELY;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsDelay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * flight delay change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/18 10:31
 */
@Service("FLOP_DELY")
@Slf4j
public class DelyProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public DelyProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the DELY message.
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
                    log.error("There is no FFID tag in DELY message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }

                List<FmsDelay> delayList = flightDTO.getDelays();
                ConcurrentMap<String, FmsDelay> delayMap = new ConcurrentHashMap<>();

                if (null != delayList && !delayList.isEmpty()) {
                    delayMap = delayList.stream()
                            .collect(Collectors.toConcurrentMap(FmsDelay::getDelayCode, (p) -> p));
                }

                List<DELY> delys = record.getDelys();
                boolean existEmptyTag = false;
                if (null != delys) {
                    for (DELY delay : delys) {
                        String newDelayCode = delay.getCODE();

                        String newStartTimeStr = delay.getSTRT();
                        Date newStartTime = null;
                        if (null != newStartTimeStr && !newStartTimeStr.isEmpty()) {
                            try {
                                newStartTime = ThreadLocalDateUtil.parseXmlDate(newStartTimeStr);
                                if (null == newStartTime) {
                                    log.error("The format of STRT is wrong. Delay code [" + newDelayCode + "]. Message text [" + msg + "], STRT text [" + newStartTimeStr + "]");
                                    return;
                                }
                            } catch (ParseException e) {
                                log.error("The format of STRT is wrong. Delay code [" + newDelayCode + "]. Message text [" + msg + "], STRT text [" + newStartTimeStr + "]");
                                return;
                            }
                        }

                        String newDurationStr = delay.getDURA();
                        Short newDuration = parseDuration(newDurationStr);
                        if (null != newDurationStr) {
                            if (null == newDuration) {
                                log.error("failed to parse DURA tag [" + newDurationStr + "]");
                                return;
                            }
                        } else {
                            log.info("DURA is empty in DELY message. Message text [" + msg + "]");
                        }

                        String newComment = delay.getValue();

                        if (null == newDelayCode || newDelayCode.isEmpty()) {
                            if (null != newDurationStr || null != newStartTimeStr || null != newComment) {
                                log.error("CODE cannot be empty in non-empty DELY message. Message text [" + msg + "]");
                                return;
                            } else {
                                log.info("All existing flight delays are being deleted. Message text [" + msg + "]");
                                existEmptyTag = true;
                                continue;
                            }
                        }

                        FmsDelay oldDelay = delayMap.get(newDelayCode);

                        // create empty domain FmsDelay entity and map properties
                        FmsDelay newDelay = new FmsDelay();
                        newDelay.setFlightId(flightDTO.getId());
                        newDelay.setDelayCode(newDelayCode);
                        newDelay.setStartTime(newStartTime);
                        newDelay.setDuration(newDuration);
                        newDelay.setComments(newComment);

                        if (null == oldDelay) {
                            // new FmsDelay then put it to map.
                            newDelay.setCreateTime(new Date());
                            delayMap.put(newDelay.getDelayCode(), newDelay);
                        } else {
                            // not new FmsDelay, if same ignored, otherwise update it.
                            newDelay.setId(oldDelay.getId());
                            if (!newDelay.equals(oldDelay)) {
                                newDelay.setCreateTime(oldDelay.getCreateTime());
                                newDelay.setUpdateTime(new Date());
                                delayMap.put(oldDelay.getDelayCode(), newDelay);
                            }
                        }
                    }
                    List<FmsDelay> updatedDelayList = null;
                    if (!existEmptyTag) {
                        updatedDelayList = new ArrayList<>(delayMap.values());
                    }

                    if (ListUtil.isListEqual(delayList, updatedDelayList)) {
                        log.info("DELY is same so nothing changed.");
                        return;
                    }

                    FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
                    if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
                        newFlightDTO.setDelays(updatedDelayList);
                        flightDAO.updateFmsDelayInNewTx(newFlightDTO, flightDTO);

                        FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(record.getFLID())
                                .addUpdateFields("delays",updatedDelayList);
                        publishToRedis(dto);
                    } else {
                        log.error("Failed to clone the old FlightDTO object.");
                    }
                } else {
                    log.error("DELY is empty. Message text [" + msg + "]");
                    return;
                }
            }
        } else {
            log.error("There is no FLOP section in DELY message. Message text [" + msg + "]");
        }
    }
}
