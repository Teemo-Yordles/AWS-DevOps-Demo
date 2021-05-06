package com.unisys.aos.view.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.unisys.aos.view")
@EnableDiscoveryClient
public class ViewAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViewAdminApplication.class, args);
    }

}
