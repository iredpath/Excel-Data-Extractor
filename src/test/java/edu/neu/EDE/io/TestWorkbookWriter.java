package edu.neu.EDE.io;

import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.data_structs.FourDimArray;
import edu.neu.EDE.data_structs.OutputConfiguration;
import edu.neu.EDE.data_structs.SheetConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ian on 3/30/15.
 */
public class TestWorkbookWriter extends Assert {

    @Test
    public void testWrite() throws IOException {
        WorkbookWriter writer = new WorkbookWriter();
        File f = File.createTempFile("test-out", null);
        OutputConfiguration config = new OutputConfiguration();
        config.setStatistics(new ArrayList<String>());
        config.setStimuli(new ArrayList<String>());
        config.setSubjects(new ArrayList<String>());
        config.setTab(DataType.STATISTIC);
        config.setColumn(DataType.STIMULUS);
        config.setRow(DataType.SUBJECT);
        writer.write(config, f);
        // assure .xlsx added
        File created = new File(f + ".xlsx");
        assertTrue(created.exists());

        writer.write(config, created);
        File notCreated = new File(created + ".xlsx");
        assertFalse(notCreated.exists());
    }

    @Test
    public void testGetValue() {
        WorkbookWriter writer = new WorkbookWriter();
        FourDimArray data = Mockito.spy(new FourDimArray());
        Mockito.doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                SheetConfiguration config = (SheetConfiguration) args[0];
                assertEquals(config.getStimulus(), "stim");
                assertEquals(config.getStatistic(), "stat");
                assertEquals(config.getSubject(), "subj");
                return 1.0;
            }
        }).when(data).get(Mockito.any(SheetConfiguration.class));
        writer.setSheetType(DataType.STATISTIC);
        writer.setColumnType(DataType.STIMULUS);
        writer.setRowType(DataType.SUBJECT);
        writer.setData(data);
        writer.getValue("stat", "subj", "stim");
    }
}
