package com.atguigu.java;

import org.junit.Test;

/**
 * String的使用
 * 使用单元测试方法
 */

public class StringTest {


    /*
    结论：
    1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
    2.只要其中有一个是变量，结果就在堆中。
    3.如果拼接的结果调用intern()方法，那么返回值就在常量池中。
     */
    @Test
    public void test3() {
        String s1 = "javaEE";
        String s2 = "hadoop";
        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";

        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s5 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
        String s8 = s5.intern();//如果拼接的结果调用intern()方法，那么返回值就在常量池中。
        System.out.println(s3 == s8);//true

    }

    /**
     *    String str1 = "abc";与 String str2 = new String("abc"); 的区别？
     *     字符串常量存储再字符串常量池，目的是共享
     *     字符串非常量对象存储在堆中。
     *
     * String实例化方式：
     * 方式一：通过字面量定义的方式
     * 方式二：通过new+构造器的方式
     */
    @Test
    public void test2() {
        //通过字面量定义的方式：此时的s1和s2的数据javaEE，生命在方法区中的字符串常量池中。
        String s1 = "javaEE";
        String s2 = "javaEE";

        //通过new+构造器的方式：此时的s3和s4保存的地址值是数据在堆空间中开辟空间以后对应的地址值。
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false

    }


    /**
     String:字符串，使用一对""来表示
     1.String声明为final的，不可被继承
     2.String实现了Serializable接口：表示字符串是支持序列化的(IO流时再详说)
     实现了Comparable接口：表示String可以比较大小
     3.String内部定义了final char[] value用于存储字符串数据
     4.String:代表一个不可变的字符序列。简称：不可变性
     体现:1.当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值
     2.当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能在原有的value上进行赋值
     3.当调用String的replace()方法修改指定字符或字符串时，也必须重新指定内存区域进行赋值
     5.通过字面量的方式(区别于new的方式)，给一个字符串赋值，此时的字符串值生命在字符串常量池中。
     6.字符串常量池中是不会存储相同内容的字符串的
     */
    @Test
    public void test1() {
        // String类型不用new对象，可以直接写
        String s1 = "abc"; //字面量的定义方式
        String s2 = "abc";
        s1 = "hello";

        System.out.println(s1 == s2); //比较s1和s2的地址值:False
        // 常量池中不会存两个相同的字符串

        System.out.println(s1); //hello
        System.out.println(s2); //abc

        System.out.println("***********");

        String s3 = "abc";
        s3 += "def"; // 拼接了字符串内容 abcdef
        System.out.println(s3);
        System.out.println(s2);

        System.out.println("***********");

        String s4 = "abc";
        String s5 = s4.replace('a', 'm');
        System.out.println(s4); // abc
        System.out.println(s5); // mbc


    }

}
