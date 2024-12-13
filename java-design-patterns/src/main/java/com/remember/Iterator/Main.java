package com.remember.Iterator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/17 21:46
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("A"));
        bookShelf.appendBook(new Book("B"));
        bookShelf.appendBook(new Book("C"));
        bookShelf.appendBook(new Book("D"));
        bookShelf.appendBook(new Book("E"));
        bookShelf.appendBook(new Book("F"));
        Iterator it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = (Book) it.next();
            System.out.println(book.getName());
        }
    }
}
