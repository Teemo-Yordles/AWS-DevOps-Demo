/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.cache.listener;

/**
 * @author jianglushuang
 * @since 2020/11/26 1:35 下午
 */
public interface RedisSubscriber {
    public void onMessage(Object message);
}
