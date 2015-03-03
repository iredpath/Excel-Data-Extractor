package edu.neu.EDE.data_structs;

public class SheetConfiguration {

    private String statistic;
    private String stimulus;
    private String subject;
    private Double value; // only used for setting

    public SheetConfiguration() {}

    /* this set will only be used by the WorkbookWriter code.
        when we don't know which field we are setting */
    public void set(DataType type, String value) {
        if (type.equals(DataType.STATISTIC)) {
            this.statistic = value;
        } else if (type.equals(DataType.STIMULUS)) {
            this.stimulus = value;
        } else {
            this.subject = value;
        }
    }

    public String getStatistic() { return this.statistic; }

    public String getStimulus() { return this.stimulus; }

    public String getSubject() { return this.subject; }

    public Double getValue() { return this.value; }

    public void setStatistic(String stat) { this.statistic = stat; }

    public void setStimulus(String stim) { this.stimulus = stim; }

    public void setSubject(String subj) { this.subject = subj; }

    public void setValue(Double d) { this.value = d; }

}
