package com.remember.Iterator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/17 21:44
 * @Description :
 */
public class BookShelfIterator implements Iterator{
    private BookShelf bookShelf;
    private int index;
    public BookShelfIterator(BookShelf bookShelf){
        this.bookShelf = bookShelf;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        if (index < bookShelf.getLength()){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
