/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.exception;

/**
 * @author LiuJ2
 * @since 2020/10/22 8:12
 */
public class XmlFormatException extends Exception {
    @SuppressWarnings("unused")
    public XmlFormatException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public XmlFormatException(Throwable cause) {
        super(cause);
    }

    @SuppressWarnings("unused")
    public XmlFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
