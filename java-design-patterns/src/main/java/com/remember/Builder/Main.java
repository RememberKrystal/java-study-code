package com.remember.Builder;

import java.security.PublicKey;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/27 22:55
 * @Description :
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1){
            usage();
            System.exit(0);
        }
        if (args[0].equals("plain")){
            TextBuilder builder = new TextBuilder();
            Director director = new Director(builder);
            director.construct();

            String result = builder.getResult();
            System.out.println(result);
        }else if (args[0].equals("html")){
            HTMLBuilder builder = new HTMLBuilder();
            Director director = new Director(builder);
            director.construct();

            String result = builder.getResult();
            System.out.println(result);
        }
    }
    public static void usage(){
        System.out.println("Usage: java Main plain 编写纯文本文档");
        System.out.println("Usage: java Main html 编写HTML文档");
    }
}
