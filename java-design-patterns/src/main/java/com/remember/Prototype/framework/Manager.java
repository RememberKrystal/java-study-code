package com.remember.Prototype.framework;

import java.util.HashMap;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/25 23:10
 * @Description :
 */
public class Manager {
    private HashMap showcase = new HashMap();
    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }
    public Product create(String protoName) {
        Product p = (Product) showcase.get(protoName);
        return p.createClone();
    }
}
