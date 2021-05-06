package com.unisys.aos.view.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unisys.aos.view.admin.entity.SmmParameter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * system global parameters Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Mapper
@Repository
public interface SmmParameterMapper extends BaseMapper<SmmParameter> {

    /**
     * delete profile-related parameters based on profile id
     *
     * @param profileId - profile id
     */
    void deleteByProfileId(Long profileId);

    /**
     * delete role-related parameters based on role id
     *
     * @param id - role id
     */
    void deleteByRoleId(Long id);
}
