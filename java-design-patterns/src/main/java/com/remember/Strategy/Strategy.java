package com.remember.Strategy;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/10 20:34
 * @Description :
 */
public interface Strategy {
    Hand nextHand();
    void study(boolean win);
}
