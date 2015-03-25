package edu.neu.EDE.data_structs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ian on 3/25/15.
 */
public class OutputConfiguration {
    private Map<DataType, List<String>> data;
    private List<String> statistics;
    private List<String> subjects;
    private List<String> stimuli;
    private DataType row;
    private DataType column;
    private DataType tab;

    public OutputConfiguration() {
        data = new HashMap<DataType, List<String>>();
    }

    public void setStatistics(List<String> stats) {
        this.data.put(DataType.STATISTIC, stats);
    }

    public void setStimuli(List<String> stimuli) {
        this.data.put(DataType.STIMULUS, stimuli);
    }

    public void setSubjects(List<String> subjects) {
        this.data.put(DataType.SUBJECT, subjects);
    }

    public void setRow(DataType d) { this.row = d; }

    public void setColumn(DataType d) { this.column = d; }

    public void setTab(DataType d) { this.tab = d; }

    public List<String> getRows() {
        return data.get(row);
    }

    public List<String> getColumns() {
        return data.get(column);
    }

    public List<String> getTabs() {
        return data.get(tab);
    }

}
