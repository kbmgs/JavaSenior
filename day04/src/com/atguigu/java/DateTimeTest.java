package com.atguigu.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * jdk8之前的日期时间的api测试
 * 1.System类中currentTimeMillis()
 * 2.java.util.Date和子类java.sql.Date
 * 3.SimpleDateFormat
 * 4.Calendar
 */
public class DateTimeTest {
    /*
    SimpleDateFormat的使用：SimpleDateFormat对日期Date类的格式化和解析

    1.两个操作：
    1.1格式化：日期--->字符串
    1.2解析：格式化的逆过程：字符串--->日期

    2.SimpleDateFormat的实例化

     */

    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期--->字符串
        Date date = new Date();//java.util.Date
        System.out.println(date);//Thu Apr 14 21:36:57 CST 2022
        String format = sdf.format(date); //将其格式化
        System.out.println(format);//22-4-14 下午9:37

        //解析：格式化的逆过程：字符串--->日期
        System.out.println("**************");
        String str = "22-4-14 下午9:41";
        Date date1 = sdf.parse(str);//throws
        System.out.println(date1);

        //********************按照指定的方式格式化和解析********************
        System.out.println("**************");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);  //2022-04-17 11:24:20

        //解析 要求字符串必须是符合SimpleDateFormat所识别的格式(通过构造器参数体现)
        //否则抛异常
        Date date2 = sdf1.parse("2022-04-17 11:24:20");
        System.out.println(date2);  //得到Date对象

    }

    /*
        练习一：字符串"2020-09-08"转换为java.sql.Date
        练习二："三天打鱼两天晒网" 1990-01-01 xxxx-xx-xx 打鱼？晒网？
    */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-08";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(birth); //此时date为util下的Date格式
        System.out.println(date); //Tue Sep 08 00:00:00 CST 2020
        System.out.println(date.getTime()); //1599494400000
        //util下的Date转换为sql下的Date

        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate); //2020-09-08
    }

    /*
    Calendar日历类的使用
     */
    @Test
    public void testCalendar() {
        //1.实例化 抽象类，需要实例化
        //方式一：创建其子类(GregorianCalendar)的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());  //查看getInstance()创造的对象calendar的类 --- class java.util.GregorianCalendar

        //2.常用方法
        //get()
        System.out.println("**************");
        int days = calendar.get(Calendar.DAY_OF_MONTH); //当日在当月的第几天
        System.out.println(days);
        System.out.println(Calendar.DAY_OF_YEAR);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        //set()
        System.out.println("**************");
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //add()
        System.out.println("**************");
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //getTime() 日历类--->Date
        System.out.println("**************");
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime() Date--->日历类
        System.out.println("**************");
        Date date1 = new Date();
        calendar.setTime(date1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

    }
}
