package edu.neu.EDE.gui;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import edu.neu.EDE.data_structs.DataGroupType;
import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.gui.checkbox_list.CheckboxListItem;
import edu.neu.EDE.gui.checkbox_list.CheckboxListModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Ian Redpath
 * @version 3/29/2015
 */
public class TestGuiModel extends Assert {

    @Test
    public void testUpdateDataGroupType() {
        GuiModel model = new GuiModel();
        model.updateDataGroupType(DataGroupType.SLIDEMETRIC);
        assertEquals(model.getDataGroupType(), DataGroupType.SLIDEMETRIC);
        model.updateDataGroupType(DataGroupType.LOOKZONE);
        assertEquals(model.getDataGroupType(), DataGroupType.LOOKZONE);
    }

    @Test
    public void testSwapAxes() {
        GuiModel model = new GuiModel();
        model.swapAxes();
        assertEquals(model.getColumnType(), DataType.SUBJECT);
        assertEquals(model.getRowType(), DataType.STIMULUS);
        // tab type shouldn't change
        assertEquals(model.getTabType(), DataType.STATISTIC);
    }

    @Test
    public void testUpdateSheetType() {
        GuiModel model = new GuiModel();
        model.updateSheetType(DataType.STATISTIC);
        assertEquals(model.getTabType(), DataType.STATISTIC);
        assertEquals(model.getColumnType(), DataType.STIMULUS);
        assertEquals(model.getRowType(), DataType.SUBJECT);

        model.updateSheetType(DataType.STIMULUS); // swap with column
        assertEquals(model.getTabType(), DataType.STIMULUS);
        assertEquals(model.getColumnType(), DataType.STATISTIC);
        assertEquals(model.getRowType(), DataType.SUBJECT);

        model.updateSheetType(DataType.SUBJECT); // swap with row
        assertEquals(model.getTabType(), DataType.SUBJECT);
        assertEquals(model.getColumnType(), DataType.STATISTIC);
        assertEquals(model.getRowType(), DataType.STIMULUS);
    }

    @Test
    public void testAddFiles() throws IOException {
        GuiModel model = Mockito.spy(new GuiModel());
        Mockito.doNothing().when(model).addFile(Mockito.any(File.class));
        File[] files = { File.createTempFile("test", null) };
        model.addFiles(files);
        assertEquals(model.getInvalidFiles().size(), 1);
        File dir = Files.createTempDir();
        File f = File.createTempFile("bar", null, dir);
        File[] innerDirectory = { dir };
        model.addFiles(innerDirectory);
        assertEquals(model.getInvalidFiles().size(), 2);
        File valid = File.createTempFile("valid", ".xlsx");
        File[] validArray = { valid };
        model.addFiles(validArray);
        assertEquals(model.getInvalidFiles().size(), 2);
    }

    @Test
    public void testAddToFileListModel() throws IOException {
        GuiModel model = new GuiModel();
        File file1 = File.createTempFile("foo", null);
        model.addToFileListModel(file1, "foo-subject");
        File file2 = File.createTempFile("bar", null);
        model.addToFileListModel(file2, "bar-subject");
        File file3 = File.createTempFile("foo", null);
        model.addToFileListModel(file1, "same-foo-subject");
        assertEquals(model.getFileListModel().getSize(), 2);
        CheckboxListModel m = model.getFileListModel();
        CheckboxListItem i1 = m.get(0);
        assertEquals(i1.getFile(), file1);
        assertEquals(i1.getSubject(), "foo-subject");
        assertEquals(i1.toString(), file1.getName());
        CheckboxListItem i2 = m.get(1);
        assertEquals(i2.getFile(), file2);
        assertEquals(i2.getSubject(), "bar-subject");
        assertEquals(i2.toString(), file2.getName());
    }

    @Test
    public void testRemoveSelectedFiles() throws IOException {
        GuiModel model = Mockito.spy(new GuiModel());
        Mockito.doAnswer(new Answer<GuiModel>() {
            public GuiModel answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                GuiModel mock = (GuiModel) invocationOnMock.getMock();
                for (Object o: args) {
                    File f = (File) o;
                    mock.addToFileListModel(f, "subject");
                }
                return mock;
            }
        }).when(model).addFile(Mockito.any(File.class));
        File file1 = File.createTempFile("foo", ".xlsx");
        File file2 = File.createTempFile("bar", ".xlsx");
        File file3 = File.createTempFile("buzz", ".xlsx");
        File[] files = { file1, file2, file3 };
        model.addFiles(files);
        CheckboxListModel m = model.getFileListModel();
        m.get(1).setSelected(true);
        model.removeSelectedFiles();
        assertEquals(model.getFileListModel().getSize(), 2);
    }

    @Test
    public void testAddNewItemsToModel() {
        GuiModel model = new GuiModel();
        DataType type = DataType.STIMULUS;
        List<String> vals = Lists.newArrayList("foo", "bar", "buzz");
        model.addNewItemsToModel(vals, model.getActiveModel(type));
        assertEquals(model.getActiveModel(type).getSize(), 3);
        vals.add(0, "new");
        vals.add(2, "also new");
        vals.add(4, "new");
        model.addNewItemsToModel(vals, model.getActiveModel(type));
        CheckboxListModel m = model.getActiveModel(type);
        assertEquals(m.getSize(), 5);
        assertEquals(m.get(0).toString(), "foo");
        assertEquals(m.get(1).toString(), "bar");
        assertEquals(m.get(2).toString(), "buzz");
        assertEquals(m.get(3).toString(), "new");
        assertEquals(m.get(4).toString(), "also new");
    }

    @Test
    public void testClearModels() {
        GuiModel model = new GuiModel();
        CheckboxListModel m = model.getActiveModel(DataType.STATISTIC);
        m.addElement(new CheckboxListItem("foo"));
        m = model.getActiveModel(DataType.SUBJECT);
        m.addElement(new CheckboxListItem("bar"));
        model.updateDataGroupType(DataGroupType.LOOKZONE);
        m = model.getActiveModel(DataType.SUBJECT);
        m.addElement(new CheckboxListItem("buzz"));
        m = model.getActiveModel(DataType.STIMULUS);
        m.addElement(new CheckboxListItem("test"));
        model.clearModels();
        assertEquals(model.getActiveModel(DataType.STIMULUS).getSize(), 0);
        assertEquals(model.getActiveModel(DataType.SUBJECT).getSize(), 0);
        model.updateDataGroupType(DataGroupType.SLIDEMETRIC);
        assertEquals(model.getActiveModel(DataType.SUBJECT).getSize(), 0);
        assertEquals(model.getActiveModel(DataType.STATISTIC).getSize(), 0);
    }

    @Test
    public void testUpdateJListModels() {
    }

    @Test
    public void testAddFile() {

    }

    @Test
    public void testSelectAll() {
        GuiModel model = new GuiModel();
        CheckboxListModel m = model.getActiveModel(DataType.STATISTIC);
        CheckboxListItem i = new CheckboxListItem("foo");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("bar");
        i.setSelected(false);
        m.addElement(i);
        i = new CheckboxListItem("buzz");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test2");
        i.setSelected(false);
        m.addElement(i);

        model.selectAll(DataType.STATISTIC);
        List<String> selected = model.getSelectedData(m);
        assertEquals(selected.size(), 5);
        assertEquals(selected.get(0).toString(), "foo");
        assertEquals(selected.get(1).toString(), "bar");
        assertEquals(selected.get(2).toString(), "buzz");
        assertEquals(selected.get(3).toString(), "test");
        assertEquals(selected.get(4).toString(), "test2");
    }

    @Test
    public void testDeselectAll() {
        GuiModel model = new GuiModel();
        CheckboxListModel m = model.getActiveModel(DataType.STATISTIC);
        CheckboxListItem i = new CheckboxListItem("foo");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("bar");
        i.setSelected(false);
        m.addElement(i);
        i = new CheckboxListItem("buzz");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test2");
        i.setSelected(false);
        m.addElement(i);

        model.deselectAll(DataType.STATISTIC);
        assertEquals(model.getSelectedData(m).size(), 0);
    }

    @Test
    public void testSetAllItemsAs() {
        GuiModel model = new GuiModel();
        CheckboxListModel m = model.getActiveModel(DataType.STATISTIC);
        CheckboxListItem i = new CheckboxListItem("foo");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("bar");
        i.setSelected(false);
        m.addElement(i);
        i = new CheckboxListItem("buzz");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test2");
        i.setSelected(false);
        m.addElement(i);

        model.setAllItemsAs(true, DataType.STATISTIC);
        List<String> selected = model.getSelectedData(m);
        assertEquals(selected.size(), 5);
        assertEquals(selected.get(0).toString(), "foo");
        assertEquals(selected.get(1).toString(), "bar");
        assertEquals(selected.get(2).toString(), "buzz");
        assertEquals(selected.get(3).toString(), "test");
        assertEquals(selected.get(4).toString(), "test2");

        model.setAllItemsAs(false, DataType.STATISTIC);
        assertEquals(model.getSelectedData(m).size(), 0);
    }

    @Test
    public void testGetActiveModel() {
        GuiModel model = new GuiModel();
        CheckboxListModel m = model.getActiveModel(DataType.STATISTIC);
        m.addElement(new CheckboxListItem("foo"));
        model.updateDataGroupType(DataGroupType.LOOKZONE);
        m = model.getActiveModel(DataType.STATISTIC);
        assertEquals(m.getSize(), 0);
        model.updateDataGroupType(DataGroupType.SLIDEMETRIC);
        m = model.getActiveModel(DataType.STATISTIC);
        assertEquals(m.getSize(), 1);
    }

    @Test
    public void testExport() {

    }

    @Test
    public void testGetSelectedData() {
        GuiModel model = new GuiModel();
        CheckboxListModel m = model.getActiveModel(DataType.STATISTIC);
        CheckboxListItem i = new CheckboxListItem("foo");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("bar");
        i.setSelected(false);
        m.addElement(i);
        i = new CheckboxListItem("buzz");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test");
        i.setSelected(true);
        m.addElement(i);
        i = new CheckboxListItem("test2");
        i.setSelected(false);
        m.addElement(i);

        List<String> selected = model.getSelectedData(m);
        assertEquals(selected.size(), 3);
        assertEquals(selected.get(0).toString(), "foo");
        assertEquals(selected.get(1).toString(), "buzz");
        assertEquals(selected.get(2).toString(), "test");

    }
}
