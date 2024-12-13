package com.remember.Strategy;

import java.util.Random;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/10 20:34
 * @Description :
 */
public class WinningStrategy implements Strategy{
    private Random random;
    private boolean won = false; // 上一局结果
    private Hand prevHand; // 上一句手势
    public WinningStrategy(int seed) {
        random = new Random(seed);
    }
    @Override
    public Hand nextHand() {
        if (!won) {
            prevHand = Hand.getHand(random.nextInt(3));
        }
        return prevHand;
    }

    @Override
    public void study(boolean win) {
        won = win;
    }
}
