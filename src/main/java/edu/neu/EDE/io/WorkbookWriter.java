package edu.neu.EDE.io;

import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.SheetConfiguration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * Created by Ian on 3/1/15.
 */
public class WorkbookWriter {

    private final int HEADER_ROW_INDEX = 0;
    private final int DATA_ROW_START_INDEX = 1;
    private XSSFWorkbook workbook;
    private DataType sheetType;
    private DataType rowType;
    private DataType columnType;
    private FourDimArray data;
    int sheetIndex;

    public WorkbookWriter() {
        workbook = new XSSFWorkbook();
        sheetIndex = 0;
    }

    public void reset() {
        sheetIndex = 0;
        workbook = new XSSFWorkbook();
    }
    
    public void setSheetType(DataType type) { sheetType = type; }

    public void setRowType(DataType type) { rowType = type; }

    public void setColumnType(DataType type) { columnType = type; }

    public void setData(FourDimArray d) { data = d;}
    
    public void write(List<String> sheetNames, List<String> columnHeaders, List<String> rowHeaders, String name) {
        for (String sheetName: sheetNames) {
            addSheet(sheetName, columnHeaders, rowHeaders);
        }
        FileOutputStream fileOut;
        try {
            String workingDir = System.getProperty("user.dir");
            File outputDir = new File(workingDir + "/output");
            outputDir.mkdir();
            // TODO: with gui, we probably want to require an output name
            fileOut = new FileOutputStream(workingDir + "/output/" + name + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addSheet(String sheetName, List<String> columnHeaders, List<String> rowHeaders) {
        XSSFSheet sheet = workbook.createSheet(sheetIndex + " - " + WorkbookUtil.createSafeSheetName(sheetName));
        createHeaderRow(sheet, sheetName, columnHeaders);
        addData2Sheet(sheet, sheetName, columnHeaders, rowHeaders);
        sheetIndex++;
    }

    private void createHeaderRow(XSSFSheet sheet, String sheetName, List<String> columnHeaders) {
        Cell cell;
        int cellIndex = 0;
        Row row = sheet.createRow(HEADER_ROW_INDEX);
        cell = row.createCell(cellIndex++);
        cell.setCellValue(sheetName); // tab name will only have 27 characters, may not be enough
        for (String columnName: columnHeaders) {
            cell = row.createCell(cellIndex++);
            cell.setCellValue(columnName);
        }
    }

    private void addData2Sheet(XSSFSheet sheet, String sheetName, List<String> columnHeaders, List<String> rowHeaders) {
        int rowIndex = DATA_ROW_START_INDEX;
        int cellIndex = 0;
        Row row;
        Cell cell;
        for (String rowName: rowHeaders) {
            row = sheet.createRow(rowIndex++);
            cell = row.createCell(cellIndex++);
            cell.setCellValue(rowName);
            for (String columnName: columnHeaders) {
                Double value = getValue(sheetName, rowName, columnName);
                cell = row.createCell(cellIndex++);
                if (value != null) {
                    cell.setCellValue(value);
                }
            }
            cellIndex = 0;
        }
    }

    private Double getValue(String sheetName, String rowName, String columnName) {
        SheetConfiguration config = new SheetConfiguration();
        config.set(sheetType, sheetName);
        config.set(rowType, rowName);
        config.set(columnType, columnName);
        return data.get(config);
    }

}
