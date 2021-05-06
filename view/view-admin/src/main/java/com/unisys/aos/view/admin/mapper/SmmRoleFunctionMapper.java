package com.unisys.aos.view.admin.mapper;

import com.unisys.aos.view.admin.entity.SmmRoleFunction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * role function access right relation table Mapper interface
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Repository
public interface SmmRoleFunctionMapper extends BaseMapper<SmmRoleFunction> {

    /**
     * insert role-function relation when function id do exists in Function table.
     * @param smmRoleFunction
     * @return
     */
    int insertIfFunctionExist(SmmRoleFunction smmRoleFunction);
}
