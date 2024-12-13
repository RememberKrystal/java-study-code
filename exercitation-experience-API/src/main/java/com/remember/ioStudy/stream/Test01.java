package com.remember.ioStudy.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 16:38
 * @Description : 拷贝文件夹（字节输出、输入流）
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        File src = new File("D:\\aa_test\\a"); // 数据源
        File dest = new File("D:\\aa_test\\b");
        copyFolder(src, dest);
    }

    public static void copyFolder(File src, File dest) throws IOException {
        dest.mkdirs();
        // 1. 进入数据源
        File[] files = src.listFiles();
        // 2. 遍历数据源
        for (File file : files) {
            // 3. 判断是文件还是文件夹
            if (file.isDirectory()) {
                // 是文件夹，递归
                copyFolder(file, new File(dest, file.getName()));
            } else if (file.isFile()) {
                // 是文件，直接拷贝
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(dest, file.getName()));
                byte[] bytes = new byte[1024];
                int len;
                while ((len = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
                fos.close();
                fis.close();
            }
        }
    }
}
