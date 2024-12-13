package com.remember.Adapter1;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/18 21:47
 * @Description :
 */
public class Banner {
    private String string;
    public Banner(String string) {
        this.string = string;
    }
    public void showWithParen() {
        System.out.println("(" + string + ")");
    }
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
