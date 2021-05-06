/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.viewentity.dto;

/**
 * This is the enum of the notification message
 * types might be used.
 *
 * @author LiuJ2
 * @since 2020/11/20 14:28
 */
public enum NotificationType {
    ADD("add"), // flight add
    UPDATE("update"), // flight update
    DELETE("delete"), // flight delete
    SCHEDULE("schedule"); // flight schedule release

    private final String type;

    /**
     * Constructor
     * @param type - notification type String
     */
    NotificationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
