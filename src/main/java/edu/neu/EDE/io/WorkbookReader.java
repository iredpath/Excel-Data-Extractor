package edu.neu.EDE.io;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Ian on 2/26/15.
 */
public class WorkbookReader {

    final int SHEET_MEDIA_NAME_ROW = 0;
    final int SHEET_MEDIA_NAME_CELL = 5;
    final int SLIDE_METRIC_ROW_START = 2;
    final int STATISTIC_NAME_CELL = 0;
    final int STATISTIC_VALUE_CELL = 5;
    String subject;
    String media;

    public void readFile(File f) throws IOException {
        String filenameFull = f.getName();
        subject = filenameFull.replace(".xlsx", "");
        FileInputStream fis = new FileInputStream(f);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        extractData(workbook);
    }

    private void extractData(XSSFWorkbook workbook) {
        int numSheets = workbook.getNumberOfSheets();
        XSSFSheet sheet;
        String sheetName;
        String[] sheetNamePieces;
        for (int i = 0; i < numSheets; i++) {
            sheet = workbook.getSheetAt(i);
            sheetName = sheet.getSheetName();
            // valid sheets end in " - F", " - G", or " - STAT"
            sheetNamePieces = sheetName.split(" - ");
            if (sheetNamePieces.length < 2) { // bad sheet, ignore it
                continue;
            }
            if (sheetNamePieces[1].equals("STAT")) {
                extractMediaName(sheet);
                extractStimuliFromSheet(sheet);
            }
        }
    }

    private void extractMediaName(XSSFSheet sheet) {
        Row row = sheet.getRow(SHEET_MEDIA_NAME_ROW);
        Cell cell = row.getCell(SHEET_MEDIA_NAME_CELL);
        String uncleanMediaName = cell.getStringCellValue();
        String cleanName;
        if (uncleanMediaName.contains(";")) { // name from video study.  cut all after ";"
            cleanName = uncleanMediaName.split(";")[0];
        } else { // name from image study.  remove " DATA" at the end
            cleanName = uncleanMediaName.replace(" DATA", "");
        }
        media = cleanName;
    }

    private void extractStimuliFromSheet(XSSFSheet sheet) {
        int endSlideMetric = extractSlideMetricDataFromSheet(sheet);
        extractLookZoneDataFromSheet(sheet, endSlideMetric);
    }

    private int extractSlideMetricDataFromSheet(XSSFSheet sheet) {
        Row row;
        int rowIndex = SLIDE_METRIC_ROW_START;
        String statName;
        Double statValue;
        String stimuli = media + "-SM";
        while ((row = sheet.getRow(rowIndex++)) != null) {
            Cell statNameCell = row.getCell(STATISTIC_NAME_CELL);
            Cell statValueCell = row.getCell(STATISTIC_VALUE_CELL);
            statName = statNameCell.getStringCellValue();
            statValue = statValueCell.getNumericCellValue();
            //TODO: set data when integrated with 4d-array
            // set-the-data(subject, media, stimuli, statName, statValue)
        }
        return rowIndex;
    }

    private void extractLookZoneDataFromSheet(XSSFSheet sheet, int endSlideMetricData) {
        //TODO: add logic to extract look zone data
    }

}