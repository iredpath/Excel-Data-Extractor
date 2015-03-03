package edu.neu.EDE.io;

import edu.neu.EDE.data_structs.SheetConfiguration;
import edu.neu.EDE.data_structs.ThreeDimArray;
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
    ThreeDimArray slideMetricData;
    ThreeDimArray lookZoneData;

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
        SheetConfiguration config = new SheetConfiguration();
        config.setSubject(subject);
        config.setStimulus(stimulus);
        while ((row = sheet.getRow(rowIndex++)) != null) {
            Cell statNameCell = row.getCell(STATISTIC_NAME_CELL);
            Cell statValueCell = row.getCell(STATISTIC_VALUE_CELL);
            statName = statNameCell.getStringCellValue();
            statValue = statValueCell.getNumericCellValue();

            config.setStatistic(statName);
            config.setValue(statValue);
            slideMetricData.set(config);
        }
        return rowIndex - 1;
    }


    void extractLookZoneDataFromSheet(XSSFSheet sheet, int startLookZone) {
        List<Integer> nullIndices = getNullRowIndices(sheet, startLookZone);
        int numStats = getNumStats(sheet, nullIndices);
        addLookZoneData(sheet, numStats, nullIndices);
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

    void addLookZoneData(XSSFSheet sheet, int numStats, List<Integer> nullIndices) {
        int numLookZones = nullIndices.size() - 1; // last entry is null line after final lookZone
        String stimulus, statName;
        Row row;
        Cell statNameCell, statValueCell;
        Double statValue;
        SheetConfiguration config = new SheetConfiguration();
        config.setSubject(subject);
        for (int lookZoneIndex = 1; lookZoneIndex <= numLookZones; lookZoneIndex++) {
            if (numLookZones > lookZoneIndex) {
                stimulus = getStimulusName(sheet, nullIndices, lookZoneIndex);
            } else {
                stimulus = media + LOOK_ZONE_OUT_SUFFIX;
            }
            config.setStimulus(stimulus);
            int endIndex = nullIndices.get(lookZoneIndex) - 1;
            int startIndex = endIndex - numStats;
            for (int i = endIndex; i > startIndex; i--) {
                row = sheet.getRow(i);
                statNameCell = row.getCell(STATISTIC_NAME_CELL);
                statName = statNameCell.getStringCellValue();
                statValueCell = row.getCell(STATISTIC_VALUE_CELL);
                statValue = statValueCell == null ? null : statValueCell.getNumericCellValue();
                config.setStatistic(statName);
                config.setValue(statValue);
                lookZoneData.set(config);
            }
        }
    }

    String getStimulusName(XSSFSheet sheet, List<Integer> nullIndices, int lookZoneIndex) {
        // TODO: lookZoneIndex may not be correct; look zones can be out of order
        String stimulus;
        stimulus = media + LOOK_ZONE_SUFFIX + lookZoneIndex;
        Row startRow = sheet.getRow(nullIndices.get(lookZoneIndex - 1) + 2); // row is null, +1 is LZ name, +2 is description
        Cell descCell = startRow.getCell(STATISTIC_VALUE_CELL);
        if (descCell != null) {
            stimulus += " (" + descCell.getStringCellValue() + ")";
        }
        return stimulus;
    }

    public void setSlideMetricData(ThreeDimArray data) { this.slideMetricData = data; }

    public void setLookZoneData(ThreeDimArray data) { this.lookZoneData = data; }
}