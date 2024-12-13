package com.remember.TemplateMethod;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/21 21:43
 * @Description : 模板方法
 */
public abstract class AbstractDisplay {
    public abstract void open();
    public abstract void print();
    public abstract void close();
    public final void display(){
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}
