package com.remember.recursion;

import java.util.Arrays;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/10/24 11:07
 * @Description : 反向打印字符串
 */
public class ReversePrintString {
    public static void main(String[] args) {
        String str = "abcdefg";
        reversePrintString(0, str);
    }

    // str.charAt(i) : 获取字符串中索引为i的字符
    private static void reversePrintString(int n, String str) {
        // 递归结束条件
        if (n == str.length()){
            return;
        }
        // 递归调用
        reversePrintString(n + 1, str);
        System.out.print(str.charAt(n));
    }
}
