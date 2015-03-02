package edu.neu.EDE.io;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;


public class TestWorkbookReader extends Assert {

    @Test
    public void testExtractMediaNameVideo() {
        WorkbookReader reader = new WorkbookReader();
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row1 = sheet.createRow(0);
        Cell cell5 = row1.createCell(5);
        cell5.setCellValue("sample video name; unnecessary other text");
        reader.extractMediaName(sheet);
        assertEquals(reader.media, "sample video name");
    }

    @Test
    public void testExtractMediaNameImage() {
        WorkbookReader reader = new WorkbookReader();
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row1 = sheet.createRow(0);
        Cell cell5 = row1.createCell(5);
        cell5.setCellValue("sample image name.jpg DATA");
        reader.extractMediaName(sheet);
        assertEquals(reader.media, "sample image name.jpg");
    }

    @Test
    public void testExtractSlideMetricDataFromSheet() {
        WorkbookReader reader = new WorkbookReader();
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Cell statCell, valCell;
        Row row1 = sheet.createRow(2);
        statCell = row1.createCell(0);
        statCell.setCellValue("test1");
        valCell = row1.createCell(5);
        valCell.setCellValue(10);
        Row row2 = sheet.createRow(3);
        statCell = row2.createCell(0);
        statCell.setCellValue("test2");
        valCell = row2.createCell(5);
        valCell.setCellValue(15);
        int smEnd = reader.extractSlideMetricDataFromSheet(sheet);
        assertEquals(smEnd, 5);

    }
}
