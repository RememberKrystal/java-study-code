package com.remember.BIO.file;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.UUID;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/15 14:25
 * @Description :
 */
public class ServerReaderThread extends Thread {
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1.得到一个数据输入流读取客户端发送的数据
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 2.读取客户端发送的文件类型
            String suffix = dis.readUTF();
            System.out.println("客户端发送的文件类型是：" + suffix);
            // 3.定义一个字节输出管道负责把客户端发送的文件保存到本地
            FileOutputStream fos = new FileOutputStream("D:\\Code\\java-study-code\\java-study-IO\\src\\main\\java\\com\\remember\\bio\\file\\"
                    + UUID.randomUUID() + suffix);
            // 4.读取客户端发送的数据
            byte[] bytes = new byte[1024];
            int len;
            while ((len = dis.read(bytes)) != -1) {
                // 5.把读取到的数据写到本地
                fos.write(bytes, 0, len);
            }
            // 6.关闭资源
            fos.close();
            System.out.println("文件保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
