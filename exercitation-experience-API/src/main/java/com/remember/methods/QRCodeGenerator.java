package com.remember.methods;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/7 16:23
 * @Description : 二维码生成
 */
public class QRCodeGenerator {
    /**
     * 生成二维码图像
     *
     * @param text   二维码内容，可以是URL或者任何文本
     * @param width  二维码图像的宽度
     * @param height 二维码图像的高度
     * @return 二维码图像的字节数组
     * @throws WriterException 如果二维码生成失败
     * @throws IOException     如果写入图像失败
     */
    public static byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        // 创建 QRCodeWriter 实例
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // 使用 QRCodeWriter 生成二维码的位矩阵
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        // 将位矩阵转换为图像并输出为字节数组
        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            // 使用 MatrixToImageWriter 将 BitMatrix 转换为 PNG 格式
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        }
    }
}
