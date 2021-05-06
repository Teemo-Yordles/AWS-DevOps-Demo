/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.messageprocessor.MessageProcessorApplication;
import com.unisys.aos.view.viewentity.dao.RdmsVipServiceDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsVipService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jianglushuang
 * @since 2020/9/23 8:22 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageProcessorApplication.class)
public class RdmsVipServiceDAOImplTest {
    @Autowired
    private RdmsVipServiceDAO rdmsVipServiceDAO;

    @Test
    public void testGetMapEntryKey() {
        RdmsVipService record = new RdmsVipService();
        record.setId(Long.valueOf(1234567890L));
        assertThat("1234567890".equals(rdmsVipServiceDAO.getMapEntryKey(record))).isTrue();
    }
}
