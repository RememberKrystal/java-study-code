package com.remember.ioStudy.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 9:02
 * @Description : 字节输入流的基本使用
 */
public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        methods1();
    }

    /**
     * 循环读取
     */
    private static void methods1() throws IOException {
        // 创建输入流
        FileInputStream fis =
                new FileInputStream("D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\a.txt");
        File file = new File("D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\a.txt");
        // 获取文件的长度
        long length = file.length();
        for (int i = 0; i < length; i++){
            int readByte = fis.read();
            System.out.print((char)readByte);
        }
        // 关闭流
        fis.close();
    }

    /**
     * 循环读取
     */
    private static void method2() throws IOException{
        // 创建输入流
        FileInputStream fis =
                new FileInputStream("D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\a.txt");
        int b;
        while ((b = fis.read()) != -1){
            System.out.print((char)b);
        }
        fis.close();
    }
}
