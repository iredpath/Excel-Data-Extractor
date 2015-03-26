package edu.neu.EDE.gui;

import java.awt.Component;

/**
 * Created by Ian on 3/25/15.
 */
public class ButtonListItem extends Component {

    private String filename;
    private String subject;

    public ButtonListItem(String filename, String subject) {
        this.filename = filename;
        this.subject = subject;
    }

    public String toString() {
        return filename;
    }

    public String getFilename() { return filename; }

    public String getSubject() { return subject; }
}
