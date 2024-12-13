package com.remember.linkedList;

import junit.framework.TestCase;
import org.junit.Test;
public class SinglyLinkedListTest{

    @Test
    public void test1() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        list.loop2(value -> {
            // 打印输出
            System.out.println(value);
        });
    }
    @Test
    public void test2() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    public void test3() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    public void test4() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        int value = list.get(2);
        System.out.println(value);
    }

    @Test
    public void test5() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);


        for (Integer value : list) {
            System.out.println(value);
        }

        System.out.println("--------------");

        // 在指定位置插入元素
        list.add(0, 4);
        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    public void test6() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);


        for (Integer value : list) {
            System.out.println(value);
        }

        System.out.println("--------------");

        // 删除指定的元素
        list.remove(2);
        for (Integer value : list) {
            System.out.println(value);
        }
    }
}