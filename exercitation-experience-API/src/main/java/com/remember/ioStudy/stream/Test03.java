package com.remember.ioStudy.stream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/6 17:25
 * @Description : 修改（文本）文件中的数据：字符流
 */
public class Test03 {
    /*
        文本文件中有以下的数据：
            2-1-4-3-5
        将文件中的数据进行排序，变成以下的数据：
            1-2-3-4-5
     */
    public static void main(String[] args) throws IOException {
        String url = "D:\\Code\\exercitation-experience-API\\src\\main\\java\\com\\remember\\ioStudy\\a.txt";

        ArrayList<String> list = new ArrayList<>();
        // 读取数据
        FileReader reader = new FileReader(url);
        int len;
        while ((len = reader.read()) != -1) {
            list.add(String.valueOf((char) len));
        }
        ArrayList<Integer> newList = new ArrayList<>();
        for (String s : list) {
            if (!s.equals("-")) {
                newList.add(Integer.parseInt(s));
            }
        }
        // 排序
        Collections.sort(newList);
        // 写出数据
        FileWriter fileWriter = new FileWriter(url);
        for (Integer i : newList) {
            if (!i.equals(newList.getLast())) {
                fileWriter.write(i + "-");
            } else {
                fileWriter.write(i + "");
            }
        }
        // 关流
        fileWriter.close();
        reader.close();
    }
}
