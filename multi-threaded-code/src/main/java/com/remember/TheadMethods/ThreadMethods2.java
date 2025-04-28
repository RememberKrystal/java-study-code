package com.remember.TheadMethods;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/18 23:49
 * @Description :
 */
public class ThreadMethods2 {
    public static void main(String[] args) {
        /*
            守护线程：
            setDaemon(true) : 设置为守护线程
            isDaemon() : 判断是否为守护线程
            细节：当其他非守护线程运行完毕，守护线程会自动陆续结束
         */

        MyThread thread1 = new MyThread();
        thread1.setName("线程一");
        thread1.start(); // 启动线程

        MyRunnable runnable = new MyRunnable();
        Thread thread2 = new Thread(runnable, "守护线程");
        thread2.setDaemon(true); // 设置为守护线程

        thread2.start();
    }
}
