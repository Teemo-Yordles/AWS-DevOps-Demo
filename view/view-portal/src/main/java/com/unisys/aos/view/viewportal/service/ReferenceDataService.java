/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewportal.service;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 *Reference Data Service
 *
 * @author jianglushuang
 * @since 2020/10/29 1:46 下午
 */
public interface ReferenceDataService {
    public Collection<Serializable> selectAll(String dataType);

    public Collection<Serializable> selectByCode(String dataType, String code);
}
