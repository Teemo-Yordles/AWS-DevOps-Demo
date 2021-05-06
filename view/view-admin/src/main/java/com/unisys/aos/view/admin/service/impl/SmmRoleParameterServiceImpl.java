package com.unisys.aos.view.admin.service.impl;

import com.unisys.aos.view.admin.entity.SmmParameter;
import com.unisys.aos.view.admin.entity.SmmProfileParameter;
import com.unisys.aos.view.admin.entity.SmmRoleParameter;
import com.unisys.aos.view.admin.mapper.SmmParameterMapper;
import com.unisys.aos.view.admin.mapper.SmmRoleParameterMapper;
import com.unisys.aos.view.admin.service.SmmRoleParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.exception.ApiException;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * role parameter association table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-02-25
 */
@Service
public class SmmRoleParameterServiceImpl extends ServiceImpl<SmmRoleParameterMapper, SmmRoleParameter> implements SmmRoleParameterService {
    private final SmmRoleParameterMapper roleParameterMapper;
    private final SmmParameterMapper parameterMapper;

    @Autowired
    public SmmRoleParameterServiceImpl(SmmRoleParameterMapper roleParameterMapper,
                                       SmmParameterMapper parameterMapper) {
        this.roleParameterMapper = roleParameterMapper;
        this.parameterMapper = parameterMapper;
    }


    /**
     * Copy one role's parameter to another new role
     *
     * @param fromId - from role id
     * @param toId   - to role id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRoleParametersFrom(Long fromId, long toId) {
        List<SmmParameter> fromRoleParameters = roleParameterMapper.findParameterByRoleId(fromId);
        if(null == fromRoleParameters || fromRoleParameters.isEmpty()) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "Role parameters are emtpy for role id=[" + fromId + "]");
        }
        for (SmmParameter fromParameter : fromRoleParameters) {
            SmmParameter toParameter = new SmmParameter();
            toParameter.setDescription(fromParameter.getDescription());
            toParameter.setKey(fromParameter.getKey());
            toParameter.setLevel(fromParameter.getLevel());
            toParameter.setLocalDescription(fromParameter.getLocalDescription());
            toParameter.setValue(fromParameter.getValue());
            parameterMapper.insert(toParameter);

            SmmRoleParameter toRoleParameter = new SmmRoleParameter();
            toRoleParameter.setParameterId(toParameter.getId());
            toRoleParameter.setRoleId(toId);
            roleParameterMapper.insert(toRoleParameter);
        }
    }

    @Override
    public void deleteByRoleId(Long id) {
        parameterMapper.deleteByRoleId(id);
        Map<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("role_id", id);
        roleParameterMapper.deleteByMap(deleteMap);
    }

}
