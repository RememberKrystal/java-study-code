package com.remember.Decorator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/12 21:55
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        Display b1 = new SideBorder(new FullBorder(new StringDisplay("Hello,World")), '#');
        Display b2 = new FullBorder(new SideBorder(new FullBorder(new StringDisplay("Hello,World")), '*'));
        Display b3 = new SideBorder(new FullBorder(new SideBorder(new FullBorder(new StringDisplay("Hello,World")), ':')), '.');
        b1.show();
        b2.show();
        b3.show();
    }
}
