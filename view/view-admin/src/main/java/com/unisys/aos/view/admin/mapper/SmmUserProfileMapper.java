package com.unisys.aos.view.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unisys.aos.view.admin.entity.SmmUserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * user-profile relation table Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Mapper
@Repository
public interface SmmUserProfileMapper extends BaseMapper<SmmUserProfile> {
    /**
     * find ids of user related profiles
     *
     * @param userId specified user's id
     * @return list of profile id
     */
    List<Long> findProfileIdListByUserId(Long userId);

    /***
     * find ids of profile related users
     * @param profileId specified profile's id
     * @return list of user id
     */
    List<Long> findUserIdListByProfileId(Long profileId);

    /***
     * add a new user profile association
     * @param userId user's id
     * @param profileId profile's id
     * @return count of rows affected
     */
    int addUserProfile(@Param("userId") Long userId, @Param("profileId") Long profileId);

    /***
     * delete user profile associations with given user's id
     * @param userId specified user's id
     * @return the count of deleted records
     */
    int deleteByUserId(Long userId);

    /***
     * delete user profile associations with given profile's id
     * @param profileId specified profile's id
     * @return the count of deleted records
     */
    int deleteByProfileId(Long profileId);
}
