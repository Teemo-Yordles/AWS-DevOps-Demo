package com.unisys.aos.view.admin.service.impl;

import com.unisys.aos.view.admin.entity.SmmParameter;
import com.unisys.aos.view.admin.mapper.SmmParameterMapper;
import com.unisys.aos.view.admin.service.SmmParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * system global parameters 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2020-12-17
 */
@Service
public class SmmParameterServiceImpl extends ServiceImpl<SmmParameterMapper, SmmParameter> implements SmmParameterService {
    private final SmmParameterMapper parameterMapper;

    @Autowired
    public SmmParameterServiceImpl(SmmParameterMapper parameterMapper) {
        this.parameterMapper = parameterMapper;
    }


    /**
     * compare two list of parameter and update if any difference found
     * @param currentParameters - current parameter list
     * @param newParameters - new parameter list
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateParameters(List<SmmParameter> currentParameters, List<SmmParameter> newParameters) {
        //Sanity check
        if(currentParameters == null ||
                newParameters == null ||
                currentParameters.isEmpty() ||
                newParameters.isEmpty()) {
            return;
        }

        // create a map to accelerate the element search
        Map<Long, SmmParameter> currentMap = new HashMap<>();
        for (SmmParameter oldParam : currentParameters) {
            currentMap.put(oldParam.getId(), oldParam);
        }

        for (SmmParameter newParam : newParameters) {
            SmmParameter oldParam = currentMap.get(newParam.getId());
            if(null != oldParam) {
                if(!oldParam.equals(newParam)) {
                    parameterMapper.updateById(newParam);
                }
            }
        }
    }
}
