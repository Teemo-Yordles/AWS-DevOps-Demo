/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.messaging;

import com.unisys.aos.view.aodbinterface.config.MessagingProperties;
import com.unisys.aos.view.aodbinterface.service.MessagingService;
import com.unisys.aos.view.viewentity.entity.aodbinterface.Cminmsgs;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/9/4 17:00
 */
@Component
@Slf4j
public class Receiver implements Runnable {
    private final static String RECEIVER_THREAD_NAME = "ReceiverThread";
    private final static long EXCEPTION_SLEEP_INTERVAL = 10000L;
    private final static String LOCK_NAME="aodbinterface-lock";
    @Autowired
    private MessagingProperties messagingProperties;
    @Resource
    private MessagingService messagingService;
    @Autowired
    private RedissonClient redissonClient;


    /**
     * start the receiver thread.
     */
    @PostConstruct
    public void init() {
        Thread receiverThread = new Thread(this);
        receiverThread.setName(RECEIVER_THREAD_NAME);
        receiverThread.start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            RLock lock = redissonClient.getLock(LOCK_NAME);
            try {
                // lock the message receiving
                lock.lock();

                List<Cminmsgs> msgList = messagingService.getNewMessages();
                log.info("Got {} messages by {}", (null == msgList) ? 0 : msgList.size(), this);
                if (null == msgList || msgList.isEmpty()) {
                    Thread.sleep(messagingProperties.getReceiverInterval());
                } else {
                    transmitMsgs(msgList);
                }
            } catch (Throwable ex) {
                log.error("Exception met during message getting from CMINMSGS", ex);
                try {
                    // Sleep for some time to let system have chance to recover.
                    Thread.sleep(EXCEPTION_SLEEP_INTERVAL);
                } catch (InterruptedException e) {
                    log.error("Exception met during exception sleeping", e);
                }
            } finally {
                // unlock the message receiving for other aodbinterface module
                try {
                    lock.unlock();
                } catch (Throwable e) {
                    log.error("Exception met during redisson unlock", e);
                }
            }
        }
    }

    /**
     * Transmit messages through MQ.
     * If a message is successfully sent, then mark this message with send time in CMINSMGS table.
     * If any exception met for a message, the subsequent messages will be ignored and they
     * will be processed in the next round.
     *
     * @param msgList - message list to be transmitted.
     */
    private void transmitMsgs(List<Cminmsgs> msgList) {
        // sanity check
        if(null == msgList || msgList.isEmpty()) {
            return;
        }

        // loop each message to send to MQ and mark as processed
        for (Cminmsgs message : msgList) {
            try {
                log.debug("Transmitting message ID:[{}]", message.getCminmsgsId());
                messagingService.sendForProcessing(message.getCminmsgsClobMsg());
                messagingService.markMessageAsProcessed(message.getCminmsgsId());
            } catch(AmqpException amqpException) {
                // if MQ got problem then it won't mark message as processed
                log.error("Failed to send AODB message to MQ.", amqpException);
                break;
            } catch(Exception ex) {
                log.error("Failed to transmit AODB message", ex);
                break;
            }
        }
    }
}
