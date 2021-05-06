package com.unisys.aos.view.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.entity.SmmUser;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * system profile table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
public interface SmmProfileService extends IService<SmmProfile> {
    /**
     * find a profile in by profileId.
     *
     * @return profile with internal column settings and parameters
     */
    Optional<SmmProfile> findById(@NonNull Long id);

    /**
     * find user's role related users' profiles plus role profile
     *
     * @param username - user name
     * @return profile list
     */
    List<SmmProfile> findRoleRelatedProfilesByUsername(@NonNull String username);

    /**
     * initialize a new profile for the newly created role
     *
     * @param roleId   - new role id
     * @param roleName - new role name
     * @return initialized profile
     */
    SmmProfile initProfileForNewRole(Long roleId, String roleName);

    /**
     * initialize a new profile for the newly created user
     *
     * @param user - newly added user
     * @return initialized profile
     */
    SmmProfile initProfileForNewUser(SmmUser user);

    /**
     * delete profile by profile id
     *
     * @param profileId - profile Id
     * @return - deleted profile's id
     */
    Long deleteById(Long profileId);

    /**
     * Update the profile and underlying column settings and parameters.
     *
     * @param profile - profile to be updated.
     * @return updated profile
     */
    SmmProfile updateProfile(SmmProfile profile);

    /**
     * Find user's current selected profile
     *
     * @param username - username
     * @return user's current selected profile
     */
    SmmProfile findSelectedProfileByUsername(String username);

    /***
     * Find related profiles by user id
     * @param userId - specified user id
     * @return related profiles of user
     */
    List<SmmProfile> findByUserId(Long userId);

    /***
     * Find profile by role id
     * @param roleId specified role's id
     * @return role's profile
     */
    SmmProfile findByRoleId(Long roleId);

    /**
     * check if the profile is a role related profile
     *
     * @param profile - profile to be checked
     * @return true
     */
    boolean isRoleProfile(SmmProfile profile);

    /**
     * Delete all profiles belong to the user, also the user-profile relationship
     * need to be deleted.
     *
     * @param id user id
     */
    void deleteByUserId(Long id);

    /***
     * check if the profile is the user's default profile.
     * @param userId specified user's id
     * @param profileId specified profile's id
     * @return true - the profile is the user's default profile,
     *         false - the profile is not the user's default profile,
     *         null - the user's id or the profile's id is null.
     */
    Boolean isUserDefaultProfile(Long userId, Long profileId);

    /***
     * Update the profile and underlying column settings and parameters of role.
     * @param profile profile to be updated
     * @return updated profile
     */
    SmmProfile updateRoleProfile(SmmProfile profile);

    /***
     * Add a new profile and initialize column settings and parameters for the specified profile
     * @param newProfile new profile
     * @param sourceProfile specified profile
     * @return new profile
     */
    SmmProfile addProfileFromSourceProfile(SmmProfile newProfile, SmmProfile sourceProfile);

    /***
     * Add a new user profile according to the specified profile
     * @param user current user
     * @param newProfile new profile
     * @param sourceProfile specified profile
     * @return new profile
     */
    SmmProfile addUserProfileFromSourceProfile(SmmUser user, SmmProfile newProfile, SmmProfile sourceProfile);
}
