package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataGroupType;

import javax.swing.JRadioButton;

/**
 * Created by Ian on 3/26/15.
 */
public class DataGroupButton extends JRadioButton {
    private DataGroupType dataGroupType;

    DataGroupButton(String text, DataGroupType type) {
        this.setText(text);
        this.dataGroupType = type;
    }

    public DataGroupType getDataGroupType() { return dataGroupType; }
}
