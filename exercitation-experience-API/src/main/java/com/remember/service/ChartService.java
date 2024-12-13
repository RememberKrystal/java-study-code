package com.remember.service;

import com.remember.domain.po.TestEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/17 17:15
 * @Description : 根据数据集合生成图片和表格,提供下载链接
 */
@Service
public class ChartService {
    private final Map<String, String> fileMap = new HashMap<>();

    public String createChart() {

        List<TestEntity> events = new ArrayList<>();
        events.add(new TestEntity(1, "张三"));
        events.add(new TestEntity(2, "李四"));
        events.add(new TestEntity(3, "麻子"));

        // 创建临时目录
        String tempDir = System.getProperty("java.io.tmpdir");
        String uniqueDir = tempDir + File.separator + UUID.randomUUID();
        new File(uniqueDir).mkdirs();

        // 生成柱状图
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TestEntity event : events) {
            dataset.addValue(event.getId(), "Values", event.getName());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "MySQL Data Chart",
                "Date",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        File chartFile = new File(uniqueDir + File.separator + "chart.png");
        try {
            ChartUtils.saveChartAsPNG(chartFile, barChart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 生成Excel表格
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("姓名表");

        // 表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("姓名");

        // ...

        // 数据行
        int rowNum = 1;
        for (TestEntity event : events) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(event.getId());
            row.createCell(1).setCellValue(event.getName());
            // ...
        }

        File excelFile = new File(uniqueDir + File.separator + "events.xlsx");
        try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String chartKey = UUID.randomUUID().toString();
        String excelKey = UUID.randomUUID().toString();
        fileMap.put(chartKey, chartFile.getAbsolutePath());
        fileMap.put(excelKey, excelFile.getAbsolutePath());

        return String.format("Data exported. Download links: /download?file=%s&name=chart.png for chart and /download?file=%s&name=events.xlsx for Excel",
                chartKey, excelKey);
    }

    /**
     * 根据文件键获取文件的字节内容。
     *
     * 此方法通过文件键从文件映射表中查找文件路径，然后读取文件的所有字节内容。
     * 如果文件映射表中不存在该文件键，则抛出IOException，指示文件未找到。
     *
     * @param fileKey 文件的唯一标识键，用于在文件映射表中查找对应的文件路径。
     * @return 文件的全部字节内容。
     * @throws IOException 如果文件键不存在于文件映射表中，则抛出此异常。
     */
    public byte[] getFile(String fileKey) throws IOException {
        // 通过文件键获取文件路径。
        String filePath = fileMap.get(fileKey);
        // 检查文件路径是否存在。
        if (filePath != null) {
            // 如果文件路径存在，则读取文件的所有字节并返回。
            return Files.readAllBytes(Paths.get(filePath));
        } else {
            // 如果文件路径不存在，则抛出IOException，指示文件未找到。
            throw new IOException("File not found");
        }
    }

}
