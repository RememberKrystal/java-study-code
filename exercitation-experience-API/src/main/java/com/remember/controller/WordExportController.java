package com.remember.controller;

import com.remember.domain.po.DocRequestBody;
import com.remember.service.WordExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/4/7 14:54
 * @Description : 导出文档（自动调整字体大小适应文本框）
 */
@RestController
@RequestMapping("/word")
public class WordExportController {

    private final WordExportService wordExportService;

    public WordExportController(WordExportService wordExportService) {
        this.wordExportService = wordExportService;
    }

    @PostMapping(value = "/export")
    public ResponseEntity<byte[]> exportWord(@RequestBody DocRequestBody docRequestBody) {
        try {
            String content = docRequestBody.getContent();
            // 1. 生成文档内容
            byte[] wordContent = wordExportService.generateWord(content).toByteArray();

            // 2. 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "export.docx");
            headers.setContentLength(wordContent.length);

            // 3. 返回响应实体
            return new ResponseEntity<>(wordContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Word document", e);
        }
    }
}
