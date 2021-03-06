package edu.neu.EDE.io;

import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.OutputConfiguration;
import edu.neu.EDE.data_structs.SheetConfiguration;
import org.apache.commons.io.FilenameUtils;
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

    /**
     * public constructor
     */
    public WorkbookWriter() {
        workbook = new XSSFWorkbook();
        sheetIndex = 0;
    }

    /**
     * sets which data type is being used for sheets
     * @param type the data type for sheets
     */
    public void setSheetType(DataType type) { sheetType = type; }

    /**
     * sets which data type is being used for rows
     * @param type the data type for rows
     */
    public void setRowType(DataType type) { rowType = type; }

    /**
     * sets which data type is being used for columns
     * @param type the data type for columns
     */
    public void setColumnType(DataType type) { columnType = type; }

    /**
     * set the data struct to be used in writing
     * @param d the data
     */
    public void setData(FourDimArray d) { data = d;}

    /**
     * write the file
     * @param config the configuration object (contains sheet/column/row names)
     * @param outputFile the outputFile to write to
     * @return the response string to show in the dialog
     */
    public String write(OutputConfiguration config, File outputFile) {
        List<String> sheetNames = config.getTabs();
        List<String> columnHeaders = config.getColumns();
        List<String> rowHeaders = config.getRows();
        for (String sheetName: sheetNames) {
            addSheet(sheetName, columnHeaders, rowHeaders);
        }
        FileOutputStream fileOut;
        try {
            String extension = FilenameUtils.getExtension(outputFile.getName());
            if (!extension.equals("xlsx")) {
                outputFile = new File(outputFile + ".xlsx");
            }
            fileOut = new FileOutputStream(outputFile);
            workbook.write(fileOut);
            fileOut.close();
            return "File successfully exported";
        }
        catch (FileNotFoundException e) {
            return e.getMessage();
        }
        catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * add a sheet to the workbook
     * @param sheetName the name of the sheet
     * @param columnHeaders the headers for columns
     * @param rowHeaders the headers for rows
     */
    private void addSheet(String sheetName, List<String> columnHeaders, List<String> rowHeaders) {
        XSSFSheet sheet = workbook.createSheet(sheetIndex + " - " + WorkbookUtil.createSafeSheetName(sheetName));
        createHeaderRow(sheet, sheetName, columnHeaders);
        addData2Sheet(sheet, sheetName, columnHeaders, rowHeaders);
        sheetIndex++;
    }

    /**
     * create the first row of the sheet
     * @param sheet the sheet
     * @param sheetName the name of the sheet
     * @param columnHeaders the name of each column
     */
    void createHeaderRow(XSSFSheet sheet, String sheetName, List<String> columnHeaders) {
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

    /**
     * write the data rows to the sheet
     * @param sheet the sheet
     * @param sheetName the name of the sheet
     * @param columnHeaders the name of each column
     * @param rowHeaders the name of each row
     */
    void addData2Sheet(XSSFSheet sheet, String sheetName, List<String> columnHeaders, List<String> rowHeaders) {
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

    /**
     * get the value for a specific cell
     * params represent statistic, stimuli, and subject in some order
     * @param sheetName the name of the sheet
     * @param rowName the name of the row
     * @param columnName the name of the column
     * @return the value specified from the data struct
     */
    Double getValue(String sheetName, String rowName, String columnName) {
        SheetConfiguration config = new SheetConfiguration();
        config.set(sheetType, sheetName);
        config.set(rowType, rowName);
        config.set(columnType, columnName);
        return data.get(config);
    }

}
