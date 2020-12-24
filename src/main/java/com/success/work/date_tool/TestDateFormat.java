package com.success.work.date_tool;

import org.apache.commons.lang3.time.FastDateFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Title： 线程安全的日期格式化
 * @Author：wangchenggong
 * @Date 2020/12/23 18:53
 * @Description
 * @Version
 */
public class TestDateFormat {

    public static void main(String[] args) {
        //这里留个玄机，三个y为何结果也是正常的呢 (*^▽^*)
        String s1 = dateToStr1(new Date(), "yyy-MM-dd HH:mm:ss");
        String s2 = dateToStr2(new Date(), "yyyy-MM-dd HH:mm:ss");

        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * 调用JDK1.8的java.time包下的LocalDateTime
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr1(Date date, String pattern){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 调用apache lang3包的工具类
     * FastDateFormat is a fast and thread-safe version of {@link java.text.SimpleDateFormat}
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr2(Date date, String pattern){
        return FastDateFormat.getInstance(pattern).format(date);
    }


}
