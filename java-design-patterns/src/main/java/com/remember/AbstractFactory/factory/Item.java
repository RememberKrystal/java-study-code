package com.remember.AbstractFactory.factory;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:06
 * @Description :
 */
public abstract class Item {
    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML(); // 定义了一个抽象方法
}
