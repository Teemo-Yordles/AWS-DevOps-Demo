/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author LiuJ2
 * @since 2020/9/6 16:30
 */
public interface Processor {
    String DEL = "DEL";
    String UPD = "UPD";
    String ADD = "ADD";
    String DNLD = "DNLD";
    String RESP = "RESP";

    /**
     * Process the message.
     * @param msg - Message to be processed
     */
    void process(String msg) throws JsonProcessingException;
}
