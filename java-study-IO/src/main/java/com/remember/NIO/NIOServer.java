package com.remember.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        // 创建一个ServerSocketChannel并绑定到指定的端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 将ServerSocketChannel注册到Selector上，并监听OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器已启动，等待客户端连接...");

        while (true) {
            // 阻塞，等待事件发生
            selector.select();

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {     // 处理连接请求事件
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);
                    //监听OP_ACCEPT事件
                    client.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    client.getRemoteAddress();
                    //分配缓存区容量
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    client.read(buffer);
                    String output = new String(buffer.array()).trim();

                    Socket socket = client.socket();
                    InetAddress inetAddress = socket.getInetAddress();
                    int port = socket.getPort();
                    String clientInfo = inetAddress+":"+port;
                    String message = String.format("来自客户端 %s , 消息：%s", clientInfo , output);
                    System.out.println(message);

                    System.out.print("回复消息: ");
                    writeMessage(selector, client, buffer);
                }

                keyIterator.remove();
            }
        }
    }

    public static void writeMessage(Selector selector, SocketChannel client, ByteBuffer buffer) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        buffer.clear();
        buffer.put(message.getBytes());
        //从写模式切换到读模式
        buffer.flip();
        while (buffer.hasRemaining()) {
            client.write(buffer);
        }

        //  重新监听OP_ACCEPT事件
        client.register(selector, SelectionKey.OP_READ);
    }
}
