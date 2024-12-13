package com.remember.ioStudy.stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 17:03
 * @Description : 文件的加密解密（异或运算来实现）
 */
public class Test02 {
    public static void main(String[] args) throws IOException {
        String url = "D:\\Code\\exercitation-experience-API\\images.jpg";
        String encryptUrl = "D:\\Code\\exercitation-experience-API\\encryptImages.jpg";
        encrypt(url, encryptUrl);
    }

    /*
     * 加密
     */
    private static void encrypt(String filePath,String encryptPath) throws IOException {
        // 创建字节输入流
        FileInputStream inputStream = new FileInputStream(filePath);
        // 创建字节输出流
        FileOutputStream outputStream = new FileOutputStream(encryptPath);
        int len;
        while ((len = inputStream.read()) != -1) {
            outputStream.write(len ^ 2004); // 异或运算，2004为秘钥
        }
        // 关流
        outputStream.close();
        inputStream.close();
    }
}
