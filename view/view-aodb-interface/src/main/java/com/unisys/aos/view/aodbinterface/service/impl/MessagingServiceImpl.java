/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.service.impl;

import com.unisys.aos.view.aodbinterface.config.MessagingProperties;
import com.unisys.aos.view.aodbinterface.service.MessagingService;
import com.unisys.aos.view.viewentity.entity.aodbinterface.Cminmsgs;
import com.unisys.aos.view.viewentity.mapper.aodbinterface.CminmsgsMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.unisys.aos.view.viewentity.mapper.aodbinterface.CminmsgsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isNull;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 * @author LiuJ2
 * @since 2020/9/5 15:27
 */
@Service
@Slf4j
public class MessagingServiceImpl implements MessagingService {
    private final MessagingProperties messagingProperties;
    private final CminmsgsMapper cminmsgsMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessagingServiceImpl(MessagingProperties messagingProperties, CminmsgsMapper cminmsgsMapper, RabbitTemplate rabbitTemplate) {
        this.messagingProperties = messagingProperties;
        this.cminmsgsMapper = cminmsgsMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * This method is to get new messages in CMINMSGS table.
     * The amount of messages get in one go is configured in configuration file.
     *
     * @return List of Cminmsgs entities.
     */
    @Override
    public List<Cminmsgs> getNewMessages() {
        String STATUS_VALID = "VALID";
        return cminmsgsMapper.selectMany(
                select(cminmsgsId,
                        cminmsgsStatus,
                        cminmsgsClobMsg,
                        cminmsgsDateProcessed,
                        cminmsgsDateReceived,
                        cminmsgsSubsystemDateSent,
                        cminmsgsSubsystemName,
                        cminmsgsSubsystemSubtype,
                        cminmsgsSubsystemType,
                        cminmsgsSubsystemSequence)
                        .from(cminmsgs)
                        .where(cminmsgsDateProcessed, isNull())
                        .and(cminmsgsStatus, isEqualTo(STATUS_VALID))
                        .orderBy(cminmsgsDateReceived, cminmsgsId)
                        .limit(messagingProperties.getReceiverAmount())
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
    }

    /**
     * Mark the message with given ID in CMINMSGS as processed
     * by filling up the date processed column to current date/time.
     *
     * @param id - message id.
     */
    @Override
    public void markMessageAsProcessed(Long id) {
        Cminmsgs updatedMsg = new Cminmsgs();
        updatedMsg.setCminmsgsId(id);
        updatedMsg.setCminmsgsDateProcessed(new Date());
        cminmsgsMapper.updateByPrimaryKeySelective(updatedMsg);
    }

    /**
     * Send the message to MQ for following processing.
     * <p>
     * It might send AmqpException if RabbitMQ got some problem.
     *
     * @param msg - message to be sent
     */
    @Override
    public void sendForProcessing(String msg) {
        rabbitTemplate.convertAndSend(messagingProperties.getReceiverQueueName(), msg);
        log.info("AODB message is successfully sent to MQ");
    }
}
