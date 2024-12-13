package com.remember.Bridge;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/5 22:39
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("Hello, Bridge!"));
        Display d2 = new CountDisplay(new StringDisplayImpl("Hello, Bridge!"));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello, Bridge!"));
        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);
    }
}
