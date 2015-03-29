package edu.neu.EDE.gui.checkboxList;

/**
 * a custom checkbox component
 */

import java.awt.Component;
import java.io.File;

/**
 * @author Teddy Stoddard
 * @version 3/25/2015
 */
public class CheckboxListItem extends Component {

    private String  label;
    private boolean isSelected = false;
    private String subject;
    private File file;

    /**
     * basic constructor
     * @param label the label for the checkbox
     */
    public CheckboxListItem(String label) {
        this.label = label;
    }

    /**
     * is this box selected
     * @return whether or not the box is selected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * set the state of the box
     * @param isSelected the state to set the box
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * gets the string representation of the box
     * @return the label of the box
     */
    public String toString() {
        return label;
    }

    /**
     * set the subject related to this box
     * NOTE: used exclusively for list of added files
     * @param s the subject
     */
    public void setSubject(String s) { subject = s; }

    /**
     * get the subject associated with this box
     * @return the associated subject
     */
    public String getSubject() { return subject; }

    /**
     * set the file related to this box
     * NOTE: used exclusively for list of added files
     * @param f the associated file
     */
    public void setFile(File f) { file = f; }

    /**
     * get the file associated with this box
     * @return the associated file
     */
    public File getFile() { return file; }
}
