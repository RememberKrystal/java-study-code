package com.remember.test;
import java.net.Socket;

/**
 * 测试SMTP服务器是否可用
 */
public class SMTPConnectionTest {
    public static void main(String[] args) {
        String smtpServer = "smtp.qq.com";
        int port = 465;

        try (Socket socket = new Socket(smtpServer, port)) {
            System.out.println("Successfully connected to " + smtpServer + " on port " + port);
        } catch (Exception e) {
            System.out.println("Failed to connect to " + smtpServer + " on port " + port);
            e.printStackTrace();
        }
    }
}

