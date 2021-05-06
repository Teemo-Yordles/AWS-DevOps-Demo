/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.exception;

import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.exception.ApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is a global controller exception handler
 * @author LiuJ2
 * @since 2020/10/13 10:08
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public Result handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return Result.failed(e.getErrorCode(), e.getMessage());
        }
        return Result.failed(e.getMessage());
    }
}
