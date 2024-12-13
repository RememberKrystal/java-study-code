package com.remember.Prototype;

import com.remember.Prototype.framework.Product;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/25 23:13
 * @Description :
 */
public class UnderlinePen implements Product {
    private char ulchar;

    public UnderlinePen(char ulchar) {
        this.ulchar = ulchar;
    }

    @Override
    public void use(String s) {
        System.out.println("\"" + s + "\"");
        int length = s.getBytes().length;
        System.out.print(" ");
        for (int i = 0; i < length; i++) {
            System.out.print(ulchar);
        }
        System.out.println("");
    }

    @Override
    public Product createClone() {
        Product p = null;
        try {
            p = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
