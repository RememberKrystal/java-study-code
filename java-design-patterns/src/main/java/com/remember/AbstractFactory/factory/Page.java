package com.remember.AbstractFactory.factory;

import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:16
 * @Description :
 */
public abstract class Page {
    protected String title;
    protected String author;
    protected ArrayList contents = new ArrayList();

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public abstract String makeHTML();

    public void add(Item item) {
        contents.add(item);
    }

    public void output() {
        String filename = title + ".html";
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.print(this.makeHTML());
            writer.close();
            System.out.println(filename + "编写完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
