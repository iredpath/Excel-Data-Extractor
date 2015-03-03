package edu.neu.EDE.io;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WorkbookReader {

    final int SHEET_MEDIA_NAME_ROW = 0;
    final int SHEET_MEDIA_NAME_CELL = 5;
    final int SLIDE_METRIC_ROW_START = 2;
    final int STATISTIC_NAME_CELL = 0;
    final int STATISTIC_VALUE_CELL = 5;
    final String SLIDE_METRIC_SUFFIX = "-SM";
    final String LOOK_ZONE_SUFFIX = "-LZ";
    final String LOOK_ZONE_OUT_SUFFIX = "-OUT";
    final String FILE_EXTENSION = ".xlsx";
    String subject;
    String media;

    public void readFile(File f) throws IOException {
        String filenameFull = f.getName();
        subject = filenameFull.replace(FILE_EXTENSION, "");
        FileInputStream fis = new FileInputStream(f);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        extractData(workbook);
    }

    void extractData(XSSFWorkbook workbook) {
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

    void extractMediaName(XSSFSheet sheet) {
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

    void extractStimuliFromSheet(XSSFSheet sheet) {
        int startLookZone = extractSlideMetricDataFromSheet(sheet);
        extractLookZoneDataFromSheet(sheet, startLookZone);
    }

    int extractSlideMetricDataFromSheet(XSSFSheet sheet) {
        Row row;
        int rowIndex = SLIDE_METRIC_ROW_START;
        String statName;
        Double statValue;
        String stimulus = media + SLIDE_METRIC_SUFFIX;
        while ((row = sheet.getRow(rowIndex++)) != null) {
            Cell statNameCell = row.getCell(STATISTIC_NAME_CELL);
            Cell statValueCell = row.getCell(STATISTIC_VALUE_CELL);
            statName = statNameCell.getStringCellValue();
            statValue = statValueCell.getNumericCellValue();
            //TODO: set data when integrated with 4d-array
            // slideMetricData.set(subject, media, stimulus, statName, statValue);
        }
        return rowIndex - 1;
    }


    void extractLookZoneDataFromSheet(XSSFSheet sheet, int startLookZone) {
        List<Integer> nullIndices = getNullRowIndices(sheet, startLookZone);
        int numStats = getNumStats(sheet, nullIndices);
        readLookZoneData(sheet, numStats, nullIndices);
    }

    List<Integer> getNullRowIndices(XSSFSheet sheet, int startIndex) {
        List<Integer> nullIndices = new ArrayList<Integer>();
        int rowCounter = startIndex;
        Row row;
        while (true) {
            row = sheet.getRow(rowCounter++);
            if (row == null) {
                nullIndices.add(rowCounter - 1);
                if (sheet.getRow(rowCounter) == null) {
                    break;
                }
            }
        }
        // we need to increment the first null row by 1 since there is an extra text field to ignore
        nullIndices.set(0, nullIndices.get(0) + 1);
        return nullIndices;
    }

    int getNumStats(XSSFSheet sheet, List<Integer> nullIndices) {
        // get the last not null row
        int rowIndex = nullIndices.get(nullIndices.size() - 1) - 1;
        int numStats = 0;
        while (sheet.getRow(rowIndex--).getCell(0) != null) { // loop until no more stats
            numStats++;
        }
        return numStats;
    }

    void readLookZoneData(XSSFSheet sheet, int numStats, List<Integer> nullIndices) {
        int numLookZones = nullIndices.size() - 1; // last entry is null line after final lookZone
        // getting first lookZone (may be out)
        String stimulus, statName;
        Double statValue;
        Row row;
        for (int lookZoneIndex = 1; lookZoneIndex <= numLookZones; lookZoneIndex++) {
            if (numLookZones > lookZoneIndex) {
                stimulus = getStimulusName(sheet, nullIndices, lookZoneIndex);
            } else {
                stimulus = media + LOOK_ZONE_OUT_SUFFIX;
            }
            int endIndex = nullIndices.get(lookZoneIndex) - 1;
            int startIndex = endIndex - numStats;
            addLookZoneData(sheet, startIndex, endIndex);
        }
    }

    void addLookZoneData(XSSFSheet sheet, int startIndex, int endIndex) {
        Row row;
        String statName;
        Double statValue;
        for (int i = endIndex; i > startIndex; i--) {
            row = sheet.getRow(i);
            Cell statNameCell = row.getCell(STATISTIC_NAME_CELL);
            statName = statNameCell.getStringCellValue();
            Cell statValueCell = row.getCell(STATISTIC_VALUE_CELL);
            statValue = statValueCell == null ? null : statValueCell.getNumericCellValue();
            // lookZoneData.set(subject, media, stimulus, statName, statValue);
        }
    }

    String getStimulusName(XSSFSheet sheet, List<Integer> nullIndices, int lookZoneIndex) {
        String stimulus;
        stimulus = media + LOOK_ZONE_SUFFIX + lookZoneIndex;
        Row startRow = sheet.getRow(nullIndices.get(lookZoneIndex - 1) + 2); // row is null, +1 is LZ name, +2 is description
        Cell descCell = startRow.getCell(STATISTIC_VALUE_CELL);
        if (descCell != null) {
            stimulus += " (" + descCell.getStringCellValue() + ")";
        }
        return stimulus;
    }
}