package com.unisys.aos.view.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unisys.aos.view.admin.entity.SmmProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * system profile table Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Repository
@Mapper
public interface SmmProfileMapper extends BaseMapper<SmmProfile> {
    /**
     * Find profile with profile ID.
     *
     * @param id - profile ID
     * @return whole profile
     */
    @Select("select * from smm_profile where id=#{id}")
    SmmProfile findById(@Param("id") Long id);

    /**
     * Find profile with profile name.
     *
     * @param name - profile name
     * @return whole profile
     */
    @Select("select * from smm_profile where name=#{name}")
    SmmProfile findByName(@Param("name") String name);

    /**
     * find user's role related users' profiles plus role profile
     *
     * @param username - user name
     * @return profile list
     */
    List<SmmProfile> findRoleRelatedProfilesByUsername(@Param("username") String username);

    /**
     * Check if the profile is a role related profile
     *
     * @param profileId - profile id to be checked
     * @return 1 if profile is role related profile, 0 otherwise
     */
    Integer isRoleProfile(Long profileId);

    /**
     * initialize column settings for the profile with specified profile id
     * it copies column settings from a current profile column setting table
     *
     * @param currentProfileId - current profile id
     * @param newProfileId     - profile id
     */
    void initColumnSettingsFromProfile(@Param("currentProfileId") Long currentProfileId, @Param("newProfileId") Long newProfileId);

    /**
     * initialize parameters for the profile with specified profile id
     * it copies parameters from a current profile parameter table
     *
     * @param currentProfileId - current profile id
     * @param newProfileId     - profile id
     */
    void initParametersFromProfile(@Param("currentProfileId") Long currentProfileId, @Param("newProfileId") Long newProfileId);

    /**
     * get role profile id from for a role
     *
     * @param roleId - role id
     * @return profile id in Long
     */
    Long getProfileIdForRole(@Param("roleId") Long roleId);

    /***
     * add a new profile
     * @param profile new profile
     * @return count of rows affected
     */
    int addProfile(@Param("profile") SmmProfile profile);
}
