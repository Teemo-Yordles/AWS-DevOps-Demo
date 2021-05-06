package com.unisys.aos.view.viewportal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.unisys.aos.view")
@EnableDiscoveryClient
@MapperScan("com.unisys.aos.view.viewentity.mapper")
public class ViewPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViewPortalApplication.class, args);
    }

}
