package com.remember.Singleton;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/25 22:25
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // 判断是否是同一个实例对象
    }
}
