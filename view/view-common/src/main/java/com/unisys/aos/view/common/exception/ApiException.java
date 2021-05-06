/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.exception;

import com.unisys.aos.view.common.api.IErrorCode;

/**
 * RESTFul API related exception
 * @author LiuJ2
 * @since 2020/10/12 19:45
 */

public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    /**
     * Constructor with error code
     * @param errorCode - error code enum value
     */
    @SuppressWarnings("unused")
    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @SuppressWarnings("unused")
    public ApiException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ApiException(Throwable cause) {
        super(cause);
    }

    @SuppressWarnings("unused")
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(IErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}

