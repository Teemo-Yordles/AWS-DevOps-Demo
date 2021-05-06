/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiuJ2
 * @since 2020/9/4 16:48
 */
@SpringBootApplication(scanBasePackages = "com.unisys.aos.view")
@MapperScan("com.unisys.aos.view.viewentity.mapper")
@EnableDiscoveryClient
public class AodbInterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AodbInterfaceApplication.class, args);
    }
}
