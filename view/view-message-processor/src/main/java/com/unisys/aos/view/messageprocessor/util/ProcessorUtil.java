/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.messageprocessor.util;

/**
 * @author ZhangWe2
 * @since 9/24/2020 11:13 AM
 */
public class ProcessorUtil {
    /**
     * Help to convert the Boolean values represented by "Y" and "N" in XML.
     *
     * @param value "Y" or "N"
     * @return Only "Y" will be judged as true, ignoring case.
     */
    public static Boolean stringToBoolean(String value) {
        return "Y".equalsIgnoreCase(value);
    }
}
