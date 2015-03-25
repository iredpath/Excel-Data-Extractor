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
    private Map<DataType, Map<String, Boolean>> lookZoneDataType2Status;
    private Map<DataType, Map<String, Boolean>> slideMetricDataType2Status;
    private DataType tabType;
    private DataType columnType;
    private DataType rowType;

    public GuiModel() {
        this.reader = new WorkbookReader();
        this.lookZoneData = new FourDimArray();
        this.slideMetricData = new FourDimArray();
        this.validFiles = new HashMap<String, String>();
        this.invalidFiles = new HashSet<String>();

        this.lookZoneDataType2Status = new HashMap<DataType, Map<String, Boolean>>();
        this.lookZoneDataType2Status.put(DataType.SUBJECT, new HashMap<String, Boolean>());
        this.lookZoneDataType2Status.put(DataType.STIMULUS, new HashMap<String, Boolean>());
        this.lookZoneDataType2Status.put(DataType.STATISTIC, new HashMap<String, Boolean>());

        this.slideMetricDataType2Status = new HashMap<DataType, Map<String, Boolean>>();
        this.slideMetricDataType2Status.put(DataType.SUBJECT, new HashMap<String, Boolean>());
        this.slideMetricDataType2Status.put(DataType.STIMULUS, new HashMap<String, Boolean>());
        this.slideMetricDataType2Status.put(DataType.STATISTIC, new HashMap<String, Boolean>());

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
        update();
    }

    void addFile(File f) throws IOException {
        reader.readFile(f);
        String subjectName = reader.getSubject();
        validFiles.put(f.getName(), subjectName);
    }

    void update() {
        for (String subject: slideMetricData.getSubjects()) {
            if (slideMetricDataType2Status.get(DataType.SUBJECT).get(subject) == null) {
                slideMetricDataType2Status.get(DataType.SUBJECT).put(subject, true);
            }
        }
        for (String stimulus: slideMetricData.getStimuli()) {
            if (slideMetricDataType2Status.get(DataType.STIMULUS).get(stimulus) == null) {
                slideMetricDataType2Status.get(DataType.STIMULUS).put(stimulus, true);
            }
        }
        for (String statistic: slideMetricData.getStatistics()) {
            if (slideMetricDataType2Status.get(DataType.STATISTIC).get(statistic) == null) {
                slideMetricDataType2Status.get(DataType.STATISTIC).put(statistic, true);
            }
        }
        for (String subject: lookZoneData.getSubjects()) {
            if (lookZoneDataType2Status.get(DataType.SUBJECT).get(subject) == null) {
                lookZoneDataType2Status.get(DataType.SUBJECT).put(subject, true);
            }
        }
        for (String stimulus: lookZoneData.getStimuli()) {
            if (lookZoneDataType2Status.get(DataType.STIMULUS).get(stimulus) == null) {
                lookZoneDataType2Status.get(DataType.STIMULUS).put(stimulus, true);
            }
        }
        for (String statistic: lookZoneData.getStatistics()) {
            if (lookZoneDataType2Status.get(DataType.STATISTIC).get(statistic) == null) {
                lookZoneDataType2Status.get(DataType.STATISTIC).put(statistic, true);
            }
        }
    }

    Map<String, String> getFiles() {
        return validFiles;
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

    Map<DataType, Map<String, Boolean>> getDataFor(String which) {
        if (which.equals("lookZone")) {
            return this.lookZoneDataType2Status;
        } else {
            return this.slideMetricDataType2Status;
        }
    }

    void setAsDeselected(String name, DataType type, String which) {
        if (which.equals("lookZone")) {
            this.lookZoneDataType2Status.get(type).put(name, false);
        } else {
            this.slideMetricDataType2Status.get(type).put(name, false);
        }
    }

    void setAsSelected(String name, DataType type, String which) {
        if (which.equals("lookZone")) {
            this.slideMetricDataType2Status.get(type).put(name, true);
        } else {
            this.lookZoneDataType2Status.get(type).put(name, true);
        }
    }

    void export(String which) {
        WorkbookWriter writer = new WorkbookWriter();
        writer.setColumnType(columnType);
        writer.setData(which.equals("lookZone") ? lookZoneData : slideMetricData);
        writer.setRowType(rowType);
        writer.setSheetType(tabType);
        // FUCK.  This may get ugly.  we have lists of stats/subjects/stimuli, not tabs/rows/columns
        // Have I mentioned I hate how swing isn't even remotely backed by real data?
        List<String> statistics = getSelectedData(which, DataType.STATISTIC);
        List<String> stimuli = getSelectedData(which, DataType.STIMULUS);
        List<String> subjects = getSelectedData(which, DataType.SUBJECT);
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(statistics);
        config.setSubjects(subjects);
        config.setStimuli(stimuli);
        config.setRow(rowType);
        config.setColumn(columnType);
        config.setTab(tabType);
        writer.write(config, "test-output");

    }

    List<String> getSelectedData(String which, DataType type) {
        Map<String, Boolean> map = which.equals("lookZone") ? lookZoneDataType2Status.get(type) : slideMetricDataType2Status.get(type);
        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, Boolean> e: map.entrySet()) {
            if (e.getValue()) {
                result.add(e.getKey());
            }
        }
        return result;
    }

}
