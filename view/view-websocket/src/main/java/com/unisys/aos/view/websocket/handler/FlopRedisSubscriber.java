/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.unisys.aos.view.common.cache.listener.RedisMessageListener;
import com.unisys.aos.view.common.cache.listener.RedisSubscriber;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author jianglushuang
 * @since 2020/11/26 6:22 下午
 */
@Service
@Slf4j
public class FlopRedisSubscriber implements RedisSubscriber {

    private final RedisMessageListener messageListener;

    public FlopRedisSubscriber(RedisMessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @PostConstruct
    public void init() {
        messageListener.addSubscriber(this);
    }


    @Override
    public void onMessage(Object message) {
        log.info("redis :" + message.toString());
        FlopChannelHandlerPool.channelGroup.writeAndFlush(
                new TextWebSocketFrame(JSON.toJSONString(message)));
    }
}
