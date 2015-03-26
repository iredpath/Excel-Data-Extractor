package edu.neu.EDE.gui;

import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Ian on 3/26/15.
 */
public class ButtonListItemClickHandler extends MouseAdapter {

    private GuiView parent;

    public ButtonListItemClickHandler(GuiView v) {
        this.parent = v;
    }

    public void mouseClicked(MouseEvent event) {
        JList list = (JList) event.getSource();
        // Get index of item clicked
        int index = list.locationToIndex(event.getPoint());
        ButtonListItem item = (ButtonListItem) list.getModel().getElementAt(index);

        parent.remove(item);
    }
}
