package edu.neu.EDE.data_structs;

/**
 * @author Ian Redpath
 * @version 3/22/2015
 */
public enum DataType {
    STATISTIC, STIMULUS, SUBJECT;

    @Override
    public String toString() {
        switch(this) {
            case STATISTIC:
                return "Statistic";
            case STIMULUS:
                return "Stimulus";
            case SUBJECT:
                return "Subject";
            default:
                throw new IllegalArgumentException();
        }
    }
}
