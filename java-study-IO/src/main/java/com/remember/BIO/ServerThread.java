package com.remember.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 服务端线程处理类
 */
class ServerThread implements Runnable {

    private Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        //获取客户端输入流以便接收客户端数据
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //获取客户端输出流以便向客户端发送数据
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

            int port = clientSocket.getPort();
            InetAddress inetAddress = clientSocket.getInetAddress();
            String address = inetAddress + ":" + port;

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //接收客户端消息
                System.out.println("客户端" + address + "发来消息：" + inputLine);
                //给客户端发送消息
                out.println("服务端已接收到消息并回复：" + inputLine);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
