package com.remember.ioStudy.serializationStream;

import com.remember.domain.po.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/7 11:22
 * @Description : 利用序列化流/反序列化流读写多个对象 (属于字节流下面的序列化流)
 */
public class ObjectStreamDemo2 {
    public static void main(String[] args) throws Exception {
        writeObject();
        readObject();
    }

    // 使用序列化流，写入多个对象
    private static void writeObject() throws Exception {
        Person person1 = new Person(1, "Remember", "Remember@qq.com");
        Person person2 = new Person(2, "Remember2", "Remember2@qq.com");
        Person person3 = new Person(3, "Remember3", "Remember3@qq.com");
        ArrayList<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        // 创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Code\\exercitation-experience-API\\person.txt"));
        oos.writeObject(list);
        oos.close();
    }

    // 使用反序列化流，读取多个对象
    private static void readObject() throws Exception {
        // 创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Code\\exercitation-experience-API\\person.txt"));
        ArrayList<Person> list = (ArrayList<Person>) ois.readObject();
        for (Person person : list) {
            System.out.println(person.toString());
        }
    }
}
