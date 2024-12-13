package com.remember.AIO;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        // 当读取完成时，反转缓冲区并打印出来
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        System.out.println("收到的消息: " + new String(bytes , StandardCharsets.UTF_8));
        attachment.clear();

        // 从键盘读取输入
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入消息: ");
        String message = scanner.nextLine();
        System.out.println();
        // 写入数据到异步套接字通道
        channel.write(ByteBuffer.wrap(message.getBytes()));

        channel.read(attachment , attachment , new ReadCompletionHandler(channel));
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("Failed to read message");
    }
}
