package edu.neu.EDE.gui.checkbox_list;

/**
 * click handler for CheckboxListItems
 */
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Teddy Stoddard
 * @version 3/25/2015
 */
public class CheckboxListItemClickHandler extends MouseAdapter {

    /**
     * responds to click events
     * @param event the click event
     */
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
