package com.remember.TheadMethods;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/18 23:50
 * @Description :
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "-----" + i);
        }
    }
}
