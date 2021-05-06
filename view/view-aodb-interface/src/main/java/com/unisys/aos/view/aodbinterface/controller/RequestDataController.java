/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.controller;

import com.alibaba.fastjson.JSONObject;
import com.unisys.aos.view.aodbinterface.service.RequestDataService;
import com.unisys.aos.view.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.unisys.aos.view.aodbinterface.utils.BuildXmlUtil.*;


/**
 * @author Zhang Wenqiang
 * @since 2021/1/13 15:20
 */
@Slf4j
@Controller
public class RequestDataController {
    private final RequestDataService requestDataService;

    @Autowired
    public RequestDataController(RequestDataService requestDataService) {
        this.requestDataService = requestDataService;
    }

    @RequestMapping(value = "/view/admin/data-request", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> requestData(@RequestBody JSONObject jsonObject) {
        String referenceSubtype = jsonObject.getString(REFERENCE_DATA_TYPE);
        String xmlString;
        if (null == referenceSubtype) {
            String dailyScheduleSubtype = jsonObject.getString(DAILY_SCHEDULE_DATA_TYPE);
            String startTime = jsonObject.getString("STDB");
            String endTime = jsonObject.getString("STDE");
            if (null == startTime || startTime.isEmpty() || null == endTime || endTime.isEmpty()) {
                log.error("There is an error in time parameter of request, and neither start time nor end time can be empty. Start time = {}, end time = {}.", startTime, endTime);
                return Result.failed("There is an error in time parameter of request.");
            }
            if (null == dailyScheduleSubtype || !dailyScheduleSubtype.isEmpty()) {
                log.error("The subtype of daily schedule must be empty in URI, subtype = {}.", dailyScheduleSubtype);
                return Result.failed("Message type error.");
            }
            dailyScheduleSubtype = DAILY_SCHEDULE_SUBTYPE;
            xmlString = requestDataService.buildAndInsertToCout(DAILY_SCHEDULE_DATA_TYPE, dailyScheduleSubtype, startTime, endTime);
        } else {
            xmlString = requestDataService.buildAndInsertToCout(REFERENCE_DATA_TYPE, referenceSubtype, null, null);
        }
        if (null == xmlString) {
            return Result.failed("Failed to build xml, failed to send request to AODB.");
        } else {
//            return Result.success(xmlString);
            return Result.success(null);
        }
    }
}
