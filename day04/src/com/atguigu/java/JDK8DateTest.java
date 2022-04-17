package com.atguigu.java;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * jdk8中的日期时间API的测试
 */
public class JDK8DateTest {
    @Test
    public void testDate() {
        Date date1 = new Date(2020, 9, 8);
        System.out.println(date1); //Fri Oct 08 00:00:00 CST 3920
        //此时涉及了偏移量的问题
        Date date2 = new Date(2020 - 1900, 9 - 1, 8);
        System.out.println(date2); //Tue Sep 08 00:00:00 CST 2020
    }

    /*
    LocalDate LocalTime LocalDateTime的使用
    说明：
        1.LocalDateTime相较于其它类使用频率较高
        2.类似于Calendar()
     */
    @Test
    public void test1() {
        //now() 获取当前的日期，时间或日期时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate); //2022-04-18
        System.out.println(localTime); //01:06:37.922
        System.out.println(localDateTime); //2022-04-18T01:06:37.922

        //of() 设定指定的年月日时分秒，没有偏移量
        System.out.println("************************");
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 43);
        System.out.println(localDateTime1);

        //getXxx() 获取相关的属性
        System.out.println("************************");
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //with() 体现了不可变性,
        //withXxx() 设置相关的属性
        System.out.println("************************");
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);
        System.out.println(localDate1); //2022-04-22

        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);

        //plusXxx() 日期时间加减，仍然体现了不可变性
        LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
        System.out.println(localDateTime3);

        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime4);

    }

    /*
    Instant的使用
    类似于java.util.Date类
     */
    @Test
    public void test2() {
        //now() 获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        System.out.println(instant); //2022-04-17T17:25:18.602Z

        //添加时间的偏移量
        //本初子午线时间基础上加了8个小时 (中国东八区)
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2022-04-18T01:28:33.257+08:00

        //toEpochMilli() 获取瞬时点对应的毫秒数
        long toEpochMilli = instant.toEpochMilli();
        System.out.println(toEpochMilli); //1650216682522

        //ofEpochMilli() 通过给定的毫秒数获取Instant实例 --->Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1650216682522L);
        System.out.println(instant1);
    }

    /*
    DateTimeFormatter 格式化或解析日期/时间
    类似于SimpleDateFormat
     */
    @Test
    public void test3() {
        //方式一：预定义的标准格式 ISO_LOCAL_DATE_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime); //2022-04-18T01:40:09.019
        System.out.println(str1); //2022-04-18T01:40:09.019

        //解析：字符串 ---> 日期
        System.out.println(formatter.parse(str1)); //{},ISO resolved to 2022-04-18T01:41:10.511

        //方法二：本地化相关的格式

    }
}
