/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.mapper;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ZhangWe2
 * @version 1.0
 * @program view-master
 * @package com.unisys.aos.view.admin.mapper
 * @className SmmUserMapperTest
 * @description SmmUserMapper test
 * @since 4/1/2021 3:28 PM
 */
@SpringBootTest
@FixMethodOrder
public class SmmUserMapperTest {
    @Autowired
    SmmUserMapper userMapper;

    @Test
    void isUserProfileOwnerTest() {
        String username = "admin";
        Long profileId = 1L;
        assertEquals(1, userMapper.isUserProfileOwner(username, profileId));
    }
}
