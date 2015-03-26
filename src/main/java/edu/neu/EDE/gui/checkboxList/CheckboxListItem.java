package edu.neu.EDE.gui.checkboxList;

import java.awt.Component;

/**
 * Created by Teddy on 3/25/15.
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
