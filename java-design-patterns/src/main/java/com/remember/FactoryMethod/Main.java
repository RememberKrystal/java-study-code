package com.remember.FactoryMethod;

import com.remember.FactoryMethod.framework.Factory;
import com.remember.FactoryMethod.framework.Product;
import com.remember.FactoryMethod.idcard.IDCardFactory;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/20 22:16
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("张三");
        Product card2 = factory.create("李四");
        Product card3 = factory.create("王五");
        card1.use();
        card2.use();
        card3.use();
    }
}
