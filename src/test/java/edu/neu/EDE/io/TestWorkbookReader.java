package edu.neu.EDE.io;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        assertEquals(smEnd, 4);
    }

    @Test
    public void testGetNullRowIndices() {
        WorkbookReader reader = new WorkbookReader();
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        sheet.createRow(1);
        sheet.createRow(2);
        sheet.createRow(4);
        sheet.createRow(6);
        sheet.createRow(7);
        List<Integer> nullIndices = reader.getNullRowIndices(sheet, 0);
        assertEquals(nullIndices.size(), 4);
        assertEquals(nullIndices.get(0), new Integer(1));
        assertEquals(nullIndices.get(1), new Integer(3));
        assertEquals(nullIndices.get(2), new Integer(5));
        assertEquals(nullIndices.get(3), new Integer(8));
    }

    @Test
    public void getNumStats() {
        WorkbookReader reader = new WorkbookReader();
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row = sheet.createRow(1);
        row.createCell(1);
        for (int i = 2; i < 10; i++) {
            row = sheet.createRow(i);
            row.createCell(0);
        }
        int numStats = reader.getNumStats(sheet, Arrays.asList(0, 10));
        assertEquals(numStats, 8);
    }

    @Test
    public void getStimulusName() {
        WorkbookReader reader = new WorkbookReader();
        reader.media = "foo";
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row = sheet.createRow(2);
        Cell cell = row.createCell(5);
        cell.setCellValue("test");
        String name = reader.getStimulusName(sheet, Arrays.asList(0), 1);
        assertEquals(name, "foo-LZ1 (test)");
        row.removeCell(cell);
        name = reader.getStimulusName(sheet, Arrays.asList(0), 1);
        assertEquals(name, "foo-LZ1");
    }
}
