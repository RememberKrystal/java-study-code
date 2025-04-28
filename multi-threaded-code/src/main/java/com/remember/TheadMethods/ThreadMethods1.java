package com.remember.TheadMethods;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/18 23:35
 * @Description :
 */
public class ThreadMethods1 {
    public static void main(String[] args) {
        /*
            线程的优先级(1~10) :默认值是 5
            setPriority()：设置线程的优先级
            getPriority()：获取线程的优先级
         */

        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable, "线程一");
        Thread thread2 = new Thread(myRunnable, "线程二");

        // 设置优先级
        thread1.setPriority(1);
        thread2.setPriority(10);

        thread1.start();
        thread2.start();
    }
}
