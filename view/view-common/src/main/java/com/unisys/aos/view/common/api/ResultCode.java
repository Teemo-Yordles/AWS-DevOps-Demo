/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.api;

/**
 * This is the enum of result code returns back
 * to front-end
 *
 * @author LiuJ2
 * @since 2020/10/12 19:48
 */
public enum ResultCode implements IErrorCode {
    SUCCESS("00000", "success"),
    REQUEST_FAILED_PARAMETER("A0400", "request parameter invalid"),
    OPERATION_FAILED("B0001", "server operation failed"),
    USER_NOT_EXISTED("A0201", "user account not existed"),
    PASSWORD_ERROR("A0210", "password error"),
    LOGIN_EXPIRED("A0230", "login expired"),
    NOT_AUTHORIZED("A0301", "no authorization to access"),
    ACCOUNT_DISABLED("A0322", "account is disabled"),
    INVALID_TOKEN("A0340", "invalid token"),
    INVALID_VERSION("A0414", "invalid entity version"),
    // 450-454 role related
    ROLE_EXISTED("A0450", "role name already existed"),
    ROLE_HAS_RELATED_USER("A0451", "Cannot be deleted since role has related users"),
    //455-459 function related
    FUNCTION_EXISTED("A0455", "function code already existed"),
    // 460-464 role related
    GROUP_EXISTED("A0460", "group name already existed"),
    // 465-469 role related
    USER_EXISTED("A0465", "user name already existed");

    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}