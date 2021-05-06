package com.unisys.aos.view.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unisys.aos.view.admin.entity.SmmParameter;

import java.util.List;

/**
 * <p>
 * system global parameters 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
public interface SmmParameterService extends IService<SmmParameter> {
    /**
     * compare two list of parameter and update if any difference found
     *
     * @param currentParameters - current parameter list
     * @param newParameters     - new parameter list
     */
    void updateParameters(List<SmmParameter> currentParameters, List<SmmParameter> newParameters);
}