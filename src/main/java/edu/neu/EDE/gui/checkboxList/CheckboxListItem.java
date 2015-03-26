package edu.neu.EDE.gui.checkboxList;

import java.awt.Component;
import java.io.File;

/**
 * Created by Teddy on 3/25/15.
 */
public class CheckboxListItem extends Component {

    private String  label;
    private boolean isSelected = false;
    private String subject;
    private File file;

    public CheckboxListItem(String label) {
        this.label = label;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String toString() {
        return label;
    }

    // exclusively used for files
    public void setSubject(String s) { subject = s; }

    public String getSubject() { return subject; }

    public void setFile(File f) { file = f; }

    public File getFile() { return file; }
}
