package edu.neu.EDE.data_structs;

/**
 * Created by Ian on 3/25/15.
 */
public enum DataGroupType {
    LOOKZONE, SLIDEMETRIC;

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
