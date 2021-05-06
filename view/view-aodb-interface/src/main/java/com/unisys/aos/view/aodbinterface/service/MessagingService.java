/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.service;

import com.unisys.aos.view.viewentity.entity.aodbinterface.Cminmsgs;

import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/9/5 15:22
 */
public interface MessagingService {
    /**
     * This method is to get new messages in CMINMSGS table.
     * The amount of messages get in one go is configured in configuration file.
     * @return
     */
    public List<Cminmsgs> getNewMessages();

    /**
     * Mark the message with given ID in CMINMSGS as processed.
     * @param id - message id.
     */
    public void markMessageAsProcessed(Long id);

    /**
     * Send the message to MQ for following processing.
     * @param msg - message to be sent
     */
    public void sendForProcessing(String msg);
}
