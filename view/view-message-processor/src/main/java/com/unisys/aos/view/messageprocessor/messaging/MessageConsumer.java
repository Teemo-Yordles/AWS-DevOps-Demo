/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.messaging;

import com.unisys.aos.view.messageprocessor.factory.ProcessorFactory;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * This is the bean to consume the XML messages
 * sent to RabbitMQ from aodbinterface micro-service.
 *
 * @author LiuJ2
 * @since 2020/9/7 17:36
 */
@Service
@Slf4j
public class MessageConsumer implements Runnable {
    private final static String LOCK_NAME="message-processor-lock";
    private final static String RECEIVER_THREAD_NAME = "MsgProcThread";
    private final ProcessorFactory processorFactory;
    private final RabbitTemplate rabbitTemplate;
    private final RedissonClient redissonClient;
    @Value("${messageprocessor.messaging.consumer.queue-name}")
    private String msgQueueName;
    @Value("${messageprocessor.wait-duration.empty-queue}")
    private long receiveInterval;
    @Value("${messageprocessor.wait-duration.failed}")
    private long waitForFailed;

    @Autowired
    public MessageConsumer(ProcessorFactory processorFactory, RabbitTemplate rabbitTemplate, RedissonClient redissonClient) {
        this.processorFactory = processorFactory;
        this.rabbitTemplate = rabbitTemplate;
        this.redissonClient = redissonClient;
    }

    @PostConstruct
    public void init() {
        log.info("got queue name from config: [{}]", msgQueueName);
        log.info("got loop interval from config: [{}]", receiveInterval);
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
                if (processQueue(msgQueueName)) {
                    Thread.sleep(receiveInterval);
                } else {
                    Thread.sleep(waitForFailed);
                }
            } catch (Throwable e) {
                log.error("Unhandled exception met", e);
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
     * Process all the messages in queue
     * @param queueName - queue name
     * @return - true if successfully processed all messages in queue
     *           false if exception happens.
     */
    private Boolean processQueue(String queueName) {
        return rabbitTemplate.execute(new ChannelCallbackImpl(queueName, processorFactory));
    }
}
