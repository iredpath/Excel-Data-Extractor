package edu.neu.EDE.gui.checkboxList;

/**
 * Drag-and-drop handler for CheckboxListItems
 */
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Teddy Stoddard
 * @version 3/26/2015
 */
public class CheckboxListItemMoveHandler extends MouseAdapter {

    private JList list;
    private int pressIndex = 0;
    private int releaseIndex = 0;

    /**
     * basic constructor
     * @param list the list of items to monitor
     */
    public CheckboxListItemMoveHandler(JList list) {
        if (!(list.getModel() instanceof DefaultListModel)) {
            throw new IllegalArgumentException("List must have a DefaultListModel");
        }
        this.list = list;
    }

    /**
     * handles mouse pressed events
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        pressIndex = list.locationToIndex(e.getPoint());
    }

    /**
     * handles mouse released events
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO: this is potentially bad.  Somebody please research downsides of requestFocus()
        list.getParent().requestFocus();
    }

    /**
     * checks to see if the list needs to be reordered
     * @param e the mouse event
     */
    void checkForReorder(MouseEvent e) {
        releaseIndex = list.locationToIndex(e.getPoint());
        if (releaseIndex != pressIndex && releaseIndex != -1) {
            reorder();
        }
    }

    /**
     * responds to mouse drag events
     * @param e the mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        checkForReorder(e);
        pressIndex = releaseIndex;
    }

    /**
     * reorders the list
     */
    private void reorder() {
        DefaultListModel model = (DefaultListModel) list.getModel();
        Object dragee = model.elementAt(pressIndex);
        model.removeElementAt(pressIndex);
        model.insertElementAt(dragee, releaseIndex);
    }
}
