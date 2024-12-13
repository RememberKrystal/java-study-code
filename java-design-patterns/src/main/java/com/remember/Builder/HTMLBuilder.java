package com.remember.Builder;

import java.io.FileWriter;
import java.io.PrintWriter;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/27 22:48
 * @Description :
 */
public class HTMLBuilder extends Builder{

    private String filename;
    private PrintWriter writer; // 用于编写文件的printWriter
    @Override
    public void makeTitle(String title) {
        filename = title + ".html";
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.println("<html><head><title>" + title + "</title></head><body>");
        writer.println("<h1>" + title + "</h1>");
    }

    @Override
    public void makeString(String str) {
        writer.println("<p>" + str + "</p>");
    }

    @Override
    public void makeItems(String[] items) {
        writer.println("<ul>");
        for (String item : items) {
            writer.println("<li>" + item + "</li>");
        }
        writer.println("</ul>");
    }

    @Override
    public void close() {
        writer.println("</body></html>");
        writer.close();
    }

    public String getResult() {
        return filename;
    }
}
