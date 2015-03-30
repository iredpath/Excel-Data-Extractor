package edu.neu.EDE.data_structs;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Ian on 3/29/15.
 */
public class TestOutputConfiguration extends Assert {

    @Test
    public void testGetRows() {
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(Lists.newArrayList("stat1", "stat2", "stat3"));
        config.setStimuli(Lists.newArrayList("stim1", "stim2", "stim3"));
        config.setSubjects(Lists.newArrayList("subj1", "subj2", "subj3"));
        config.setRow(DataType.STATISTIC);
        List<String> rows = config.getRows();
        assertEquals(rows.size(), 3);
        assertEquals(rows.get(0), "stat1");
        assertEquals(rows.get(1), "stat2");
        assertEquals(rows.get(2), "stat3");
    }

    @Test
    public void testGetColumns() {
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(Lists.newArrayList("stat1", "stat2", "stat3"));
        config.setStimuli(Lists.newArrayList("stim1", "stim2", "stim3"));
        config.setSubjects(Lists.newArrayList("subj1", "subj2", "subj3"));
        config.setColumn(DataType.STIMULUS);
        List<String> cols = config.getColumns();
        assertEquals(cols.size(), 3);
        assertEquals(cols.get(0), "stim1");
        assertEquals(cols.get(1), "stim2");
        assertEquals(cols.get(2), "stim3");
    }

    @Test
    public void testGetTabs() {
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(Lists.newArrayList("stat1", "stat2", "stat3"));
        config.setStimuli(Lists.newArrayList("stim1", "stim2", "stim3"));
        config.setSubjects(Lists.newArrayList("subj1", "subj2", "subj3"));
        config.setTab(DataType.SUBJECT);
        List<String> tabs = config.getTabs();
        assertEquals(tabs.size(), 3);
        assertEquals(tabs.get(0), "subj1");
        assertEquals(tabs.get(1), "subj2");
        assertEquals(tabs.get(2), "subj3");
    }
}
