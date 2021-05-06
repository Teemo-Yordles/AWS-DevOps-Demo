package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.SmmRoleParameter;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * role parameter association table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-02-25
 */
public interface SmmRoleParameterService extends IService<SmmRoleParameter> {
    /**
     * Copy one role's parameter to another new role
     *
     * @param fromId - from role id
     * @param toId   - to role id
     */
    public void insertRoleParametersFrom(Long fromId, long toId);

    void deleteByRoleId(Long id);
}
