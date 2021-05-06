/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.LoginDTO;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.cache.RedisUtil;
import com.unisys.aos.view.common.exception.ApiException;
import com.unisys.aos.view.security.entity.ViewUserDetails;
import com.unisys.aos.view.security.service.ViewUserDetailsService;
import com.unisys.aos.view.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author LiuJ2
 * @since 2020/12/18 12:08
 */
@Service
@Slf4j
public class JwtAuthService {
    private static final String USERNAME_REDIS_SET_KEY_PREFIX = "username:";
    private static final long TEST_EXPIRY = 6000L * 60 * 24 * 365;
    private final AuthenticationManager authenticationManager;
    private final ViewUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisUtil redisUtil;
    private final SmmProfileService profileService;

    @Autowired
    public JwtAuthService(AuthenticationManager authenticationManager,
                          ViewUserDetailsService myUserDetailsService,
                          JwtTokenUtil jwtTokenUtil,
                          RedisUtil redisUtil,
                          SmmProfileService profileService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.redisUtil = redisUtil;
        this.profileService = profileService;
    }

    /**
     * user login to get the JWT token
     *
     * @return LoginDTO - login successful object contains token and some user info.
     */
    public LoginDTO login(String username,
                          String password,
                          Map<String, String> payloads)
            throws AuthenticationException {
        // get userDetails
        // if username not exists, a UsernameNotFoundException will be thrown out
        ViewUserDetails userDetails = (ViewUserDetails) userDetailsService.loadUserByUsername(username);
        // check if user account status normal
        if (!userDetails.isAccountNonLocked() || !userDetails.isAccountNonExpired()) {
            throw new DisabledException("Account is disabled.");
        }

        // use spring security to authenticate username password
        // set authentication for current context for spring security
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(userToken);
        log.info("User [{}] passed login authentication.", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        long accessExpire = jwtTokenUtil.getJwtProperties().getAccessTokenExpiration();
        long refreshExpire = jwtTokenUtil.getJwtProperties().getRefreshTokenExpiration();
        String accessToken = jwtTokenUtil.generateToken(userDetails, payloads, accessExpire);
        String refreshToken = jwtTokenUtil.generateToken(userDetails, payloads, refreshExpire);

        // persist the tokens-UserDetails to Redis with the expiration
        redisUtil.setEx(accessToken, userDetails, accessExpire);
        redisUtil.setEx(refreshToken, userDetails, refreshExpire);
        // e.g. username:admin
        String usernameSetKey = USERNAME_REDIS_SET_KEY_PREFIX + username;
        redisUtil.sadd(usernameSetKey, accessToken);
        redisUtil.sadd(usernameSetKey, refreshToken);
        log.info("User [{}] tokens cached.", username);
        int deletedCount = userDetailsService.deleteExpiredTokenSetValue(username);
        log.info("[{}] expired token deleted from User:[{}] token cache.", deletedCount, username);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccessToken(accessToken);
        loginDTO.setRefreshToken(refreshToken);
        loginDTO.setUsername(username);
        loginDTO.setFunctions(userDetails.getFunctions());
        loginDTO.setProfile(profileService.findSelectedProfileByUsername(username));
        return loginDTO;
    }

    /**
     * Refresh the access token. refreshToken remain unchanged.
     *
     * @param refreshToken - refreshToken used to refresh the access token
     * @return - LoginDTO with tokens and user details
     */
    public LoginDTO refreshToken(String refreshToken) {
        UserDetails userDetails = retrieveUserDetailsFromContext();
        String username = userDetails.getUsername();

        // check if user account status normal
        if (!userDetails.isAccountNonLocked() || !userDetails.isAccountNonExpired()) {
            throw new DisabledException("Account is disabled.");
        }

        long accessExpire = jwtTokenUtil.getJwtProperties().getAccessTokenExpiration();
        String accessToken = jwtTokenUtil.generateToken(userDetails, null, accessExpire);

        // persist the tokens-UserDetails to Redis with the expiration
        redisUtil.setEx(accessToken, userDetails, accessExpire);
        // e.g. username:admin
        String usernameSetKey = USERNAME_REDIS_SET_KEY_PREFIX + username;
        redisUtil.sadd(usernameSetKey, accessToken);
        log.info("User [{}] refreshed access token cached.", username);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccessToken(accessToken);
        loginDTO.setRefreshToken(refreshToken);
        loginDTO.setUsername(username);
        loginDTO.setFunctions(((ViewUserDetails) userDetails).getFunctions());
        return loginDTO;
    }

    /**
     * retrieve user details from Security Context
     *
     * @return UserDetails in context
     */
    private UserDetails retrieveUserDetailsFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null == principal) {
            log.error("authentication system failure");
            throw new AuthenticationServiceException("cannot get user details in security context");
        }
        // check if "anonymous" string is returned from SecurityContext
        if (!(principal instanceof ViewUserDetails)) {
            throw new InvalidCookieException("Cannot find user details in security context. Login expired.");
        }

        return (UserDetails) principal;
    }

    /**
     * logout service
     *
     * @param accessToken  - access token of the user login
     * @param refreshToken - refresh token of the user login
     */
    public void logout(String accessToken, String refreshToken) {
        UserDetails userDetails = retrieveUserDetailsFromContext();
        String username = userDetails.getUsername();

        // delete the cached token
        redisUtil.del(accessToken);
        redisUtil.del(refreshToken);
        // e.g. username:admin
        if (!StringUtils.isEmpty(username)) {
            String usernameSetKey = USERNAME_REDIS_SET_KEY_PREFIX + username;
            redisUtil.srem(usernameSetKey, accessToken);
            redisUtil.srem(usernameSetKey, refreshToken);
        }
        log.info("User [{}] both access token and refresh token are deleted from cache.", username);
    }

    /**
     * login to cache testToken-UserDetail. If test token is empty, then ignore
     * the login action. Test token will actually as a login "admin" user.
     *
     * @return - login DTO. Null if no test token specified.
     */
    public LoginDTO loginForTest() {
        String testToken = jwtTokenUtil.getJwtProperties().getTestToken();
        if (StringUtils.isEmpty(testToken)) {
            // this means production environment, no test-token specified.
            log.warn("test-token is not specified. Ignore test login.");
            return null;
        }

        String username = "admin";
        // get userDetails
        // if username not exists, a UsernameNotFoundException will be thrown out
        ViewUserDetails userDetails = (ViewUserDetails) userDetailsService.loadUserByUsername(username);
        // check if user account status normal
        if (!userDetails.isAccountNonLocked() || !userDetails.isAccountNonExpired()) {
            throw new DisabledException("Account is disabled.");
        }

        // persist the tokens-UserDetails to Redis with the expiration
        redisUtil.setEx(testToken, userDetails, TEST_EXPIRY);

        // e.g. username:admin
        String usernameSetKey = USERNAME_REDIS_SET_KEY_PREFIX + "admin";
        redisUtil.sadd(usernameSetKey, testToken);
        log.info("User [{}]'s test token cached.", username);
        int deletedCount = userDetailsService.deleteExpiredTokenSetValue(username);
        log.info("[{}] expired token deleted from User:[{}] token cache for test-token login.", deletedCount, username);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccessToken(testToken);
        loginDTO.setRefreshToken(testToken);
        loginDTO.setUsername(username);
        loginDTO.setFunctions(userDetails.getFunctions());
        return loginDTO;
    }

    /**
     * logout to clear cached testToken-UserDetail.
     * If test token is empty, then ignore the logout action.
     */
    public void logoutForTest() {
        String testToken = jwtTokenUtil.getJwtProperties().getTestToken();
        if (StringUtils.isEmpty(testToken)) {
            // this means production environment, no test-token specified.
            log.warn("test-token is not specified. Ignore test logout.");
            return;
        }

        // delete the cached token
        redisUtil.del(testToken);

        // e.g. username:admin
        String username = "admin";
        String usernameSetKey = USERNAME_REDIS_SET_KEY_PREFIX + username;
        redisUtil.srem(usernameSetKey, testToken);

        log.info("User [{}] test token are deleted from cache.", username);
    }

    /**
     * For a logged in user, get the username from security context
     *
     * @return logged in user
     */
    public String getUsernameFromContext() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (null == ctx) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "Cannot find SecurityContext");
        }
        Authentication auth = ctx.getAuthentication();
        if (null == auth) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "Cannot find Authentication in SecurityContext");
        }
        return ((ViewUserDetails) auth.getPrincipal()).getUsername();
    }

}
