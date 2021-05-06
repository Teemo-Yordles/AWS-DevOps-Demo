package com.unisys.aos.view.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.admin.entity.*;
import com.unisys.aos.view.admin.mapper.SmmProfileColumnSettingMapper;
import com.unisys.aos.view.admin.mapper.SmmProfileMapper;
import com.unisys.aos.view.admin.mapper.SmmProfileParameterMapper;
import com.unisys.aos.view.admin.mapper.SmmUserProfileMapper;
import com.unisys.aos.view.admin.service.*;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * <p>
 * system profile table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Service
public class SmmProfileServiceImpl extends ServiceImpl<SmmProfileMapper, SmmProfile> implements SmmProfileService {
    static final String ROLE_SUFFIX = "role:";
    static final String USER_SUFFIX = "user:";
    static final String DEFAULT_PROFILE_NAME = "default_profile";
    static final String DELIMITER = "/";
    static final String ROLE_DEFAULT_DESCRIPTION = " role profile";
    static final String USER_DEFAULT_DESCRIPTION = " user profile";
    static final Long TEMPLATE_PROFILE_ID = 1L;
    private final SmmProfileMapper profileMapper;
    private final SmmProfileColumnSettingMapper profileColumnSettingMapper;
    private final SmmProfileParameterMapper profileParameterMapper;
    private final SmmProfileParameterService profileParameterService;
    private final SmmParameterService parameterService;
    private final SmmProfileColumnSettingService profileColumnSettingService;
    private final SmmUserProfileMapper userProfileMapper;
    private final SmmUserService userService;

    @Autowired
    public SmmProfileServiceImpl(SmmProfileMapper profileMapper,
                                 SmmProfileColumnSettingMapper profileColumnSettingMapper,
                                 SmmProfileParameterMapper profileParameterMapper,
                                 SmmProfileParameterService profileParameterService,
                                 SmmParameterService parameterService,
                                 SmmProfileColumnSettingService profileColumnSettingService,
                                 SmmUserProfileMapper userProfileMapper,
                                 SmmUserService userService) {
        this.profileMapper = profileMapper;
        this.profileColumnSettingMapper = profileColumnSettingMapper;
        this.profileParameterMapper = profileParameterMapper;
        this.profileParameterService = profileParameterService;
        this.parameterService = parameterService;
        this.profileColumnSettingService = profileColumnSettingService;
        this.userProfileMapper = userProfileMapper;
        this.userService = userService;
    }


    /**
     * find a profile in by profileId.
     *
     * @param id profile id
     * @return profile with internal column settings and parameters
     */
    @Override
    public Optional<SmmProfile> findById(@NonNull Long id) {
        // get basic profile information
        SmmProfile profile = profileMapper.findById(id);
        if (null != profile) {
            fillProfileDetail(profile);
        }
        return Optional.ofNullable(profile);
    }

    /**
     * find user's role related users' profiles plus role profile
     *
     * @param username - user name
     * @return profile list
     */
    @Override
    public List<SmmProfile> findRoleRelatedProfilesByUsername(@NonNull String username) {
        List<SmmProfile> profiles = profileMapper.findRoleRelatedProfilesByUsername(username);
        if (profiles.isEmpty()) {
            return new ArrayList<>();
        } else {
            for (SmmProfile profile : profiles) {
                fillProfileDetail(profile);
            }
            return profiles;
        }
    }

    /**
     * initialize a new profile for the newly created role
     *
     * @param roleId   - new role id
     * @param roleName - new role name
     * @return initialized profile
     */
    @Override
    @Transactional(propagation = REQUIRED)
    public SmmProfile initProfileForNewRole(Long roleId, String roleName) {
        // insert new profile
        SmmProfile profile = new SmmProfile();
        profile.setName(ROLE_SUFFIX + roleName + DELIMITER + DEFAULT_PROFILE_NAME);
        profile.setDescription(roleName + ROLE_DEFAULT_DESCRIPTION);
        profile.setRoleId(roleId);
        profileMapper.insert(profile);

        // insert initial column settings
        Long newProfileId = profile.getId();
        profileMapper.initColumnSettingsFromProfile(TEMPLATE_PROFILE_ID, newProfileId);

        // insert initial profile parameters
        profileParameterService.insertProfileParametersFrom(TEMPLATE_PROFILE_ID, newProfileId);

        profile = profileMapper.findById(newProfileId);
        return profile;
    }

    /**
     * initialize a new profile for the newly created user
     *
     * @param user - newly added user
     * @return initialized profile
     */
    @Override
    @Transactional(propagation = REQUIRED)
    public SmmProfile initProfileForNewUser(SmmUser user) {
        // insert new profile
        SmmProfile profile = new SmmProfile();
        profile.setName(USER_SUFFIX + user.getUsername() + DELIMITER + DEFAULT_PROFILE_NAME);
        profile.setDescription(user.getUsername() + USER_DEFAULT_DESCRIPTION);
        profile.setRoleId(user.getRoleId());
        profileMapper.insert(profile);

        // insert initial column settings
        Long newProfileId = profile.getId();
        Long roleProfileId = profileMapper.getProfileIdForRole(user.getRoleId());
        profileMapper.initColumnSettingsFromProfile(roleProfileId, newProfileId);

        // insert initial profile parameters
        profileParameterService.insertProfileParametersFrom(TEMPLATE_PROFILE_ID, newProfileId);

        profile = profileMapper.findById(newProfileId);
        SmmUserProfile userProfileRelation = new SmmUserProfile();
        userProfileRelation.setProfileId(newProfileId);
        userProfileRelation.setUserId(user.getId());
        userProfileMapper.insert(userProfileRelation);
        return profile;
    }

    /**
     * delete profile by profile id
     *
     * @param profileId - profile Id
     * @return - deleted profile's id
     */
    @Override
    @Transactional(propagation = REQUIRED)
    public Long deleteById(Long profileId) {
        int deleteCount = profileMapper.deleteById(profileId);
        if (0 == deleteCount) {
            throw new ApiException(ResultCode.INVALID_VERSION, "Profile must have been deleted by other user.");
        }
        profileColumnSettingMapper.deleteByProfileId(profileId);
        profileParameterService.deleteByProfileId(profileId);
        userProfileMapper.deleteByProfileId(profileId);
        // If the profile used by the user is deleted,
        // change the profile used by the user to its default profile.
        setUsedProfileToDefaultProfile(profileId);
        return profileId;
    }


    /***
     * Change the profile used by the user to its default profile by profile id.
     * @param profileId profile id
     */
    private void setUsedProfileToDefaultProfile(Long profileId) {
        List<SmmUser> userList = userService.findByProfileId(profileId);
        if (null != userList && !userList.isEmpty()) {
            userList.forEach(user -> {
                List<Long> profileIdList = userProfileMapper.findProfileIdListByUserId(user.getId());
                if (null != profileIdList && !profileIdList.isEmpty()) {
                    user.setProfileId(profileIdList.get(0));
                    SmmUser updatedUser = userService.updateUser(user);
                    if (null == updatedUser) {
                        throw new ApiException(ResultCode.INVALID_VERSION, "user must have been updated by other user.");
                    }
                }
            });
        }
    }

    /**
     * Update the profile and underlying column settings and parameters.
     *
     * @param profile - profile to be updated.
     * @return updated profile
     */
    @Override
    @Transactional(propagation = REQUIRED)
    public SmmProfile updateProfile(SmmProfile profile) {
        // update the basic profile object
        int updatedCount = profileMapper.updateById(profile);
        if (updatedCount == 0) {
            throw new ApiException(ResultCode.INVALID_VERSION, "profile must have been updated by other user.");
        }

        // update profile column settings and parameters
        Optional<SmmProfile> profileOptional = findById(profile.getId());
        SmmProfile updatedProfile = profileOptional.orElseThrow(() -> new ApiException(ResultCode.OPERATION_FAILED, "cannot find profile with id[" + profile.getId() + "]"));
        List<SmmProfileColumnSetting> currentColumnSettings = updatedProfile.getColumnSettings();
        List<SmmParameter> currentParameters = updatedProfile.getParameters();
        List<SmmProfileColumnSetting> newColumnSettings = profile.getColumnSettings();
        List<SmmParameter> newParameters = profile.getParameters();
        parameterService.updateParameters(currentParameters, newParameters);
        profileColumnSettingService.updateProfileColumnSetting(currentColumnSettings, newColumnSettings);

        profileOptional = findById(profile.getId());
        return profileOptional.orElseThrow(() -> new ApiException(ResultCode.OPERATION_FAILED, "cannot find profile with id[" + profile.getId() + "]"));
    }

    /***
     * Update the profile and underlying column settings and parameters of role.
     * @param profile profile to be updated
     * @return updated profile
     */
    @Override
    public SmmProfile updateRoleProfile(SmmProfile profile) {
        List<SmmUser> userList = userService.findUserListByRoleId(profile.getRoleId());
        if (null != userList && !userList.isEmpty()) {
            userList.forEach(userService::updateUserColumnAuthorizedByRole);
        }
        return updateProfile(profile);
    }


    /**
     * Find user's current selected profile
     *
     * @param username - username
     * @return user's current selected profile
     */
    @Override
    public SmmProfile findSelectedProfileByUsername(String username) {
        SmmUser user = userService.findByUsername(username);
        if (null == user) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "Cannot find user with username[" + username + "]");
        }
        Optional<SmmProfile> profile = findById(user.getProfileId());
        return profile.orElseThrow(() -> new ApiException(ResultCode.OPERATION_FAILED, "Cannot find profile with username[" + username + "]"));
    }

    /***
     * Find related profiles by user id
     * @param userId - specified user id
     * @return related profiles of user
     */
    @Override
    public List<SmmProfile> findByUserId(Long userId) {
        if (null == userId) {
            log.error("Find failed because the provided user id is null.");
            return null;
        }
        List<Long> profileIdList = userProfileMapper.findProfileIdListByUserId(userId);
        if (null == profileIdList || profileIdList.isEmpty()) {
            return null;
        }
        List<SmmProfile> profileList = new ArrayList<>();
        for (Long profileId : profileIdList) {
            SmmProfile profile = profileMapper.findById(profileId);
            if (null != profile) {
                fillProfileDetail(profile);
            }
            profileList.add(profile);
        }
        return profileList;
    }

    /***
     * Find profile by role id
     * @param roleId specified role's id
     * @return role's profile
     */
    @Override
    public SmmProfile findByRoleId(Long roleId) {
        if (null == roleId) {
            return null;
        }
        Long profileId = profileMapper.getProfileIdForRole(roleId);
        if (null == profileId) {
            return null;
        }
        SmmProfile profile = profileMapper.findById(profileId);
        if (null != profile) {
            fillProfileDetail(profile);
        }
        return profile;
    }

    /**
     * check if the profile is a role related profile
     *
     * @param profile - profile to be checked
     * @return true
     */
    @Override
    public boolean isRoleProfile(SmmProfile profile) {
        return (profileMapper.isRoleProfile(profile.getId()) > 0);
    }

    /**
     * Delete all profiles belong to the user, also the user-profile relationship
     * need to be deleted.
     *
     * @param id user id
     */
    @Override
    @Transactional(propagation = REQUIRED)
    public void deleteByUserId(Long id) {
        log.debug("start deleting user related profile.");
        QueryWrapper<SmmUserProfile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        List<SmmUserProfile> userProfileRelations = userProfileMapper.selectList(queryWrapper);
        if (null == userProfileRelations) {
            return;
        }
        for (SmmUserProfile userProfileRelation : userProfileRelations) {
            this.deleteById(userProfileRelation.getProfileId());
        }
        userProfileMapper.delete(queryWrapper);
        log.debug("finished deleting user related profile and user/profile relationship.");
    }

    /***
     * check if the profile is the user's default profile.
     * @param userId specified user's id
     * @param profileId specified profile's id
     * @return true - the profile is the user's default profile,
     *         false - the profile is not the user's default profile,
     *         null - the user's id or the profile's id is null.
     */
    @Override
    public Boolean isUserDefaultProfile(Long userId, Long profileId) {
        if (null == userId) {
            log.error("Check failed because the provided user id is null.");
            return null;
        }
        if (null == profileId) {
            log.error("Check failed because the provided profile id is null.");
            return null;
        }
        List<Long> profileIdList = userProfileMapper.findProfileIdListByUserId(userId);
        if (null != profileIdList && !profileIdList.isEmpty()) {
            Long defaultUserProfileId = profileIdList.get(0);
            return defaultUserProfileId.equals(profileId);
        }
        return false;
    }

    /***
     * Add a new profile and initialize column settings and parameters for the specified profile
     * @param newProfile new profile
     * @param sourceProfile specified profile
     * @return new profile
     */
    @Override
    @Transactional(propagation = REQUIRED)
    public SmmProfile addProfileFromSourceProfile(SmmProfile newProfile, SmmProfile sourceProfile) {
        newProfile.setRoleId(sourceProfile.getRoleId());
        int insertCount = profileMapper.addProfile(newProfile);
        if (0 != insertCount) {
            SmmProfile addProfile = profileMapper.findByName(newProfile.getName());
            profileMapper.initColumnSettingsFromProfile(sourceProfile.getId(), addProfile.getId());
            profileMapper.initParametersFromProfile(sourceProfile.getId(), addProfile.getId());
            addProfile.setColumnSettings(profileColumnSettingMapper.findColumnSettingByProfileId(sourceProfile.getId()));
            addProfile.setParameters(profileParameterMapper.findParameterByProfileId(sourceProfile.getId()));
            return addProfile;
        } else {
            log.error("Failed to add user profile.");
            return null;
        }
    }

    /***
     * Add a new user profile according to the specified profile
     * @param user current user
     * @param newProfile new profile
     * @param sourceProfile specified profile
     * @return new profile
     */
    @Override
    @Transactional(propagation = REQUIRED)
    public SmmProfile addUserProfileFromSourceProfile(SmmUser user, SmmProfile newProfile, SmmProfile sourceProfile) {
        if (null == user) {
            log.error("Failed to add user profile, the user is null.");
            return null;
        }
        if (null == newProfile) {
            log.error("Failed to add user profile, the new profile is null.");
            return null;
        }
        if (null == sourceProfile) {
            log.error("Failed to add user profile, the source profile is null.");
            return null;
        }
        Long userId = user.getId();
        String newProfileName = USER_SUFFIX + user.getName() + "/" + newProfile.getName();
        newProfile.setName(newProfileName);
        SmmProfile profile = addProfileFromSourceProfile(newProfile, sourceProfile);
        if (null != profile) {
            int count = userProfileMapper.addUserProfile(userId, profile.getId());
            if (0 == count) {
                log.error("Failed to add user profile association to table.");
            }
        }
        return profile;
    }

    /**
     * Fill profile details with column settings and profile parameters
     *
     * @param profile - profile object with basic information
     */
    private void fillProfileDetail(SmmProfile profile) {
        // find profile related column setting
        List<SmmProfileColumnSetting> columnSettings = profileColumnSettingMapper.findColumnSettingByProfileId(profile.getId());
        if (null == columnSettings) {
            columnSettings = new ArrayList<>();
        }
        profile.setColumnSettings(columnSettings);

        // find profile related parameters
        List<SmmParameter> parameters = profileParameterMapper.findParameterByProfileId(profile.getId());
        if (null == parameters) {
            parameters = new ArrayList<>();
        }
        profile.setParameters(parameters);
    }
}
