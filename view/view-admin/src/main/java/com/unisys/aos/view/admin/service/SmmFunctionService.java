package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.SmmFunction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统功能接口表，配合smm_role_function控制功能访问权限 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
public interface SmmFunctionService extends IService<SmmFunction> {

    /**
     * Add new function
     *
     * @param newFunction - new function
     * @return new function added to the db.
     */
    SmmFunction addFunction(SmmFunction newFunction);

    /**
     * delete function and also delete the role-function relationship
     * SmmFunction table and SmmRoleFunction table have to be deleted at the same time
     *
     * @param functionId - function id
     * @return true if function deleted successfully
     */
    boolean deleteFunction(Long functionId);

    /**
     * update a function
     * so need transaction to ensure transaction atomic.
     *
     * @param function - function entity to be persisted to database
     * @return function updated with new values
     */
    SmmFunction updateFunction(SmmFunction function);
}
