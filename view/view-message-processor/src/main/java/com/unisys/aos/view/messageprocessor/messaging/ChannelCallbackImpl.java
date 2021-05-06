/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import com.unisys.aos.view.messageprocessor.factory.ProcessorFactory;
import com.unisys.aos.view.messageprocessor.service.Processor;
import com.unisys.aos.view.messageprocessor.util.MessagePeeker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Channel Callback implementation to process the message.
 *
 * @author LiuJ2
 * @since 2020/11/30 14:14
 */
@Slf4j
public class ChannelCallbackImpl implements ChannelCallback<Boolean> {
    private final String queueName;
    private final ProcessorFactory processorFactory;

    public ChannelCallbackImpl(String queueName, ProcessorFactory processorFactory) {
        this.queueName = queueName;
        this.processorFactory = processorFactory;
    }

    /**
     * Do the real processing work.
     * @param channel - Rabbitmq communication channel.
     * @return - Boolean.TRUE if message successfully processed or environment is OK.
     * Boolean.FALSE if environment is broken, need more time to recover.
     */
    @Override
    public Boolean doInRabbit(@NonNull Channel channel) {
        GetResponse response = null;
        String msg = "";
        while (true) {
            try {
                response = channel.basicGet(queueName, false);
                if (response == null) {
                    break;
                }

                msg = new String(response.getBody(), StandardCharsets.UTF_8);
                String msgType = MessagePeeker.getType(msg);

                if (null == msgType) {
                    log.warn("Cannot find message type. Message must be wrong [{}]", msg);
                } else {
                    Processor processor;
                    // for FLOP message, need to add subtype to determine the processor
                    // such as FLOP_ACTT or FLOP_PSDT etc.
                    // for other message, use TYPE to determine the processor
                    // such as ABDG or SCHD etc.
                    if ("FLOP".equalsIgnoreCase(msgType)) {
                        String msgSubType = MessagePeeker.getSubType(msg);
                        processor = processorFactory.getProcessor(msgType + "_" + msgSubType);
                    } else {
                        processor = processorFactory.getProcessor(msgType);
                    }
                    if (null != processor) {
                        // process the message
                        log.info("Find processor for message type [{}]", msgType);
                        processor.process(msg);
                    } else {
                        // unsupported message type
                        log.error("Cannot find processor for message type [{}]", msgType);
                        log.error("Consider to process this kind of message or not forwarding it anymore.");
                    }
                    // successfully processed, then ACK the message.
                    channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
                }
            } catch (DataIntegrityViolationException ex) {
                log.error("Failed to update to DB", ex);
                if (null != response && null != response.getEnvelope()) {
                    manualAck(response.getEnvelope().getDeliveryTag(), channel);
                }
                // return true since no environment issue with MQ.
                return Boolean.TRUE;
            } catch (PersistenceException ex) {
                log.error("Exception met during persistence. Message will be processed again", ex);
                if (null != response && null != response.getEnvelope()) {
                    manualNack(response.getEnvelope().getDeliveryTag(), channel);
                }
                return Boolean.FALSE;
            } catch (JsonProcessingException ex) {
                log.error("Failed to format the message to entity. MSG[" + msg + "]", ex);
                if (null != response && null != response.getEnvelope()) {
                    manualAck(response.getEnvelope().getDeliveryTag(), channel);
                }
                // return true since no environment issue, just msg format problem.
                return Boolean.TRUE;
            } catch (Throwable ex) {
                log.error("Unexpected exception met during processing. Message will be processed again", ex);
                if (null != response && null != response.getEnvelope()) {
                    manualNack(response.getEnvelope().getDeliveryTag(), channel);
                }
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Manually ACK a message received from MQ
     * @param tag - message to be ACKed
     * @param channel - channel used to ack the message
     */
    private void manualAck(long tag, Channel channel) {
        try {
            channel.basicAck(tag, false);
        } catch (IOException e) {
            log.error("Failed to ACK the message [" + tag + "]", e);
        }
    }

    /**
     * Manually NACK a message received from MQ, send it back to queue.
     *
     * @param tag     - message to be NACKed
     * @param channel - channel used to Nack the message
     */
    private void manualNack(long tag, Channel channel) {
        try {
            // batch=false, requeue=true
            channel.basicNack(tag, false, true);
        } catch (IOException ex) {
            log.error("Failed to NACK the message [" + tag + "]", ex);
        }
    }
}

