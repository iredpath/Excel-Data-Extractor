package edu.neu.EDE.data_structs;

/**
 * ENUM for the three possible data types -
 * statistic, subject, or stimulus
 */

/**
 * @author Ian Redpath
 * @version 3/22/2015
 */
public enum DataType {
    STATISTIC, STIMULUS, SUBJECT;

    /**
     * get the user friendly version of the data type
     * @return user friendly string version of the data type
     */
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
