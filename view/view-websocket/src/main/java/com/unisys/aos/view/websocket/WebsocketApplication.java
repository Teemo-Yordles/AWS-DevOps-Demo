package com.unisys.aos.view.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
		"com.unisys.aos.view.websocket",
		"com.unisys.aos.view.common.cache",
		"com.unisys.aos.view.common"
})
public class WebsocketApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}
}


