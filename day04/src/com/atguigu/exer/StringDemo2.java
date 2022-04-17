package com.atguigu.exer;

import org.junit.Test;

public class StringDemo2 {
    /*
    获取一个字符串在另一个字符串中出现的次数。
    str1 = "abcwerthelloyuiodef";str2 = "cvhellobnm";

    str.indexOf("xxx")
     */

    public String getMaxSameString(String str1, String str2) {
        String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
        String minStr = (str1.length() >= str2.length()) ? str2 : str1;

        int length = minStr.length();
        for (int i = 0; i < length; i++) {
            for (int x = 0, y = length - i; x <= i; x++, y++) {
                String subStr = minStr.substring(x, y);
                if (maxStr.contains(subStr)) {
                    return subStr;
                }
            }
        }
        return null;
    }

    @Test
    public void testGetMaxSameString() {
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";
        String maxSameString = getMaxSameString(str1, str2);
        System.out.println(maxSameString);
    }
}
