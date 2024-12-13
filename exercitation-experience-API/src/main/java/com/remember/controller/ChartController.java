package com.remember.controller;

import com.remember.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/17 17:14
 * @Description : 根据数据集合生成图片和表格
 */
@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/export")
    public String exportData() {
        return chartService.createChart();
    }

    /**
     * 处理文件下载请求。
     * 通过文件键（fileKey）获取文件内容，并以流的形式返回给客户端，以便客户端可以下载文件。
     *
     * @param fileKey 用于标识文件的键，通过此键获取文件内容。
     * @param fileName 下载后的文件名。
     * @return 包含文件内容的 ResponseEntity 对象，配置了合适的响应头以触发浏览器下载。
     * @throws IOException 如果读取文件内容时发生错误。
     */
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("file") String fileKey,@RequestParam("name") String fileName) throws IOException {
        // 根据文件键获取文件的字节内容。
        byte[] fileBytes = chartService.getFile(fileKey);
        // 创建 InputStreamResource 对象，它包装了一个输入流，使得它可以作为响应体返回。
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fileBytes));

        // 根据文件名设置响应头，以触发浏览器下载，并根据文件类型设置Content-Type。
        // 对于PNG图片，设置为图像类型；否则，设置为通用的二进制流类型。
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(fileName.endsWith(".png") ? MediaType.IMAGE_PNG : MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(fileBytes.length)
                .body(resource);
    }


}
