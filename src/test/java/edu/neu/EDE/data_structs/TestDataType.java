package edu.neu.EDE.data_structs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ian on 3/29/15.
 */
public class TestDataType extends Assert {

    @Test
    public void testToString() {
        assertEquals(DataType.STATISTIC.toString(), "Statistic");
        assertEquals(DataType.STIMULUS.toString(), "Stimulus");
        assertEquals(DataType.SUBJECT.toString(), "Subject");
    }
}
