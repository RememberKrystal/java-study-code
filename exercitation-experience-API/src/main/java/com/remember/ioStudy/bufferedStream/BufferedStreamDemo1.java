package com.remember.ioStudy.bufferedStream;

import java.io.*;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 20:40
 * @Description : 利用字节缓冲流拷贝文件
 */
public class BufferedStreamDemo1 {
    public static void main(String[] args) throws IOException {
        String url = "D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\text.txt";
        String copyUrl = "D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\copyText.txt";

        copy2(url, copyUrl);
    }

    public static void copy(String url, String copyUrl) throws IOException {
        // 创建字节缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(url));
        // 创建字节缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyUrl));

        // 循环读取并写入到目的地
        int len;
        while ((len = bis.read()) != -1) {
            bos.write(len);
        }

        // 关流
        bos.close();
        bis.close();
    }

    /*
     * 一次读取多个字节
     */
    public static void copy2(String url, String copyUrl) throws IOException {
        // 创建字节缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(url));
        // 创建字节缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyUrl));

        byte[] bytes = new byte[1024];
        // 循环读取并写入到目的地
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        // 关流
        bos.close();
        bis.close();
    }
}
