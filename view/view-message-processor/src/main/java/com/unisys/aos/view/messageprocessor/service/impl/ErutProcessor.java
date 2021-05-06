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
import com.unisys.aos.view.messageprocessor.entity.ERUT;
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsEntireRoute;
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
 * entire flight routing change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/18 10:31
 */
@Service("FLOP_ERUT")
@Slf4j
public class ErutProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public ErutProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the ERUT message.
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
                    log.error("There is no FFID tag in ERUT message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }

                List<FmsEntireRoute> entireRouteList = flightDTO.getEntireRoutes();
                ConcurrentMap<Byte, FmsEntireRoute> entireRouteMap = new ConcurrentHashMap<>();
                if (null != entireRouteList && !entireRouteList.isEmpty()) {
                    entireRouteMap = entireRouteList.stream()
                            .collect(Collectors.toConcurrentMap(FmsEntireRoute::getSequenceNumber, (p) -> p));
                }

                List<ERUT> eruts = record.getEruts();
                if (null != eruts) {
                    boolean isEmptytag = false;
                    for (ERUT erut : eruts) {
                        Byte routeNumber = erut.getRTNO();
                        String airportCode = erut.getAPCD();
                        String arrivalTimeStr = erut.getSCAT();
                        String departureTimeStr = erut.getSCDT();

                        if (null == routeNumber && null == airportCode && null == arrivalTimeStr && null == departureTimeStr) {
                            isEmptytag = true;
                        } else {
                            if (null == routeNumber) {
                                log.error("RTNO cannot be empty in ERUT message. Message text [" + msg + "]");
                                return;
                            }

                            if (null == airportCode || airportCode.isEmpty()) {
                                log.error("APCD cannot be empty in ERUT message. Message text [" + msg + "]");
                                return;
                            }

                            Date arrivalTime = null;
                            if (null == arrivalTimeStr || arrivalTimeStr.isEmpty()) {
                                log.info("SCAT is empty in ERUT message. Route number [" + routeNumber + "]. Message text [" + msg + "]");
                            } else {
                                try {
                                    arrivalTime = ThreadLocalDateUtil.parseXmlDate(arrivalTimeStr);
                                    if (null == arrivalTime) {
                                        log.error("The format of SCAT is wrong. Route number [" + routeNumber + "]. Message text [" + msg + "]. SCAT text [" + arrivalTimeStr + "]");
                                        return;
                                    }
                                } catch (ParseException e) {
                                    log.error("The format of SCAT is wrong. Route number [" + routeNumber + "]. Message text [" + msg + "]. SCAT text [" + arrivalTimeStr + "]");
                                    return;
                                }
                            }

                            Date departureTime = null;
                            if (null == departureTimeStr || departureTimeStr.isEmpty()) {
                                log.info("SCDT is empty in ERUT message. Route number [" + routeNumber + "]. Message text [" + msg + "]");
                            } else {
                                try {
                                    departureTime = ThreadLocalDateUtil.parseXmlDate(departureTimeStr);
                                    if (null == departureTime) {
                                        log.error("The format of SCDT is wrong. Route number [" + routeNumber + "]. Message text [" + msg + "], SCDT text [" + departureTimeStr + "]");
                                        return;
                                    }
                                } catch (ParseException e) {
                                    log.error("The format of SCDT is wrong. Route number [" + routeNumber + "]. Message text [" + msg + "], SCDT text [" + departureTimeStr + "]");
                                    return;
                                }
                            }

                            // create empty domain FmsEntireRoute entity and map properties
                            FmsEntireRoute newEntireRoute = new FmsEntireRoute();
                            newEntireRoute.setFlightId(flightDTO.getId());
                            newEntireRoute.setSequenceNumber(routeNumber);
                            newEntireRoute.setAirportCode(airportCode);
                            newEntireRoute.setArrivalTime(arrivalTime);
                            newEntireRoute.setDepartureTime(departureTime);

                            FmsEntireRoute oldEntireRoute = entireRouteMap.get(routeNumber);

                            if (null == oldEntireRoute) {
                                // new entireRoute route then put it to map 
                                newEntireRoute.setCreateTime(new Date());
                                entireRouteMap.put(routeNumber, newEntireRoute);
                            } else {
                                // not new FmsEntireRoute, if same ignored, otherwise update it.
                                newEntireRoute.setId(oldEntireRoute.getId());
                                if (!newEntireRoute.equals(oldEntireRoute)) {
                                    newEntireRoute.setCreateTime(oldEntireRoute.getCreateTime());
                                    newEntireRoute.setUpdateTime(new Date());
                                    entireRouteMap.put(oldEntireRoute.getSequenceNumber(), newEntireRoute);
                                }
                            }
                        }
                    }

                    List<FmsEntireRoute> updatedEntireRouteList = null;
                    if (!isEmptytag) {
                        updatedEntireRouteList = new ArrayList<>(entireRouteMap.values());
                    }

                    if (ListUtil.isListEqual(entireRouteList, updatedEntireRouteList)) {
                        log.info("ERUT is same so nothing changed.");
                        return;
                    }

                    // do actual update
                    FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
                    if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
                        newFlightDTO.setEntireRoutes(updatedEntireRouteList);
                        flightDAO.updateFmsEntireRouteInNewTx(newFlightDTO, flightDTO);

                        FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                                .addUpdateFields("entireRoute",updatedEntireRouteList);
                        publishToRedis(dto);
                    } else {
                        log.error("Failed to clone the old FlightDTO object.");
                    }
                } else {
                    log.error("ERUT is empty. Message text [" + msg + "]");
                    return;
                }
            }
        } else {
            log.error("There is no FLOP section in ERUT message. Message text [" + msg + "]");
        }
    }
}
