package com.remember.ioStudy.bufferedStream;

import java.io.*;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 21:48
 * @Description : 字符缓冲流的基本使用
 */
public class BufferedStreamDemo2 {
    public static void main(String[] args) throws IOException {
        String url = "D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\text.txt";
        String copyUrl = "D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\copyText.txt";
        read(url);
        write(copyUrl);
    }

    // 字符缓冲输入流读取文件
    private static void read(String url) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(url));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

    // 字符缓冲输出流写入文件
    private static void write(String url) throws IOException {
        // 创建字符缓冲输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter(url, true));
        // 写出数据
        bw.write("微风正好，你我不燥！");
        bw.newLine(); // 换行
        bw.write("hello world");
        bw.newLine();
        // 释放资源
        bw.close();

    }
}
