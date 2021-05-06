package com.unisys.aos.view.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unisys.aos.view.admin.entity.SmmUser;
import com.unisys.aos.view.admin.service.SmmUserService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
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
@RequestMapping("/view/admin/users")
public class UserController {
    private final SmmUserService userService;

    @Autowired
    public UserController(SmmUserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public Result<SmmUser> getById(@PathVariable("id") Long id) {
        log.info("get user by Id...");
        // sanity check
        if (null == id) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "User id is empty");
        }

        SmmUser user = userService.findById(id);
        return Result.success(user);
    }


    @GetMapping
    public Result<List<SmmUser>> findAll() {
        log.info("get all users ...");
        try {
            List<SmmUser> users = userService.findAll();
            return Result.success(users);
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during getting users", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to get user list. Please check admin log.");
        }
    }

    /**
     * add new user
     * need to check if the user name is duplicate
     *
     * @param newUser - new user to be added
     * @return - new user inserted into database
     */
    @PostMapping
    public Result<SmmUser> add(@RequestBody SmmUser newUser) {
        log.info("add user...");
        // sanity check
        if (null == newUser
                || StringUtils.isEmpty(newUser.getUsername())
                || StringUtils.isEmpty(newUser.getName())
                || StringUtils.isEmpty(newUser.getPassword())) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "User property cannot be empty");
        }

        // check if user already exists
        QueryWrapper<SmmUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", newUser.getUsername());
        int count = userService.count(wrapper);
        if (count > 0) {
            return Result.failed(ResultCode.USER_EXISTED, "User name already existed");
        }

        SmmUser insertedUser;
        try {
            insertedUser = userService.addUser(newUser);
        } catch (DuplicateKeyException ex) {
            // this only happens when two users adding users with same name at the same time.
            return Result.failed(ResultCode.USER_EXISTED, "User name already existed");
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during insert new user", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to insert new user. Please check admin log.");
        }
        return Result.success(insertedUser);
    }

    /**
     * Delete user with ID
     *
     * @param id - user ID to be deleted
     * @return result code
     */
    @DeleteMapping("{id}")
    public Result<SmmUser> deleteUser(@PathVariable("id") Long id) {
        log.info("delete user...");
        // sanity check
        if (null == id) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "User id is empty");
        }

        userService.removeById(id);
        return Result.success(null);
    }

    /**
     * update a user
     *
     * @param user - user to be updated
     * @return updated user entity
     */
    @PutMapping
    public Result<SmmUser> updateUser(@RequestBody SmmUser user) {
        log.info("updating user...");
        try {
            SmmUser updatedUser = userService.updateUser(user);
            if (null == updatedUser) {
                return Result.failed(ResultCode.INVALID_VERSION, "user must have been updated by other user.");
            } else {
                return Result.success(updatedUser);
            }
        } catch (ApiException ex) {
            return Result.failed(ResultCode.INVALID_VERSION, "user must have been updated by other user.");
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during update user", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update user. Please check admin log.");
        }
    }
}

