package com.remember.Strategy;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/10 20:29
 * @Description :
 */
public class Hand {
    public static final int HANDVALUE_GUU = 0; // 表示石头的值
    public static final int HANDVALUE_CHO = 1; // 表示剪刀的值
    public static final int HANDVALUE_PAA = 2; // 表示布的值
    private static final Hand[] hand = {
            new Hand(HANDVALUE_GUU),
            new Hand(HANDVALUE_CHO),
            new Hand(HANDVALUE_PAA)
    };

    private static final String[] name = { // 表示猜拳中手势所对应的字符串
            "石头", "剪刀", "布"
    };

    private int handValue; // 猜拳中出的手势的值

    private Hand(int handValue) {
        this.handValue = handValue;
    }

    public static Hand getHand(int handValue) { // 根据手势的值获取其对应的实例
        return hand[handValue];
    }
    public boolean isStrongerThan(Hand h) { // 如果this胜了h则返回true
        return fight(h) == 1;
    }
    public boolean isWeakerThan(Hand h) { // 如果this输给了h则返回true
        return fight(h) == -1;
    }
    private int fight(Hand h) { // 计分 平 0 胜 1 输 -1
        if (this == h) {
            return 0;
        } else if ((this.handValue + 1) % 3 == h.handValue) {
            return 1;
        } else {
            return -1;
        }
    }
    public String toString() { // 转换为手势值所对应的字符串
        return name[handValue];
    }
}
