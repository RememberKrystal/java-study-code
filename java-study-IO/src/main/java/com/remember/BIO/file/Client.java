package com.remember.BIO.file;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/15 14:13
 * @Description : 客户端
 */
public class Client {
    /*
        实现客户端上传任意类型的文件数据给服务端
     */
    public static void main(String[] args) {
        try{
            File file = new File("D:\\Code\\java-study-code\\exercitation-experience-API\\printStream.txt");
            // 1. 请求与服务端的连接
            Socket socket = new Socket("127.0.0.1", 8888);
            // 2. 把字节输出流包装成一个数据输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // 3. 先发送上传文件的后缀给服务端
            String suffix = file.getName().substring(file.getName().lastIndexOf("."));
            dos.writeUTF(suffix);
            // 4. 发送文件数据给服务端
            FileInputStream is = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                // 5. 把文件数据发送给服务端
                dos.write(buffer, 0, len);
            }
            dos.flush();
            socket.shutdownOutput(); // 通知服务端，数据发送完毕
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
