package com.remember.TheadMethods;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/18 23:36
 * @Description :
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-----" + i);
        }
    }
}
