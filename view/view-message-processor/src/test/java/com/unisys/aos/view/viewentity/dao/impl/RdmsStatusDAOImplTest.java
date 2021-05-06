package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.messageprocessor.MessageProcessorApplication;
import com.unisys.aos.view.viewentity.dao.RdmsStatusDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author LiuJ2
 * @since 2020/9/14 14:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageProcessorApplication.class)
public class RdmsStatusDAOImplTest {
    @Autowired
    private RdmsStatusDAO rdmsStatusDAO;

    @Test
    public void testGetMapEntryKey() {
        RdmsStatus record = new RdmsStatus();
        record.setId(Long.valueOf(1234567890L));
        assertThat("1234567890".equals(rdmsStatusDAO.getMapEntryKey(record))).isTrue();
    }
}