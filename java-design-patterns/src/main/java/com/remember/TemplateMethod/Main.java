package com.remember.TemplateMethod;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/21 21:47
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        AbstractDisplay d1 = new CharDisplay('A');
        AbstractDisplay d2 = new StringDisplay("Hello, World.");
        AbstractDisplay d3 = new StringDisplay("你好，世界。");
        d1.display();
        d2.display();
        d3.display();
    }
}
