package com.unisys.aos.view.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unisys.aos.view.admin.entity.SmmGroup;
import com.unisys.aos.view.admin.service.SmmGroupService;
import com.unisys.aos.view.common.api.Result;
import com.unisys.aos.view.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * group information table 前端控制器
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Slf4j
@RestController
@RequestMapping("/view/admin/groups")
public class GroupController {
    @Autowired
    SmmGroupService groupService;

    // query group list
    @GetMapping
    public Result<Collection<SmmGroup>> getGroups() {
        log.info("Get all groups...");
        List<SmmGroup> groupList;
        try {
            groupList = groupService.findGroups();
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during getting groups", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to get group list. Please check admin log.");
        }
        return Result.success(groupList);
    }

    /**
     * add new group
     * need to check if the group name is duplicate
     *
     * @param newGroup - new group to be added
     * @return - new group inserted into database
     */
    @PostMapping
    public Result<SmmGroup> add(@RequestBody SmmGroup newGroup) {
        log.info("add group...");
        // sanity check
        if (null == newGroup
                || StringUtils.isEmpty(newGroup.getName())) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Group name cannot be empty");
        }

        // check if user already exists
        QueryWrapper<SmmGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("name", newGroup.getName());
        int count = groupService.count(wrapper);
        if (count > 0) {
            return Result.failed(ResultCode.GROUP_EXISTED, "Group name already existed");
        }

        SmmGroup insertedGroup;
        try {
            insertedGroup = groupService.addGroup(newGroup);
        } catch (DuplicateKeyException ex) {
            // this only happens when two users adding groups with same name at the same time.
            return Result.failed(ResultCode.GROUP_EXISTED, "Group name already existed");
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during insert new group", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to insert new group. Please check admin log.");
        }
        return Result.success(insertedGroup);
    }

    /**
     * Delete group with ID
     *
     * @param id - group ID to be deleted
     * @return result code
     */
    @DeleteMapping("{id}")
    public Result<SmmGroup> deleteGroup(@PathVariable("id") Long id) {
        log.info("delete group...");
        // sanity check
        if (null == id) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Group id is empty");
        }

        groupService.removeById(id);
        return Result.success(null);
    }

    /**
     * update a group
     *
     * @param group - group to be updated
     * @return updated group entity
     */
    @PutMapping("{id}")
    public Result<SmmGroup> updateGroup(@PathVariable("id") Long id, @RequestBody SmmGroup group) {
        log.info("updating group...");
        try {
            SmmGroup updatedGroup = groupService.updateGroup(group);
            if (null == updatedGroup) {
                return Result.failed(ResultCode.INVALID_VERSION, "group must have been updated by other user.");
            } else {
                return Result.success(updatedGroup);
            }
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during update group", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update group. Please check admin log.");
        }
    }
}

