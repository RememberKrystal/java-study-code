package com.remember.TemplateMethod;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/21 21:44
 * @Description : 具体实现类
 */
public class CharDisplay extends AbstractDisplay{
    private char ch;
    public CharDisplay(char ch) {
        this.ch = ch;
    }
    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.print(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
