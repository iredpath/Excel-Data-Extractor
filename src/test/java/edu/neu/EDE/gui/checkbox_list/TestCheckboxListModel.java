package edu.neu.EDE.gui.checkbox_list;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ian on 3/29/15.
 */
public class TestCheckboxListModel extends Assert {

    @Test
    public void testContains() {
        CheckboxListModel model = new CheckboxListModel();
        model.addElement(new CheckboxListItem("foo"));
        model.addElement(new CheckboxListItem("bar"));
        model.addElement(new CheckboxListItem("buzz"));
        assertTrue(model.contains("foo"));
        assertTrue(model.contains("bar"));
        assertTrue(model.contains("buzz"));
        assertFalse(model.contains("fo"));
        assertFalse(model.contains("test"));
    }
}
