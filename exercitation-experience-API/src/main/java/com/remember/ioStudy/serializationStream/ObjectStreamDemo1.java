package com.remember.ioStudy.serializationStream;

import com.remember.domain.po.Person;

import java.io.*;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/7 10:38
 * @Description : 序列化流/反序列化流的基本使用
 */
public class ObjectStreamDemo1 {
    public static void main(String[] args) throws Exception {
        readObject();
    }

    /**
     * 利用序列化流/对象操作输出流，把一个对象写到本地文件中
     * @throws Exception
     */
    private static void writeObject() throws Exception{
        Person person = new Person(1, "Remember", "Remember@qq.com");
        // 创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Code\\exercitation-experience-API\\person.txt"));
        // 写出数据
        oos.writeObject(person);
        // 关流
        oos.close();
    }

    /**
     * 利用反序列化流/对象操作输入流，把一个对象从本地文件中读出
     */
    private static void readObject() throws IOException, ClassNotFoundException {
        // 创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Code\\exercitation-experience-API\\person.txt"));
        // 读取数据
        Person person = (Person) ois.readObject(  );
        // 输出
        System.out.println(person.toString());
        // 关流
        ois.close();}
}
