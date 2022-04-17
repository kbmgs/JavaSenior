package com.atguigu.java;

import org.junit.Test;

import java.util.Date;

/**
 * jdk8之前的日期和时间的api测试
 */
public class DateTimeTest {
    /*
    java.util.Date类
        |---java.sql.Date类

    1.两个构造器的使用

    2.两个方法的使用
        >toString():针对Date()重写后的toString()方法。显示当前的年月日时分秒及时区
        >getTime():返回date()对象对应的时间与1970.1.1的时间差(时间戳)

    3.java.sql.Date对应数据库中的日期类型的变量
        >如何实例化
        >如何将java.util.Date对象转换为java.sql.Date对象

     */
    @Test
    public void test2() {
        //构造器一：Date()：创建一个对应当前时间的Date对象
        Date date1 = new Date();//import java.util.Date;
        System.out.println(date1.toString());
        System.out.println(date1.getTime());

        //构造器二：创建指定毫秒数的Date()对象
        Date date2 = new Date(1649921368615L);
        System.out.println(date2.toString());

        //创建java.sql.Date对象
        java.sql.Date date3 = new java.sql.Date(1649921368615L);
        System.out.println(date3.toString()); //2022-04-14

        //如何将java.util.Date对象转换为java.sql.Date对象
        //java.util.Date父类 java.sql.Date子类
        //情况一：
        java.util.Date date4 = new java.sql.Date(1649921368615L);//向上转化
        java.sql.Date date5 = (java.sql.Date) date4;//向下强转

        //情况二：
        java.util.Date date6 = new Date();
        java.sql.Date date7 = new java.sql.Date(date6.getTime());//把毫秒数扔进去
    }

    //1.System类中的currentTimeMillis()
    @Test
    public void test1() {
        long time = System.currentTimeMillis();
        //返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差
        //称为时间戳
        System.out.println(time);

    }
}
