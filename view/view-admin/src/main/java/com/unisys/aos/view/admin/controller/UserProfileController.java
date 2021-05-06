/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.controller;

import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.entity.SmmUser;
import com.unisys.aos.view.admin.service.JwtAuthService;
import com.unisys.aos.view.admin.service.SmmProfileService;
import com.unisys.aos.view.admin.service.SmmUserService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author LiuJ2
 * @since 2021/3/9 9:49
 */
@Slf4j
@RestController
@RequestMapping("/view/admin/permit-all/user-profile")
public class UserProfileController {
    private final SmmProfileService profileService;
    private final JwtAuthService authService;
    private final SmmUserService userService;

    @Autowired
    public UserProfileController(SmmProfileService profileService,
                                 JwtAuthService authService,
                                 SmmUserService userService) {
        this.profileService = profileService;
        this.authService = authService;
        this.userService = userService;
    }

    @PutMapping
    public Result<SmmProfile> updateProfile(@RequestBody SmmProfile profile) {
        log.info("updating user related profile...");
        try {
            if (!validAuthority(profile)) {
                return Result.failed(ResultCode.NOT_AUTHORIZED, "Cannot update profile not related to your user");
            }
            SmmProfile updatedProfile = profileService.updateProfile(profile);
            if (null == updatedProfile) {
                return Result.failed(ResultCode.INVALID_VERSION, "Profile must have been updated by other user.");
            } else {
                return Result.success(updatedProfile);
            }
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during update profile", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update profile. Please check admin log.");
        }
    }

    /***
     * Delete the profile specified by the user, but the user's default profile cannot be deleted.
     * @param profileId specified profile's id
     * @return result code
     */
    @DeleteMapping
    public Result<SmmProfile> deleteProfile(@RequestParam("profileId") Long profileId) {
        log.info("deleting the profile specified by the user...");
        if (null == profileId) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Profile is is null.");
        }
        try {
            String username = authService.getUsernameFromContext();
            SmmUser user = userService.findByUsername(username);
            if (null == user) {
                return Result.failed(ResultCode.OPERATION_FAILED, "Failed to delete profile, the user not existed.");
            }
            Long userId = user.getId();
            Boolean isDefaultProfile = profileService.isUserDefaultProfile(userId, profileId);
            if (isDefaultProfile) {
                return Result.failed(ResultCode.OPERATION_FAILED, "Failed to delete profile, because the profile is the user's default profile.");
            } else {
                Optional<SmmProfile> profileOptional = profileService.findById(profileId);
                if (!profileOptional.isPresent()) {
                    return Result.failed(ResultCode.OPERATION_FAILED, "Failed to delete profile, the profile with id = [" + profileId + "] not existed");
                }
                // Only allow users to delete their own profiles.
                boolean userProfileOwner = userService.isUserProfileOwner(user.getUsername(), profileOptional.get());
                if (!userProfileOwner) {
                    return Result.failed(ResultCode.OPERATION_FAILED, "Failed to delete profile, users are not allowed to delete profiles that do not belong to them.");
                }
                profileService.deleteById(profileId);
                return Result.success(null);
            }
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during deleting the profile specified by the user", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to delete profile. Please check admin log.");
        }
    }

    /***
     * add user profile according to the source profile
     * @param profile  user profile
     * @return user profile
     */
    @PostMapping
    public Result<SmmProfile> addProfileFromSourceProfile(@RequestBody SmmProfile profile) {
        if (null == profile) {
            log.error("Failed to add new profile, profile is null.");
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Failed to add the profile of user, parameter(profile) is null.");
        }
        // source profile id
        Long profileId = profile.getId();
        if (null == profileId) {
            log.error("Failed to add new profile, source profile id is null.");
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Failed to add the profile of user, parameter(profile id) is null.");
        }
        SmmProfile sourceProfile = profileService.getById(profileId);
        if (null == sourceProfile) {
            log.error("Failed to add new profile, source profile not existed.");
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to add the profile of user, source profile not existed.");
        }
        SmmProfile newProfile = new SmmProfile();
        newProfile.setName(profile.getName());
        newProfile.setDescription(profile.getDescription());
        try {
            String username = authService.getUsernameFromContext();
            SmmUser user = userService.findByUsername(username);
            if (null == user) {
                return Result.failed(ResultCode.OPERATION_FAILED, "Failed to add the profile of user, the user account not existed.");
            }
            newProfile = profileService.addUserProfileFromSourceProfile(user, newProfile, sourceProfile);
            if (null == newProfile) {
                return Result.failed(ResultCode.OPERATION_FAILED, "Failed to add the profile of user. Please check admin log.");
            }
            return Result.success(newProfile);
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during add the profile of user", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to add the profile of user. Please check admin log.");
        }
    }

    /**
     * check if the current user's is the profile's owner
     *
     * @param profile - profile to be updated
     * @return true if user has the authority, otherwise false.
     */
    private boolean validAuthority(SmmProfile profile) {
        String username = authService.getUsernameFromContext();
        return userService.isUserProfileOwner(username, profile);
    }

}
