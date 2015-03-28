package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataGroupType;
import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.DataTypeModelManager;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.OutputConfiguration;
import edu.neu.EDE.gui.checkboxList.CheckboxListItem;
import edu.neu.EDE.gui.checkboxList.CheckboxListModel;
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
    private CheckboxListModel fileListModel;
    private DataTypeModelManager dataTypeModelManager;
    private DataType tabType;
    private DataType columnType;
    private DataType rowType;
    private DataGroupType dataGroupType;

    public GuiModel() {
        this.reader = new WorkbookReader();
        this.lookZoneData = new FourDimArray();
        this.slideMetricData = new FourDimArray();
        this.invalidFiles = new HashSet<String>();

        this.dataTypeModelManager = new DataTypeModelManager();

        this.fileListModel = new CheckboxListModel();

        this.tabType = DataType.STATISTIC;
        this.columnType = DataType.STIMULUS;
        this.rowType = DataType.SUBJECT;
        this.dataGroupType = DataGroupType.SLIDEMETRIC;

        reader.setLookZoneData(lookZoneData);
        reader.setSlideMetricData(slideMetricData);
    }

    void updateDataGroupType(DataGroupType d) {
        dataGroupType = d;
    }

    void swapAxes() {
        DataType tmp = columnType;
        columnType = rowType;
        rowType = tmp;
    }

    void updateSheetType(DataType d) {
        DataType tmp = tabType;
        tabType = d;
        if (rowType.equals(tabType)) {
            rowType = tmp;
        } else {
            columnType = tmp;
        }
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

    void addToFileListModel(File f, String subject) {
        if (!fileListModel.contains(f.getName())) {
            CheckboxListItem i = new CheckboxListItem(f.getName());
            i.setSubject(subject);
            i.setFile(f);
            fileListModel.addElement(i);
        }
    }

    void removeSelectedFiles() throws IOException {
        CheckboxListItem[] allFiles = new CheckboxListItem[fileListModel.getSize()];
        fileListModel.copyInto(allFiles);
        fileListModel.removeAllElements();
        for (CheckboxListItem item: allFiles) {
           if (!item.isSelected()) {
               addFile(item.getFile());
           }
        }
        clearModels();
        updateJListModels();
    }

    void addNewItemsToModel(List<String> items, CheckboxListModel model) {
        for (String s: items) {
            if (!model.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                model.addElement(item);
            }
        }
    }

    void clearModels() {
        for (CheckboxListModel m: dataTypeModelManager.getAllModels()) {
            m.removeAllElements();
        }
    }
    
    void updateJListModels() {
        CheckboxListModel listModel = dataTypeModelManager.get(DataGroupType.SLIDEMETRIC, DataType.SUBJECT);
        List<String> list = slideMetricData.getSubjects();
        addNewItemsToModel(list, listModel);

        listModel = dataTypeModelManager.get(DataGroupType.SLIDEMETRIC, DataType.STIMULUS);
        list = slideMetricData.getStimuli();
        addNewItemsToModel(list, listModel);

        listModel = dataTypeModelManager.get(DataGroupType.SLIDEMETRIC, DataType.STATISTIC);
        list = slideMetricData.getStatistics();
        addNewItemsToModel(list, listModel);

        listModel = dataTypeModelManager.get(DataGroupType.LOOKZONE, DataType.SUBJECT);
        list = lookZoneData.getSubjects();
        addNewItemsToModel(list, listModel);

        listModel = dataTypeModelManager.get(DataGroupType.LOOKZONE, DataType.STIMULUS);
        list = lookZoneData.getStimuli();
        addNewItemsToModel(list, listModel);

        listModel = dataTypeModelManager.get(DataGroupType.LOOKZONE, DataType.STATISTIC);
        list = lookZoneData.getStatistics();
        addNewItemsToModel(list, listModel);
    }

    void addFile(File f) throws IOException {
        reader.readFile(f);
        String subjectName = reader.getSubject();
        addToFileListModel(f, subjectName);
    }

    CheckboxListModel getFileListModel() {
        return fileListModel;
    }

    void selectAll(DataType which) {
        setAllItemsAs(true, which);
    }

    void deselectAll(DataType which) {
        setAllItemsAs(false, which);
    }

    void setAllItemsAs(boolean what, DataType which) {
        CheckboxListModel model = getActiveModel(which);
        for (int i = 0; i < model.getSize(); i++) {
            CheckboxListItem item = model.get(i);
            item.setSelected(what);
        }
    }

    CheckboxListModel getActiveModel(DataType dataType) {
        return dataTypeModelManager.get(dataGroupType, dataType);
    }

    void export(File outputFile) {
        WorkbookWriter writer = new WorkbookWriter();
        writer.setColumnType(columnType);
        List<String> statistics = getSelectedData(dataTypeModelManager.get(dataGroupType, DataType.STATISTIC));
        List<String> stimuli = getSelectedData(dataTypeModelManager.get(dataGroupType, DataType.STIMULUS));
        List<String> subjects = getSelectedData(dataTypeModelManager.get(dataGroupType, DataType.SUBJECT));
        FourDimArray data = dataGroupType.equals(DataGroupType.LOOKZONE) ? lookZoneData : slideMetricData;
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
