package com.remember.AIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;

/**
 * 客户端
 */
public class AIOClient {
    public static void main(String[] args) throws Exception {
        // 创建一个新的异步套接字通道
        AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();

        // 连接到服务器
        clientChannel.connect(new InetSocketAddress("localhost", 5000), null, new CompletionHandler<Void, Void>() {
            @Override
            public void completed(Void result, Void attachment) {
                System.out.println("连接到服务端成功。");
            }
            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("Failed to connect server");
            }
        });

        // 从键盘读取输入
        Scanner scanner = new Scanner(System.in);
        System.out.print("发送消息: ");
        String message = scanner.nextLine();

        // 写入数据到异步套接字通道
        clientChannel.write(ByteBuffer.wrap(message.getBytes()), null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                clientChannel.read(buffer, buffer, new ReadCompletionHandler(clientChannel));
            }
            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("Failed to write message");
            }
        });

        // 保持客户端开启
        Thread.sleep(Integer.MAX_VALUE);
    }
}