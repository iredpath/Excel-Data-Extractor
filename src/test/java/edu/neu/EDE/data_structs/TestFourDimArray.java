package edu.neu.EDE.data_structs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ian on 3/31/15.
 */
public class TestFourDimArray extends Assert {

    @Test
    public void testReset() {
        FourDimArray data = new FourDimArray();
        SheetConfiguration config = new SheetConfiguration();
        config.setMedia("med");
        config.setSubject("subj");
        config.setStatistic("stat");
        config.setValue(1.0);
        config.setStimulus("stim");
        data.set(config);
        data.reset();
        assertEquals(data.getSubjects().size(), 0);
        assertEquals(data.getMedias().size(), 0);
        assertEquals(data.getStimuli().size(), 0);
        assertEquals(data.getStatistics().size(), 0);
    }

    @Test
    public void testGetStimuli() {
        FourDimArray data = new FourDimArray();
        SheetConfiguration config = new SheetConfiguration();
        config.setMedia("med");
        config.setSubject("subj");
        config.setStatistic("stat");
        config.setValue(1.0);
        config.setStimulus("stim");
        data.set(config);
        config.setStimulus("other stim");
        data.set(config);
        assertEquals(data.getStimuli().size(), 2);
        assertEquals(data.getStimuli().get(0), "stim");
        assertEquals(data.getStimuli().get(1), "other stim");
    }

    @Test
    public void testSetBlankSheet() {
        FourDimArray data = new FourDimArray();
        data.setBlankSheet("foo", "bar");
        assertEquals(data.getSubjects().size(), 1);
        assertEquals(data.getMedias().size(), 1);
        assertEquals(data.getStimuli().size(), 0);
        assertEquals(data.getStatistics().size(), 0);
    }

    @Test
    public void testSet() {
        FourDimArray data = new FourDimArray();
        SheetConfiguration config = new SheetConfiguration();
        config.setMedia("med");
        config.setSubject("subj");
        config.setStatistic("stat");
        config.setValue(1.0);
        config.setStimulus("stim");
        data.set(config);
        assertEquals(data.getStatistics().get(0), "stat");
        assertEquals(data.getStimuli().get(0), "stim");
        assertEquals(data.getSubjects().get(0), "subj");
        assertEquals(data.getMedias().get(0), "med");
    }

    @Test
    public void testGet() {
        FourDimArray data = new FourDimArray();
        SheetConfiguration config = new SheetConfiguration();
        config.setMedia("med");
        config.setSubject("subj");
        config.setStatistic("stat");
        config.setValue(1.0);
        config.setStimulus("stim");
        data.set(config);
        config = new SheetConfiguration();
        config.set(DataType.STIMULUS, "stim");
        config.set(DataType.STATISTIC, "stat");
        config.set(DataType.SUBJECT, "subj");
        Double val = data.get(config);
        assertTrue(val == 1.0);
    }
}
