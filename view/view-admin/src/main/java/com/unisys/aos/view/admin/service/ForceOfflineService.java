/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.unisys.aos.view.security.config.JwtConstants.USERNAME_REDIS_SET_KEY_PREFIX;

/**
 * @author jianglushuang
 * @since 2021/2/24 10:45 下午
 */
@Service
@Slf4j
public class ForceOfflineService {

    @Autowired
    SmmUserService userService;
    @Autowired
    RedisUtil redisUtil;

    public void forceOffline(Long id){
        List<String> usernames = userService.findUsernamesByRoleId(id);
        usernames.parallelStream().forEach( username -> {
            String usernameSetKey = USERNAME_REDIS_SET_KEY_PREFIX + username;
            Set<Object> tokens = redisUtil.smember(usernameSetKey);
            for (Object token : tokens){
                redisUtil.del((String)token);
            }
            redisUtil.del(usernameSetKey);
        });
    }
}
