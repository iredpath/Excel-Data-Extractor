package edu.neu.EDE.gui.checkbox_list;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ian on 3/29/15.
 */
public class TestCheckboxListItem extends Assert {

    @Test
    public void testToString() throws IOException {
        CheckboxListItem item = new CheckboxListItem("foo");
        item.setFile(File.createTempFile("test", null));
        item.setSubject("subject");
        item.setSelected(true);
        assertEquals(item.toString(), "foo");
        item.setSelected(false);
        assertEquals(item.toString(), "foo");
    }
}
