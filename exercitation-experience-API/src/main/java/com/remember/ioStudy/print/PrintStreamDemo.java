package com.remember.ioStudy.print;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/7 11:33
 * @Description : 字节打印流
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws Exception {
        // 创建字节打印流
        PrintStream ps =
                new PrintStream(new FileOutputStream("D:\\Code\\exercitation-experience-API\\printStream.txt",true));
        ps.println("hello world"); // 写出+自动刷新+换行
        ps.print("true");
        ps.println();
        ps.close();
    }
}
