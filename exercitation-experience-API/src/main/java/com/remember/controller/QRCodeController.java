package com.remember.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.remember.methods.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.WriterException;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class QRCodeController {

    // 生成用户二维码
    @GetMapping("/generateQR/{userId}")
    public ResponseEntity<byte[]> generateUserQRCode(@PathVariable Long userId) throws WriterException, IOException {
        String qrContent = "http://localhost:8080/api/user/scan/" + userId;
//        String qrContent = "https://www.baidu.com";
        byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(qrContent, 250, 250);

        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(qrCodeImage);
    }

    // 扫码查看用户信息
    @GetMapping("/scan/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userId) {
        // 返回用户信息的逻辑
        /*Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }*/
        return ResponseEntity.ok("是会员，会员id为XXX"); // 返回相关信息
    }

    // 确认核销
    @PostMapping("/verify/{userId}")
    public ResponseEntity<String> verifyUser(@PathVariable Long userId) {
        // 核销成功的逻辑
        /*boolean verified = userService.verifyUser(userId);
        if (verified) {
            return ResponseEntity.ok("Verification successful");
        } else {
            return ResponseEntity.status(400).body("User already verified or does not exist");
        }*/
        return ResponseEntity.ok("核销成功");
    }

    /**
     * 生成图片验证码-hutool
     */
    @GetMapping("/imgCode")
    public ResponseEntity<byte[]> getImgCode(){
        // 创建验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(160, 60, 4, 30);
        String code = lineCaptcha.getCode(); // 获取验证码

        // 获取图像的字节数组并返回
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(lineCaptcha.getImageBytes());
    }
}
