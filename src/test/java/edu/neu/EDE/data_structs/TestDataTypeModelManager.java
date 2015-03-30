package edu.neu.EDE.data_structs;

import edu.neu.EDE.gui.checkbox_list.CheckboxListModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Ian on 3/29/15.
 */
public class TestDataTypeModelManager extends Assert {

    @Test
    public void testGetAllModels() {
        DataTypeModelManager manager = new DataTypeModelManager();
        List<CheckboxListModel> models = manager.getAllModels();
        assertEquals(models.size(), 6);
    }
}
