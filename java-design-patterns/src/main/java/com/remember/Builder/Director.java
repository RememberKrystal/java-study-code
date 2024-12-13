package com.remember.Builder;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/27 22:44
 * @Description :
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder; // 因为接受的参数是Builder类的子类，所以保存在builder字段中
    }

    public void construct() {
        builder.makeTitle("Greeting");
        builder.makeString("从早上到下午");
        builder.makeItems(new String[]{"早上好。", "下午好。"});
        builder.makeString("晚上");
        builder.makeItems(new String[]{"晚上好。", "晚安。", "再见。"});
        builder.close(); // 完成文档
    }
}
