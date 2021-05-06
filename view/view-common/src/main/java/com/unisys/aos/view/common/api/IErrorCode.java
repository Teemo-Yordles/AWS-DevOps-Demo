/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.api;

/**
 * Error code interface.
 *
 * @author LiuJ2
 * @since 2020/10/12 19:47
 */
public interface IErrorCode {
    String getCode();
    String getMessage();
}
