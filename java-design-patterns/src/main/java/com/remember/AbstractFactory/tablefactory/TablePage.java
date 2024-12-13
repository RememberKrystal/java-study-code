package com.remember.AbstractFactory.tablefactory;

import com.remember.AbstractFactory.factory.Item;
import com.remember.AbstractFactory.factory.Page;

import java.util.Iterator;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/12/3 21:42
 * @Description :
 */
public class TablePage extends Page {
    public TablePage(String title, String author) {
        super(title, author);
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<!DOCTYPE html>\n");
        buffer.append("<html><head><title>" + title + "</title><head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>" + title + "</h1>\n");
        buffer.append("<table width=\"80%\" border=\"3\">\n");
        Iterator it = contents.iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            buffer.append(item.makeHTML());
        }
        buffer.append("</table>\n");
        buffer.append("<hr><address>" + author + "</address>");
        buffer.append("</body></html>\n");
        return buffer.toString();
    }
}
