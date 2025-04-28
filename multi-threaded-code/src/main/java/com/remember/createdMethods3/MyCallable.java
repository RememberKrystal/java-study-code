package com.remember.createdMethods3;

import java.util.concurrent.Callable;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/18 23:05
 * @Description :
 */
public class MyCallable implements Callable<Integer> { // 这里的泛型是返回值类型
    @Override
    public Integer call() throws Exception {
        // 求 1——100的和
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}
