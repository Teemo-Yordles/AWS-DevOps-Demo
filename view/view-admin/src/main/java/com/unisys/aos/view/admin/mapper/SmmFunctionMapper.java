package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.admin.entity.SmmFunction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统功能接口表，配合smm_role_function控制功能访问权限 Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Repository
public interface SmmFunctionMapper extends BaseMapper<SmmFunction> {
    List<SmmFunction> findFunctionsByRoleNames(@Param("roleNames") List<String> roleNames);
}
