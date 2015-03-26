package edu.neu.EDE.gui.checkboxList;

import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Ian on 3/26/15.
 */
public class CheckboxListItemClickHandler extends MouseAdapter{
    public void mouseClicked(MouseEvent event) {
        JList list = (JList) event.getSource();
        // Get index of item clicked
        int index = list.locationToIndex(event.getPoint());
        CheckboxListItem item = (CheckboxListItem) list.getModel().getElementAt(index);
        // Toggle selected state
        item.setSelected(!item.isSelected());
        // Repaint cell
        list.repaint(list.getCellBounds(index, index));
    }
}
