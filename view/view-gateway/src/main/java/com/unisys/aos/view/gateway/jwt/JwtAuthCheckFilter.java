/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.gateway.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisys.aos.view.gateway.jwt.config.JwtProperties;
import com.unisys.aos.view.gateway.jwt.utils.JwtConstants;
import com.unisys.aos.view.gateway.jwt.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * Global filter to do JWT check
 *
 * @author LiuJ2
 * @since 2021/1/3 17:44
 */
@Configuration
@Slf4j
public class JwtAuthCheckFilter {
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * provide the global filter
     * @return global filter for jwt token check
     */
    @Bean
    @Order(-100)
    public GlobalFilter jwtAuthGlobalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            String requestUrl = serverHttpRequest.getURI().getPath();

            if (!requestUrl.equals(JwtConstants.CONTROLLER_LOGIN)
                && !requestUrl.equals(JwtConstants.CONTROLLER_TEST_LOGIN)) {
                // get JWT token from request header.
                String jwtToken = serverHttpRequest
                        .getHeaders()
                        .getFirst(jwtProperties.getHeader());
                // check token signature and if expired.
                boolean isJwtNotValid = false;
                if (!StringUtils.isEmpty(jwtToken)) {
                    jwtToken = jwtToken.replace(JwtConstants.JWT_HEADER_PREFIX, "").trim();
                    String testToken = jwtProperties.getTestToken();

                    // check for test-token and do special handling
                    if (!StringUtils.isEmpty(testToken)
                            && testToken.equals(jwtToken)) {
                        // if the token is test token, then use admin user.
                        ServerHttpRequest mutableReq = serverHttpRequest
                                .mutate()
                                .header(jwtProperties.getUsernameHeader(), "admin")
                                .build();
                        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
                        return chain.filter(mutableExchange);
                    }

                    isJwtNotValid = jwtTokenUtil.isTokenExpired(jwtToken);
                    if (isJwtNotValid && requestUrl.equals(JwtConstants.CONTROLLER_LOGOUT)) {
                        // if it is logout, even the token is expired, still allow user to logout.
                        isJwtNotValid = false;
                    }
                }
                if (isJwtNotValid) {
                    // return unauthorized if not valid
                    return writeUnAuthorizedMessageAsJson(serverHttpResponse);
                }
                //get username from token and forward the request
                ServerHttpRequest mutableReq = serverHttpRequest
                        .mutate()
                        .header(jwtProperties.getUsernameHeader(), jwtTokenUtil.getUsernameFromToken(jwtToken))
                        .build();
                ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
                return chain.filter(mutableExchange);
            } else {
                //if login, no need to check JWT token
                return chain.filter(exchange);
            }
        };
    }

    /**
     * Return authorized error code back to frontend
     *
     * @param serverHttpResponse http response
     * @return Mono
     */
    private Mono<Void> writeUnAuthorizedMessageAsJson(ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String errorMsg = "{\n" +
                "\"code\":\"A0301\",\n" +
                "\"message\":\"no authorization to access\",\n" +
                "\"data\": null\n" +
                "}";
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(errorMsg.getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

}
