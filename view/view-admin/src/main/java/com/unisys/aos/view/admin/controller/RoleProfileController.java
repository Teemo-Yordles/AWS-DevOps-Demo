/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.controller;

import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.service.JwtAuthService;
import com.unisys.aos.view.admin.service.SmmProfileService;
import com.unisys.aos.view.admin.service.SmmUserService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuJ2
 * @since 2021/3/9 9:51
 */
@Slf4j
@RestController
@RequestMapping("/view/admin/permit-all/role-profile")
public class RoleProfileController {
    private final SmmProfileService profileService;
    private final JwtAuthService authService;
    private final SmmUserService userService;

    @Autowired
    public RoleProfileController(SmmProfileService profileService,
                                 JwtAuthService authService,
                                 SmmUserService userService) {
        this.profileService = profileService;
        this.authService = authService;
        this.userService = userService;
    }

    @PutMapping
    public Result<SmmProfile> updateProfile(@RequestBody SmmProfile profile) {
        log.info("updating role related profile...");
        try {
            if (!validAuthority(profile)) {
                return Result.failed(ResultCode.NOT_AUTHORIZED, "Cannot update profile not related to your role");
            }
            SmmProfile updatedProfile = profileService.updateRoleProfile(profile);
            if (null == updatedProfile) {
                return Result.failed(ResultCode.INVALID_VERSION, "profile must have been updated by other user.");
            } else {
                return Result.success(updatedProfile);
            }
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during update profile", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update profile. Please check admin log.");
        }
    }

    /**
     * check if the current user's role equals to the profile's role
     *
     * @param profile - profile to be updated
     * @return true if user has the authority, otherwise false.
     */
    private boolean validAuthority(SmmProfile profile) {
        return profileService.isRoleProfile(profile);
    }

}
