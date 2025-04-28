package com.remember.service;


import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/4/7 14:54
 * @Description : 导出文档（自动调整字体大小适应文本框）
 */
@Service
public class WordExportService {
    private static final Logger log = Logger.getLogger(WordExportService.class.getName());
    private static final String TEMPLATE_RELATIVE_PATH = "exercitation-experience-API/src/main/java/com/remember/干部任免审批表模板.docx";
    private static final String PATTERN_ONE = "^(\\d{4}年(0[1-9]|1[0-2])月-(\\d{4}年(0[1-9]|1[0-2])月|至今) [^\\n]+(\\n|$))+$";
    private static final String PATTERN_TWO = "^(\\d{4}\\.(0[1-9]|1[0-2])-\\d{4}\\.(0[1-9]|1[0-2]) [^\\n]+\\n?)+$";
    private static final int EVALUATION_TABLE_INDEX = 0;   // 表格索引
    private static final int EVALUATION_ROW_INDEX = 9;     // 行索引
    private static final int EVALUATION_CELL_INDEX = 1;    // 单元格索引

    public ByteArrayOutputStream generateWord(String content) throws Exception {
        // 参数校验
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("内容不能为空");
        }
        // 正则表达式进行参数校验
        boolean isMatchONE = Pattern.matches(PATTERN_ONE, content);
        boolean isMatchTWO = Pattern.matches(PATTERN_TWO, content);
        if (!isMatchONE && !isMatchTWO) {
            throw new IllegalArgumentException("输入格式不正确，请按照示例格式输入,示例格式： YYYY年(.)MM月()-YYYY年(.)MM月() 简历情况...");
        }

        // 使用try-with-resources确保资源自动关闭
        try (InputStream inputStream = loadTemplateFile();
             XWPFDocument doc = new XWPFDocument(inputStream);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // 获取目标表格
            XWPFTable table = getTableSafely(doc, EVALUATION_TABLE_INDEX);
            // 获取目标单元格
            XWPFTableCell cell = getCellSafely(table, EVALUATION_ROW_INDEX, EVALUATION_CELL_INDEX);
            // 获取行高（以twips为单位）
            int rowHeightTwips = cell.getTableRow().getHeight();
            // 获取单元格宽度（以twips为单位）
            int widthTwips = cell.getWidth();
            log.info("行高：" + rowHeightTwips + "，宽度：" + widthTwips);
            // 调整内容适应单元格
            adjustContentToCell(cell, content, widthTwips, rowHeightTwips);

            // 写入输出流
            doc.write(outputStream);
            return outputStream;
        }
    }

    /**
     * 读取模板文件
     *
     * @return
     * @throws FileNotFoundException
     */
    private InputStream loadTemplateFile() throws FileNotFoundException {
        // 获取当前工作目录
        String workingDir = System.getProperty("user.dir");
        File file = new File(workingDir, TEMPLATE_RELATIVE_PATH);

        if (!file.exists()) {
            throw new FileNotFoundException("找不到模板文件: " + file.getAbsolutePath());
        }

        return new FileInputStream(file);
    }

    /**
     * 获取目标表格
     *
     * @param doc        文档
     * @param tableIndex 表格索引
     * @return
     */
    private XWPFTable getTableSafely(XWPFDocument doc, int tableIndex) {
        List<XWPFTable> tables = doc.getTables();
        if (tables == null || tables.size() <= tableIndex) {
            throw new IllegalStateException("模板中找不到第" + (tableIndex + 1) + "个表格");
        }
        return tables.get(tableIndex);
    }

    /**
     * 获取目标单元格
     *
     * @param table     目标表格
     * @param rowIndex  行索引
     * @param cellIndex 列索引
     * @return
     */
    private XWPFTableCell getCellSafely(XWPFTable table, int rowIndex, int cellIndex) {
        XWPFTableRow row = table.getRow(rowIndex);
        if (row == null) {
            throw new IllegalStateException("表格中找不到第" + (rowIndex + 1) + "行");
        }

        XWPFTableCell cell = row.getCell(cellIndex);
        if (cell == null) {
            throw new IllegalStateException("行中找不到第" + (cellIndex + 1) + "个单元格");
        }

        return cell;
    }

    /**
     * 调整字体大小适应文本框
     *
     * @param cell        目标单元格
     * @param content     文本内容
     * @param fixedWidth  固定宽度，单位twips
     * @param fixedHeight 固定高度，单位twips
     */
    private void adjustContentToCell(XWPFTableCell cell, String content, int fixedWidth, int fixedHeight) {
        // 设置单元格固定尺寸
        setFixedSize(cell, fixedWidth, fixedHeight);

        // 设置单元格垂直对齐方式为顶部对齐
        setCellVerticalAlignment(cell, STVerticalJc.TOP);

        // 清空内容并开始调整字体大小
        clearCellContent(cell);

        // 从最大字体开始，逐步减小字体，直到内容适应
        int fontSize = 23;  // 初始字体大小
        int minFontSize = 1;  // 最小字体大小
        int maxIterations = 23;  // 最大循环次数，避免陷入死循环
        int iteration = 0;  // 循环计数器

        while (fontSize >= minFontSize) {
            if (smartTruncate(content, fixedWidth, fixedHeight, fontSize)) {
                log.info("字体大小：" + fontSize + "，内容已适应");
                createTextRun(cell, content, fontSize, fixedWidth);
                break;
            }
            fontSize--;  // 减小字体大小
            iteration++;

            // 如果超过最大迭代次数，跳出循环
            if (iteration > maxIterations) {
                break;
            }
        }
    }

    private void setCellVerticalAlignment(XWPFTableCell cell, STVerticalJc.Enum verticalAlignment) {
        CTTcPr tcPr = cell.getCTTc().isSetTcPr() ? cell.getCTTc().getTcPr() : cell.getCTTc().addNewTcPr();
        CTVerticalJc vAlign = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr.addNewVAlign();
        vAlign.setVal(verticalAlignment);
    }


    private void setFixedSize(XWPFTableCell cell, int widthTwips, int heightTwips) {
        // 设置单元格宽度
        CTTcPr tcPr = cell.getCTTc().isSetTcPr() ? cell.getCTTc().getTcPr() : cell.getCTTc().addNewTcPr();
        CTTblWidth cellWidth = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
        cellWidth.setW(BigInteger.valueOf(widthTwips)); // 设置单元格宽度，单位twips
        cellWidth.setType(STTblWidth.DXA);

        // 设置行高
        XWPFTableRow row = cell.getTableRow();
        row.setHeight(heightTwips); // 设置行高，单位twips

    }

    /**
     * 判断内容是否适合单元格(判断字体大小是否合适)
     *
     * @param content     需写入内容
     * @param widthTwips  单元格宽度，单位twips
     * @param heightTwips 单元格高度，单位twips
     * @param fontSize    字体大小
     * @return 是否能容纳
     */
    private Boolean smartTruncate(String content, int widthTwips, int heightTwips, double fontSize) {
        // 估算每个汉字的宽度和每行的高度
        int chineseCharWidth = (int) (fontSize * 10); // 汉字字符宽度 twips
        int lineHeight = (int) (fontSize * 10); // 汉字字符高度

        // 记录总共有多少个换行符
        String[] lineNumbers = content.split("\n");

        // 计算每行能容纳的字符数
        int charsPerLine = widthTwips / chineseCharWidth;

        // 计算文本需要多少行
        int lines = (int) Math.ceil((double) content.length() / charsPerLine) + lineNumbers.length;

        // 计算文本总高度
        int totalHeight = lines * lineHeight;

        // 如果文本高度不超过最大高度，直接返回内容
        if (totalHeight <= heightTwips) {
            log.info("当前字体大小每行可以容纳的字体数量为：" + charsPerLine);
            log.info("当前字体大小需要" + lines + "行，总高度为：" + totalHeight + "，字体大小：" + fontSize);
            return true;
        } else {
            return false;
        }
    }


    private void clearCellContent(XWPFTableCell cell) {
        // 清空单元格内容
        for (XWPFParagraph para : cell.getParagraphs()) {
            for (int i = para.getRuns().size() - 1; i >= 0; i--) {
                para.removeRun(i);
            }
        }
    }

    /**
     * 写入文本到单元格
     *
     * @param cell     目标单元格
     * @param content  文本内容
     * @param fontSize 字体大小
     */
    private void createTextRun(XWPFTableCell cell, String content, int fontSize, int fixeWidth) {
        // 按换行符（\n）分割文本
        String[] lines = content.split("\n");

        // 转换文本格式
        Map<List<String>, List<Integer>> map = processingTextFormat(lines, fontSize, "宋体", fixeWidth);
        List<String> lastLines = map.keySet().stream().findFirst().orElse(null); // 写入的文档
        List<Integer> value = map.values().stream().findFirst().orElse(null); // 需要换行的行号

        // 清除默认段落
        if (cell.getParagraphs().size() == 1 && cell.getParagraphs().getFirst().getText().isEmpty()) {
            cell.removeParagraph(0);
        }

        // 遍历每一行文本并为每一行创建独立段落
        if (lastLines != null) {
            for (int index = 0; index < lastLines.size(); index++) {
                // 为每一行创建新的段落
                XWPFParagraph para = cell.addParagraph();
                if (index != 0) {
                    para.setWordWrap(true); // 启用自动换行
                }
                if (value.contains(index)) {
                    // 需要缩进
                    para.setIndentationFirstLine((int) (fontSize * 1.5 * 5) * 14); // 设置段落缩进
                }
                para.setAlignment(ParagraphAlignment.LEFT); // 设置左对齐
                para.setVerticalAlignment(TextAlignment.TOP);  // 设置内容从顶部开始填充
                // 创建Run并设置文本
                XWPFRun run = para.createRun();
                run.setText(lastLines.get(index));
                run.setFontSize(fontSize / 2); // 设置字体大小
            }
        }
    }

    public static FontMetrics getFontMetrics(Font font) {
        // 创建一个BufferedImage对象，用来获取Graphics2D
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        // 设置字体并获取度量信息
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.dispose();  // 释放资源

        return metrics;
    }

    /**
     * 处理文本格式
     *
     * @param lines 行数据
     * @return 处理后的文本格式 + 需要换行的行号
     */
    private Map<List<String>, List<Integer>> processingTextFormat(String[] lines, int fontSize, String textName, int fixeWidth) {
        // 文本框总宽度(???存在单位之间的换算???)
        int fixedWidth = (int) (fixeWidth / 10);
        // 最长的日期宽度
        int maxDateWidth = 0;
        // 构建返回参数
        List<String> result = new ArrayList<>();

        // 根据日期和内容中间的空格 分割每行的日期 和 内容 统一放入集合中
        List<String> dateAndContent = new ArrayList<>(lines.length * 2);
        // 用于记录需要缩进的行号
        List<Integer> dateAndContentNumberList = new ArrayList<>();
        int dateAndContentNumber = -1; // 需要缩进行的计数器，每存入一次 次数加一
        for (String line : lines) {
            // 将日期和内容按空格分割
            String[] parts = line.split(" ");
            if (parts.length % 2 != 0) {
                throw new RuntimeException("日期和内容中间没有空格，请检查");
            }
            dateAndContent.addAll(Arrays.asList(parts));
        }

        // 创建字体对象
        Font font = new Font(textName, Font.PLAIN, fontSize);

        // 获取字体度量
        FontMetrics metrics = getFontMetrics(font);

        // 计算最长的日期宽度
        for (int index = 0; index < dateAndContent.size(); index = index + 2) {
            String date = dateAndContent.get(index);
            int dateWidth = metrics.stringWidth(date);
            if (dateWidth > maxDateWidth) {
                maxDateWidth = dateWidth;
            }
        }
        // 单行文本的最大的宽度
        int maxContentWidth = fixedWidth - maxDateWidth; // 减去日期宽度

        // 遍历日期和内容集合，将日期和内容分别放入结果集合中
        for (int index = 0; index < dateAndContent.size(); index++) {
            if (index % 2 == 0) {
                continue;
                // 日期直接略过
                // result.add(dateAndContent.get(index));
            } else {
                // 文本内容
                String text = dateAndContent.get(index);
                int number = 0; // 计数器
                int indexNumber = 0; // 索引计数器
                String dateContent = dateAndContent.get(index - 1); // 获取当前文本前的日期
                // 计算与最大日期相差多少宽度
                int dateWidth = metrics.stringWidth(dateContent);
                int difference = maxDateWidth - dateWidth; // 计算日期与最大日期的宽度差
                log.info("日期与最大日期的宽度差====>" + difference);

                // 遍历文本中的每个字符并计算其宽度
                for (int i = 0; i < text.length(); i++) {
                    char ch = text.charAt(i);
                    int charWidth = metrics.charWidth(ch);
                    number += charWidth; // 累加字符宽度

                    if (number > maxContentWidth) {
                        dateAndContentNumber++;
                        if (indexNumber == 0) {
                            // 第一次写入
                            if (dateContent.contains("至今")){
                                result.add(dateContent + " ".repeat(7) + text.substring(0, i + 1)); // 添加到结果集合中
                            }else{
                                result.add(dateContent + " ".repeat(1) + text.substring(0, i + 1)); // 添加到结果集合中
                            }
                            log.info("第一次包含时间处理后的文本====>" + dateContent + " ".repeat(1) + text.substring(indexNumber, i));
                        } else {
                            // 不用拼接日期
                            result.add(text.substring(indexNumber, i + 1));
                            log.info("中间过程处理后的文本=====>" + text.substring(indexNumber, i));
                            // 记录需要缩进的行号
                            dateAndContentNumberList.add(dateAndContentNumber);
                        }
                        // 写入之后应重置计数器
                        number = 0;
                        indexNumber = i + 1;
                    }
                    // 最后一次循环
                    if (i == text.length() - 1 && number > 0 && !text.substring(indexNumber).equals(text)) {
                        dateAndContentNumber++;
                        result.add(text.substring(indexNumber));
                        log.info("最后一次包含时间处理后的文本====>" + text.substring(indexNumber));
                        // 记录需要缩进的行号
                        dateAndContentNumberList.add(dateAndContentNumber);
                    }
                    // 文本长度不满足最大宽度 直接写出
                    if (i == text.length() - 1 && number > 0 && text.substring(indexNumber).equals(text)) {
                        dateAndContentNumber++;
                        if (dateContent.contains("至今")){
                            result.add(dateContent + " ".repeat(7) + text);
                        }else{
                            result.add(dateContent + " ".repeat(1) + text);
                        }
                        log.info("文本长度不满足最大宽度，直接写出====>" + dateContent + " ".repeat(1) + text);
                    }
                }
            }
        }

        log.info("需要缩进的行号=====>" + dateAndContentNumberList);
        // 构建返回参数
        Map<List<String>, List<Integer>> resultMap = new HashMap<>();
        resultMap.put(result, dateAndContentNumberList);

        return resultMap;
    }
}
