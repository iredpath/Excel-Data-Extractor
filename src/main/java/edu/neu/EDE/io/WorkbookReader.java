package edu.neu.EDE.io;

/**
 * WorkbookReader - class to read in excel files
 * and add their data to internal structure
**/

import edu.neu.EDE.data_structs.SheetConfiguration;
import edu.neu.EDE.data_structs.FourDimArray;
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
    final int SHEET_MEDIA_NAME_CELL = 0;
    final int SLIDE_METRIC_ROW_START = 2;
    final int STATISTIC_NAME_CELL = 0;
    final int STATISTIC_VALUE_CELL = 5;
    final String SLIDE_METRIC_SUFFIX = "-SM";
    final String LOOK_ZONE_SUFFIX = "-LZ";
    final String LOOK_ZONE_OUT_SUFFIX = "-OUT";
    final String FILE_EXTENSION = ".xlsx";
    final String F_SHEET_END = " - F";
    final String STAT_SHEET_END = " - STAT";
    String subject;
    String media;
    String stimulus;
    String statistic;
    Double value;
    FourDimArray slideMetricData;
    FourDimArray lookZoneData;

    /**
     * create POI workbook from file to later extract data
     * @param f the excel file to read (already validated as excel)
     * @throws IOException throws IOException
     */
    public void readFile(File f) throws IOException {
        String filenameFull = f.getName();
        subject = filenameFull.replace(FILE_EXTENSION, "");
        FileInputStream fis = new FileInputStream(f);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        extractData(workbook);
        fis.close();
    }

    /**
     * loops through each sheet in the workbook
     * extracts data from data sheets, adds placeholders for missing sheets
     * @param workbook the workbook to extract from
     */
    void extractData(XSSFWorkbook workbook) {
        int numSheets = workbook.getNumberOfSheets();
        XSSFSheet sheet;
        String sheetName;
        for (int i = 0; i < numSheets; i++) {
            sheet = workbook.getSheetAt(i);
            sheetName = sheet.getSheetName();
            if (sheetName.endsWith(F_SHEET_END)) {
                extractMediaName(sheet);
                lookZoneData.setBlankSheet(subject, media);
                slideMetricData.setBlankSheet(subject, media);
            }
            else if (sheetName.endsWith(STAT_SHEET_END)) {
                extractStimuliFromSheet(sheet);
            }
        }
    }

    /**
     * gets meadia (image/video) name from sheet
     * maintained as a field in the reader object
     * @param sheet the sheet to extract the name from (F)
     */
    void extractMediaName(XSSFSheet sheet) {
        Row row = sheet.getRow(SHEET_MEDIA_NAME_ROW);
        Cell cell = row.getCell(SHEET_MEDIA_NAME_CELL);
        String uncleanMediaName = cell.getStringCellValue();
        String cleanName;
        if (uncleanMediaName.contains(";")) { // name from video study.  cut all after ";"
            cleanName = uncleanMediaName.split(";")[0];
        } else { // name from image study.  remove " DATA" at the end
            cleanName = uncleanMediaName.replace(F_SHEET_END, "");
        }
        media = cleanName;
    }

    /**
     * delegate to lower functions to extract out relevant data
     * @param sheet sheet to get data from
     */
    void extractStimuliFromSheet(XSSFSheet sheet) {
        int startLookZone = extractSlideMetricDataFromSheet(sheet);
        extractLookZoneDataFromSheet(sheet, startLookZone);
    }

    /**
     * get all slide metric data from sheet, add to slide metric data struct
     * @param sheet the sheet to get data from
     * @return the row index directly before the first look zone section
     */
    int extractSlideMetricDataFromSheet(XSSFSheet sheet) {
        Row row;
        int rowIndex = SLIDE_METRIC_ROW_START;
        String stimulus = media + SLIDE_METRIC_SUFFIX;
        SheetConfiguration config = new SheetConfiguration();
        config.setSubject(subject);
        config.setStimulus(stimulus);
        config.setMedia(media);
        while ((row = sheet.getRow(rowIndex++)) != null) {
            Cell statNameCell = row.getCell(STATISTIC_NAME_CELL);
            Cell statValueCell = row.getCell(STATISTIC_VALUE_CELL);
            statistic = statNameCell.getStringCellValue();
            value = statValueCell.getNumericCellValue();
            config.setStatistic(statistic);
            config.setValue(value);
            slideMetricData.set(config);
        }
        return rowIndex - 1;
    }

    /**
     * get relevant look zone information from sheet
     * @param sheet the sheet to get data from
     * @param startLookZone the row before the first look zone row
     */
    void extractLookZoneDataFromSheet(XSSFSheet sheet, int startLookZone) {
        List<Integer> nullIndices = getNullRowIndices(sheet, startLookZone);
        int numStats = getNumStats(sheet, nullIndices);
        addLookZoneData(sheet, numStats, nullIndices);
    }

    /**
     * gets the list of indices of null rows (separating look zones
     * @param sheet the sheet to get indices from
     * @param startIndex the first null row index
     * @return a list of integers representing indices of null rows
     */
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

    /**
     * gets the count for number of look zone stats
     * @param sheet the sheet to analyze
     * @param nullIndices the boundaries of each look zone
     * @return the number of look zone stats to extract
     */
    int getNumStats(XSSFSheet sheet, List<Integer> nullIndices) {
        // get the last not null row
        int rowIndex = nullIndices.get(nullIndices.size() - 1) - 1;
        int numStats = 0;
        while (sheet.getRow(rowIndex--).getCell(0) != null) { // loop until no more stats
            numStats++;
        }
        return numStats;
    }

    /**
     * adds the look zone data to the data structure
     * @param sheet sheet with data to extract
     * @param numStats number of stats to extract
     * @param nullIndices boundaries of each look zone
     */
    void addLookZoneData(XSSFSheet sheet, int numStats, List<Integer> nullIndices) {
        int numLookZones = nullIndices.size() - 1; // last entry is null line after final lookZone
        Row row;
        Cell statNameCell, statValueCell;
        SheetConfiguration config = new SheetConfiguration();
        config.setSubject(subject);
        for (int lookZoneIndex = 1; lookZoneIndex <= numLookZones; lookZoneIndex++) {
            if (numLookZones > lookZoneIndex) {
                stimulus = getStimulusName(sheet, nullIndices, lookZoneIndex);
            } else {
                stimulus = media + LOOK_ZONE_OUT_SUFFIX;
            }
            config.setStimulus(stimulus);
            config.setMedia(media);
            int endIndex = nullIndices.get(lookZoneIndex) - 1;
            int startIndex = endIndex - numStats;
            for (int i = startIndex + 1; i <= endIndex; i++) {
                row = sheet.getRow(i);
                statNameCell = row.getCell(STATISTIC_NAME_CELL);
                statistic = statNameCell.getStringCellValue();
                statValueCell = row.getCell(STATISTIC_VALUE_CELL);
                value = statValueCell == null ? null : statValueCell.getNumericCellValue();
                config.setStatistic(statistic);
                config.setValue(value);
                lookZoneData.set(config);
            }
        }
    }

    /**
     * gets a description for a look zone if present
     * @param sheet sheet to get info from
     * @param nullIndices bounds of each look zone
     * @param lookZoneIndex index of look zone we're working with
     * @return the description of the look zone added to its name,
     *  or just the name if no description exists
     */
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

    /**
     * initialize slide metric data struct
     * @param data the data struct for slide metric data
     */
    public void setSlideMetricData(FourDimArray data) { this.slideMetricData = data; }

    /**
     * initialize look zone metric data struct
     * @param data the data struct for look zone data
     */
    public void setLookZoneData(FourDimArray data) { this.lookZoneData = data; }

    /**
     * gets the current subject
     * @return the current subject
     */
    public String getSubject() { return this.subject; }
}