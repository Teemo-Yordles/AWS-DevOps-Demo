package com.unisys.aos.view.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.entity.SmmProfileColumnSetting;
import com.unisys.aos.view.admin.entity.SmmUser;
import com.unisys.aos.view.admin.mapper.SmmUserMapper;
import com.unisys.aos.view.admin.mapper.SmmUserProfileMapper;
import com.unisys.aos.view.admin.service.SmmProfileService;
import com.unisys.aos.view.admin.service.SmmUserService;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * user information table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Service
public class SmmUserServiceImpl extends ServiceImpl<SmmUserMapper, SmmUser> implements SmmUserService {
    private final SmmUserMapper userMapper;
    private final SmmUserProfileMapper userProfileMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private SmmProfileService profileService;

    @Autowired
    public SmmUserServiceImpl(SmmUserMapper userMapper,
                              SmmUserProfileMapper userProfileMapper,
                              PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userProfileMapper = userProfileMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * find a user by user id.
     *
     * @param id - user id
     * @return full user object with profile inside. Maybe null.
     */
    @Override
    public SmmUser findById(@NonNull Long id) {
        SmmUser user = userMapper.findById(id);
        if (null != user) {
            fillUserDetail(user);
        } else {
            throw new ApiException(ResultCode.OPERATION_FAILED, "User id=[" + id + "] cannot be found");
        }
        return user;
    }

    /**
     * find all users.
     * If no user can be found, ApiException would be thrown.
     *
     * @return list of SmmUser objects.
     */
    @Override
    public List<SmmUser> findAll() {
        List<SmmUser> users = userMapper.findAll();
        if (null == users || users.isEmpty()) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "No users found in DB");
        }
        return users;
    }

    /***
     * find user based on the id of profile used by user.
     * @param profileId the id of profile used by user
     * @return user list
     */
    @Override
    public List<SmmUser> findByProfileId(Long profileId) {
        return userMapper.findByProfileId(profileId);
    }

    /**
     * Find users by role id
     *
     * @param roleId role id
     * @return user list
     */
    @Override
    public List<SmmUser> findUserListByRoleId(Long roleId) {
        return userMapper.findUserListByRoleId(roleId);
    }

    /**
     * find user by username
     *
     * @param username - username
     * @return user with the specified username
     */
    @Override
    public SmmUser findByUsername(String username) {
        QueryWrapper<SmmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SmmUser user = userMapper.selectOne(queryWrapper);
        if (null != user) {
            fillUserDetail(user);
        }
        return user;
    }

    /**
     * Check if the user is the profile's owner
     *
     * @param username - user name
     * @param profile  - profile to be checked
     * @return true if use is the profile owner
     */
    @Override
    public boolean isUserProfileOwner(String username, SmmProfile profile) {
        return (userMapper.isUserProfileOwner(username, profile.getId()) > 0);
    }

    @Override
    @Transactional
    public SmmUser addUser(SmmUser newUser) {
        newUser.setPassword(passwordEncode(newUser.getPassword()));
        // set a temporary profile id
        newUser.setProfileId(0L);
        userMapper.insert(newUser);
        Long newUserId = newUser.getId();
        SmmProfile newProfile = profileService.initProfileForNewUser(newUser);
        newUser.setProfileId(newProfile.getId());
        userMapper.updateById(newUser);
        return findById(newUserId);
    }

    @Override
    @Transactional
    public SmmUser updateUser(SmmUser user) {
        Boolean roleChanged = checkUserRelateRoleChanged(user);
        // update user details
        user.setPassword(passwordEncode(user.getPassword()));
        Long userId = user.getId();
        // If user's role is changed, update related profiles.
        if (null != roleChanged && roleChanged) {
            // If other users are using this user's profile,
            // need to change these users to use their default profile.
            List<SmmProfile> userOwnerProfileList = profileService.findByUserId(userId);
            if (null != userOwnerProfileList && !userOwnerProfileList.isEmpty()) {
                userOwnerProfileList.forEach(profile -> {
                    List<SmmUser> userList = userMapper.findByProfileId(profile.getId());
                    if (null != userList && !userList.isEmpty()) {
                        userList.removeIf(u -> u.getId().equals(userId));
                        userList.forEach(relatedUser -> {
                            log.debug("Updating current selected profile to user's default profile, user id = [" + relatedUser.getId() + "].");
                            SmmUser changedUser = changeSelectedProfileToDefaultProfile(relatedUser);
                            int count = userMapper.updateById(changedUser);
                            if (0 == count) {
                                log.error("Update failed, this user with id = [" + relatedUser.getId() + "] must have been updated by other user.");
                            }
                            log.debug("Update successful.");
                        });
                    }
                });
            }

            // If this user's previously profile is the user's profile in another role,
            // change the user's profile to its default profile.
            String userName = user.getUsername();
            SmmProfile selectedProfile = profileService.findSelectedProfileByUsername(userName);
            boolean userProfileOwner = isUserProfileOwner(userName, selectedProfile);
            if (!userProfileOwner) {

                user = changeSelectedProfileToDefaultProfile(user);
            }
            // Update the authorized field of the column settings in the user's profile
            updateUserColumnAuthorizedByRole(user);
        }

        int updateCount = userMapper.updateById(user);
        // optimistic locker check
        if (updateCount == 0) {
            return null;
        }
        return userMapper.findById(userId);
    }

    /***
     * Change current selected profile to user's default profile.
     * @param user user to be updated
     * @return updated user
     */
    private SmmUser changeSelectedProfileToDefaultProfile(SmmUser user) {
        List<Long> relateProfileId = userProfileMapper.findProfileIdListByUserId(user.getId());
        // The first item in the association table is user's default profile.
        Long profileId = relateProfileId.get(0);
        user.setProfileId(profileId);
        return user;
    }

    /***
     * Check whether the user's role has changed.
     * @param user The user being checked.
     * @return true if the user has changed, otherwise false.
     */
    private Boolean checkUserRelateRoleChanged(SmmUser user) {
        if (null == user) {
            log.error("Failed to check, because given parameter is null.");
            return null;
        }
        SmmUser oldUser = userMapper.findById(user.getId());
        if (null == oldUser) {
            return false;
        }
        Long oldUserRoleId = oldUser.getRoleId();
        Long newUserRoleId = user.getRoleId();
        return !oldUserRoleId.equals(newUserRoleId);
    }

    /***
     * Update the authorized field of the column settings in the user's profile by the authorized in role's profile.
     * @param user user to be updated
     */
    @Override
    public void updateUserColumnAuthorizedByRole(SmmUser user) {
        List<SmmProfile> userProfileList = profileService.findByUserId(user.getId());
        if (null == userProfileList || userProfileList.isEmpty()) {
            log.warn("No profile related to the user[user id = " + user.getId() + "] was found.");
            return;
        }
        SmmProfile roleProfile = profileService.findByRoleId(user.getRoleId());
        if (null == roleProfile) {
            log.warn("No profile related to the role[role id = " + user.getRoleId() + "] was found.");
            return;
        }
        List<SmmProfileColumnSetting> roleProfileColumnSettingList = roleProfile.getColumnSettings();
        // If there is no relevant column setting in role's profile,
        // set "authorized" in column setting of user's profiles to 0.
        if (null == roleProfileColumnSettingList || roleProfileColumnSettingList.isEmpty()) {
            userProfileList.forEach(profile -> {
                List<SmmProfileColumnSetting> columnSettingList = profile.getColumnSettings();
                if (null != columnSettingList && !columnSettingList.isEmpty()) {
                    columnSettingList.forEach(column -> column.setAuthorized(0));
                    SmmProfile updatedProfile = updateProfileColumnSetting(profile, columnSettingList);
                    if (null == updatedProfile) {
                        throw new ApiException(ResultCode.INVALID_VERSION, "profile must have been updated by other user.");
                    }
                }
            });
        } else {
            Map<Long, Integer> columnSettingMap = roleProfileColumnSettingList
                    .stream()
                    .collect(Collectors.toConcurrentMap(SmmProfileColumnSetting::getColumnId, SmmProfileColumnSetting::getAuthorized));
            userProfileList.forEach(profile -> {
                List<SmmProfileColumnSetting> columnSettingList = profile.getColumnSettings();
                if (null != columnSettingList && !columnSettingList.isEmpty()) {
                    columnSettingList.forEach(column -> {
                        Integer authorized = columnSettingMap.get(column.getColumnId());
                        if (null != authorized) {
                            column.setAuthorized(authorized);
                        } else {
                            column.setAuthorized(0);
                        }
                    });
                    SmmProfile updatedProfile = updateProfileColumnSetting(profile, columnSettingList);
                    if (null == updatedProfile) {
                        throw new ApiException(ResultCode.INVALID_VERSION, "profile must have been updated by other user.");
                    }
                }
            });
        }
    }

    /***
     * Update column settings of profile.
     * @param profile the specified profile
     * @param columnSettingList updated column setting list
     * @return updated profile
     */
    private SmmProfile updateProfileColumnSetting(SmmProfile profile, List<SmmProfileColumnSetting> columnSettingList) {
        profile.setColumnSettings(columnSettingList);
        return profileService.updateProfile(profile);
    }

    @Override
    public List<String> findUsernamesByRoleId(Long id) {
        return userMapper.findUsernamesByRoleId(id);
    }

    /**
     * delete user by id
     *
     * @param id - user id
     * @return true if user successfully deleted
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteUserById(Long id) {
        // delete user profiles
        profileService.deleteByUserId(id);
        // delete user table
        int deleted = userMapper.deleteById(id);
        return deleted > 0;
    }

    private String passwordEncode(String password) {
        if (password != null
                && !(password.length() == 60 && password.startsWith("$2a$"))) {
            password = passwordEncoder.encode(password);
            System.out.println("-----------");
            System.out.println(password.startsWith("$2a$"));
            System.out.println(!(password.length() == 60 && password.startsWith("$2a$")));
            System.out.println(password);

        }
        return password;
    }

    /**
     * Fill user details with profile
     *
     * @param user - user object with basic information
     */
    private void fillUserDetail(SmmUser user) {
        Optional<SmmProfile> profile = profileService.findById(user.getProfileId());
        user.setProfile(profile.orElseThrow(() -> new ApiException(ResultCode.OPERATION_FAILED, "profile with ID[" + user.getProfileId() + "] cannot be found")));
    }
}
