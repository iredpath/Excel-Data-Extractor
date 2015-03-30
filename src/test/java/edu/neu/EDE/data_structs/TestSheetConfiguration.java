package edu.neu.EDE.data_structs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ian on 3/29/15.
 */
public class TestSheetConfiguration extends Assert {

    @Test
    public void testSet() {
        SheetConfiguration config = new SheetConfiguration();
        config.set(DataType.STIMULUS, "stim");
        config.set(DataType.SUBJECT, "subj");
        config.set(DataType.STATISTIC, "stat");
        assertEquals(config.getSubject(), "subj");
        assertEquals(config.getStatistic(), "stat");
        assertEquals(config.getStimulus(), "stim");
    }
}
