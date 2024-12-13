package com.remember.ioStudy.convertStream;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/7 9:26
 * @Description : 转换流的基本使用(属于字符流下面的转换流)
 */
public class ConvertStreamDemo1 {
    public static void main(String[] args) throws Exception {
        writeGBK2();
    }

    /**
     * 读取GBK编码的文件(JDK11之后已淘汰)
     */
    private static void readGBK1() throws Exception{
        // 创建字符转换输入流，并指定字符编码
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\Code\\exercitation-experience-API\\GBK.txt"),"GBK");
        int len;
        while ((len = isr.read()) != -1){
            System.out.print((char) len);
        }
        isr.close();
    }


    /*
     * 读取编码格式为GBK的文件(推荐)
     * JDK11 以上版本，可以直接使用FileReader读取GBK编码的文件，不需要再使用InputStreamReader进行转换。
     */
    private static void readGBK2() throws Exception{
        // 创建字符输入流，并指定字符编码
        FileReader fileReader = new FileReader("D:\\Code\\exercitation-experience-API\\GBK.txt", Charset.forName("GBK"));
        int len;
        while ((len = fileReader.read()) != -1){
            System.out.print((char) len);
        }
        // 关流
        fileReader.close();
    }

    /**
     * 按照指定的编码，写出数据(JDK11之后已淘汰)
     */
    private static void writeGBK1() throws Exception{
        // 创建字符转换输出流，并指定字符编码
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\Code\\exercitation-experience-API\\GBK.txt"),"GBK");
        osw.write("你好，世界！使用的是字节转换流");
        osw.close();
    }

    /**
     * 按照指定的编码，写出数据(推荐)
     * JDK11 以上版本，可以直接使用FileWriter写出GBK编码的文件，不需要再使用OutputStreamWriter进行转换。
     */
    private static void writeGBK2() throws Exception{
        // 创建字符输出流，并指定字符编码
        FileWriter fileWriter = new FileWriter("D:\\Code\\exercitation-experience-API\\GBK.txt", Charset.forName("GBK"));
        fileWriter.write("你好，世界！使用的是字符输出流");
        fileWriter.close();
    }
}
