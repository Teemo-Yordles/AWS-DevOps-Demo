/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.controller;

import com.unisys.aos.view.admin.service.ForceOfflineService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianglushuang
 * @since 2021/2/24 10:40 下午
 */
@Slf4j
@RestController
public class ForceOfflineController {

    private final ForceOfflineService forceOfflineService;

    @Autowired
    public ForceOfflineController(ForceOfflineService forceOfflineService) {
        this.forceOfflineService = forceOfflineService;
    }

    @PostMapping(value = "/view/admin/force-offline/{roleId}")
    public Result forceOffline(@PathVariable("roleId") Long roleId){
        try {
            forceOfflineService.forceOffline(roleId);
        }catch (Exception e){
            return Result.failed(ResultCode.OPERATION_FAILED, "Force role[" + roleId + "] user to be offline failed");
        }
        return Result.success(null);
    }
}
