/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Wenqiang
 * @since 2021/1/18 10:20
 */
@Slf4j
@Component
public class MessageSequenceMaintainUtil {
    private static final String MESSAGE_SEQUENCE_NAME = "request.data.sequence";
    private static final int SEQUENCE_SIZE = 6;
    private final StringRedisTemplate stringRedisTemplate;

    public MessageSequenceMaintainUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /***
     * Maintain a 6-digit id in redis, the id circulates between 1-999999.
     * @return Return id as the value of "SEQN" tag.
     */
    public String getSequenceFromRedis() {
        // Query whether the key exists, return 1 if it does not exist, and increase it by 1 if it exists
        Long autoId = stringRedisTemplate.opsForValue().increment(MESSAGE_SEQUENCE_NAME, 1);
        if (null == autoId || autoId > 999999L) {
            autoId = 0L;
            stringRedisTemplate.opsForValue().set(MESSAGE_SEQUENCE_NAME, String.valueOf(autoId));
        }

        // It is 6-digit id. If it is not enough, add 0 on the left, for example, "123" will become "000123"
        return StringUtils.leftPad(String.valueOf(autoId), SEQUENCE_SIZE, "0");
    }
}
