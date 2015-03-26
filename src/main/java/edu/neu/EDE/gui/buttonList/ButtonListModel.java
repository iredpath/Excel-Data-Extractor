package edu.neu.EDE.gui.buttonList;

import javax.swing.DefaultListModel;

/**
 * Created by Ian on 3/26/15.
 */
public class ButtonListModel extends DefaultListModel<ButtonListItem> {

    public boolean contains(String s) {
        for (int i = 0; i < getSize(); i++) {
            ButtonListItem item = get(i);
            if (item.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void remove(String s) {
        for (int i = 0; i < getSize(); i++) {
            ButtonListItem item = get(i);
            if (item.toString().equals(s)) {
                remove(i);
            }
        }
    }
}
