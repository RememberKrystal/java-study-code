package com.remember.ioStudy.print;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/7 11:34
 * @Description : 字符打印流
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws IOException {
        String url = "D:\\Code\\exercitation-experience-API\\printStream.txt";

        PrintWriter pw = new PrintWriter(new FileOutputStream(url,true),true);
        // 写出数据
        pw.println("hello world");
        pw.println("true");

        // 关流
        pw.close();
    }
}
