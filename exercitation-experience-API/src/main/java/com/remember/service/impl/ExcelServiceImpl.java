package com.remember.service.impl;

import com.remember.domain.po.Person;
import com.remember.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/23 17:06
 * @Description : ExcelServiceImpl
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    private Workbook importedWorkbook;

    @Override
    public void importExcel(InputStream inputStream) throws Exception {
        this.importedWorkbook = new XSSFWorkbook(inputStream);
    }

    @Override
    public void exportExcel(OutputStream outputStream, List<Person> data) throws Exception {
        if (this.importedWorkbook == null) {
            throw new IllegalStateException("No data imported to export");
        }

        Workbook workbook = new XSSFWorkbook();
        for (int i = 0; i < importedWorkbook.getNumberOfSheets(); i++) {
            Sheet importedSheet = importedWorkbook.getSheetAt(i);
            Sheet newSheet = workbook.createSheet(importedSheet.getSheetName());

            // 复制列宽
            copyColumnWidths(importedSheet, newSheet);

            // 复制表头和第一行数据
            copyHeaderAndFirstRow(importedSheet, newSheet, workbook);

            // 创建一个样式，数据居中
            CellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // 设置边框
            dataCellStyle.setBorderTop(BorderStyle.THIN);
            dataCellStyle.setBorderBottom(BorderStyle.THIN);
            dataCellStyle.setBorderLeft(BorderStyle.THIN);
            dataCellStyle.setBorderRight(BorderStyle.THIN);

            // 填充数据行
            int rowNum = 2; // 从第三行开始填充数据
            for (Person person : data) {
                Row newRow = newSheet.createRow(rowNum++);
                copyRowStyle(importedSheet.getRow(1), newRow); // 复制数据行的样式

                createDataCell(newRow, 0, person.getId(), dataCellStyle);
                createDataCell(newRow, 1, person.getName(), dataCellStyle);
                createDataCell(newRow, 2, person.getEmail(), dataCellStyle);
            }

            // 复制合并单元格
            copyMergedRegions(importedSheet, newSheet);
        }

        workbook.write(outputStream);
        workbook.close();
    }

    /**
     * 复制表格列宽
     */
    private void copyColumnWidths(Sheet importedSheet, Sheet newSheet) {
        // 遍历原始表格的第一行，以确定有多少列需要设置宽度
        for (int i = 0; i < importedSheet.getRow(0).getLastCellNum(); i++) {
            // 设置目标表格的每列宽度与原始表格对应列的宽度相同
            newSheet.setColumnWidth(i, importedSheet.getColumnWidth(i));
        }
    }


    /**
     * 复制源工作表的表头和第一行到目标工作表。
     * 此方法用于保持新创建的Sheet与原有Sheet在表头和第一行内容及样式的一致性。
     */
    private void copyHeaderAndFirstRow(Sheet importedSheet, Sheet newSheet, Workbook workbook) {
        // 遍历前两行（表头和第一行），进行复制
        for (int rowNum = 0; rowNum <= 1; rowNum++) { // 复制表头和第一行
            Row importedRow = importedSheet.getRow(rowNum);
            Row newRow = newSheet.createRow(rowNum);
            // 如果源行存在，则复制行高和单元格内容及样式
            if (importedRow != null) {
                newRow.setHeight(importedRow.getHeight()); // 复制行高
                // 遍历源行的所有单元格，进行复制
                for (int cellNum = 0; cellNum < importedRow.getLastCellNum(); cellNum++) {
                    Cell importedCell = importedRow.getCell(cellNum);
                    Cell newCell = newRow.createCell(cellNum);
                    // 如果源单元格存在，则复制单元格内容和样式
                    if (importedCell != null) {
                        copyCellValue(importedCell, newCell);
                        copyCellStyle(importedCell, newCell, workbook);
                    }
                }
            }
        }
    }


    private void copyRowStyle(Row sourceRow, Row targetRow) {
        if (sourceRow != null) {
            targetRow.setHeight(sourceRow.getHeight()); // 复制行高
            for (int cellNum = 0; cellNum < sourceRow.getLastCellNum(); cellNum++) {
                Cell sourceCell = sourceRow.getCell(cellNum);
                Cell targetCell = targetRow.createCell(cellNum);
                if (sourceCell != null) {
                    copyCellStyle(sourceCell, targetCell, targetRow.getSheet().getWorkbook());
                }
            }
        }
    }

    private void copyCellValue(Cell importedCell, Cell newCell) {
        switch (importedCell.getCellType()) {
            case STRING:
                newCell.setCellValue(importedCell.getStringCellValue());
                break;
            case NUMERIC:
                newCell.setCellValue(importedCell.getNumericCellValue());
                break;
            case BOOLEAN:
                newCell.setCellValue(importedCell.getBooleanCellValue());
                break;
            case FORMULA:
                newCell.setCellFormula(importedCell.getCellFormula());
                break;
            case BLANK:
                newCell.setBlank();
                break;
            default:
                throw new IllegalArgumentException("Unsupported cell type");
        }
    }

    private void copyCellStyle(Cell importedCell, Cell newCell, Workbook workbook) {
        CellStyle newCellStyle = workbook.createCellStyle();
        newCellStyle.cloneStyleFrom(importedCell.getCellStyle());

        // 复制字体
        Font importedFont = importedWorkbook.getFontAt(importedCell.getCellStyle().getFontIndex());
        Font newFont = workbook.createFont();
        newFont.setBold(importedFont.getBold());
        newFont.setColor(importedFont.getColor());
        newFont.setFontHeight(importedFont.getFontHeight());
        newFont.setFontName(importedFont.getFontName());
        newFont.setItalic(importedFont.getItalic());
        newFont.setStrikeout(importedFont.getStrikeout());
        newFont.setTypeOffset(importedFont.getTypeOffset());
        newFont.setUnderline(importedFont.getUnderline());
        newCellStyle.setFont(newFont);

        // 复制对齐方式
        newCellStyle.setAlignment(importedCell.getCellStyle().getAlignment());
        newCellStyle.setVerticalAlignment(importedCell.getCellStyle().getVerticalAlignment());

        // 复制填充
        newCellStyle.setFillBackgroundColor(importedCell.getCellStyle().getFillBackgroundColor());
        newCellStyle.setFillForegroundColor(importedCell.getCellStyle().getFillForegroundColor());
        newCellStyle.setFillPattern(importedCell.getCellStyle().getFillPattern());

        // 复制边框
        newCellStyle.setBorderTop(importedCell.getCellStyle().getBorderTop());
        newCellStyle.setBorderBottom(importedCell.getCellStyle().getBorderBottom());
        newCellStyle.setBorderLeft(importedCell.getCellStyle().getBorderLeft());
        newCellStyle.setBorderRight(importedCell.getCellStyle().getBorderRight());
        newCellStyle.setTopBorderColor(importedCell.getCellStyle().getTopBorderColor());
        newCellStyle.setBottomBorderColor(importedCell.getCellStyle().getBottomBorderColor());
        newCellStyle.setLeftBorderColor(importedCell.getCellStyle().getLeftBorderColor());
        newCellStyle.setRightBorderColor(importedCell.getCellStyle().getRightBorderColor());

        newCell.setCellStyle(newCellStyle);
    }

    private void copyMergedRegions(Sheet importedSheet, Sheet newSheet) {
        for (int i = 0; i < importedSheet.getNumMergedRegions(); i++) {
            newSheet.addMergedRegion(importedSheet.getMergedRegion(i));
        }
    }

    private void createDataCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void createDataCell(Row row, int column, int value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
}
