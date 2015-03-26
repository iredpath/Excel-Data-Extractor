package edu.neu.EDE.gui.checkboxList;

import javax.swing.DefaultListModel;

/**
 * Created by Teddy on 3/25/15.
 */
public class CheckboxListModel extends DefaultListModel<CheckboxListItem> {

    public boolean contains(String s) {
        for (int i = 0; i < getSize(); i++) {
            CheckboxListItem item = get(i);
            if (item.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
