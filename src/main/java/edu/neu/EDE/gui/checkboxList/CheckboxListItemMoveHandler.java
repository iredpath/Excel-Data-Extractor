package edu.neu.EDE.gui.checkboxList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Teddy on 3/26/15.
 */
public class CheckboxListItemMoveHandler extends MouseAdapter {

    private JList list;
    private int pressIndex = 0;
    private int releaseIndex = 0;

    public CheckboxListItemMoveHandler(JList list) {
        if (!(list.getModel() instanceof DefaultListModel)) {
            throw new IllegalArgumentException("List must have a DefaultListModel");
        }
        this.list = list;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        pressIndex = list.locationToIndex(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO: this is potentially bad.  Somebody please research downsides of requestFocus()
        list.getParent().requestFocus();
    }

    void checkForReorder(MouseEvent e) {
        releaseIndex = list.locationToIndex(e.getPoint());
        if (releaseIndex != pressIndex && releaseIndex != -1) {
            reorder();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //mouseReleased(e);
        checkForReorder(e);
        pressIndex = releaseIndex;
    }
    private void reorder() {
        DefaultListModel model = (DefaultListModel) list.getModel();
        Object dragee = model.elementAt(pressIndex);
        model.removeElementAt(pressIndex);
        model.insertElementAt(dragee, releaseIndex);
    }
}
