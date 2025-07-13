package userDefinedFiles;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {
    private final String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            return sheet.getLastRowNum();
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            return row != null ? row.getLastCellNum() : 0;
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return "";
            XSSFCell cell = row.getCell(colnum);
            if (cell == null) return "";
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File file = new File(path);
        XSSFWorkbook workbook;
        XSSFSheet sheet;

        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }

        try (FileInputStream fi = new FileInputStream(path)) {
            workbook = new XSSFWorkbook(fi);
        }

        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        XSSFRow row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }

        XSSFCell cell = row.createCell(colnum);
        cell.setCellValue(data);

        try (FileOutputStream fo = new FileOutputStream(path)) {
            workbook.write(fo);
        }
        workbook.close();
    }

    public void fillCellColor(String sheetName, int rownum, int colnum, IndexedColors color) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) row = sheet.createRow(rownum);

            XSSFCell cell = row.getCell(colnum);
            if (cell == null) cell = row.createCell(colnum);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) row = sheet.createRow(rownum);

            XSSFCell cell = row.getCell(colnum);
            if (cell == null) cell = row.createCell(colnum);

            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();

            // Background: light green #C6EFCE
            style.setFillForegroundColor(new XSSFColor(new Color(198, 239, 206), null));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Text: dark green #006100
            font.setColor(new XSSFColor(new Color(0, 97, 0), null));
            style.setFont(font);

            cell.setCellStyle(style);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) row = sheet.createRow(rownum);

            XSSFCell cell = row.getCell(colnum);
            if (cell == null) cell = row.createCell(colnum);

            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();

            // Background: light red #FFC7CE
            style.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 199, 206), null));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Text: dark red #9C0006
            font.setColor(new XSSFColor(new java.awt.Color(156, 0, 6), null));
            style.setFont(font);

            cell.setCellStyle(style);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }
}