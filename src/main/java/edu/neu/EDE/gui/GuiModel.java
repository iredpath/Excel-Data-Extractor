package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataGroupType;
import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.OutputConfiguration;
import edu.neu.EDE.io.WorkbookReader;
import edu.neu.EDE.io.WorkbookWriter;

import javax.swing.DefaultListModel;
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
    private Map<DataType, CheckboxListModel> lookZoneDataType2Excluded;
    private Map<DataType, CheckboxListModel> slideMetricDataType2Excluded;
    private DataType tabType;
    private DataType columnType;
    private DataType rowType;

    public GuiModel() {
        this.reader = new WorkbookReader();
        this.lookZoneData = new FourDimArray();
        this.slideMetricData = new FourDimArray();
        this.validFiles = new HashMap<String, String>();
        this.invalidFiles = new HashSet<String>();

        this.lookZoneDataType2Excluded = new HashMap<DataType, CheckboxListModel>();
        this.lookZoneDataType2Excluded.put(DataType.SUBJECT, new CheckboxListModel());
        this.lookZoneDataType2Excluded.put(DataType.STIMULUS, new CheckboxListModel());
        this.lookZoneDataType2Excluded.put(DataType.STATISTIC, new CheckboxListModel());

        this.slideMetricDataType2Excluded = new HashMap<DataType, CheckboxListModel>();
        this.slideMetricDataType2Excluded.put(DataType.SUBJECT, new CheckboxListModel());
        this.slideMetricDataType2Excluded.put(DataType.STIMULUS, new CheckboxListModel());
        this.slideMetricDataType2Excluded.put(DataType.STATISTIC, new CheckboxListModel());

        this.tabType = DataType.STATISTIC;
        this.columnType = DataType.STIMULUS;
        this.rowType = DataType.SUBJECT;

        reader.setLookZoneData(lookZoneData);
        reader.setSlideMetricData(slideMetricData);
    }

    void swapAxes() {
        DataType tmp = columnType;
        columnType = rowType;
        rowType = tmp;
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
        updateJListModels();
    }

    void updateJListModels() {
        CheckboxListModel listModel = slideMetricDataType2Excluded.get(DataType.SUBJECT);
        List<String> list = slideMetricData.getSubjects();
        for (String s: list) {
            if (!listModel.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                listModel.addElement(item);
            }
        }

        listModel = slideMetricDataType2Excluded.get(DataType.STIMULUS);
        list = slideMetricData.getStimuli();
        for (String s: list) {
            if (!listModel.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                listModel.addElement(item);
            }
        }

        listModel = slideMetricDataType2Excluded.get(DataType.STATISTIC);
        list = slideMetricData.getStatistics();
        for (String s: list) {
            if (!listModel.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                listModel.addElement(item);
            }
        }

        listModel = lookZoneDataType2Excluded.get(DataType.SUBJECT);
        list = lookZoneData.getSubjects();
        for (String s: list) {
            if (!listModel.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                listModel.addElement(item);
            }
        }

        listModel = lookZoneDataType2Excluded.get(DataType.STIMULUS);
        list = lookZoneData.getStimuli();
        for (String s: list) {
            if (!listModel.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                listModel.addElement(item);
            }
        }

        listModel = lookZoneDataType2Excluded.get(DataType.STATISTIC);
        list = lookZoneData.getStatistics();
        for (String s: list) {
            if (!listModel.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                listModel.addElement(item);
            }
        }
    }

    DefaultListModel<CheckboxListItem> getListModel(DataGroupType groupType, DataType dataType) {
        if (groupType.equals(DataGroupType.LOOKZONE)) {
            return lookZoneDataType2Excluded.get(dataType);
        } else {
            return slideMetricDataType2Excluded.get(dataType);
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

    void export(DataGroupType groupType, File outputFile) {
        WorkbookWriter writer = new WorkbookWriter();
        writer.setColumnType(columnType);
        Map<DataType, CheckboxListModel> map = groupType.equals(DataGroupType.LOOKZONE) ? lookZoneDataType2Excluded : slideMetricDataType2Excluded;
        List<String> statistics = getSelectedData(map.get(DataType.STATISTIC));
        List<String> stimuli = getSelectedData(map.get(DataType.STIMULUS));
        List<String> subjects = getSelectedData(map.get(DataType.SUBJECT));
        FourDimArray data = groupType.equals(DataGroupType.LOOKZONE) ? lookZoneData : slideMetricData;
        writer.setData(data);
        writer.setRowType(rowType);
        writer.setSheetType(tabType);
        writer.setColumnType(columnType);
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(statistics);
        config.setSubjects(subjects);
        config.setStimuli(stimuli);
        config.setRow(rowType);
        config.setColumn(columnType);
        config.setTab(tabType);
        writer.write(config, outputFile);
    }

    // How too slow is this?
    List<String> getSelectedData(DefaultListModel<CheckboxListItem> model) {
        List<String> retVal = new ArrayList<String>();
        for (int i = 0; i < model.getSize(); i++) {
            CheckboxListItem item = model.get(i);
            if (item.isSelected()) {
                retVal.add(item.toString());
            }
        }
        return retVal;
    }

    public DataType getColumnType() { return columnType; }

    public DataType getRowType() { return rowType; }

}
