/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.service;

import com.unisys.aos.view.admin.entity.SmmProfile;
import com.unisys.aos.view.admin.entity.SmmUser;
import com.unisys.aos.view.common.api.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author LiuJ2
 * @since 2021/3/5 12:05
 */
public interface UserSettingService {

    /**
     * get user's role related all profile from the users under this role
     * @return profile list
     */
    public List<SmmProfile> getProfiles();
}
