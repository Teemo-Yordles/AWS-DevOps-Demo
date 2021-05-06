/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LiuJ2
 * @since 2020/12/28 21:05
 */
@Component
@AllArgsConstructor
public class ViewAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        Result result = null;
        if (authException instanceof InvalidCookieException) {
            result = Result.failed(ResultCode.LOGIN_EXPIRED);
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = Result.failed(ResultCode.NOT_AUTHORIZED);
        }
        PrintWriter printWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        printWriter.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        printWriter.close();
    }
}