package com.remember.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.remember.config.CustomCellStyleHandler;
import com.remember.domain.po.Address;
import com.remember.config.ExcelListener;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class AddressController {

    // 导入 Excel 文件
    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<Address> dataList = EasyExcel.read(file.getInputStream(), Address.class, new ExcelListener()).sheet().doReadSync();
            System.out.println("导入数据：" + dataList);
            return "导入成功！";
        } catch (IOException e) {
            e.printStackTrace();
            return "导入失败：" + e.getMessage();
        }
    }

    // 导出 Excel 文件
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        List<Address> dataList = mockData();

        try {
            // 设置响应头
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("地址信息", "UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx");

            // 写入 Excel 文件
            EasyExcel.write(response.getOutputStream(), Address.class)
                    .excelType(ExcelTypeEnum.XLSX) // 设置文件类型
                    .registerWriteHandler(new CustomCellStyleHandler()) // 注册自定义样式处理器
                    .sheet("地址列表")
                    .doWrite(dataList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 模拟导出数据
    private List<Address> mockData() {
        List<Address> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Address address = new Address();
            address.setUserId((long) i);
            address.setProvince("省份" + i);
            address.setCity("城市" + i);
            address.setTown("县/区" + i);
            address.setMobile("1380000000" + i);
            address.setStreet("详细地址" + i);
            address.setContact("联系人" + i);
            list.add(address);
        }
        return list;
    }
}

