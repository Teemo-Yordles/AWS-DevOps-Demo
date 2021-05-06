/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.factory;

import com.unisys.aos.view.messageprocessor.service.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is a factory to produce processor implementation based on message type
 * specified.
 * Factory pattern and Strategy pattern are used.
 *
 * @author LiuJ2
 * @since 2020/9/7 11:01
 */
@Slf4j
@Service
public class ProcessorFactory {

    @Autowired
    private final Map<String, Processor> processorMap = new ConcurrentHashMap<>(30);

    /**
     * Get message processor based on message subtype.
     * If no matching processor found, return null. This shouldn't happen at all.
     * @param msgType - message type, could be ACFT, FLOP etc.
     * @return - a Processor implementation for specified message type.
     */
    public Processor getProcessor(String msgType) {
        Processor processor = processorMap.get(msgType);
        if(null == processor) {
           log.error("No message processor found for type[" + msgType + "]");
        }
        return processor;
    }
}
