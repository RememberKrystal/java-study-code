package com.remember.linkedList;

import java.util.Iterator;
import java.util.function.Consumer;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/10/13 17:45
 * @Description : 单向链表
 */
public class SinglyLinkedList implements Iterable<Integer> {
    private Node head = null; // 头指针

    /**
     * 遍历链表3
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head;

            @Override
            public boolean hasNext() { // 是否还有下一个元素
                return p != null;
            }

            @Override
            public Integer next() { // 返回当前值，并指向下一个值
                int value = p.value; // 记录当前值
                p = p.next; // 指向下一个节点
                return value;
            }
        };
    }

    /**
     * 节点类
     */
    private static class Node {
        int value; // 值
        Node next; // 下一个节点指针

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 在头部添加元素
     *
     * @param value -> 待添加的值
     */
    public void addFirst(int value) {
        head = new Node(value, head);
    }

    /**
     * 遍历链表1
     */
    public void loop1(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            // 让指针后移
            p = p.next;
        }
    }

    /**
     * 遍历链表2
     *
     * @param consumer
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     * 查找链表最后一个值
     *
     * @return 尾部节点
     */
    private Node findLast() {
        // 链表为空
        if (head == null) {
            return null;
        }
        Node p = head;
        while (p.next != null) { // 循环到链表尾部
            p = p.next;
        }
        return p; // 返回最后一个节点
    }

    /**
     * 在尾部添加元素
     *
     * @param value -> 待添加的值
     */
    public void addLast(int value) {
        // 查找最后一个节点
        Node last = findLast();
        if (last == null) {
            // 链表为空，则将新节点作为头节点
            head = new Node(value, null);
            return;
        }
        // 将新节点指向尾部节点
        last.next = new Node(value, null);
    }

    /**
     * 获取指定位置的元素
     *
     * @param index->索引
     * @return 节点值
     */
    public int get(int index) {
        int i = 0;// 定义一个计数器
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p.value; // 找到对应位置的元素,返回值
            }
        }
        throw new IllegalArgumentException("index[" + index + "]索引越界");
    }

    /**
     * 向指定索引位置插入新节点
     */
    public void add(int index, int value) {
        // 判断是否为头部插入
        if (index == 0) {
            addFirst(value);
            return;
        }
        // 判断索引是否越界
        get(index - 1); // 这里调用get方法，判断是否越界，这里-1考虑到尾部插入的情况

        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == (index - 1)) { // 定位到目标索引的上一个节点
                p.next = new Node(value, p.next);
                break;
            }
        }
    }

    /**
     * 删除第一个元素
     */
    public void removeFirst() {
        if (head == null){
            throw new IllegalArgumentException("链表为空");
        }
        head = head.next;
    }

    /**
     * 删除指定位置的元素
     * @param index
     */
    public void remove(int index){
        // 判断是否为空
        if (head == null){
            throw new IllegalArgumentException("链表为空");
        }
        // 判断是否为第一个元素
        if (index == 0){
            removeFirst();
            return;
        }
        // 判断索引是否越界
        get(index);

        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index - 1){ // 找到目标索引的上一个节点
                p.next = p.next.next;
                break;
            }
        }
    }

}
