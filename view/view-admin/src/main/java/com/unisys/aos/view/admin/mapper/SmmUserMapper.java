package com.unisys.aos.view.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unisys.aos.view.admin.entity.SmmUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * user information table Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Mapper
@Repository
public interface SmmUserMapper extends BaseMapper<SmmUser> {
    /**
     * find role related usernames
     *
     * @param roleId - specified role's id
     * @return - list of usernames
     */
    List<String> findUsernamesByRoleId(Long roleId);

    /**
     * find role related user
     *
     * @param roleId - specified role's id
     * @return - list of user
     */
    List<SmmUser> findUserListByRoleId(Long roleId);

    /**
     * find user based on user id
     *
     * @param id - user id
     * @return user object
     */
    @Select("select * from smm_user where `id`=#{id}")
    SmmUser findById(@Param("id") Long id);

    /***
     * find user based on the id of profile used by user.
     * @param profileId the id of profile used by user
     * @return user list
     */
    @Select("select * from smm_user where `profile_id`=#{profileId}")
    List<SmmUser> findByProfileId(@Param("profileId") Long profileId);

    /**
     * find all users in DB.
     *
     * @return List of users
     */
    @Select("select * from smm_user")
    List<SmmUser> findAll();

    /**
     * Check if the user is the profile's owner
     *
     * @param username  - user name
     * @param profileId - profile id to be checked
     * @return 1 if user is the profile owner, 0 otherwise
     */
    Integer isUserProfileOwner(@Param("username") String username, @Param("profileId") Long profileId);

    /**
     * get user by username
     *
     * @param username - username of the user
     * @return user with the specified username
     */
    SmmUser findByUsername(String username);
}
