package com.unisys.aos.view.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unisys.aos.view.admin.entity.SmmRole;
import com.unisys.aos.view.admin.service.SmmRoleService;
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
 * system role table 前端控制器
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Slf4j
@RestController
@RequestMapping(value = "/view/admin/roles")
public class RoleController {
    private final SmmRoleService roleService;

    @Autowired
    public RoleController(SmmRoleService roleService) {
        this.roleService = roleService;
    }

    // query role list
    @GetMapping
    public Result<Collection<SmmRole>> getRoles() {
        log.info("Get all roles...");
        List<SmmRole> roleList;
        try {
            roleList = roleService.findRoles();
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during getting roles", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to get role list. Please check admin log.");
        }
        return Result.success(roleList);
    }

    /**
     * get role with ID
     *
     * @param id - role ID to be retrieved
     * @return role with specified ID
     */
    @GetMapping("{id}")
    public Result<SmmRole> getById(@PathVariable("id") Long id) {
        log.info("get role by Id...");
        // sanity check
        if (null == id) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Role id is empty");
        }

        SmmRole role = roleService.findById(id);
        return Result.success(role);
    }


    /**
     * add new role
     * need to check if the role name is duplicate
     *
     * @param newRole - new role to be added
     * @return - new role inserted into database
     */
    @PostMapping
    public Result<SmmRole> add(@RequestBody SmmRole newRole) {
        log.info("add role...");
        // sanity check
        if (null == newRole
                || StringUtils.isEmpty(newRole.getName())
                || StringUtils.isEmpty(newRole.getDescription())) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Role name and description cannot be empty");
        }

        // check if user already exists
        QueryWrapper<SmmRole> wrapper = new QueryWrapper<>();
        wrapper.eq("name", newRole.getName());
        int count = roleService.count(wrapper);
        if (count > 0) {
            return Result.failed(ResultCode.ROLE_EXISTED, "Role name already existed");
        }

        SmmRole insertedRole;
        try {
            insertedRole = roleService.addRole(newRole);
        } catch (DuplicateKeyException ex) {
            // this only happens when two users adding roles with same name at the same time.
            return Result.failed(ResultCode.ROLE_EXISTED, "Role name already existed");
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during insert new role", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to insert new role. Please check admin log.");
        }
        return Result.success(insertedRole);
    }

    /**
     * Delete role with ID
     *
     * @param id - role ID to be deleted
     * @return result code
     */
    @DeleteMapping("{id}")
    public Result<SmmRole> deleteRole(@PathVariable("id") Long id) {
        log.info("delete role...");
        // sanity check
        if (null == id) {
            return Result.failed(ResultCode.REQUEST_FAILED_PARAMETER, "Role id is empty");
        }

        // check if role has associated user, if has, it is not allowed to be deleted.
        if (roleService.hasAssociatedUser(id)) {
            return Result.failed(ResultCode.ROLE_HAS_RELATED_USER, "Cannot be deleted since role has related users");
        }
        boolean isSuccessful = roleService.deleteRole(id);
        if (isSuccessful) {
            return Result.success(null);
        } else {
            return Result.failed(ResultCode.OPERATION_FAILED, "failed to delete role");
        }
    }

    /**
     * update a role
     * need to update role description and its function relationship
     *
     * @param role - role to be updated
     * @return updated role entity
     */
    @PutMapping
    public Result<SmmRole> updateRole(@RequestBody SmmRole role) {
        log.info("updating role...");
        try {
            SmmRole updatedRole = roleService.updateRole(role);
            if (null == updatedRole) {
                return Result.failed(ResultCode.INVALID_VERSION, "role must have been updated by other user.");
            } else {
                return Result.success(updatedRole);
            }
        } catch (Exception ex) {
            // unexpected error handling
            log.error("Unexpected error during update role", ex);
            return Result.failed(ResultCode.OPERATION_FAILED, "Failed to update role. Please check admin log.");
        }
    }

}
