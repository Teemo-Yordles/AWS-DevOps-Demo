package com.unisys.aos.view.admin.service.impl;

import com.unisys.aos.view.admin.entity.SmmProfileColumnSetting;
import com.unisys.aos.view.admin.mapper.SmmProfileColumnSettingMapper;
import com.unisys.aos.view.admin.service.SmmProfileColumnSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * profile - flight column access right relation table 服务实现类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
@Service
public class SmmProfileColumnSettingServiceImpl extends ServiceImpl<SmmProfileColumnSettingMapper, SmmProfileColumnSetting> implements SmmProfileColumnSettingService {
    private final SmmProfileColumnSettingMapper profileColumnSettingMapper;

    @Autowired
    public SmmProfileColumnSettingServiceImpl(SmmProfileColumnSettingMapper profileColumnSettingMapper) {
        this.profileColumnSettingMapper = profileColumnSettingMapper;
    }

    /**
     * update the profile column settings for the profile
     * @param currentSettings - old column settings of the profile
     * @param newSettings - new column settings of the profile
     */
    @Override
    public void updateProfileColumnSetting(List<SmmProfileColumnSetting> currentSettings, List<SmmProfileColumnSetting> newSettings) {
        //Sanity check
        if(currentSettings == null ||
                newSettings == null ||
                currentSettings.isEmpty() ||
                newSettings.isEmpty()) {
            return;
        }

        // create a map to accelerate the element search
        Map<Long, SmmProfileColumnSetting> currentMap = new HashMap<>();
        for (SmmProfileColumnSetting currentSetting : currentSettings) {
            currentMap.put(currentSetting.getId(), currentSetting);
        }

        for (SmmProfileColumnSetting newSetting : newSettings) {
            SmmProfileColumnSetting oldSetting = currentMap.get(newSetting.getId());
            if(null != oldSetting) {
                if(!oldSetting.equals(newSetting)) {
                    profileColumnSettingMapper.updateById(newSetting);
                }
            }
        }
    }

}
