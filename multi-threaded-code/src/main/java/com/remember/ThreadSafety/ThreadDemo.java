package com.remember.ThreadSafety;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/1/13 19:05
 * @Description :
 */
public class ThreadDemo {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread thread3 = new MyThread();
        thread1.setName("窗口一");
        thread2.setName("窗口二");
        thread3.setName("窗口三");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
