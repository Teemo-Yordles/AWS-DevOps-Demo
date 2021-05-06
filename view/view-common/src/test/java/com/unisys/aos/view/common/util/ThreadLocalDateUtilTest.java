package com.unisys.aos.view.common.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author LiuJ2
 * @since 2020/10/21 11:14
 */
class ThreadLocalDateUtilTest {

    @Test
    void parse() {
        try {
            SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date fullDate = fullDateFormat.parse("202001010202");
            Date simpleDate = simpleDateFormat.parse("20200101");
            Date newFullDate = ThreadLocalDateUtil.parseXmlDate("01JAN200202");
            Date newSimpleDate = ThreadLocalDateUtil.parseXmlDate("01JAN20");
            assertEquals(fullDate, newFullDate);
            assertEquals(simpleDate, newSimpleDate);
        } catch (ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void formatXmlDateString() {
        try {
            assertEquals("2002011550", ThreadLocalDateUtil.formatXmlDateString("01FEB201550"));
            assertEquals("200201", ThreadLocalDateUtil.formatXmlDateString("01FEB20"));
        } catch (ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void testTimestampToDate() {
        Date date = ThreadLocalDateUtil.timestampToDate(1614142618586L);
        System.out.println(date);
    }

    @Test
    void testDateToTimestamp() {
        Long timestamp = ThreadLocalDateUtil.dateToTimestamp(new Date());
        System.out.println(timestamp);
    }
}