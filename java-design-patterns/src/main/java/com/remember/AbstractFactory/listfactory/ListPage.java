package com.remember.AbstractFactory.listfactory;

import com.remember.AbstractFactory.factory.Item;
import com.remember.AbstractFactory.factory.Page;

import java.util.Iterator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:34
 * @Description :
 */
public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }
    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<html><head><title>" + title + "</title></head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>" + title + "</h1>\n");
        buffer.append("<ul>\n");
        Iterator it = contents.iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("<hr><address>" + author + "</address>");
        buffer.append("</body></html>\n");
        return buffer.toString();
    }
}
