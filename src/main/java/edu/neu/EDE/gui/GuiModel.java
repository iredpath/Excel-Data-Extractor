package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataGroupType;
import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.DataTypeModelManager;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.OutputConfiguration;
import edu.neu.EDE.gui.checkbox_list.CheckboxListItem;
import edu.neu.EDE.gui.checkbox_list.CheckboxListModel;
import edu.neu.EDE.io.WorkbookReader;
import edu.neu.EDE.io.WorkbookWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * GuiModel - model for GuiView
 * maintain/manipulate/access data to be displayed
 * and later exported
 */

/**
 * @author Ian Redpath
 * @version 3/24/2015
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

    /**
     * basic constructor
     * initialize necessary fields, set default values
     */
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

    /**
     * set the data group type (lookzone/slide metric)
     * @param d the new data group type
     */
    void updateDataGroupType(DataGroupType d) {
        dataGroupType = d;
    }

    /**
     * swap the column and row data types
     */
    void swapAxes() {
        DataType tmp = columnType;
        columnType = rowType;
        rowType = tmp;
    }

    /**
     * set the data type to be used per sheet
     * @param d the new sheet data type
     */
    void updateSheetType(DataType d) {
        DataType tmp = tabType;
        tabType = d;
        if (rowType.equals(tabType)) {
            rowType = tmp;
        } else if (columnType.equals(tabType)) {
            columnType = tmp;
        }
    }

    /**
     * add data from excel files to data structures
     * @param files the files to add
     * @throws IOException if POI can't read the file
     */
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

    /**
     * add information relevant to this file
     * to the model backing the list of files
     * @param f the file
     * @param subject the subject of the file
     */
    void addToFileListModel(File f, String subject) {
        if (!fileListModel.contains(f.getName())) {
            CheckboxListItem i = new CheckboxListItem(f.getName());
            i.setSubject(subject);
            i.setFile(f);
            fileListModel.addElement(i);
        }
    }

    /**
     * removes files marked as selected from the data structure
     * internally, removes all files first,
     * then adds files that were not marked for removal
     * @throws IOException if POI can't re-add the unmarked file(s)
     */
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

    /**
     * adds new data items to their relevant model
     * @param items all items for a given data type
     * @param model the model for these items
     */
    void addNewItemsToModel(List<String> items, CheckboxListModel model) {
        for (String s: items) {
            if (!model.contains(s)) {
                CheckboxListItem item = new CheckboxListItem(s);
                item.setSelected(true);
                model.addElement(item);
            }
        }
    }

    /**
     * removes all items from every data type model
     */
    void clearModels() {
        for (CheckboxListModel m: dataTypeModelManager.getAllModels()) {
            m.removeAllElements();
        }
    }

    /**
     * updates all data type models
     * adds all data items not previously part of the models
     */
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

    /**
     * add a specific file
     * @param f the file
     * @throws IOException if POI can't read the file
     */
    void addFile(File f) throws IOException {
        reader.readFile(f);
        String subjectName = reader.getSubject();
        addToFileListModel(f, subjectName);
    }

    /**
     * get the model backing the list of added files
     * @return the model backing the list of added files
     */
    CheckboxListModel getFileListModel() {
        return fileListModel;
    }

    /**
     * set all data items as selected
     * @param which the type of data
     */
    void selectAll(DataType which) {
        setAllItemsAs(true, which);
    }

    /**
     * set all data items as deselected
     * @param which the type of data
     */
    void deselectAll(DataType which) {
        setAllItemsAs(false, which);
    }

    /**
     * set all data items as selected or deselected
     * @param what state to set items to (true = selected)
     * @param which which type of data to set
     */
    void setAllItemsAs(boolean what, DataType which) {
        CheckboxListModel model = getActiveModel(which);
        for (int i = 0; i < model.getSize(); i++) {
            CheckboxListItem item = model.get(i);
            item.setSelected(what);
        }
    }

    /**
     * gets the model backing the currently selected data group type
     * @param dataType the data type of the desired model
     * @return the model backing that data type
     */
    CheckboxListModel getActiveModel(DataType dataType) {
        return dataTypeModelManager.get(dataGroupType, dataType);
    }

    /**
     * writes the desired data to the specified output file
     * @param outputFile the output file to write to
     */
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

    /**
     * get all selected data items from the given model
     * @param model the model containing the data items
     * @return the list of selected data items
     */
    List<String> getSelectedData(CheckboxListModel model) {
        // How too slow is this?
        List<String> retVal = new ArrayList<String>();
        for (int i = 0; i < model.getSize(); i++) {
            CheckboxListItem item = model.get(i);
            if (item.isSelected()) {
                retVal.add(item.toString());
            }
        }
        return retVal;
    }

    /**
     * get the current data type for columns
     * @return the column data type
     */
    public DataType getColumnType() { return columnType; }

    /**
     * get the current data type for rows
     * @return the row data type
     */
    public DataType getRowType() { return rowType; }

    /**
     * get the current data type for tabs
     * @return the tab data type
     */
    public DataType getTabType() { return tabType; }

    /**
     * return the current data group type
     * @return the data group type
     */
    public DataGroupType getDataGroupType() { return dataGroupType; }

    /**
     * return the set of invalid files that user tried to add
     * @return the set of invalid files
     */
    public Set<String> getInvalidFiles() { return invalidFiles; }
}
