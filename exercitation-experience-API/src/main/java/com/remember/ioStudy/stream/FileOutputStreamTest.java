package com.remember.ioStudy.stream;

import java.io.FileOutputStream;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 8:41
 * @Description : 字节输出流的基本使用
 */
public class FileOutputStreamTest {
    public static void main(String[] args) throws IOException {
        // 创建输出流
        FileOutputStream stream =
                new FileOutputStream("D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\a.txt",true);
        stream.write('\n'); // 换行符
        byte[] bytes = {'d','b','c'};
        stream.write(bytes);
        stream.close();
    }
}
