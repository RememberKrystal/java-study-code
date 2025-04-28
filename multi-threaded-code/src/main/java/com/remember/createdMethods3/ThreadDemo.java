package com.remember.createdMethods3;

import java.util.concurrent.FutureTask;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/18 23:05
 * @Description :
 */
public class ThreadDemo {
    public static void main(String[] args) {
        /*
         * 多线程的第三种实现方式：
         *  特点：可以获取到多线程运行的结果
         *  步骤：
         *      1. 创建一个类实现Callable接口
         *      2. 重写call()方法，将此方法返回值作为线程运行结果
         *      3. 创建Callable接口实现类的对象
         *      4. 创建FutureTask类的对象，将Callable接口实现类的对象作为构造方法的参数传给FutureTask类的构造方法
         *      5. 创建Thread类的对象，并调用start()方法启动线程
         */
        // 创建Callable接口实现类的对象
        MyCallable mc = new MyCallable();
        // 创建FutureTask类的对象
        FutureTask<Integer> ft = new FutureTask<>(mc);
        // 创建Thread类的对象
        Thread thread = new Thread(ft);
        // 启动线程
        thread.start();

        try {
            Integer sum = ft.get(); // 获取多线程的结果
            System.out.println("多线程的结果：" + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
