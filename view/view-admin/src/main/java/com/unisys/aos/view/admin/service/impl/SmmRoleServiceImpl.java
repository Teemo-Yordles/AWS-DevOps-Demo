package com.unisys.aos.view.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.admin.entity.*;
import com.unisys.aos.view.admin.mapper.*;
import com.unisys.aos.view.admin.service.SmmParameterService;
import com.unisys.aos.view.admin.service.SmmProfileService;
import com.unisys.aos.view.admin.service.SmmRoleParameterService;
import com.unisys.aos.view.admin.service.SmmRoleService;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.exception.ApiException;
import com.unisys.aos.view.security.service.ViewUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * system role table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Service
public class SmmRoleServiceImpl extends ServiceImpl<SmmRoleMapper, SmmRole> implements SmmRoleService {
    private final SmmRoleMapper roleMapper;
    private final SmmRoleFunctionMapper roleFunctionMapper;
    private final SmmRoleParameterMapper roleParameterMapper;
    private final SmmUserMapper userMapper;
    private final ViewUserDetailsService userDetailsService;
    private final SmmProfileService profileService;
    private final SmmRoleParameterService roleParameterService;
    private final SmmParameterService parameterService;

    @Autowired
    public SmmRoleServiceImpl(SmmRoleMapper roleMapper,
                              SmmRoleFunctionMapper roleFunctionMapper,
                              SmmRoleParameterMapper roleParameterMapper,
                              SmmUserMapper userMapper,
                              ViewUserDetailsService userDetailsService,
                              SmmProfileService profileService,
                              SmmRoleParameterService roleParameterService,
                              SmmParameterService parameterService) {
        this.roleMapper = roleMapper;
        this.roleFunctionMapper = roleFunctionMapper;
        this.roleParameterMapper = roleParameterMapper;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.profileService = profileService;
        this.roleParameterService = roleParameterService;
        this.parameterService = parameterService;
    }

    @Override
    public List<SmmRole> findRoles() {
        List<SmmRole> roles = roleMapper.findRoles();
        if (null != roles) {
            for (SmmRole role : roles) {
                fillRoleDetail(role);
            }
        }
        return roles;
    }

    /**
     * find a role in by roleId.
     *
     * @return role
     */
    @Override
    public SmmRole findById(Long roleId) {
        SmmRole role = roleMapper.findRoleById(roleId);
        if (null != role) {
            fillRoleDetail(role);
        }
        return role;
    }

    /**
     * Fill role details with profile and role parameters
     * @param role - role object with basic information
     */
    private void fillRoleDetail(SmmRole role) {
        Optional<SmmProfile> profile = profileService.findById(role.getProfileId());
        role.setProfile(profile.orElseThrow(() -> new ApiException(ResultCode.OPERATION_FAILED, "profile with ID[" + role.getProfileId() + "] cannot be found")));
        List<SmmParameter> roleParams = roleParameterMapper.findParameterByRoleId(role.getId());
        role.setParameters(roleParams);
    }

    /**
     * Add new role
     * before calling this, you have to make sure the role doesn't exist
     *
     * @param newRole - new role
     * @return new role added to the db.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmmRole addRole(SmmRole newRole) {
        roleMapper.insert(newRole);
        Long newRoleId = newRole.getId();
        SmmProfile newProfile = profileService.initProfileForNewRole(newRoleId, newRole.getName());
        newRole.setProfileId(newProfile.getId());
        roleMapper.updateById(newRole);
        roleParameterService.insertRoleParametersFrom(1L, newRoleId);
        return findById(newRole.getId());
    }

    /**
     * if there are uses have role with specified id
     *
     * @param roleId - role id
     * @return true if there are some otherwise false
     */
    @Override
    public boolean hasAssociatedUser(Long roleId) {
        QueryWrapper<SmmUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        Integer count = userMapper.selectCount(wrapper);
        return count > 0;
    }

    /**
     * update a role
     * SmmRole table and SmmRoleFunction table have to be updated at the same time
     * so need transaction to ensure transaction atomic.
     *
     * @param role - role entity to be persisted to database
     * @return role updated with new values, null means optimistic locker check failed
     * which means role has been updated by some other user.
     */
    @Override
    @Transactional
    public SmmRole updateRole(SmmRole role) {
        // update role details
        // the role fields might not changed but still need to do update to update the update_time
        // field and version field so that optimistic lock could work.
        int updateCount = roleMapper.updateById(role);
        // optimistic locker check
        if (updateCount == 0) {
            return null;
        }

        SmmRole updatedRole = findById(role.getId());
        List<SmmFunction> currentFunctions = updatedRole.getFunctions();
        List<SmmParameter> currentParameters = updatedRole.getParameters();
        List<SmmFunction> newFunctions = role.getFunctions();
        List<SmmParameter> newParameters = role.getParameters();

        // update the functions of the role
        duplicateRemovalForFunctions(currentFunctions, newFunctions);
        updateRoleFunctions(role.getId(), currentFunctions, newFunctions);

        // update the functions of the role
        parameterService.updateParameters(currentParameters, newParameters);

        updatedRole = findById(role.getId());
        if (null != updatedRole) {
            // Update this role related users' "token - UserDetails" in cache
            List<String> usernames = userMapper.findUsernamesByRoleId(updatedRole.getId());
            if (null != usernames && !usernames.isEmpty()) {
                for (String username : usernames) {
                    userDetailsService.reloadUserDetails(username);
                }
            }
        }

        return updatedRole;
    }

    /**
     * Delete the removed function from role and add new functions assigned to the role
     * @param roleId - related role id
     * @param currentFunctions - removed functions
     * @param newFunctions - new functions assigned to the role
     */
    private void updateRoleFunctions(Long roleId, List<SmmFunction> currentFunctions, List<SmmFunction> newFunctions) {
        Map<String, Object> roleFunctionMap = new HashMap<>();
        roleFunctionMap.put("role_id", roleId);
        for (SmmFunction currentFunction : currentFunctions) {
            roleFunctionMap.put("function_id", currentFunction.getId());
            roleFunctionMapper.deleteByMap(roleFunctionMap);
        }

        for (SmmFunction newFunction : newFunctions) {
            roleFunctionMapper.insertIfFunctionExist(new SmmRoleFunction(roleId, newFunction.getId()));
        }
    }

    /**
     * Remove duplicate functions in the two function list
     * @param currentFunctions - current role related functions
     * @param newFunctions - new role related functions
     */
    private void duplicateRemovalForFunctions(List<SmmFunction> currentFunctions, List<SmmFunction> newFunctions) {
        //Sanity check
        if(currentFunctions == null ||
                newFunctions == null ||
                currentFunctions.isEmpty() ||
                newFunctions.isEmpty()) {
            return;
        }

        // create a map to accelerate the element search
        Map<Long, SmmFunction> currentMap = new HashMap<>();
        for (SmmFunction currentFunction : currentFunctions) {
            currentMap.put(currentFunction.getId(), currentFunction);
        }

        // duplicate removal
        Iterator<SmmFunction> it = newFunctions.iterator();
        while(it.hasNext()) {
            SmmFunction newFunction = it.next();
            if(currentMap.containsKey(newFunction.getId())) {
                currentFunctions.remove(currentMap.get(newFunction.getId()));
                it.remove();
            }
        }
    }

    /**
     * delete a role
     * SmmRole table, SmmRoleFunction table and SmmRoleFlightColumn table have to be updated at the same time
     * so need transaction to ensure transaction atomic.
     *
     * @param id - role id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteRole(Long id) {
        SmmRole role = roleMapper.findRoleById(id);
        if(null == role) {
            return false;
        }

        // delete role related profile
        // and then add new relation records if specified some
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("role_id", id);
        profileService.deleteById(role.getProfileId());

        // delete role related parameters
        roleParameterService.deleteByRoleId(id);

        // delete role
        boolean isDeleted = this.removeById(id);
        if (!isDeleted) {
            return false;
        }


        // delete all current role-function relationship
        // and then add new relation records if specified some
        roleFunctionMapper.deleteByMap(roleMap);
        // delete all current role-function relationship
        // and then add new relation records if specified some
//        roleParameterMapper.deleteByMap(roleMap);

        return true;
    }


}
