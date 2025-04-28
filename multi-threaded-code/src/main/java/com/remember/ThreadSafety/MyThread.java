package com.remember.ThreadSafety;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/1/13 19:05
 * @Description :
 */
public class MyThread extends Thread{
    static int number = 1;
    @Override
    public void run() {
        while (true){
            synchronized (MyThread.class) {
                if (number <= 100){
                    System.out.println(getName() + "-----第" + number + "张票！");
                    number++;
                }else {
                    break;
                }
            }
            // 让出CPU资源，使其他线程有机会抢占CPU资源
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
