package edu.neu.EDE.gui;

import java.awt.Component;

/**
 * Created by Ian on 3/25/15.
 */
public class CheckboxListItem extends Component {

    private String  label;
    private boolean isSelected = false;

    public CheckboxListItem(String label) {
        this.label = label;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String toString() {
        return label;
    }
}
