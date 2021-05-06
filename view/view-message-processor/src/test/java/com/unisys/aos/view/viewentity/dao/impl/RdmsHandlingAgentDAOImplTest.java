package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.messageprocessor.MessageProcessorApplication;
import com.unisys.aos.view.viewentity.dao.RdmsHandlingAgentDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsHandlingAgent;
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
public class RdmsHandlingAgentDAOImplTest {
    @Autowired
    private RdmsHandlingAgentDAO rdmsHandlingAgentDAO;

    @Test
    public void testGetMapEntryKey() {
        RdmsHandlingAgent record = new RdmsHandlingAgent();
        record.setId(Long.valueOf(1234567890L));
        assertThat("1234567890".equals(rdmsHandlingAgentDAO.getMapEntryKey(record))).isTrue();
    }
}