package edu.neu.EDE.gui.checkboxList;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Color;
import java.awt.Component;

/**
 * Created by Teddy on 3/25/15.
 */
public class CheckboxListRenderer extends JCheckBox implements ListCellRenderer {

    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean hasFocus) {

        if (hasFocus) {
            setBackground(Color.CYAN);
        } else {
            setBackground(list.getBackground());
        }
        setEnabled(list.isEnabled());
        setSelected(((CheckboxListItem)value).isSelected());
        setFont(list.getFont());
        setForeground(list.getForeground());
        setText(value.toString());
        return this;
    }
}
