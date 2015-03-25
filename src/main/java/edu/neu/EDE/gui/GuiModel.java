package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.io.WorkbookReader;

import java.io.File;
import java.io.IOException;
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
    private Map<DataType, Set<String>> includedData;

    public GuiModel() {
        this.reader = new WorkbookReader();
        this.lookZoneData = new FourDimArray();
        this.slideMetricData = new FourDimArray();
        this.validFiles = new HashMap<String, String>();
        this.invalidFiles = new HashSet<String>();

        this.includedData = new HashMap<DataType, Set<String>>();
        includedData.put(DataType.SUBJECT, new HashSet<String>());
        includedData.put(DataType.STIMULUS, new HashSet<String>());
        includedData.put(DataType.STATISTIC, new HashSet<String>());
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

    void setAsSelected(String name, DataType type) {
        includedData.get(type).add(name);
    }

    void setAsDeselected(String name, DataType type) {
        includedData.get(type).remove(name);
    }
}
