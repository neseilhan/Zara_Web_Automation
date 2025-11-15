package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet bulunamadı: " + sheetName);
            }

        } catch (Exception e) {
            throw new RuntimeException("Excel okunamadı: " + e.getMessage());
        }
    }

    public String getCell(int row, int col) {
        DataFormatter formatter = new DataFormatter();
        Row r = sheet.getRow(row);

        if (r == null) return null;
        Cell c = r.getCell(col);
        if (c == null) return null;

        return formatter.formatCellValue(c);
    }

    public List<String> getRow(int rowIndex) {
        List<String> rowData = new ArrayList<>();
        Row row = sheet.getRow(rowIndex);

        if (row != null) {
            DataFormatter formatter = new DataFormatter();
            for (Cell cell : row) {
                rowData.add(formatter.formatCellValue(cell));
            }
        }

        return rowData;
    }

    public List<List<String>> getSheetData() {
        List<List<String>> data = new ArrayList<>();

        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowCount; i++) {
            data.add(getRow(i));
        }
        return data;
    }

    public void close() {
        try {
            workbook.close();
        } catch (Exception ignored) {}
    }
}
