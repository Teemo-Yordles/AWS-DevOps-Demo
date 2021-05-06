package com.unisys.aos.view.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unisys.aos.view.admin.entity.SmmProfileParameter;

/**
 * <p>
 * profile parameter association table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
public interface SmmProfileParameterService extends IService<SmmProfileParameter> {

    /**
     * Copy one profile's parameter to another new profile
     *
     * @param fromId - from profile id
     * @param toId   - to profile id
     */
    void insertProfileParametersFrom(Long fromId, long toId);

    /**
     * delete profile related parameters by profile id
     *
     * @param profileId - profile's id to be deleted
     */
    void deleteByProfileId(Long profileId);
}
