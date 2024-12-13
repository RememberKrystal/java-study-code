package com.remember.AIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 服务端
 */
public class AIOServer {
    public static void main(String[] args) throws Exception {
        // 创建一个新的异步服务器套接字通道，绑定到指定的端口上
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(5000));
        System.out.println("服务端启动成，等待客户端连接。");
        // 开始接受新的客户端连接
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel clientChannel, Void att) {
                // 当一个新的连接完成时，再次接受新的客户端连接
                serverChannel.accept(null, this);

                // 创建一个新的缓冲区来读取数据
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                try {
                    InetSocketAddress clientAddress = (InetSocketAddress) clientChannel.getRemoteAddress();
                    InetAddress clientIP = clientAddress.getAddress();
                    int clientPort = clientAddress.getPort();
                    System.out.println("客户端 "+ clientIP + ":" + clientPort + " 连接成功。");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 从异步套接字通道中读取数据
                clientChannel.read(buffer, buffer, new ReadCompletionHandler(clientChannel));
            }
            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("Failed to accept a connection");
            }
        });
        // 保持服务器开启
        Thread.sleep(Integer.MAX_VALUE);
    }
}
