package com.remember.BIO;

import java.io.*;
import java.net.*;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            //创建服务端
            serverSocket = new ServerSocket(8888);
            System.out.println("服务端已启动，等待客户端连接...");

            while (true) {
                // 监听客户端请求，接收不到请求会一直等待
                clientSocket = serverSocket.accept();
                int port = clientSocket.getPort();
                InetAddress inetAddress = clientSocket.getInetAddress();
                System.out.println("客户端 " + inetAddress + ":" + port + " 连接成功！");
                //处理客户端消息
                new Thread(new ServerThread(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("客户端连接失败：" + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("关闭资源失败：" + e.getMessage());
            }
        }
    }
}