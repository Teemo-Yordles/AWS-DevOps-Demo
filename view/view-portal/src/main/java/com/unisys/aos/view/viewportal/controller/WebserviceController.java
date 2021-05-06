/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewportal.controller;

import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.viewentity.dto.FlightNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jianglushuang
 * @since 2020/12/28 3:42 下午
 */
@Controller
@Slf4j
public class WebserviceController {

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("/view/websocket/{topic}")
    @ResponseBody
    public Result send(@PathVariable("topic") String topic, @RequestBody FlightNotificationDTO dto){
        try {
            redisUtil.publish(topic, dto);
        }catch (Exception e){
            log.error(e.getMessage());
            return Result.failed();
        }
        return Result.success(ResultCode.SUCCESS);
    }
}
