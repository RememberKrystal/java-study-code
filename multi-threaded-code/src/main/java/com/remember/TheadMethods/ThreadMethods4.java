package com.remember.TheadMethods;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/21 21:10
 * @Description : 插入线程的使用
 */
public class ThreadMethods4 {
    public static void main(String[] args) throws InterruptedException {
        /*
         * 插入线程：（不常用）
         *      join() : 插队线程，将当前线程插队到指定的线程后面，并等待插队线程执行完毕，再继续执行当前线程
         */

        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable);
        thread.setName("线程一");
        // 启动线程
        thread.start();

        // 插队线程
        thread.join();

        for (int i = 0; i < 10; i++){
            System.out.println("主线程：" + i);
        }
    }
}
