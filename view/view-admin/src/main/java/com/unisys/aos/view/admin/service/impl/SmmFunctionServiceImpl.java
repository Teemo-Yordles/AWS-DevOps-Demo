package com.unisys.aos.view.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.admin.entity.SmmFunction;
import com.unisys.aos.view.admin.entity.SmmRoleFunction;
import com.unisys.aos.view.admin.mapper.SmmFunctionMapper;
import com.unisys.aos.view.admin.mapper.SmmRoleFunctionMapper;
import com.unisys.aos.view.admin.service.SmmFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统功能接口表，配合smm_role_function控制功能访问权限 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Service
public class SmmFunctionServiceImpl extends ServiceImpl<SmmFunctionMapper, SmmFunction> implements SmmFunctionService {
    private final SmmFunctionMapper functionMapper;
    private final SmmRoleFunctionMapper roleFunctionMapper;
    /**
     * administrator role id
     */
    private static final Long ADMINISTRATOR_ROLE_ID=1L;

    @Autowired
    public SmmFunctionServiceImpl(SmmFunctionMapper functionMapper, SmmRoleFunctionMapper roleFunctionMapper) {
        this.functionMapper = functionMapper;
        this.roleFunctionMapper = roleFunctionMapper;
    }

    /**
     * Add new function
     * And by default associate this new function with administrator role
     * And the role id is always 1.
     *
     * @param newFunction - new function
     * @return new function added to the db.
     */
    @Override
    @Transactional
    public SmmFunction addFunction(SmmFunction newFunction) {
        functionMapper.insert(newFunction);
        SmmFunction insertedFunction = functionMapper.selectById(newFunction.getId());
        SmmRoleFunction newRoleFunction = new SmmRoleFunction();
        newRoleFunction.setFunctionId(insertedFunction.getId());
        newRoleFunction.setRoleId(ADMINISTRATOR_ROLE_ID);
        roleFunctionMapper.insert(newRoleFunction);
        return insertedFunction;
    }

    /**
     * delete function and also delete the role-function relationship
     * SmmFunction table and SmmRoleFunction table have to be deleted at the same time
     *
     * @param functionId - function id
     * @return true if function deleted successfully
     */
    @Override
    @Transactional
    public boolean deleteFunction(Long functionId) {
        // delete the role-function relationship first
        QueryWrapper<SmmRoleFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("function_id", functionId);
        roleFunctionMapper.delete(queryWrapper);

        // delete the function record
        int deletedCount = functionMapper.deleteById(functionId);
        return deletedCount > 0;
    }

    /**
     * update a function
     * so need transaction to ensure transaction atomic.
     *
     * @param function - function entity to be persisted to database
     * @return function updated with new values
     */
    @Override
    public SmmFunction updateFunction(SmmFunction function) {
        int updateCount = functionMapper.updateById(function);
        // optimistic locker check
        if (updateCount == 0) {
            return null;
        }
        return functionMapper.selectById(function.getId());
    }
}
