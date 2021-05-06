/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.security.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author LiuJ2
 * @since 2021/1/11 11:20
 */
@Service
@Primary
public interface ViewUserDetailsService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Get UserDetails from cache with token key
     *
     * @param token token string. Cannot be empty.
     * @return UserDetails - actually ViewUserDetails object
     */
    UserDetails loadUserByToken(String token);

    /**
     * reload user details in cache for user specified
     *
     * @param username - target user
     */
    void reloadUserDetails(String username);

    /**
     * Delete the set values if the value represented token has already expired.
     *
     * @param username - username
     * @return - numbers of deleted set value
     */
    int deleteExpiredTokenSetValue(String username);

    /**
     * reset the user details values if the value represented token.
     *
     * @param username - username
     * @return - numbers of updated set value
     */
    int resetUserDetailsCache(String username, UserDetails userDetails);
}
