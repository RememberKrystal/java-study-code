package com.remember.AbstractFactory.tablefactory;

import com.remember.AbstractFactory.factory.Link;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:40
 * @Description :
 */
public class TableLink extends Link {

    public TableLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "<td><a href=\"" + url + "\">" + caption + "</a></td>\n";
    }
}
