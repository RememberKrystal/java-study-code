package com.remember.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            //绑定服务端ip和端口号
            clientSocket = new Socket("localhost", 8888);
            System.out.println("连接服务端成功！");
            //获取输入流，接收服务端消息
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //获取输出流,给服务端发送消息
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("给服务端发送消息：");
                String msg = scanner.nextLine();
                out.println(msg);

                String response;
                if ((response = in.readLine()) != null) {
                    //接收服务端响应
                    System.out.println("服务端响应：" + response);
                }
            }
        } catch (IOException e) {
            System.out.println("连接服务端失败：" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("关闭资源失败：" + e.getMessage());
            }
        }
    }
}
