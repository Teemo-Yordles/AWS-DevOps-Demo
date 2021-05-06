/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuJ2
 * @since 2021/1/20 9:04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "flight")
public class FlightProperties {
    /**
     * movement indicator code for arrival
     */
    private String movementIndicatorArrival = "A";
    /**
     * movement indicator code for departure
     */
    private String movementIndicatorDeparture = "D";
    /**
     * home airport iata code
     */
    private String homeAirport = "CTU";
}
