/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.service.impl;

import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.service.JwtAuthService;
import com.unisys.aos.view.admin.service.SmmProfileService;
import com.unisys.aos.view.admin.service.UserSettingService;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiuJ2
 * @since 2021/3/5 12:16
 */
@Slf4j
@Service
public class UserSettingServiceImpl implements UserSettingService {
    private final SmmProfileService profileService;
    private final JwtAuthService authService;

    @Autowired
    public UserSettingServiceImpl(SmmProfileService profileService,
                                  JwtAuthService authService) {
        this.profileService = profileService;
        this.authService = authService;
    }

    /**
     * get user's role related all profile from the users under this role
     *
     * @return profile list
     */
    @Override
    public List<SmmProfile> getProfiles() {
        String username = authService.getUsernameFromContext();
        // sanity check
        if (null == username) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "Cannot find username in SecurityContext");
        }
        log.info("get user[{}]'s role related all profile from the users under this role....", username);

        List<SmmProfile> profiles = profileService.findRoleRelatedProfilesByUsername(username);
        if (profiles.isEmpty()) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "Cannot find any profiles");
        }
        return profiles;
    }

}
