package com.unisys.aos.view.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.entity.SmmUser;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * user information table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
public interface SmmUserService extends IService<SmmUser> {
    /**
     * find a profile in by profileId.
     * If user cannot be found, ApiException would be thrown.
     *
     * @return profile with internal column settings and parameters
     * @throws com.unisys.aos.view.common.exception.ApiException - if user cannot be found with id provided.
     */
    SmmUser findById(@NonNull Long id);

    /**
     * find all users.
     * If no user can be found, ApiException would be thrown.
     *
     * @return list of SmmUser objects.
     */
    List<SmmUser> findAll();

    /***
     * find user based on the id of profile used by user.
     * @param profileId the id of profile used by user
     * @return user list
     */
    List<SmmUser> findByProfileId(Long profileId);

    /**
     * find user by username
     *
     * @param username - username
     * @return user with the specified username
     */
    SmmUser findByUsername(String username);

    /**
     * Check if the user is the profile's owner
     *
     * @param username - user name
     * @param profile  - profile to be checked
     * @return true if use is the profile owner
     */
    boolean isUserProfileOwner(String username, SmmProfile profile);

    /**
     * Add new user
     *
     * @param newUser - new user
     * @return new user added to the db.
     */
    SmmUser addUser(SmmUser newUser);

    /**
     * update a user
     *
     * @param user - user entity to be persisted to database
     * @return user updated with new values
     */
    SmmUser updateUser(SmmUser user);

    /**
     * Find usernames by id
     *
     * @param id role id
     * @return usernames
     */
    List<String> findUsernamesByRoleId(Long id);

    /**
     * Find users by role id
     *
     * @param id role id
     * @return user list
     */
    List<SmmUser> findUserListByRoleId(Long id);

    /**
     * delete user by id
     *
     * @param id - user id
     * @return true if user successfully deleted
     */
    boolean deleteUserById(Long id);

    /***
     * Update the authorized field of the column settings in the user's profile by the authorized in role's profile.
     * @param user user to be updated
     */
    void updateUserColumnAuthorizedByRole(SmmUser user);
}
