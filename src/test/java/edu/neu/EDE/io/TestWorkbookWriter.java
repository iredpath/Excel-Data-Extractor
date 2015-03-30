package edu.neu.EDE.io;

import com.google.common.collect.Lists;
import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.OutputConfiguration;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 3/30/15.
 */
public class TestWorkbookWriter extends Assert {

    @Test
    public void testWrite() throws IOException {
        WorkbookWriter writer = new WorkbookWriter();
        File f = File.createTempFile("test-out", null);
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(new ArrayList<String>());
        config.setStimuli(new ArrayList<String>());
        config.setSubjects(new ArrayList<String>());
        config.setTab(DataType.STATISTIC);
        config.setColumn(DataType.STIMULUS);
        config.setRow(DataType.SUBJECT);
        writer.write(config, f);
        // assure .xlsx added
        File created = new File(f + ".xlsx");
        assertTrue(created.exists());

        writer.write(config, created);
        File notCreated = new File(created + ".xlsx");
        assertFalse(notCreated.exists());
    }

    @Test
    public void testCreateHeaderRow() {
        WorkbookWriter writer = new WorkbookWriter();
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        String sheetName = "sheet";
        List<String> headers = Lists.newArrayList("foo", "bar", "buzz");
        writer.createHeaderRow(sheet, sheetName, headers);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        assertEquals(cell.getStringCellValue(), "sheet");
        cell = row.getCell(1);
        assertEquals(cell.getStringCellValue(), "foo");
        cell = row.getCell(2);
        assertEquals(cell.getStringCellValue(), "bar");
        cell = row.getCell(3);
        assertEquals(cell.getStringCellValue(), "buzz");
    }

    @Test
    public void testAddData2Sheet() {
        WorkbookWriter writer = Mockito.spy(new WorkbookWriter());
        Mockito.doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                String row = (String) args[1];
                String col = (String) args[2];
                return Double.parseDouble(row) + Double.parseDouble(col);
            }
        }).when(writer).getValue(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class));
        XSSFSheet sheet = new XSSFWorkbook().createSheet();
        String sheetName = "sheet";
        List<String> colHeaders = Lists.newArrayList("1", "2");
        List<String> rowHeaders = Lists.newArrayList("3", "4");
        writer.addData2Sheet(sheet, sheetName, colHeaders, rowHeaders);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);
        assertEquals(cell.getStringCellValue(), "3");
        cell = row.getCell(1);
        assertTrue(cell.getNumericCellValue() == 4.0);
        cell = row.getCell(2);
        assertTrue(cell.getNumericCellValue() == 5.0);
        row = sheet.getRow(2);
        cell = row.getCell(0);
        assertEquals(cell.getStringCellValue(), "4");
        cell = row.getCell(1);
        assertTrue(cell.getNumericCellValue() == 5.0);
        cell = row.getCell(2);
        assertTrue(cell.getNumericCellValue() == 6.0);
    }

    @Test
    public void testGetValue() {
        WorkbookWriter writer = new WorkbookWriter();
        FourDimArray data = Mockito.spy(new FourDimArray());
        Mockito.doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                SheetConfiguration config = (SheetConfiguration) args[0];
                assertEquals(config.getStimulus(), "stim");
                assertEquals(config.getStatistic(), "stat");
                assertEquals(config.getSubject(), "subj");
                return 1.0;
            }
        }).when(data).get(Mockito.any(SheetConfiguration.class));
        writer.setSheetType(DataType.STATISTIC);
        writer.setColumnType(DataType.STIMULUS);
        writer.setRowType(DataType.SUBJECT);
        writer.setData(data);
        writer.getValue("stat", "subj", "stim");
    }
}
