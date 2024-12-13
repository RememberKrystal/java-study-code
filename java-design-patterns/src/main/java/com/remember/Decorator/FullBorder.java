package com.remember.Decorator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/12 21:53
 * @Description :
 */
public class FullBorder extends Border {
    public FullBorder(Display display) {
        super(display);
    }

    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1; // 列数
    }

    @Override
    public int getRows() {
        return 1 + display.getRows() + 1; // 行数
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {
            return "+" + makeLine('-', display.getColumns()) + "+"; // 返回上边框
        } else if (row == display.getRows() + 1) {
            return "+" + makeLine('-', display.getColumns()) + "+"; // 返回下边框
        } else {
            return "|" + display.getRowText(row - 1) + "|"; // 返回中间行
        }
    }

    private String makeLine(char ch, int count) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < count; i++) {
            buf.append(ch);
        }
        return buf.toString();
    }
}
