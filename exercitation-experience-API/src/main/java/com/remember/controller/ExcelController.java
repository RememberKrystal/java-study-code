package com.remember.controller;

import com.remember.domain.po.Person;
import com.remember.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/23 17:07
 * @Description : 表格的导入导出
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    // 导入Excel文件
    @GetMapping("/import")
    public ResponseEntity<String> importExcel() throws Exception {
        // 获取当前工作目录
        String workingDir = System.getProperty("user.dir");
        File file = new File(workingDir, "src/main/java/com/remember/巴迪小虎出货信息.xlsx");
        FileInputStream input = new FileInputStream(file);

        excelService.importExcel(input);
        return ResponseEntity.ok("File imported successfully");

    }

    // 导出Excel文件
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            List<Person> data = getSampleData();
            excelService.exportExcel(outputStream, data);
            byte[] bytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "export.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * 设置测试数据
     *
     * @return
     */
    private List<Person> getSampleData() {
        return Arrays.asList(
                new Person(1, "John Doe", "john.doe@example.com"),
                new Person(2, "Jane Doe", "jane.doe@example.com"),
                new Person(3, "Sam Smith", "sam.smith@example.com")
        );
    }
}
