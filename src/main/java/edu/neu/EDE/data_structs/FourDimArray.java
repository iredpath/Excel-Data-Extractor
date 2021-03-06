package edu.neu.EDE.data_structs;

/**
 * Original data structure idea/code created by Teddy Stoddard
 * Used to store all the data from different spreadsheets
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Teddy Stoddard
 * @version 3/22/2015
 */
public class FourDimArray {
    
    private ArrayList<String> subjects;
    private ArrayList<String> medias;
    private ArrayList<List<String>> stimuli;
    private ArrayList<String> statistics;
    private ArrayList<List<List<List<Double>>>> data;

    /**
     * Constructor for the FourDimArray
     * Initializes Subjects, Medias, Stimuli, Statistics, and Data
     */
    public FourDimArray() {
        subjects = new ArrayList<String>();
        medias = new ArrayList<String>();
        stimuli = new ArrayList<List<String>>();
        statistics = new ArrayList<String>();
        data = new ArrayList<List<List<List<Double>>>>();
    }
    
    /**
     * Used clear the data structure
     */
    public void reset() {
        subjects.clear();
        medias.clear();
        stimuli.clear();
        statistics.clear();
        data.clear();
    }
    
    /**
     * Used to get the list of subjects
     * @return an ArrayList of subjects (the order they were added)
     */
    public ArrayList<String> getSubjects() {
        return subjects;
    }
    
    /**
     * Used to get the list of media
     * @return an ArrayList of medias (the order they were added)
     */
    public ArrayList<String> getMedias() {
        return medias;
    }
    
    /**
     * Used to get the list of stimuli
     * @return an ArrayList of stimuli (the order they were added)
     */
    public ArrayList<String> getStimuli() {
        ArrayList<String> tempStimuliArray = new ArrayList<String>();
        for (List<String> stimuliArray: stimuli) { // 2d to 1d
            tempStimuliArray.addAll(stimuliArray);
        }
        return tempStimuliArray;
    }

    /**
     * Used to get the list of statistics
     * @return an ArrayList of statistics (the order they were added)
     */
    public ArrayList<String> getStatistics() {
        return statistics;
    }

    /**
     * Used when a sheet is blank to leave a placeholder so later sheets are in the correct order
     * @param subject String that is the name of the subject
     * @param media String that is the name of the media source
     */
    public void setBlankSheet(String subject, String media) {
        int subjectIndex, mediaIndex;
        subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            subjects.add(subject);
            subjectIndex = subjects.size() - 1;
        }
        mediaIndex = medias.indexOf(media);
        if (mediaIndex == -1) {
            medias.add(media);
            stimuli.add(new ArrayList<String>());
            mediaIndex = medias.size() - 1;
        }
        while (data.size() <= subjectIndex) {
            data.add(new ArrayList<List<List<Double>>>());
        }
        while (data.get(subjectIndex).size() <= mediaIndex) {
            data.get(subjectIndex).add(new ArrayList<List<Double>>());
        }
    }

    /**
     * Used to store data in the four dimensional array
     * @param config SheetConfiguration that has subject,  media, stimulus, statistic, and value
     */
    public void set(SheetConfiguration config) { // for use with setting values or null(blank)
        String subject = config.getSubject();
        String media = config.getMedia();
        String stimulus = config.getStimulus();
        String statistic = config.getStatistic();
        Double value = config.getValue();
        int subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            subjects.add(subject);
            subjectIndex = subjects.size() - 1;
        }
        int mediaIndex = medias.indexOf(media);
        if (mediaIndex == -1) {
            medias.add(media);
            stimuli.add(new ArrayList<String>());
            mediaIndex = medias.size() - 1;
        }
        int stimuliIndex = stimuli.get(mediaIndex).indexOf(stimulus);
        if (stimuliIndex == -1) {
            stimuli.get(mediaIndex).add(stimulus);
            stimuliIndex = stimuli.get(mediaIndex).size() - 1;
        }
        int statisticIndex = statistics.indexOf(statistic);
        if (statisticIndex == -1) {
            statistics.add(statistic);
            statisticIndex = statistics.size() - 1;
        }
        while (data.size() <= subjectIndex) { // if there are blanks that are not stored in the data structure append as necessary to align indices
            data.add(new ArrayList<List<List<Double>>>());
        }
        while (data.get(subjectIndex).size() <= mediaIndex) {
            data.get(subjectIndex).add(new ArrayList<List<Double>>());
        }
        while (data.get(subjectIndex).get(mediaIndex).size() <= stimuliIndex) {
            data.get(subjectIndex).get(mediaIndex).add(new ArrayList<Double>());
        }
        while (data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).size() <= statisticIndex) {
            data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).add(null); // null later translates to a blank cell
        }
        data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).set(statisticIndex, value);
    }

    /**
     * Used for getting a value from the four dimensional array. 
     * Requires subject stimuli and statistic to be defined in the sheet configuration
     * @param config SheetConfiguration contains the subject stimuli and statistic in the form of a string
     * @return Double if data is stored and the three parameters are found. Otherwise returns null
     */
    public Double get(SheetConfiguration config) {
        String subject = config.getSubject();
        String stimulus = config.getStimulus();
        String statistic = config.getStatistic();
        int mediaIndex = -1;
        int stimuliIndex = -1;
        int subjectIndex = subjects.indexOf(subject);
        if ((subjectIndex == -1) || (data.size() <= subjectIndex)) {
            return null; // if out of bounds or not found
        }
        boolean found = false;
        for (List<String> listOfStim: stimuli) { // get the index of the media and stimuli
            for (String stim: listOfStim) {
                if (stim.equals(stimulus)) {
                    found = true;
                    mediaIndex = stimuli.indexOf(listOfStim);
                    stimuliIndex = listOfStim.indexOf(stimulus);
                    break;
                }
            }
        }
        if (!found || (data.get(subjectIndex).size() <= mediaIndex) || (data.get(subjectIndex).get(mediaIndex).size() <= stimuliIndex)) {
            return null; // if out of bounds or not found
        }
        int statisticIndex = statistics.indexOf(statistic);
        if ((statisticIndex == -1) || (data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).size() <= statisticIndex)) {
            return null; // if out of bounds or not found
        }
        return data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).get(statisticIndex); // get and return the data
    }

    /**
     * gets the list of names for the given data type
     * @param type the data type
     * @return the list of names for that data type
     */
    public List<String> get(DataType type) {
        if (type.equals(DataType.STATISTIC)) {
            return getStatistics();
        } else if (type.equals(DataType.STIMULUS)) {
            return getStimuli();
        } else if (type.equals(DataType.SUBJECT)) {
            return getSubjects();
        } else {
            throw new IllegalArgumentException("Cannot resolve Data type " + type);
        }
    }
}