package com.remember.createdMethods2;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/17 23:23
 * @Description :
 */
public class MyRun implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            // 获取当前线程
            // Thread t = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
