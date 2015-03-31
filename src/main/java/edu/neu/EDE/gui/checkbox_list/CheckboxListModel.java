package edu.neu.EDE.gui.checkbox_list;

/**
 * a model to back lists of checkboxes
 */

import javax.swing.DefaultListModel;

/**
 * @author Teddy Stoddard
 * @version 3/25/2015
 */
public class CheckboxListModel extends DefaultListModel<CheckboxListItem> {

    /**
     * does this model contain the provided string
     * @param s the string to check for
     * @return the result of the check
     */
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
