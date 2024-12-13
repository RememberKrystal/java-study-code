package com.remember.config;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.AbstractCellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.util.List;

/**
 * 自定义单元格样式处理器
 */
public class CustomCellStyleHandler extends AbstractCellWriteHandler {
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex,
                                 Boolean isHead) {
        // 获取工作簿
        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();

        // 设置通用样式
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 左右居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 上下居中
        cellStyle.setWrapText(true); // 自动换行

        // 设置边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        // 设置表头样式
        if (isHead) {
            Font font = workbook.createFont();
//            font.setBold(true); // 加粗
            cellStyle.setFont(font);
        }

        cell.setCellStyle(cellStyle);
    }
}

