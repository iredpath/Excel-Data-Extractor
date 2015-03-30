package edu.neu.EDE.data_structs;

/**
 * data structure to manage CheckboxListModels
 */

import edu.neu.EDE.gui.checkbox_list.CheckboxListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ian Redpath
 * @version 3/28/2015
 */
public class DataTypeModelManager {

    private Map<DataGroupType, Map<DataType, CheckboxListModel>> map;

    /**
     * basic constructor.  initializes all models
     */
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

    /**
     * add the given model to the structure
     * @param dataGroupType the data group type of the model
     * @param dataType the data type of the model
     * @param model the model
     */
    public void put(DataGroupType dataGroupType, DataType dataType, CheckboxListModel model) {
        map.get(dataGroupType).put(dataType, model);
    }

    /**
     * get the specified model
     * @param dataGroupType the data group type of the desired model
     * @param dataType the data type of the desired model
     * @return the model
     */
    public CheckboxListModel get(DataGroupType dataGroupType, DataType dataType) {
        return map.get(dataGroupType).get(dataType);
    }

    /**
     * get all models in the structure
     * @return a list of all models
     */
    public List<CheckboxListModel> getAllModels() {
        ArrayList<CheckboxListModel> retVal = new ArrayList<CheckboxListModel>(6);
        retVal.addAll(map.get(DataGroupType.SLIDEMETRIC).values());
        retVal.addAll(map.get(DataGroupType.LOOKZONE).values());
        return retVal;
    }
}
