package com.remember.createdMethods2;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/17 23:23
 * @Description :
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 创建MyRun对象
        MyRun myRun = new MyRun();

        // 创建Thread对象
        Thread thread = new Thread(myRun);
        Thread thread2 = new Thread(myRun);

        // 设置线程名称
        thread.setName("线程1");
        thread2.setName("线程2");

        // 启动线程
        thread.start();
        thread2.start();
    }
}
