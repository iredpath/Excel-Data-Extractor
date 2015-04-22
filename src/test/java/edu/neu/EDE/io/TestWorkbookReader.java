package edu.neu.EDE.io;

import com.google.common.collect.Lists;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.SheetConfiguration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestWorkbookReader extends Assert {

    @Test
    public void testExtractMediaNameVideo() {
        WorkbookReader reader = new WorkbookReader(new FourDimArray(), new FourDimArray());
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row1 = sheet.createRow(0);
        Cell cell5 = row1.createCell(0);
        cell5.setCellValue("sample video name; unnecessary other text");
        reader.extractMediaName(sheet);
        assertEquals(reader.media, "sample video name");
    }

    @Test
    public void testExtractMediaNameImage() {
        WorkbookReader reader = new WorkbookReader(new FourDimArray(), new FourDimArray());
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row1 = sheet.createRow(0);
        Cell cell5 = row1.createCell(0);
        cell5.setCellValue("sample image name.jpg - F");
        reader.extractMediaName(sheet);
        assertEquals(reader.media, "sample image name.jpg");
    }

    @Test
    public void testExtractSlideMetricDataFromSheet() {
        WorkbookReader reader = new WorkbookReader(new FourDimArray(), new FourDimArray());
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
        reader.subject = "foo";
        reader.media = "bar";
        reader.slideMetricData = new FourDimArray();
        int smEnd = reader.extractSlideMetricDataFromSheet(sheet);
        assertEquals(smEnd, 4);
        assertEquals(sheet.getRow(smEnd), null);
    }

    @Test
    public void testGetNullRowIndices() {
        WorkbookReader reader = new WorkbookReader(new FourDimArray(), new FourDimArray());
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
        WorkbookReader reader = new WorkbookReader(new FourDimArray(), new FourDimArray());
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
        WorkbookReader reader = new WorkbookReader(new FourDimArray(), new FourDimArray());
        reader.media = "foo";
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("lookzone1");
        row = sheet.createRow(2);
        cell = row.createCell(5);
        cell.setCellValue("test");
        String name = reader.getStimulusName(sheet, Arrays.asList(0), 1);
        assertEquals(name, "foo-lookzone1 (test)");
        row.removeCell(cell);
        name = reader.getStimulusName(sheet, Arrays.asList(0), 1);
        assertEquals(name, "foo-lookzone1");
    }

    @Test
    public void testReadFile() throws IOException {
        WorkbookReader reader = Mockito.spy(new WorkbookReader(new FourDimArray(), new FourDimArray()));
        Mockito.doNothing().when(reader).extractData(Mockito.any(XSSFWorkbook.class));
        reader.readFile(new File("src/resources/empty-workbook.xlsx"));
        // test that subject is as expected
        assertEquals(reader.getSubject(), "empty-workbook");
    }

    @Test
    public void testAddLookZoneData() {
        List<Integer> indices = Lists.newArrayList(0, 6);
        int numStats = 3;
        FourDimArray data = Mockito.spy(new FourDimArray());
        WorkbookReader reader = new WorkbookReader(data, data);
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        Row row = sheet.createRow(3);
        Cell cell = row.createCell(0);
        cell.setCellValue("stat1");
        cell = row.createCell(5);
        cell.setCellValue(1.0);
        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("stat2");
        cell = row.createCell(5);
        cell.setCellValue(2.0);
        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("stat3");
        Mockito.doAnswer(new Answer() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                SheetConfiguration config = (SheetConfiguration) args[0];
                assertEquals(config.getStimulus(), "null-OUT");
                if (config.getStatistic().equals("stat1")) {
                    assertTrue(config.getValue() == 1.0);
                } else if (config.getStatistic().equals("stat2")) {
                    assertTrue(config.getValue() == 2.0);
                } else if (config.getStatistic().equals("stat3")) {
                    assertTrue(config.getValue() == null);
                } else { // we hit a stat we shouldn't have, fail the test
                    assertTrue(false);
                }
                return null;
            }
        }).when(data).set(Mockito.any(SheetConfiguration.class));
        reader.addLookZoneData(sheet, numStats, indices);
    }
}
