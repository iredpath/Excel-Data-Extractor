package edu.neu.EDE.data_structs;

/**
 * ENUM for the two possible data group types - lookzone and slide metric
 */

/**
 * @author Ian Redpath
 * @version 3/25/2015
 */
public enum DataGroupType {
    LOOKZONE, SLIDEMETRIC;

    /**
     * get the user friendly version of the data group type
     * @return user friendly string version of the data group type
     */
    @Override
    public String toString() {
        switch(this) {
            case LOOKZONE:
                return "Lookzone";
            case SLIDEMETRIC:
                return "Slide Metric";
            default:
                throw new IllegalArgumentException();
        }
    }
}
