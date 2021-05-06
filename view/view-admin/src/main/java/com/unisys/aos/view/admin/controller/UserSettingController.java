package com.unisys.aos.view.admin.controller;

import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.entity.SmmUser;
import com.unisys.aos.view.admin.service.JwtAuthService;
import com.unisys.aos.view.admin.service.SmmUserService;
import com.unisys.aos.view.admin.service.UserSettingService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * user information table 前端控制器
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Slf4j
@RestController
@RequestMapping("/view/admin/permit-all/user-setting")
public class UserSettingController {
    private final UserSettingService userSettingService;
    private final JwtAuthService authService;
    private final SmmUserService userService;

    @Autowired
    public UserSettingController(UserSettingService userSettingService, SmmUserService userService, JwtAuthService authService) {
        this.userSettingService = userSettingService;
        this.userService = userService;
        this.authService = authService;
    }

    /***
     * Get current user.
     * @return current user.
     */
    @GetMapping("/user")
    public Result<SmmUser> getUser() {
        try {
            String username = authService.getUsernameFromContext();
            SmmUser user = userService.findByUsername(username);
            if (null != user) {
                return Result.success(user);
            } else {
                return Result.failed(ResultCode.OPERATION_FAILED, "Failed to get user.");
            }
        } catch (Exception ex) {
            log.error("Unexpected error during get user", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to get user. Please check admin log.");
        }

    }

    @GetMapping("/profiles")
    public Result<List<SmmProfile>> getProfiles() {
        try {
            return Result.success(userSettingService.getProfiles());
        } catch (Exception ex) {
            log.error("Unexpected error during get profiles", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to get profiles. Please check admin log.");
        }
    }

    /***
     * Update user's current profile.
     * @param profileId user's current profile id.
     * @return updated user entity.
     */
    @PutMapping
    public Result<SmmUser> updateUserCurrentProfile(@RequestBody Long profileId) {
        log.info("updating default profile of user...");
        if (null == profileId) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Failed to update user's current profile, the profile id is null.");
        }
        try {
            String username = authService.getUsernameFromContext();
            SmmUser user = userService.findByUsername(username);
            if (null == user) {
                return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update user's current profile, the user not existed.");
            }
            user.setProfileId(profileId);
            SmmUser updatedUser = userService.updateUser(user);
            if (null == updatedUser) {
                return Result.failed(ResultCode.INVALID_VERSION, "Failed to update user's current profile, user must have been updated by other user.");
            } else {
                return Result.success(updatedUser);
            }
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during update user", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update user's current profile. Please check admin log.");
        }
    }
}

