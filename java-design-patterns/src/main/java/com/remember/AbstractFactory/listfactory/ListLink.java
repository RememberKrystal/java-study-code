package com.remember.AbstractFactory.listfactory;

import com.remember.AbstractFactory.factory.Link;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:29
 * @Description :
 */
public class ListLink extends Link {

    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "<li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
