/*
 * Copyright 2021 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.aodbinterface.service;

/**
 * @author Zhang Wenqiang
 * @since 2021/1/13 15:20
 */
public interface RequestDataService {
    /***
     * Build xml string and insert to database (table: coutmsgs).
     * @param type The data type to be requested.
     * @param subtype The data subtype to be requested.
     * @param startTime The start time of requested data (Only has value when requesting daily schedule data, and it is null when requesting reference data).
     * @param endTime The end time of requested data (Only has value when requesting daily schedule data, and it is null when requesting reference data).
     * @return Return xml string if build success, otherwise return null.
     */
    String buildAndInsertToCout(String type, String subtype, String startTime, String endTime);
}
