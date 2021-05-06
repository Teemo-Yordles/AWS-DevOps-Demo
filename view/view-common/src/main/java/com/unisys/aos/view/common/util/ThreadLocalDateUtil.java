/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuJ2
 * @since 2020/10/21 10:56
 */
public class ThreadLocalDateUtil {
    private static final String FULL_DATE_FORMAT = "yyMMddHHmm";
    private static final String SIMPLE_DATE_FORMAT = "yyMMdd";
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
    private static final Map<String, String> monToMmMap = new HashMap();
    private static final int FULL_XML_DATE_LENGTH = 11;
    private static final int SIMPLE_XML_DATE_LENGTH = 7;

    static {
        monToMmMap.put("JAN", "01");
        monToMmMap.put("FEB", "02");
        monToMmMap.put("MAR", "03");
        monToMmMap.put("APR", "04");
        monToMmMap.put("MAY", "05");
        monToMmMap.put("JUN", "06");
        monToMmMap.put("JUL", "07");
        monToMmMap.put("AUG", "08");
        monToMmMap.put("SEP", "09");
        monToMmMap.put("OCT", "10");
        monToMmMap.put("NOV", "11");
        monToMmMap.put("DEC", "12");
    }

    public static SimpleDateFormat getDateFormat(String dateFormat) {
        SimpleDateFormat df = threadLocal.get();
        if (null == df || !dateFormat.equals(df.toPattern())) {
            df = new SimpleDateFormat(dateFormat);
            threadLocal.set(df);
        }
        return df;
    }

    public static String formatDate(Date date, String dateFormat) throws ParseException {
        return getDateFormat(dateFormat).format(date);
    }

    public static Date parse(String strDate, String dateFormat) throws ParseException {
        return getDateFormat(dateFormat).parse(strDate);
    }

    /**
     * format the xml date string ddMONyyHHmm/ddMONyy to yyyyMMddHHmm/yyyyMMdd
     *
     * @param xmlDate date string with ddMONyyHHmm/ddMONyy format
     * @return date string with yyyyMMddHHmm/yyyyMMdd
     */
    public static String formatXmlDateString(String xmlDate) throws ParseException {
        if (null == xmlDate) {
            return null;
        }
        StringBuilder dateString = new StringBuilder();
        try {
            dateString.append(xmlDate, 5, 7);
            dateString.append(monToMmMap.get(xmlDate.substring(2, 5)));
            dateString.append(xmlDate, 0, 2);
            if (xmlDate.length() == FULL_XML_DATE_LENGTH) {
                dateString.append(xmlDate.substring(7, 11));
            }
        } catch (Exception ex) {
            throw new ParseException(xmlDate, 0);
        }
        return dateString.toString();
    }

    /**
     * format the xml date string ddMONyyHHmm/ddMONyy to yyyyMMddHHmm/yyyyMMdd
     *
     * @param xmlDate date string with ddMONyyHHmm/ddMONyy format
     * @return date string with yyyyMMddHHmm/yyyyMMdd
     */
    public static Date parseXmlDate(String xmlDate) throws ParseException {
        if (null == xmlDate) {
            return null;
        }
        String dateStr = formatXmlDateString(xmlDate);
        if (xmlDate.length() == FULL_XML_DATE_LENGTH) {
            return parse(dateStr, FULL_DATE_FORMAT);
        }
        if (xmlDate.length() == SIMPLE_XML_DATE_LENGTH) {
            return parse(dateStr, SIMPLE_DATE_FORMAT);
        }
        return null;
    }

    /***
     * Convert date to timestamp.
     * @param timestamp timestamp
     * @return Return null if timestamp is null, otherwise return the converted date.
     */
    public static Date timestampToDate(Long timestamp) {
        if (null == timestamp) {
            return null;
        }
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }

    /***
     * Convert timestamp to date.
     * @param date date
     * @return Return null if date is null, otherwise return the converted timestamp.
     */
    public static Long dateToTimestamp(Date date) {
        if (null == date) {
            return null;
        }
        return new Timestamp(date.getTime()).getTime();
    }
}
