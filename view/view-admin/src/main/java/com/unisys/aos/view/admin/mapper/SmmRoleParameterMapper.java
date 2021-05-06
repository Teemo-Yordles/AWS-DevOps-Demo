package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.admin.entity.SmmParameter;
import com.unisys.aos.view.admin.entity.SmmRoleParameter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * role parameter association table Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2021-02-25
 */
@Repository
public interface SmmRoleParameterMapper extends BaseMapper<SmmRoleParameter> {
    /**
     * Find role related parameters with role ID.
     * @param id - role ID
     * @return related parameters
     */
    List<SmmParameter> findParameterByRoleId(@Param("id") Long id);
}
