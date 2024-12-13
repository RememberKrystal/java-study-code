package com.remember.AbstractFactory.tablefactory;

import com.remember.AbstractFactory.factory.Factory;
import com.remember.AbstractFactory.factory.Link;
import com.remember.AbstractFactory.factory.Page;
import com.remember.AbstractFactory.factory.Tray;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:39
 * @Description :
 */
public class TableFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new TableLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new TableTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new TablePage(title, author);
    }
}
