package edu.neu.EDE.data_structs;

/**
 * Original data structure idea/code created by Teddy Stoddard
 */

import java.util.ArrayList;
import java.util.List;

public class ThreeDimArray {

    private List<String> subjects;
    private List<String> stimuli;
    private List<String> statistics;
    private List<List<List<Double>>> data;

    public ThreeDimArray() {
        subjects = new ArrayList<String>();
        stimuli = new ArrayList<String>();
        statistics = new ArrayList<String>();
        data = new ArrayList<List<List<Double>>>();
    }

    public void set(SheetConfiguration config) {
        String subject = config.getSubject();
        String stimulus = config.getStimulus();
        String statistic = config.getStatistic();
        Double value = config.getValue();

        int subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            subjects.add(subject);
            subjectIndex = subjects.size() - 1;
        }

        int stimulusIndex = stimuli.indexOf(stimulus);
        if (stimulusIndex == -1) {
            stimuli.add(stimulus);
            stimulusIndex = stimuli.size() - 1;
        }

        int statisticIndex = statistics.indexOf(statistic);
        if (statisticIndex == -1) {
            statistics.add(statistic);
            statisticIndex = statistics.size() - 1;
        }

        while (data.size() <= subjectIndex) {
            data.add(new ArrayList<List<Double>>());
        }
        List<List<Double>> stimuliForSubject = data.get(subjectIndex);
        while (stimuliForSubject.size() <= stimulusIndex) {
            data.get(subjectIndex).add(new ArrayList<Double>());
        }
        List<Double> statisticsForStimulus = stimuliForSubject.get(stimulusIndex);
        while (statisticsForStimulus.size() <= statisticIndex) {
            data.get(subjectIndex).get(stimulusIndex).add(null);
        }
        statisticsForStimulus.set(statisticIndex, value);
    }

    public Double get(SheetConfiguration config) {
        String subject = config.getSubject();
        int subjectIndex = subjects.indexOf(subject);
        if ((subjectIndex == -1 || data.size() <= subjectIndex)) {
            return null;
        }
        List<List<Double>> stimuliForSubject = data.get(subjectIndex);
        String stimulus = config.getStimulus();
        int stimulusIndex = stimuliForSubject.indexOf(stimulus);
        if (stimulusIndex == -1 || stimuliForSubject.size() <= stimulusIndex) {
            return null;
        }
        List<Double> statisticsForStimuli = stimuliForSubject.get(stimulusIndex);
        String statistic = config.getStatistic();
        int statisticIndex = statisticsForStimuli.indexOf(statistic);
        if (statisticIndex == -1 || statisticsForStimuli.size() <= statisticIndex) {
            return null;
        }
        return statisticsForStimuli.get(statisticIndex);
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public List<String> getStimuli() {
        return stimuli;
    }

    public List<String> getStatistics() {
        return statistics;
    }

}
