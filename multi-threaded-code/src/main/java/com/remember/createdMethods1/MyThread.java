package com.remember.createdMethods1;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/17 23:20
 * @Description :
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + " " + i);
        }
    }
}
