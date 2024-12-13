package com.remember.ioStudy.stream;

import java.io.FileWriter;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 11:14
 * @Description : 字符输出流
 */
public class FileWriterTest {
    public static void main(String[] args) throws IOException{
        String url = "D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\a.txt";
        writeFile(url);
    }
    private static void writeFile(String url) throws IOException {
        // 创建字符输出流
        FileWriter fileWriter = new FileWriter(url,true); // 开启续写
        fileWriter.write("\r\n"); // 换行
        fileWriter.write("你好世界！");
        // 关流
        fileWriter.close();
    }
}
