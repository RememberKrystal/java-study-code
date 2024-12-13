package com.remember.AbstractFactory.factory;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:07
 * @Description :
 */
public abstract class Link extends Item{
    protected String url;

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }
}
