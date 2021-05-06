package com.unisys.aos.view.viewentity.dao.impl;

import com.unisys.aos.view.messageprocessor.MessageProcessorApplication;
import com.unisys.aos.view.viewentity.dao.RdmsRegionDAO;
import com.unisys.aos.view.viewentity.entity.reference.RdmsRegion;
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
public class RdmsRegionDAOImplTest {
    @Autowired
    private RdmsRegionDAO rdmsRegionDAO;

    @Test
    public void testGetMapEntryKey() {
        RdmsRegion record = new RdmsRegion();
        record.setId(Long.valueOf(1234567890L));
        assertThat("1234567890".equals(rdmsRegionDAO.getMapEntryKey(record))).isTrue();
    }
}