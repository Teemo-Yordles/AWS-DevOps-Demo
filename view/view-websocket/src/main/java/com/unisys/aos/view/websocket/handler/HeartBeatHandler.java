/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.websocket.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * @author jianglushuang
 * @since 2020/12/4 12:10 下午
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static final ByteBuf HEARTBEAT = Unpooled.unreleasableBuffer(
            Unpooled.copiedBuffer("HEARTBEAT", CharsetUtil.ISO_8859_1));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            ctx.writeAndFlush(new TextWebSocketFrame(HEARTBEAT.duplicate()))
                    .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        }else{
            super.userEventTriggered(ctx,evt);
        }
    }
}
