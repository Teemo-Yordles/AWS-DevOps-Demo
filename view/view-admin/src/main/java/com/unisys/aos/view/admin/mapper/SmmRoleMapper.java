package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.admin.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * system role table Mapper 接口
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Repository
public interface SmmRoleMapper extends BaseMapper<SmmRole> {
    /**
     * Find all roles with functions related with role ID.
     * @return full role entity list
     */
    List<SmmRole> findRoles();

    /**
     * Find role with functions related with role ID.
     *
     * @param id - role ID
     * @return - full role entity
     */
    SmmRole findRoleById(@Param("id") Long id);

//    /**
//     * Init role column setting for new role
//     * or reset all column setting to initial state (disable all)
//     * @param id - role id
//     */
//    void initRoleColumnSettings(@Param("id") Long id);


    /**
     * Find role related parameters with role ID.
     * @param id - role ID
     * @return related parameters
     */
    List<SmmRoleParameter> findParametersByRoleId(@Param("id") Long id);

    /**
     * Init role parameters for new role
     * or reset all parameters to initial state (system parameter table default value)
     * @param id - role id
     */
    void initRoleParameters(@Param("id") Long id);
}
