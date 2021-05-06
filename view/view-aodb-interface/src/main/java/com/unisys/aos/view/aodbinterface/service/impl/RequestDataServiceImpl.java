/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.service.impl;

import com.unisys.aos.view.aodbinterface.config.MessagingProperties;
import com.unisys.aos.view.aodbinterface.service.RequestDataService;
import com.unisys.aos.view.aodbinterface.utils.BuildXmlUtil;
import com.unisys.aos.view.viewentity.entity.aodbinterface.Coutmsgs;
import com.unisys.aos.view.viewentity.mapper.aodbinterface.CoutmsgsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.unisys.aos.view.aodbinterface.utils.BuildXmlUtil.DAILY_SCHEDULE_DATA_TYPE;
import static com.unisys.aos.view.aodbinterface.utils.BuildXmlUtil.REFERENCE_DATA_TYPE;

/**
 * @author Zhang Wenqiang
 * @since 2021/1/13 15:20
 */
@Slf4j
@Service
public class RequestDataServiceImpl implements RequestDataService {
    private final MessagingProperties messagingProperties;
    private final BuildXmlUtil buildXmlUtil;
    private final CoutmsgsMapper coutmsgsMapper;
    private final Coutmsgs coutmsgs;

    @Autowired
    public RequestDataServiceImpl(MessagingProperties messagingProperties, BuildXmlUtil buildXmlUtil, CoutmsgsMapper coutmsgsMapper, Coutmsgs coutmsgs) {
        this.messagingProperties = messagingProperties;
        this.buildXmlUtil = buildXmlUtil;
        this.coutmsgsMapper = coutmsgsMapper;
        this.coutmsgs = coutmsgs;
    }

    /***
     * Build xml string and insert to database (table: coutmsgs).
     * @param type The data type to be requested.
     * @param subtype The data subtype to be requested.
     * @param startTime The start time of requested data (Only has value when requesting daily schedule data, and it is null when requesting reference data).
     * @param endTime The end time of requested data (Only has value when requesting daily schedule data, and it is null when requesting reference data).
     * @return Return xml string if build success, otherwise return null.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String buildAndInsertToCout(String type, String subtype, String startTime, String endTime) {
        if (DAILY_SCHEDULE_DATA_TYPE.equals(type)) {
            coutmsgs.setRoutingid(messagingProperties.getRoutingIdDailyScheduleRequest());
        } else if (REFERENCE_DATA_TYPE.equals(type)) {
            coutmsgs.setRoutingid(messagingProperties.getRoutingIdReferenceDataRequest());
        } else {
            log.error("Message type error, message type = {}", type);
            return null;
        }

        String xmlString = buildXmlUtil.buildXmlString(type, subtype, startTime, endTime);
        coutmsgs.setCoutmsgsClobMsg(xmlString);
        coutmsgs.setCoutmsgsDateInserted(new Date());

        Byte defaultValue = 0;
        coutmsgs.setCoutmsgsTruefalsGroup(defaultValue);
        coutmsgs.setCoutmsgsFinalGroupInd(defaultValue);
        coutmsgs.setCoutmsgsAckReqd(defaultValue);
        coutmsgs.setCoutmsgsAckResendTimes(defaultValue);
        coutmsgs.setCoutmsgsEncrypt(defaultValue);
        coutmsgs.setCoutmsgsError(defaultValue);
        if (null != xmlString) {
            log.debug("Insert to database...");
            coutmsgsMapper.insert(coutmsgs);
        }
        return xmlString;
    }
}
