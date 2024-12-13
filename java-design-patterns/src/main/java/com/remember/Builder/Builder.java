package com.remember.Builder;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/27 22:42
 * @Description : 抽象类，定义编写文档的各个方法
 */
public abstract class Builder {
    public abstract void makeTitle(String title);
    public abstract void makeString(String str);
    public abstract void makeItems(String[] items);
    public abstract void close();
}
