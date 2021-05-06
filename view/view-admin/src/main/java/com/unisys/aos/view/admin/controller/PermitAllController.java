/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.controller;

import com.unisys.aos.view.admin.entity.SmmFunction;
import com.unisys.aos.view.admin.service.SmmFunctionService;
import com.unisys.aos.view.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/12/25 17:49
 */
@Slf4j
@RestController
@RequestMapping("/view/admin/permit-all")
public class PermitAllController {
    @Autowired
    SmmFunctionService functionService;

    @GetMapping(value = "functions")
    public Result<Collection<SmmFunction>> getFunctions() {
        log.info("Get all functions...");
        List<SmmFunction> functionList = functionService.list();
        return Result.success(functionList);
    }
}
