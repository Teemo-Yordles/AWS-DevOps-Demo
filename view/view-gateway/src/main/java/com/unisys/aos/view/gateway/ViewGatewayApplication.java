/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * This is the application entry for gateway module
 *
 * @author LiuJ2
 * @since 2020/11/23 17:14
 */
@SpringBootApplication(scanBasePackages = "com.unisys.aos.view")
@EnableDiscoveryClient
public class ViewGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ViewGatewayApplication.class, args);
    }
}
