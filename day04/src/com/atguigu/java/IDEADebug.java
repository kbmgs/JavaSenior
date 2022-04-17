package com.atguigu.java;

import org.junit.Test;

public class IDEADebug {
    @Test
    public void testStringBuffer() {
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);//

        System.out.println(sb.length());//4

        System.out.println(sb);//null--->是字符"null"

        StringBuffer sb1 = new StringBuffer(str);
        System.out.println(sb1);// 空指针
    }
}
