package com.remember.Singleton;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/25 22:24
 * @Description : 单例模式
 */
public class Singleton {
    private static final Singleton singleton = new Singleton();
    private Singleton(){
        System.out.println("单例模式创建对象"); // 构造方法私有化 防止外部创建实例
    }
    public static Singleton getInstance(){ // 静态方法返回单例对象
        return singleton;
    }
}
