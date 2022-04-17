package com.atguigu.java;

import org.junit.Test;

/**
 * 关于StringBuffer和StringBuilder的使用
 */
public class StringBufferBuilderTest {

    /*
    StringBuffer的常用方法
    StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串拼接
    StringBuffer delete(int start,int end)：删除指定位置的内容
    StringBuffer replace(int start, int end, String str)：把[start,end)位置替换为str
    StringBuffer insert(int offset, xxx)：在指定位置插入xxx
    StringBuffer reverse() ：把当前字符序列逆转
    public int indexOf(String str)
    public String substring(int start,int end)
    public int length()
    public char charAt(int n )
    public void setCharAt(int n ,char ch)

    总结：
    增 append()
    删 delete()
    改 setCharAt(int n ,char ch)/replace(int start, int end, String str)
    查 charAt(int n )
    插 insert(int offset, xxx)
    长度 length()
    遍历 for + charAt()
     */
    @Test
    public void test2() {
        StringBuffer s1 = new StringBuffer("abc");
        s1.append(1);
        s1.append('1');//提供了各种重载的不同类型的append
        System.out.println(s1); //abc11
//        s1.delete(2,4);//delete(int start,int end) 左闭右开，删除c1
//        s1.replace(2, 4, "hello"); //abhello1
//        s1.insert(2,false); //abfalsec11
//        s1.reverse(); //11cba
        String s2 = s1.substring(1,3); //bc
        System.out.println(s1);
        System.out.println(s1.length());
        System.out.println(s2);


    }


    /*
    String/StringBuffer/StringBuilder三者的异同？
    String:不可变的字符序列，底层结构使用char[]存储
    StringBuffer:可变的字符序列，线程安全的，效率低，底层结构使用char[]存储
    StringBuilder:可变的字符序列，jdk5.0新增的，线程不安全，效率高，底层结构使用char[]存储

    源码分析：
    String str = new String(); //底层为new了char[0]
    String str1 = new String("abc"); //new char[]{'a','b','c'}

    StringBuffer源码中，空参构造器内有super(16);
    StringBuffer sb1 = new StringBuffer();// char[] value = new char[16]，底层创建了一个长度是16的数组
    System.out.println(sb1.length());//0

    sb1.append('a');// value[0] = 'a';
    sb1.appent('b');// value[1] = 'b';

    StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length() + 16];

    //问题1：System.out.println(sb2.length());//3
    //问题2：扩容问题:如果要添加的数据底层数组撑不下了，那需要扩容底层数组。
            默认情况下，扩容为原来容量的2倍，同时将原有数组中的元素复制到新的数组中。

            指导意义：在开发中建议使用StringBuffer(int capacity)


     */
    @Test
    public void test1() {
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0, 'm');
        System.out.println(sb1); //mbc a真实的改成了m，是可变的

        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2.length()); //0
    }

}
