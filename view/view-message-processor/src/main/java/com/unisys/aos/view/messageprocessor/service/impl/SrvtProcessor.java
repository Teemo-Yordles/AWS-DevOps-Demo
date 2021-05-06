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
import com.unisys.aos.view.messageprocessor.entity.FLOP;
import com.unisys.aos.view.messageprocessor.entity.SRVT;
import com.unisys.aos.view.messageprocessor.service.AbsProcessor;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.viewentity.dao.FlightDAO;
import com.unisys.aos.view.viewentity.dto.FlightDTO;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import com.unisys.aos.view.viewentity.entity.flight.FmsService;
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
 * flight service transaction change message processor
 *
 * @author Zhang Wenqiang
 * @since 2020/11/18 10:31
 */
@Service("FLOP_SRVT")
@Slf4j
public class SrvtProcessor extends AbsProcessor {
    private final FlightDAO flightDAO;

    @Autowired
    public SrvtProcessor(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * Process the SRVT message.
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
                    log.error("There is no FFID tag in SRVT message. Message text [" + msg + "]");
                    return;
                }
                log.debug("processing FLOP record id [{}]", record.getFLID());

                // find flight in redis or DB
                FlightDTO flightDTO = flightDAO.findByAodbId(record.getFLID());
                if (null == flightDTO) {
                    log.error("Cannot find flight ID=[" + record.getFLID() + "] FFID=[" + record.getFFID() + "]");
                    return;
                }

                List<FmsService> serviceList = flightDTO.getServices();
                ConcurrentMap<String, FmsService> serviceMap = new ConcurrentHashMap<>();

                if (null != serviceList && !serviceList.isEmpty()) {
                    serviceMap = serviceList.stream()
                            .collect(Collectors.toConcurrentMap(FmsService::getServiceCode, (p) -> p));
                }

                List<SRVT> srvts = record.getSrvts();

                if (null != srvts) {
                    boolean isEmptyTag = false;
                    for (SRVT srvt : srvts) {
                        String serviceTransactionCode = srvt.getSRTC();
                        Byte serviceTransactionQuantity = srvt.getSRQT();
                        String serviceTransactionStart = srvt.getSRST();
                        String serviceTransactionEnd = srvt.getSRET();
                        Integer serviceTransactionProvider = srvt.getSRPR();
                        String serviceTransactionAbnormalRsn = srvt.getSANR();
                        String serviceTransactionNormalRsnRmk = srvt.getSARR();

                        if (null == serviceTransactionCode && null == serviceTransactionQuantity && null == serviceTransactionStart && null == serviceTransactionEnd && null == serviceTransactionProvider && null == serviceTransactionAbnormalRsn && null == serviceTransactionNormalRsnRmk) {
                            log.info("This flight is emptied service transaction.");
                            isEmptyTag = true;
                        } else {
                            if (null == serviceTransactionCode || serviceTransactionCode.isEmpty()) {
                                log.error("SRTC cannot be empty in SRVT message. Message text [" + msg + "]");
                                return;
                            }


                            if (null == serviceTransactionQuantity) {
                                log.error("SRQT cannot be empty in SRVT message. Message text [" + msg + "]");
                                return;
                            }

                            Date serviceTransactionStartTime = null;
                            if (null == serviceTransactionStart || serviceTransactionStart.isEmpty()) {
                                log.info("No data available for SRST. Service transaction code [" + serviceTransactionCode + "]. Message text [" + msg + "]");
                            } else {
                                try {
                                    serviceTransactionStartTime = ThreadLocalDateUtil.parseXmlDate(serviceTransactionStart);
                                    if (null == serviceTransactionStartTime) {
                                        log.error("The format of SRST is wrong, Message text [" + msg + "], SRST text [" + serviceTransactionStart + "]");
                                        return;
                                    }
                                } catch (ParseException e) {
                                    log.error("The format of SRST is wrong, Message text [" + msg + "], SRST text [" + serviceTransactionStart + "]");
                                    return;
                                }
                            }

                            Date serviceTransactionEndTime = null;
                            if (null == serviceTransactionEnd || serviceTransactionEnd.isEmpty()) {
                                log.info("No data available for SRET. Service transaction code [" + serviceTransactionCode + "]. Message text [" + msg + "]");
                            } else {
                                try {
                                    serviceTransactionEndTime = ThreadLocalDateUtil.parseXmlDate(serviceTransactionEnd);
                                    if (null == serviceTransactionEndTime) {
                                        log.error("The format of SRET is wrong, Message text [" + msg + "], SRET text [" + serviceTransactionEnd + "]");
                                        return;
                                    }
                                } catch (ParseException e) {
                                    log.error("The format of SRET is wrong, Message text [" + msg + "], SRET text [" + serviceTransactionEnd + "]");
                                    return;
                                }
                            }

                            if (null == serviceTransactionProvider) {
                                log.info("No data available for SRPR. Service transaction code [" + serviceTransactionCode + "]. Message text [" + msg + "]");
                            }

                            if (null == serviceTransactionAbnormalRsn || serviceTransactionAbnormalRsn.isEmpty()) {
                                log.info("SANR is empty in SRVT. Service transaction code [" + serviceTransactionCode + "]. Message text [" + msg + "]");
                            }

                            if (null == serviceTransactionNormalRsnRmk || serviceTransactionNormalRsnRmk.isEmpty()) {
                                log.info("SARR is empty in SRVT. Service transaction code [" + serviceTransactionCode + "]. Message text [" + msg + "]");
                            }

                            FmsService oldService = serviceMap.get(serviceTransactionCode);

                            // create empty domain FmsService entity and map properties
                            FmsService newService = new FmsService();
                            newService.setFlightId(flightDTO.getId());
                            newService.setServiceCode(serviceTransactionCode);
                            newService.setServiceQuantity(serviceTransactionQuantity);
                            newService.setStartTime(serviceTransactionStartTime);
                            newService.setEndTime(serviceTransactionEndTime);
                            newService.setServiceProvider(serviceTransactionProvider);
                            newService.setAbnormalReason(serviceTransactionAbnormalRsn);
                            newService.setAbnormalRemark(serviceTransactionNormalRsnRmk);

                            if (null == oldService) {
                                // new FmsService then put it to map.
                                newService.setCreateTime(new Date());
                                serviceMap.put(serviceTransactionCode, newService);
                            } else {
                                // not new FmsService, if same ignored, otherwise update it.
                                newService.setId(oldService.getId());
                                if (!newService.equals(oldService)) {
                                    newService.setCreateTime(oldService.getCreateTime());
                                    newService.setUpdateTime(new Date());
                                    serviceMap.put(oldService.getServiceCode(), newService);
                                }
                            }
                        }
                    }

                    List<FmsService> updatedServiceList = null;
                    if (!isEmptyTag) {
                        updatedServiceList = new ArrayList<>(serviceMap.values());
                    }


                    if (ListUtil.isListEqual(serviceList, updatedServiceList)) {
                        log.info("SRVT is same so nothing changed.");
                        return;
                    }

                    FlightDTO newFlightDTO = (FlightDTO) ObjectUtil.deepClone(flightDTO);
                    if (null != newFlightDTO && null != newFlightDTO.getFlight()) {
                        newFlightDTO.setServices(updatedServiceList);
                        flightDAO.updateFmsServiceInNewTx(newFlightDTO, flightDTO);

                        FlightNotificationDTO dto = FlightNotificationDTO.buildUpdateNotification(flightDTO.getFlight().getId())
                                .addUpdateFields("services",updatedServiceList);
                        publishToRedis(dto);
                    } else {
                        log.error("Failed to clone the old FlightDTO object.");
                    }
                } else {
                    log.error("SRVT is empty. Message text [" + msg + "]");
                    return;
                }
            }
        } else {
            log.error("There is no FLOP section in SRVT message. Message text [" + msg + "]");
        }
    }
}
