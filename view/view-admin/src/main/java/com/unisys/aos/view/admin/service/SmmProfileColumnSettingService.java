package com.unisys.aos.view.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unisys.aos.view.admin.entity.SmmProfileColumnSetting;

import java.util.List;

/**
 * <p>
 * profile - flight column access right relation table 服务类
 * </p>
 *
 * @author LiuJ2
 * @since 2021-03-03
 */
public interface SmmProfileColumnSettingService extends IService<SmmProfileColumnSetting> {
    /**
     * update the profile column settings for the profile
     *
     * @param currentSettings - old column settings of the profile
     * @param newSettings     - new column settings of the profile
     */
    void updateProfileColumnSetting(List<SmmProfileColumnSetting> currentSettings, List<SmmProfileColumnSetting> newSettings);

}
