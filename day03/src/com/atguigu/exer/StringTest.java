package com.atguigu.exer;

/**
 * 一道面试题
 */
public class StringTest {
    String str = new String("good");
    String str1 = "good1";
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String b, char ch[]) {
        b = "test ok";
        ch[0] = 'b';
    }

    //String的不可变性
    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str + " and ");//good and
        System.out.println(ex.ch);//best
    }
}
