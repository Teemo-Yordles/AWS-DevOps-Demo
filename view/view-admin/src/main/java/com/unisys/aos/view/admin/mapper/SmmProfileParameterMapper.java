package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.admin.entity.SmmParameter;
import com.unisys.aos.view.admin.entity.SmmProfileParameter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * profile parameter association table Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Repository
public interface SmmProfileParameterMapper extends BaseMapper<SmmProfileParameter> {
    /**
     * Find profile related parameters with profile ID.
     * @param id - profile ID
     * @return related parameters
     */
    List<SmmParameter> findParameterByProfileId(@Param("id") Long id);

}
