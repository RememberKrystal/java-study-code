package com.remember.Decorator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/12 21:17
 * @Description :
 */
public abstract class Border extends Display{
    protected Display display; // 表示被装饰物

    protected Border(Display display) {
        this.display = display;  // 在生成实例的时候通过参数指定被装饰物
    }
}
