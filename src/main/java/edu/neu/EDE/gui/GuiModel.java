package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.OutputConfiguration;
import edu.neu.EDE.io.WorkbookReader;
import edu.neu.EDE.io.WorkbookWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ian on 3/24/15.
 */
public class GuiModel {

    private WorkbookReader reader;
    private FourDimArray lookZoneData;
    private FourDimArray slideMetricData;
    private Set<String> invalidFiles;
    private Map<String, String> validFiles;
    private Map<DataType, Set<String>> lookZoneDataType2Excluded;
    private Map<DataType, Set<String>> slideMetricDataType2Excluded;
    private DataType tabType;
    private DataType columnType;
    private DataType rowType;

    public GuiModel() {
        this.reader = new WorkbookReader();
        this.lookZoneData = new FourDimArray();
        this.slideMetricData = new FourDimArray();
        this.validFiles = new HashMap<String, String>();
        this.invalidFiles = new HashSet<String>();

        this.lookZoneDataType2Excluded = new HashMap<DataType, Set<String>>();
        this.lookZoneDataType2Excluded.put(DataType.SUBJECT, new HashSet<String>());
        this.lookZoneDataType2Excluded.put(DataType.STIMULUS, new HashSet<String>());
        this.lookZoneDataType2Excluded.put(DataType.STATISTIC, new HashSet<String>());

        this.slideMetricDataType2Excluded = new HashMap<DataType, Set<String>>();
        this.slideMetricDataType2Excluded.put(DataType.SUBJECT, new HashSet<String>());
        this.slideMetricDataType2Excluded.put(DataType.STIMULUS, new HashSet<String>());
        this.slideMetricDataType2Excluded.put(DataType.STATISTIC, new HashSet<String>());

        this.tabType = DataType.STATISTIC;
        this.columnType = DataType.STIMULUS;
        this.rowType = DataType.SUBJECT;

        reader.setLookZoneData(lookZoneData);
        reader.setSlideMetricData(slideMetricData);
    }

    void addFiles(File[] files) throws IOException {
        for (File f: files) {
            if (f.isDirectory()) {
                File[] subFiles = f.listFiles();
                addFiles(subFiles);
            } else if (f.getName().contains(".xlsx") && !f.getName().contains("~$")) {
                addFile(f);
            } else {
                invalidFiles.add(f.getAbsolutePath());
            }
        }
    }

    void addFile(File f) throws IOException {
        reader.readFile(f);
        String subjectName = reader.getSubject();
        validFiles.put(f.getName(), subjectName);
    }

    Map<String, String> getFiles() {
        return validFiles;
    }

    void remove(String filename, String subject) {
        removeFile(filename);
        removeSubject(subject);
    }
    void removeSubject(String subject) {
        //TODO: remove subject data
    }

    void removeFile(String filename) {
        validFiles.remove(filename);
    }

    List<String> getSubjects(String which) {
        if (which.equals("slideMetric")) {
            return slideMetricData.getSubjects();
        } else {
            return lookZoneData.getSubjects();
        }
    }

    List<String> getStimuli(String which) {
        if (which.equals("slideMetric")) {
            return slideMetricData.getStimuli();
        } else {
            return lookZoneData.getStimuli();
        }
    }

    List<String> getStatistics(String which) {
        if (which.equals("slideMetric")) {
            return slideMetricData.getStatistics();
        } else {
            return lookZoneData.getStatistics();
        }
    }

    Map<DataType, Set<String>> getDataFor(String which) {
        if (which.equals("lookZone")) {
            return this.lookZoneDataType2Excluded;
        } else {
            return this.slideMetricDataType2Excluded;
        }
    }

    void setAsDeselected(String name, DataType type, String which) {
        if (which.equals("lookZone")) {
            this.lookZoneDataType2Excluded.get(type).add(name);
        } else {
            this.slideMetricDataType2Excluded.get(type).add(name);
        }
    }

    void setAsSelected(String name, DataType type, String which) {
        if (which.equals("lookZone")) {
            this.lookZoneDataType2Excluded.get(type).remove(name);
        } else {
            this.slideMetricDataType2Excluded.get(type).remove(name);
        }
    }

    void export(String which) {
        WorkbookWriter writer = new WorkbookWriter();
        writer.setColumnType(columnType);
        FourDimArray data = which.equals("lookZone") ? lookZoneData : slideMetricData;
        writer.setData(data);
        writer.setRowType(rowType);
        writer.setSheetType(tabType);
        // FUCK.  This may get ugly.  we have lists of stats/subjects/stimuli, not tabs/rows/columns
        // Have I mentioned I hate how swing isn't even remotely backed by real data?
        List<String> statistics = getSelectedData(which, DataType.STATISTIC, data.getStatistics());
        List<String> stimuli = getSelectedData(which, DataType.STIMULUS, data.getStimuli());
        List<String> subjects = getSelectedData(which, DataType.SUBJECT, data.getSubjects());
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(statistics);
        config.setSubjects(subjects);
        config.setStimuli(stimuli);
        config.setRow(rowType);
        config.setColumn(columnType);
        config.setTab(tabType);
        writer.write(config, "test-output");
    }

    // How too slow is this?
    List<String> getSelectedData(String which, DataType type, List<String> all) {
        Set<String> excluded = which.equals("lookZone") ? lookZoneDataType2Excluded.get(type) : slideMetricDataType2Excluded.get(type);
        List<String> result = new ArrayList<String>();
        for (String s: all) {
            if (!excluded.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }

}
