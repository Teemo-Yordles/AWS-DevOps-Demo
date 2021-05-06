package com.unisys.aos.view.admin.service.impl;

import com.unisys.aos.view.admin.entity.SmmParameter;
import com.unisys.aos.view.admin.entity.SmmProfileParameter;
import com.unisys.aos.view.admin.mapper.SmmParameterMapper;
import com.unisys.aos.view.admin.mapper.SmmProfileParameterMapper;
import com.unisys.aos.view.admin.service.SmmProfileParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unisys.aos.view.common.api.ResultCode;
import com.unisys.aos.view.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * profile parameter association table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Service
public class SmmProfileParameterServiceImpl extends ServiceImpl<SmmProfileParameterMapper, SmmProfileParameter> implements SmmProfileParameterService {
    private final SmmProfileParameterMapper profileParameterMapper;
    private final SmmParameterMapper parameterMapper;

    @Autowired
    public SmmProfileParameterServiceImpl(SmmProfileParameterMapper profileParameterMapper,
                                          SmmParameterMapper parameterMapper) {
        this.profileParameterMapper = profileParameterMapper;
        this.parameterMapper = parameterMapper;
    }

    /**
     * Copy one profile's parameter to another new profile
     *
     * @param fromId - from profile id
     * @param toId   - to profile id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertProfileParametersFrom(Long fromId, long toId) {
        List<SmmParameter> fromProfileParameters = profileParameterMapper.findParameterByProfileId(fromId);
        if(null == fromProfileParameters || fromProfileParameters.isEmpty()) {
            throw new ApiException(ResultCode.OPERATION_FAILED, "Profile parameters are empty for profile id=[" + fromId + "]");
        }
        for (SmmParameter fromParameter : fromProfileParameters) {
            SmmParameter toParameter = new SmmParameter();
            toParameter.setDescription(fromParameter.getDescription());
            toParameter.setKey(fromParameter.getKey());
            toParameter.setLevel(fromParameter.getLevel());
            toParameter.setLocalDescription(fromParameter.getLocalDescription());
            toParameter.setValue(fromParameter.getValue());
            parameterMapper.insert(toParameter);

            SmmProfileParameter toProfileParameter = new SmmProfileParameter();
            toProfileParameter.setParameterId(toParameter.getId());
            toProfileParameter.setProfileId(toId);
            profileParameterMapper.insert(toProfileParameter);
        }
    }

    /**
     * delete profile related parameters by profile id
     *
     * @param profileId - profile's id to be deleted
     */
    @Override
    public void deleteByProfileId(Long profileId) {
        parameterMapper.deleteByProfileId(profileId);
        Map<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("profile_id", profileId);
        profileParameterMapper.deleteByMap(deleteMap);
    }
}
