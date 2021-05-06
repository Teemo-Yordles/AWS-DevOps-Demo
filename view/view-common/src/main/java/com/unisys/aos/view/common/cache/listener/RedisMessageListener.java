/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.cache.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jianglushuang
 * @since 2020/11/26 12:53 下午
 */
@Primary
@Component
@Slf4j
public class RedisMessageListener implements MessageListener {

    private List<RedisSubscriber> subscribers = new LinkedList<>();

    RedisTemplate redisTemplate;

    @Autowired
    private RedisMessageListener(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        for(RedisSubscriber subscriber:subscribers){
            try {
                RedisSerializer<?> serializer = redisTemplate.getValueSerializer();
                Object sendMsg = serializer.deserialize(message.getBody());

                subscriber.onMessage(sendMsg);
            }catch (SerializationException ex){
                log.error("serialize message failed", ex);
            }catch (Exception e){
                log.error("subscriber message failed " ,e.getMessage());
            }
        }

    }

    public synchronized void addSubscriber(RedisSubscriber subscriber){
        subscribers.add(subscriber);
    }
}
