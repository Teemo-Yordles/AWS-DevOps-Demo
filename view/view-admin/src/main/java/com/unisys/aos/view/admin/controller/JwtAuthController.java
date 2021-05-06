/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.controller;

import com.unisys.aos.view.admin.entity.LoginDTO;
import com.unisys.aos.view.admin.service.JwtAuthService;
import com.unisys.aos.view.security.config.JwtConstants;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

/**
 * This controller in charge of user login/token refresh/logout etc.
 *
 * @author LiuJ2
 * @since 2020/12/18 10:24
 */
@RestController
@Slf4j
public class JwtAuthController {
    private final JwtAuthService jwtAuthService;

    @Autowired
    public JwtAuthController(JwtAuthService jwtAuthService) {
        this.jwtAuthService = jwtAuthService;
    }

    @RequestMapping(value = JwtConstants.CONTROLLER_LOGIN)
    public Result<LoginDTO> login(@RequestBody Map<String, String> map) {

        String username = map.get(SPRING_SECURITY_FORM_USERNAME_KEY);
        String password = map.get(SPRING_SECURITY_FORM_PASSWORD_KEY);

        //request format check
        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "username and password shouldn't be empty.");
        }

        LoginDTO loginDTO;
        try {
            loginDTO = jwtAuthService.login(username, password, null);
        } catch (UsernameNotFoundException ex) {
            log.error("user not exists", ex);
            return Result.failed(ResultCode.USER_NOT_EXISTED, "user not found");
        } catch (DisabledException ex) {
            log.error("User account is disabled", ex);
            return Result.failed(ResultCode.ACCOUNT_DISABLED, "User account is disabled");
        } catch (AuthenticationException e) {
            log.error("authentication failed", e);
            return Result.failed(ResultCode.PASSWORD_ERROR);
        } catch (Exception ex) {
            log.error("Unexpected exception", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Unexpected exception, please check server log.");
        }
        log.info("User [{}] login successfully.", username);
        return Result.success(loginDTO);
    }

    /**
     * This is for create test-token to administrator UserDetails
     * mapping in cache. The test token is for postman testing and unit testing use.
     * It should not appear in production environment.
     * @return - loginDTO
     */
    @RequestMapping(value = JwtConstants.CONTROLLER_TEST_LOGIN)
    public Result<LoginDTO> loginForTest() {

        LoginDTO loginDTO;
        try {
            loginDTO = jwtAuthService.loginForTest();
        } catch (Exception ex) {
            log.error("Unexpected exception", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Unexpected exception, please check server log.");
        }
        log.info("User for test login successfully.");
        return Result.success(loginDTO);
    }

    @RequestMapping(value = JwtConstants.CONTROLLER_REFRESH)
    public Result<LoginDTO> refreshToken(@RequestBody Map<String, String> map) {
        String refreshToken = map.get(JwtConstants.REFRESH_TOKEN_NAME);
        //request format check
        if (refreshToken == null || "".equals(refreshToken)) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "refresh token shouldn't be empty.");
        }

        LoginDTO loginDTO;
        try {
            loginDTO = jwtAuthService.refreshToken(refreshToken);
        } catch (UsernameNotFoundException ex) {
            log.error("user not exists", ex);
            return Result.failed(ResultCode.USER_NOT_EXISTED, "user not found");
        } catch (InvalidCookieException ex) {
            log.error("Login expired", ex);
            return Result.failed(ResultCode.LOGIN_EXPIRED, "user login expired");
        } catch (DisabledException ex) {
            log.error("User account is disabled", ex);
            return Result.failed(ResultCode.ACCOUNT_DISABLED, "User account is disabled");
        } catch (BadCredentialsException e) {
            log.error("authentication failed", e);
            return Result.failed(ResultCode.PASSWORD_ERROR);
        } catch (AuthenticationException e) {
            log.error("authentication failed", e);
            return Result.failed(ResultCode.OPERATION_FAILED);
        } catch (Exception ex) {
            log.error("Unexpected exception", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Unexpected exception, please check server log.");
        }
        log.info("User [{}] token refreshed successfully.", loginDTO.getUsername());
        return Result.success(loginDTO);
    }

    @RequestMapping(value = JwtConstants.CONTROLLER_LOGOUT)
    public Result<Object> logout(@RequestBody Map<String, String> map) {
        String accessToken = map.get(JwtConstants.ACCESS_TOKEN_NAME);
        String refreshToken = map.get(JwtConstants.REFRESH_TOKEN_NAME);

        //request format check
        if (accessToken == null || "".equals(accessToken) || refreshToken == null || "".equals(refreshToken)) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Neither of the tokens should be empty.");
        }

        try {
            jwtAuthService.logout(accessToken, refreshToken);
        } catch (Exception ex) {
            log.error("Unexpected exception", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Unexpected exception, please check server log.");
        }
        log.info("User logged out successfully.");
        return Result.success(null);
    }

    @RequestMapping(value = JwtConstants.CONTROLLER_TEST_LOGOUT)
    public Result<Object> logoutForTest() {
        try {
            jwtAuthService.logoutForTest();
        } catch (Exception ex) {
            log.error("Unexpected exception", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Unexpected exception, please check server log.");
        }
        log.info("Test User logged out successfully.");
        return Result.success(null);
    }
}
