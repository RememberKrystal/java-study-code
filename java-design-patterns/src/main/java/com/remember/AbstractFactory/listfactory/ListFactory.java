package com.remember.AbstractFactory.listfactory;

import com.remember.AbstractFactory.factory.Factory;
import com.remember.AbstractFactory.factory.Link;
import com.remember.AbstractFactory.factory.Page;
import com.remember.AbstractFactory.factory.Tray;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:28
 * @Description :
 */
public class ListFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
