package com.remember.recursion;

import java.util.Arrays;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/10/31 20:16
 * @Description : 斐波拉契数列
 */
public class FibonacciSequence {
    public static void main(String[] args) {
        int fibonacci = fibonacci(10);
        System.out.println(fibonacci);
    }

    private static int fibonacci(int n) {
        // 创建一个数组，用来缓存已经计算过的值
        int[] cache = new int[n + 1];
        // 初始化数组，将数组中的所有元素设置为-1
        Arrays.fill(cache, -1);//[-1,-1,-1,-1,-1,-1]
        cache[0] = 0; // [0,-1,-1,-1,-1,-1]
        if (n >= 1){
            cache[1] = 1; // [0, 1,-1,-1,-1,-1]
        }
        // 返回值
        return fibonacci2(n, cache);
    }

    /**
     * 斐波拉契数列
     *
     * @param n
     * @return
     */
    private static int fibonacci1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 斐波拉契数列 - 改进版本（创建一个数组来缓存已经计算过的值） --记忆法
     *
     * @param n
     * @param cache
     * @return
     */
    private static int fibonacci2(int n, int[] cache) {
        if (cache[n] != -1){ // 缓存中有值，直接返回
            return cache[n];
        }
        // 缓存中没有值，则计算
        int x = fibonacci2(n - 1, cache);
        int y = fibonacci2(n - 2, cache);
        cache[n] = x + y; // 将计算的值缓存
        return cache[n];
    }
}
