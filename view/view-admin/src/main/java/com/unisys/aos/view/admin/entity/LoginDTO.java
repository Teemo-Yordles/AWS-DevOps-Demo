/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin.entity;

import com.unisys.aos.view.security.entity.ViewUserDetailsFunction;
import lombok.Data;

import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/12/19 11:37
 */
@Data
public class LoginDTO {
    private String accessToken;
    private String refreshToken;
    private String username;
    private List<ViewUserDetailsFunction> functions;
    private SmmProfile profile;
}
