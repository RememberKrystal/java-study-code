package com.remember.Bridge;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/5 22:34
 * @Description :
 */
public class CountDisplay extends Display{
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }
    public void multiDisplay(int times) {
        open(); // super.open(); 调用的是父类的方法
        for (int i = 0; i < times; i++) {
            print(); // super.print();
        }
        close(); // super.close();
    }
}
