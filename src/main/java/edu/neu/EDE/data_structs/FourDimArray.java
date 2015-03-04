package edu.neu.EDE.data_structs;

/**
 * Original data structure idea/code created by Teddy Stoddard
 */
import java.util.ArrayList;

public class FourDimArray {
    private ArrayList<String> subjects = new ArrayList<String>();
    private ArrayList<String> medias = new ArrayList<String>();
    private ArrayList<ArrayList<String>> stimuli = new ArrayList<ArrayList<String>>();
    private ArrayList<String> statistics = new ArrayList<String>();
    private ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> data = new ArrayList<ArrayList<ArrayList<ArrayList<Double>>>>();

    public ArrayList<String> getSubjects() { // returns an arraylist of subjects
        return subjects;
    }
    
    public ArrayList<String> getMedias() { // returns an arraylist of medias
        return medias;
    }
    
    public ArrayList<String> getStimuli() { // returns an arraylist of stimuli
        ArrayList<String> tempStimuliArray = new ArrayList<String>();
        for (ArrayList<String> stimuliArray: stimuli) { // 2d to 1d
            tempStimuliArray.addAll(stimuliArray);
        }
        return tempStimuliArray;
    }
    
    public ArrayList<String> getStatistics() { // returns an arraylist of statistics
        return statistics;
    }
    
    public void setBlankSheet(String subject, String media) { // for use with blank or missing sheets
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
            data.add(new ArrayList<ArrayList<ArrayList<Double>>>());
        }
        while (data.get(subjectIndex).size() <= mediaIndex) {
            data.get(subjectIndex).add(new ArrayList<ArrayList<Double>>());
        }
    }

    public void set(SheetConfiguration config) { // for use with setting values or null(blank)
        String subject = config.getSubject();
        String media = config.getMedia();
        String stimulus = config.getSubject();
        String statistic = config.getSubject();
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
        while (data.size() <= subjectIndex) {
            data.add(new ArrayList<ArrayList<ArrayList<Double>>>());
        }
        while (data.get(subjectIndex).size() <= mediaIndex) {
            data.get(subjectIndex).add(new ArrayList<ArrayList<Double>>());
        }
        while (data.get(subjectIndex).get(mediaIndex).size() <= stimuliIndex) {
            data.get(subjectIndex).get(mediaIndex).add(new ArrayList<Double>());
        }
        while (data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).size() <= statisticIndex) {
            data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).add(null);
        }
        data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).set(statisticIndex, value);
    }
    
    public Double get(SheetConfiguration config) { // for use getting values
        String subject = config.getSubject();
        String stimulus = config.getStimulus();
        String statistic = config.getStatistic();
        int subjectIndex = 0;
        int mediaIndex = 0;
        int stimuliIndex = 0;
        int statisticIndex = 0;
        subjectIndex = subjects.indexOf(subject);
        if ((subjectIndex == -1) || (data.size() <= subjectIndex)) {
            return null;
        }
        boolean found = false;
        for (ArrayList<String> media: stimuli) {
            for (String stim: media) {
                if (stim == stimulus) {
                    found = true;
                    mediaIndex = stimuli.indexOf(media);
                    stimuliIndex = media.indexOf(stimulus);
                }
            }
        }
        if (!found) {
            return null;
        }
        statisticIndex = statistics.indexOf(statistic);
        if ((statisticIndex == -1) || (data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).size() <= statisticIndex)) {
            return null;
        }
        return data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).get(statisticIndex);
    }
}