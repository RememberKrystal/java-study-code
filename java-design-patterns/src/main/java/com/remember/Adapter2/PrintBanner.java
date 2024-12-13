package com.remember.Adapter2;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/18 21:48
 * @Description :
 */
public class PrintBanner extends Print{
    private Banner banner;  // 这里调用了Banner类，目的就是为了方法不在本类中实现，而是通过Banner类来完成
    public PrintBanner(String string) {
        this.banner = new Banner(string);
    }
    public void printStrong() { // 重写了print类里面的两个抽象方法
        banner.showWithAster();
    }
    public void printWeak() {
        banner.showWithParen();
    }
}
