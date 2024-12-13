package com.remember.Adapter1;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/18 21:48
 * @Description :
 */
public class PrintBanner extends Banner implements Print{
    public PrintBanner(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
