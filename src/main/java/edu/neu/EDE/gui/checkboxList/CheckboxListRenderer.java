package edu.neu.EDE.gui.checkboxList;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;

/**
 * Created by Ian on 3/25/15.
 */
public class CheckboxListRenderer extends JCheckBox implements ListCellRenderer {

    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean hasFocus) {

        setEnabled(list.isEnabled());
        setSelected(((CheckboxListItem)value).isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(value.toString());
        return this;
    }
}
