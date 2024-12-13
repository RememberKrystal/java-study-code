package com.remember.NIO;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 9999));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isConnectable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();
                    }

                    System.out.print("Enter message to server: ");
                    Scanner scanner = new Scanner(System.in);
                    String message = scanner.nextLine();
                    ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                    client.write(buffer);

                    client.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    client.read(buffer);
                    String output = new String(buffer.array()).trim();
                    System.out.println("来自客户端的消息: " + output);

                    System.out.print("输入消息: ");
                    // 和服务端代码一样
                    NIOServer.writeMessage(selector, client, buffer);
                }
                keyIterator.remove();
            }
        }
    }
}
