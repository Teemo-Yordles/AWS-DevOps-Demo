/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LiuJ2
 * @since 2021/1/21 11:36
 */
@Data
@Component
@ConfigurationProperties(prefix = "messaging")
public class MessagingProperties {
    /**
     * Receiver wait interval when there is no message in CMINMSGS table.
     */
    private int receiverInterval = 5000;
    /**
     * how many messages received in one go.
     */
    private int receiverAmount = 20;
    /**
     * Queue name to put the message
     */
    private String receiverQueueName = "view.in.message.queue";
    /**
     * daily schedule request routing id
     */
    private String routingIdDailyScheduleRequest = "VIEWRQFD";
    /**
     * reference data request routing id
     */
    private String routingIdReferenceDataRequest = "VIEWRQRD";
}
