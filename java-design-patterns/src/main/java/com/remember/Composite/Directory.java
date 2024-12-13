package com.remember.Composite;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/10 22:24
 * @Description :
 */
public class Directory extends Entry{

    private String name;
    private ArrayList directory = new ArrayList(); // 文件夹中的目录条目集合

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        Iterator iterator = directory.iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            size += entry.getSize();
        }
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        Iterator iterator = directory.iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            entry.printList(prefix + "/" + name);
        }
    }

    @Override
    public Entry add(Entry entry) throws FileTreatmentException { // 添加目录条目
        directory.add(entry);
        return this;
    }
}
