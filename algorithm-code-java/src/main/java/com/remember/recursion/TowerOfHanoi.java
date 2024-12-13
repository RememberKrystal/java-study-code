package com.remember.recursion;

import java.util.LinkedList;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/1 9:34
 * @Description : 递归-汉诺塔
 */
public class TowerOfHanoi {
    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    static void init(int n) { // 加盘子 [1,2,3,4]
        for (int i = 1; i <= n; i++) {
            a.add(i);
        }
    }

    public static void main(String[] args) {
        init(5);
        print();
        move(5, a, b, c);
        print();
    }

    static void print() { // 打印三根柱的初始状态
        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("C: " + c);
    }

    static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
        if (n == 1) {
            c.addFirst(a.removeFirst());
        } else {
            move(n - 1, a, c, b); // 先将n-1个盘子从a移动到b
            c.addFirst(a.removeFirst());
            move(n - 1, b, a, c); // 将n-1个盘子从b移动到c
        }
    }
}
