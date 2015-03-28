package edu.neu.EDE.data_structs;

import edu.neu.EDE.gui.checkboxList.CheckboxListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ian on 3/28/15.
 */
public class DataTypeModelManager {

    private Map<DataGroupType, Map<DataType, CheckboxListModel>> map;

    public DataTypeModelManager() {
        this.map = new HashMap<DataGroupType, Map<DataType, CheckboxListModel>>();
        map.put(DataGroupType.LOOKZONE, new HashMap<DataType, CheckboxListModel>());
        map.put(DataGroupType.SLIDEMETRIC, new HashMap<DataType, CheckboxListModel>());

        put(DataGroupType.LOOKZONE, DataType.STATISTIC, new CheckboxListModel());
        put(DataGroupType.LOOKZONE, DataType.STIMULUS, new CheckboxListModel());
        put(DataGroupType.LOOKZONE, DataType.SUBJECT, new CheckboxListModel());
        put(DataGroupType.SLIDEMETRIC, DataType.STATISTIC, new CheckboxListModel());
        put(DataGroupType.SLIDEMETRIC, DataType.STIMULUS, new CheckboxListModel());
        put(DataGroupType.SLIDEMETRIC, DataType.SUBJECT, new CheckboxListModel());
    }

    public void put(DataGroupType dataGroupType, DataType dataType, CheckboxListModel model) {
        map.get(dataGroupType).put(dataType, model);
    }

    public CheckboxListModel get(DataGroupType dataGroupType, DataType dataType) {
        return map.get(dataGroupType).get(dataType);
    }

    public List<CheckboxListModel> getAllModels() {
        ArrayList<CheckboxListModel> retVal = new ArrayList<CheckboxListModel>(6);
        retVal.addAll(map.get(DataGroupType.SLIDEMETRIC).values());
        retVal.addAll(map.get(DataGroupType.LOOKZONE).values());
        return retVal;
    }
}
