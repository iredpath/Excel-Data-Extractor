package edu.neu.EDE;

import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.io.WorkbookReader;
import edu.neu.EDE.io.WorkbookWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataExtractor {

    private WorkbookReader reader;
    private ArrayList<String> invalidFiles;
    private FourDimArray slideMetricData;
    private FourDimArray lookZoneData;

    public ExcelDataExtractor() {
        this.reader = new WorkbookReader();
        this.invalidFiles = new ArrayList<String>();
        this.slideMetricData = new FourDimArray();
        this.lookZoneData = new FourDimArray();
        reader.setSlideMetricData(slideMetricData);
        reader.setLookZoneData(lookZoneData);
    }

    public static void main(String[] args) throws IOException {
        ExcelDataExtractor extractor = new ExcelDataExtractor();
        for (String s: args) {
            File f = new File(s);
            extractor.readFile(f);
        }
        extractor.writeFiles();
    }

    private void readFile(File f) throws IOException {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File subFile: files) {
                readFile(subFile);
            }
        } else if (f.getName().contains(".xlsx") && !f.getName().contains("~$")) {
            reader.readFile(f);
        } else {
            invalidFiles.add(f.getAbsolutePath());
        }
    }

    private void writeFiles() {
        WorkbookWriter writer = new WorkbookWriter();
        writer.setSheetType(DataType.STATISTIC);
        writer.setColumnType(DataType.STIMULUS);
        writer.setRowType(DataType.SUBJECT);
        writer.setData(slideMetricData);
        List<String> statistics = slideMetricData.getStatistics();
        List<String> stimuli = slideMetricData.getStimuli();
        List<String> subjects = slideMetricData.getSubjects();
        writer.write(statistics, stimuli, subjects, "slideMetricOutput");
        writer.setData(lookZoneData);
        writer.reset();
        statistics = lookZoneData.getStatistics();
        stimuli = lookZoneData.getStimuli();
        subjects = lookZoneData.getSubjects();
        writer.write(statistics, stimuli, subjects, "lookZoneOutput");
    }
}