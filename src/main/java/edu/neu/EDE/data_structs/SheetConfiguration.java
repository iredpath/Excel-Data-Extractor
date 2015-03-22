package edu.neu.EDE.data_structs;

/**
 * Used for setting and getting values from the four dimensional array
 * @author Ian Redpath
 * @version 3/22/2015
 */
public class SheetConfiguration {

    private String statistic;
    private String media; // only used for setting
    private String stimulus;
    private String subject;
    private Double value; // only used for setting

    /**
     * Blank Constructor for SheetConfiguration
     */
    public SheetConfiguration() {}
    
    /**
     * Used to set the sheet configuration
     * @param type DataType Enum
     * @param value String that we are using
     */
    public void set(DataType type, String value) {
        if (type.equals(DataType.STATISTIC)) {
            this.statistic = value;
        } else if (type.equals(DataType.STIMULUS)) {
            this.stimulus = value;
        } else {
            this.subject = value;
        }
    }

    /**
     * Used for getting the statistic stored in the SheetConfiguration
     * @return String that is the statistic that is stored in the SheetConfiguration
     */
    public String getStatistic() {
        return this.statistic;
    }

    /**
     * Used for getting the stimulus stored in the SheetConfiguration
     * @return String that is the stimulus that is stored in the SheetConfiguration
     */
    public String getStimulus() {
        return this.stimulus;
    }

    /**
     * Used for getting the media stored in the SheetConfiguration
     * @return String that is the media that is stored in the SheetConfiguration
     */
    public String getMedia() {
        return this.media;
    }

    /**
     * Used for getting the subject stored in the SheetConfiguration
     * @return String that is the subject that is stored in the SheetConfiguration
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Used for getting the value stored in the SheetConfiguration
     * @return String that is the value that is stored in the SheetConfiguration
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * Used for setting the statistic stored in the SheetConfiguration
     * @param stat String that we are setting the statistic to in the SheetConfiguration
     */
    public void setStatistic(String stat) {
        this.statistic = stat;
    }
    
    /**
     * Used for setting the stimulus stored in the SheetConfiguration
     * @param stat String that we are setting the stimulus to in the SheetConfiguration
     */
    public void setStimulus(String stim) {
        this.stimulus = stim;
    }

    /**
     * Used for setting the media stored in the SheetConfiguration
     * @param stat String that we are setting the media to in the SheetConfiguration
     */
    public void setMedia(String med) {
        this.media = med;
    }

    /**
     * Used for setting the subject stored in the SheetConfiguration
     * @param stat String that we are setting the subject to in the SheetConfiguration
     */
    public void setSubject(String subj) {
        this.subject = subj;
    }

    /**
     * Used for setting the value stored in the SheetConfiguration
     * @param stat String that we are setting the value to in the SheetConfiguration
     */
    public void setValue(Double val) {
        this.value = val;
    }
}