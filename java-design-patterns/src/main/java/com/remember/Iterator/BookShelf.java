package com.remember.Iterator;

import java.util.ArrayList;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/17 21:42
 * @Description :
 */
public class BookShelf implements Aggregate {
    private ArrayList books;
    public BookShelf(int maxSize) {
        this.books = new ArrayList<>(maxSize); // 指定初始容量
    }
    public Book getBookAt(int index) {
        return (Book) books.get(index);
    }
    public void appendBook(Book book) {
        books.addLast(book); // 新增图书就放入集合的末尾
    }
    public int getLength() {
        return books.size(); // 返回长度
    }
    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
