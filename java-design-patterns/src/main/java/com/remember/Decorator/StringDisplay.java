package com.remember.Decorator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/12 21:15
 * @Description :
 */
public class StringDisplay extends Display{
    private String string; // 显示的字符串
    public StringDisplay(String string) {
        this.string = string;  // 通过构造函数给字符串赋值
    }
    @Override
    public int getColumns() {
        return string.getBytes().length;  // 字符数
    }

    @Override
    public int getRows() {
        return 1;   // 行数是1
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {
            return string;  // 返回字符串
        } else {
            return null;  // 返回null
        }
    }
}
