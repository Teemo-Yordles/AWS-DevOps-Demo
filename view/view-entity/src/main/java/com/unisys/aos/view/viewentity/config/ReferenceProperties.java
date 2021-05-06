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
 * @author Zhang Wenqiang
 * @since 2021/2/22 11:23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "reference")
public class ReferenceProperties {
    /***
     * default inspire time(UTC: 1970-01-01 00:00:00);
     */
    private Long defaultInspireTime = 0L;
    /***
     * default expire time(UTC: 2100-01-01 00:00:00);
     */
    private Long defaultExpireTime = 4102444800000L;
}
