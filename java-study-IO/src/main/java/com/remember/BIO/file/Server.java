package com.remember.BIO.file;

import java.net.ServerSocket;
import java.net.Socket;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/15 14:13
 * @Description : 服务端
 */
public class Server {
    /*
        服务端开发，可以实现接收客户端的文件，并保存到本地
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true){
                // 1. 等待客户端的连接
                Socket socket = ss.accept();
                // 2. 创建一个线程，把socket对象作为参数传递给线程
                ServerReaderThread thread = new ServerReaderThread(socket);
                // 3. 启动线程
                thread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
