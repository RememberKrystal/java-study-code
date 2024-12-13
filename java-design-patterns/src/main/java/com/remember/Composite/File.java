package com.remember.Composite;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/10 22:22
 * @Description :
 */
public class File extends Entry{
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override // 重写父类方法
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
