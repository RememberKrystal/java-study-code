package com.remember.AbstractFactory.factory;

import java.util.ArrayList;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:12
 * @Description :
 */
public abstract class Tray extends Item{
    protected ArrayList tray = new ArrayList();

    public Tray(String caption) {
        super(caption);
    }

    public void add(Item item) {
        tray.add(item);
    }
}
