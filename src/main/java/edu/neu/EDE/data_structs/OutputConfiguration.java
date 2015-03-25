package edu.neu.EDE.data_structs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ian on 3/25/15.
 */
public class OutputConfiguration {
    private Map<DataType, List<String>> data;
    private DataType row;
    private DataType column;
    private DataType tab;

    /**
     * default contructor.  Initializes data map
     */
    public OutputConfiguration() {
        data = new HashMap<DataType, List<String>>();
    }

    /**
     * sets statistics
     * @param stats the statistics
     */
    public void setStatistics(List<String> stats) {
        this.data.put(DataType.STATISTIC, stats);
    }

    /**
     * sets stimuli
     * @param stimuli the stimuli
     */
    public void setStimuli(List<String> stimuli) {
        this.data.put(DataType.STIMULUS, stimuli);
    }

    /**
     * sets subjects
     * @param subjects the subjects
     */
    public void setSubjects(List<String> subjects) {
        this.data.put(DataType.SUBJECT, subjects);
    }

    /**
     * sets which data type is for rows
     * @param d the data type to be dispalyed per row
     */
    public void setRow(DataType d) { this.row = d; }

    /**
     * sets which data type is for columns
     * @param d the data type to be displayed per column
     */
    public void setColumn(DataType d) { this.column = d; }

    /**
     * sets which data type is for tabs
     * @param d the data type to be displayed per tab
     */
    public void setTab(DataType d) { this.tab = d; }

    /**
     * gets data associated with rows
     * @return the row headers
     */
    public List<String> getRows() {
        return data.get(row);
    }

    /**
     * gets data associated with columns
     * @return the column headers
     */
    public List<String> getColumns() {
        return data.get(column);
    }

    /**
     * gets data associated with tabs
     * @return the tab names
     */
    public List<String> getTabs() {
        return data.get(tab);
    }

}
