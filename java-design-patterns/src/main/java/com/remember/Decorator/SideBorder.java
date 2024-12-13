package com.remember.Decorator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/12 21:48
 * @Description :
 */
public class SideBorder extends Border{
    private char borderChar; // 表示装饰边框的字符

    // 通过构造函数指定Display和装饰边框字符
    public SideBorder(Display display, char borderChar) {
        super(display);
        this.borderChar = borderChar;
    }
    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    @Override
    public int getRows() {
        return display.getRows();  // 行数即被装饰物的行数
    }

    @Override
    public String getRowText(int row) {
        return borderChar + display.getRowText(row) + borderChar;
    }
}
