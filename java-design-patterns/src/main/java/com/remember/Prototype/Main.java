package com.remember.Prototype;

import com.remember.Prototype.framework.Manager;
import com.remember.Prototype.framework.Product;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/25 23:14
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        // 准备
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');
        manager.register("strong message", upen);
        MessageBox mbox = new MessageBox('*');
        manager.register("warning box", mbox);
        MessageBox sbox = new MessageBox('/');
        manager.register("slash box", sbox);

        // 生成
        Product p1 = manager.create("strong message");
        p1.use("Hello, world.");
        Product p2 = manager.create("warning box");
        p2.use("Hello, world.");
        Product p3 = manager.create("slash box");
        p3.use("Hello, world.");
    }
}
