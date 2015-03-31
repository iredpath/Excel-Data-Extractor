package edu.neu.EDE.data_structs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ian on 3/29/15.
 */
public class TestDataGroupType extends Assert {

    @Test
    public void testToString() {
        assertEquals(DataGroupType.LOOKZONE.toString(), "Lookzone");
        assertEquals(DataGroupType.SLIDEMETRIC.toString(), "Slide Metric");
    }
}
