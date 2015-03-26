package edu.neu.EDE.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.BorderLayout;
import java.awt.Component;

/**
 * Created by Ian on 3/25/15.
 */
public class ButtonListRenderer extends JButton implements ListCellRenderer {

    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean hasFocus) {

        JPanel p = new JPanel();
        setSelected(hasFocus);
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setLayout(new BorderLayout(0, 0));
        setText("X");
        p.add(this, BorderLayout.WEST);
        p.add(new JLabel(value.toString()));
        return p;
    }
}
