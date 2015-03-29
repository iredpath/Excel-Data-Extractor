package edu.neu.EDE.gui.checkboxList;

/**
 * Custom renderer for CheckboxListItems
 */

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Color;
import java.awt.Component;

/**
 * @author Teddy Stoddard
 * @version 3/25/2015
 */
public class CheckboxListRenderer extends JCheckBox implements ListCellRenderer {

    /**
     * gets the component to render
     * @param list the containing list
     * @param value the component
     * @param index the components index in the list
     * @param isSelected is the component selected
     * @param hasFocus does the component have focus
     * @return the component to render
     */
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean hasFocus) {

        if (hasFocus) {
            setBackground(Color.LIGHT_GRAY);
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
