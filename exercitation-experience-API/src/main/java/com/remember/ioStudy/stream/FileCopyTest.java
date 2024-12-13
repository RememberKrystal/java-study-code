package com.remember.ioStudy.stream;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 9:51
 * @Description : 文件拷贝
 */
public class FileCopyTest {
    public static void main(String[] args) throws IOException {
        String url = "D:\\PhotoAndVideo\\素材\\1.mp4";
        String copyUrl = "D:\\PhotoAndVideo\\素材\\1_copy.mp4";
        long time = fileCopy(url, copyUrl);
        System.out.println("文件拷贝耗时：" + time + "ms");

        boolean result = md5(url, copyUrl);
        System.out.println(result ? "文件拷贝成功" : "文件拷贝失败");
    }


    private static long fileCopy(String url, String copyUrl) throws IOException {
        long startTime = System.currentTimeMillis();
        // 创建输入流
        FileInputStream input = new FileInputStream(url);
        // 创建输出流
        FileOutputStream out = new FileOutputStream(copyUrl);
        // 创建缓存空间
        byte[] bytes = new byte[1024 * 1024 * 5]; // 5MB

        int b;
        while ((b = input.read(bytes)) != -1) {
            out.write(bytes, 0, b); // 写出数据
        }
        // 先创建，后关闭
        out.close();
        input.close();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }


    /**
     * 校验文件的MD5值是否相等
     *
     * @param url     原始文件url
     * @param copyUrl 拷贝文件的url
     * @return true：文件一致，false：文件不一致
     * @throws IOException 异常
     */
    private static boolean md5(String url, String copyUrl) throws IOException {
        // 创建输入流
        FileInputStream inputStream1 = new FileInputStream(url);
        FileInputStream inputStream2 = new FileInputStream(copyUrl);
        // 获取MD5值
        String md1 = DigestUtils.md5Hex(inputStream1);
        String md2 = DigestUtils.md5Hex(inputStream2);
        // 关闭输入流
        inputStream2.close();
        inputStream1.close();

        // 校验是否相等
        return md1.equals(md2);
    }
}
