package com.remember.AbstractFactory.listfactory;

import com.remember.AbstractFactory.factory.Item;
import com.remember.AbstractFactory.factory.Tray;

import java.util.Iterator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:30
 * @Description :
 */
public class ListTray extends Tray {
    public ListTray(String caption) {
        super(caption);
    }
    @Override
    public String makeHTML() {
        StringBuilder builder = new StringBuilder();
        builder.append("<li>\n");
        builder.append(caption + "\n");
        builder.append("<ul>\n");
        Iterator it = tray.iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            builder.append(item.makeHTML());
        }
        builder.append("</ul>\n");
        builder.append("</li>\n");
        return builder.toString();
    }
}
