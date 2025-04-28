package com.remember.createdMethods1;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/17 23:20
 * @Description :
 */
public class ThreadDemo {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        MyThread thread1 = new MyThread();

        // 设置线程名称
        thread.setName("thread");
        thread1.setName("thread1");

        // 启动线程
        thread.start();
        thread1.start();
    }
}
