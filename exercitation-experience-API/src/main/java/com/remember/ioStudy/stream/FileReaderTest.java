package com.remember.ioStudy.stream;

import java.io.FileReader;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 11:14
 * @Description : 字符输入流(处理文本数据)
 */
public class FileReaderTest {
    public static void main(String[] args) throws IOException{
        String url = "D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\a.txt";
        readFile2(url);
    }

    /**
     * 利用空参的read的方法，逐个读取文件内容
     */
    private static void readFile(String fileUrl) throws IOException {
        // 创建字符输入流
        FileReader fileReader = new FileReader(fileUrl);
        int ch;
        while ((ch = fileReader.read()) != -1){
            System.out.print((char) ch);
        }
        // 关流
        fileReader.close();
    }

    /**
     * 带参的read方法，一次读取多个字符
     */
    private static void readFile2(String fileUrl) throws IOException {
        // 创建字符输入流
        FileReader fileReader = new FileReader(fileUrl);
        char[] chs = new char[2];
        int len;
        while ((len = fileReader.read(chs)) != -1){
            System.out.print(new String(chs, 0, len));
        }
        // 关流
        fileReader.close();
    }
}
