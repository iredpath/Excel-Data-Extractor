package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataGroupType;
import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.gui.checkboxList.CheckboxListItemClickHandler;
import edu.neu.EDE.gui.checkboxList.CheckboxListItemMoveHandler;
import edu.neu.EDE.gui.checkboxList.CheckboxListRenderer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 3/24/15.
 */
public class GuiView {

    private GuiModel model;
    private JFrame frame;
    private JList fileListContent;
    private JList subjectListContent;
    private JList stimulusListContent;
    private JList statisticListContent;
    private JProgressBar addFileProgressBar;
    private JProgressBar exportProgressBar;
    private MouseAdapter checkboxListItemClickHandler;
    private List<JPanel> columnHeads;
    private List<JPanel> rowHeads;
    private JComboBox dataGroupDropdown;

    public GuiView(GuiModel m) {
        this.model = m;
        this.checkboxListItemClickHandler = new CheckboxListItemClickHandler();
        this.columnHeads = new ArrayList<JPanel>();
        this.rowHeads = new ArrayList<JPanel>();
        for (int i = 0; i < 3; i++) {
            JPanel col = new JPanel();
            col.add(new JLabel(model.getColumnType().toString()));
            columnHeads.add(col);

            JPanel row = new JPanel();
            row.add(new JLabel(model.getRowType().toString()));
            rowHeads.add(row);
        }
    }

    /**
     * START OF INITIALIZATION
     */
    void initialize() {

        frame = initializeFrame();
        JPanel center = initializeCentralPanel();
        frame.getContentPane().add(center, BorderLayout.CENTER);

        JPanel axisMemberPanel = initializeAxisMemberPanel();
        GridBagConstraints gbc_AxisMemberPanel = new GridBagConstraints();
        gbc_AxisMemberPanel.weighty = 1.0;
        gbc_AxisMemberPanel.weightx = 0.6;
        gbc_AxisMemberPanel.anchor = GridBagConstraints.EAST;
        gbc_AxisMemberPanel.insets = new Insets(0, 0, 5, 0);
        gbc_AxisMemberPanel.fill = GridBagConstraints.BOTH;
        gbc_AxisMemberPanel.gridx = 1;
        gbc_AxisMemberPanel.gridy = 0;
        center.add(axisMemberPanel, gbc_AxisMemberPanel);

        JPanel filePanel = initializeFilePanel();
        GridBagConstraints gbc_filePanel = new GridBagConstraints();
        gbc_filePanel.weighty = 1.0;
        gbc_filePanel.weightx = 0.4;
        gbc_filePanel.anchor = GridBagConstraints.WEST;
        gbc_filePanel.fill = GridBagConstraints.BOTH;
        gbc_filePanel.gridx = 0;
        gbc_filePanel.gridy = 0;
        center.add(filePanel, gbc_filePanel);

        JPanel footer = new JPanel();
        frame.getContentPane().add(footer, BorderLayout.SOUTH);
        JPanel leftSpacer = new JPanel();
        frame.getContentPane().add(leftSpacer, BorderLayout.WEST);
        JPanel rightSpacer = new JPanel();
        frame.getContentPane().add(rightSpacer, BorderLayout.EAST);

        frame.requestFocus();
        frame.setVisible(true);
    }

    JFrame initializeFrame() {
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1024, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel header = new JPanel();
        frame.getContentPane().add(header, BorderLayout.NORTH);
        return frame;
    }

    JPanel initializeCentralPanel() {
        JPanel center = new JPanel();
        GridBagLayout gbl_Center = new GridBagLayout();
        gbl_Center.columnWidths = new int[] {400, 600};
        gbl_Center.rowHeights = new int[] {0};
        gbl_Center.columnWeights = new double[]{1.0, 0.0};
        gbl_Center.rowWeights = new double[]{1.0};
        center.setLayout(gbl_Center);
        return center;
    }

    JPanel initializeAxisMemberPanel() {
        JPanel axisMemberPanel = new JPanel();
        axisMemberPanel.setLayout(new BorderLayout(0, 0));

        JPanel axisHeader = new JPanel();
        axisMemberPanel.add(axisHeader, BorderLayout.NORTH);
        axisHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel axisList = new JPanel();
        axisMemberPanel.add(axisList, BorderLayout.CENTER);
        axisList.setLayout(new BorderLayout(0, 0));

        initializeDataGroupDropdown();
        axisHeader.add(dataGroupDropdown);

        JTabbedPane axisTabs = initializeTabPane();
        axisList.add(axisTabs, BorderLayout.CENTER);

        JPanel axisFooter = initializeAxisFooter();
        axisMemberPanel.add(axisFooter, BorderLayout.SOUTH);

        JPanel separator = new JPanel();
        axisMemberPanel.add(separator, BorderLayout.WEST);
        return axisMemberPanel;
    }

    void initializeDataGroupDropdown() {
        dataGroupDropdown = new JComboBox();
        dataGroupDropdown.setModel(new DefaultComboBoxModel(new DataGroupType[]{DataGroupType.SLIDEMETRIC, DataGroupType.LOOKZONE}));
        dataGroupDropdown.setSelectedIndex(0);
        dataGroupDropdown.setMaximumRowCount(2);
        dataGroupDropdown.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DataGroupType d = (DataGroupType) e.getItem();
                    model.updateDataGroupType(d);
                    update();
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
    }

    JTabbedPane initializeTabPane() {
        JTabbedPane axisTabs = new JTabbedPane(JTabbedPane.TOP);

        JPanel statisticsTab = new JPanel();
        axisTabs.addTab("Statistics", null, statisticsTab, null);
        axisTabs.setEnabledAt(0, true);
        statisticsTab.setLayout(new BorderLayout(0, 0));

        JScrollPane statisticsScrollPane = new JScrollPane();
        statisticsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        statisticsTab.add(statisticsScrollPane, BorderLayout.CENTER);

        initializeStatisticListContent();
        statisticsScrollPane.setViewportView(statisticListContent);

        JPanel stimuliTab = new JPanel();
        axisTabs.addTab("Stimuli", null, stimuliTab, null);
        axisTabs.setEnabledAt(1, true);
        stimuliTab.setLayout(new BorderLayout(0, 0));

        JScrollPane stimuliScrollPane = new JScrollPane();
        stimuliScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        stimuliTab.add(stimuliScrollPane, BorderLayout.CENTER);

        initializeStimulusListContent();
        stimuliScrollPane.setViewportView(stimulusListContent);

        JPanel subjectsTab = new JPanel();
        axisTabs.addTab("Subjects", null, subjectsTab, null);
        axisTabs.setEnabledAt(2, true);
        subjectsTab.setLayout(new BorderLayout(0, 0));

        JScrollPane subjectScrollPane = new JScrollPane();
        subjectScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        subjectsTab.add(subjectScrollPane, BorderLayout.CENTER);

        initializeSubjectListContent();
        subjectScrollPane.setViewportView(subjectListContent);

        return axisTabs;
    }

    void initializeSubjectListContent() {
        // NOTE: MODEL IS NOT USED.  PURELY TO ALLOW FOR MOVEMENT LISTENER
        subjectListContent = new JList(new DefaultListModel());
        subjectListContent.setCellRenderer(new CheckboxListRenderer());
        subjectListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        subjectListContent.addMouseListener(checkboxListItemClickHandler);
        CheckboxListItemMoveHandler statisticMoveHandler = new CheckboxListItemMoveHandler(subjectListContent);
        subjectListContent.addMouseListener(statisticMoveHandler);
        subjectListContent.addMouseMotionListener(statisticMoveHandler);
    }
    void initializeStimulusListContent() {
        // NOTE: MODEL IS NOT USED.  PURELY TO ALLOW FOR MOVEMENT LISTENER
        stimulusListContent = new JList(new DefaultListModel());
        stimulusListContent.setCellRenderer(new CheckboxListRenderer());
        stimulusListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stimulusListContent.addMouseListener(checkboxListItemClickHandler);
        CheckboxListItemMoveHandler statisticMoveHandler = new CheckboxListItemMoveHandler(stimulusListContent);
        stimulusListContent.addMouseListener(statisticMoveHandler);
        stimulusListContent.addMouseMotionListener(statisticMoveHandler);
    }
    void initializeStatisticListContent() {
        // NOTE: MODEL IS NOT USED.  PURELY TO ALLOW FOR MOVEMENT LISTENER
        statisticListContent = new JList(new DefaultListModel());
        statisticListContent.setCellRenderer(new CheckboxListRenderer());
        statisticListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        statisticListContent.addMouseListener(checkboxListItemClickHandler);
        CheckboxListItemMoveHandler statisticMoveHandler = new CheckboxListItemMoveHandler(statisticListContent);
        statisticListContent.addMouseListener(statisticMoveHandler);
        statisticListContent.addMouseMotionListener(statisticMoveHandler);
    }

    JPanel initializeAxisFooter() {
        JPanel axisFooter = new JPanel();
        axisFooter.setLayout(new BorderLayout(0, 0));

        JButton btnExport = new JButton("Export");
        btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportProgressBar.setVisible(true);
                JFileChooser saveDialog = new JFileChooser();
                int retVal = saveDialog.showSaveDialog(new JFrame());
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    ExportWorker exportWorker = new ExportWorker(saveDialog);
                    exportWorker.execute();
                }
            }
        });
        axisFooter.add(btnExport, BorderLayout.EAST);

        exportProgressBar = new JProgressBar();
        exportProgressBar.setIndeterminate(true);
        exportProgressBar.setVisible(false);
        exportProgressBar.setStringPainted(true);
        axisFooter.add(exportProgressBar, BorderLayout.WEST);

        return axisFooter;
    }

    JPanel initializeFilePanel() {
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout(0, 0));
        JPanel fileHeader = new JPanel();
        fileHeader.setLayout(new BorderLayout(0, 0));
        filePanel.add(fileHeader, BorderLayout.NORTH);

        fileHeader.add(initializeAddFileButton(), BorderLayout.WEST);

        addFileProgressBar = new JProgressBar();
        addFileProgressBar.setIndeterminate(true);
        addFileProgressBar.setVisible(false);
        addFileProgressBar.setStringPainted(true);
        fileHeader.add(addFileProgressBar, BorderLayout.CENTER);

        fileHeader.add(initializeRemoveSelectedFilesButton(), BorderLayout.EAST);

        JPanel fileListWrapper = initializeFileListWrapper();
        filePanel.add(fileListWrapper, BorderLayout.CENTER);

        JPanel outputWrapper = initializeOutputWrapper();
        filePanel.add(outputWrapper, BorderLayout.SOUTH);

        return filePanel;
    }

    JButton initializeRemoveSelectedFilesButton() {
        JButton remove = new JButton("Remove Selected Files");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFileProgressBar.setVisible(true);
                FileDeleter deleter = new FileDeleter();
                deleter.execute();
            }
        });
        return remove;
    }

    JButton initializeAddFileButton() {
        JButton addFilesButton = new JButton("Add Files");
        addFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFileProgressBar.setVisible(true);
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fc.setMultiSelectionEnabled(true);
                int retVal = fc.showOpenDialog(frame);
                FileAdder adder = new FileAdder(fc, retVal);
                adder.execute();
            }
        });
        return addFilesButton;
    }

    JPanel initializeFileListWrapper() {
        JPanel fileListWrapper = new JPanel();
        fileListWrapper.setLayout(new GridLayout(0, 1, 0, 0));
        JScrollPane fileList = new JScrollPane();
        fileList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        fileList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fileListWrapper.add(fileList);
        fileListContent = new JList();
        fileListContent.setCellRenderer(new CheckboxListRenderer());
        fileListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileListContent.addMouseListener(new CheckboxListItemClickHandler());
        fileListContent.setModel(model.getFileListModel());
        fileList.setViewportView(fileListContent);

        return fileListWrapper;
    }

    JPanel initializeOutputWrapper() {
        JPanel outputWrapper = new JPanel();
        outputWrapper.setLayout(new BorderLayout(0, 0));
        JPanel outputSpacer = new JPanel();
        outputWrapper.add(outputSpacer, BorderLayout.CENTER);
        JPanel outputContainer = new JPanel();
        outputWrapper.add(outputContainer, BorderLayout.SOUTH);
        outputContainer.setLayout(new BorderLayout(0, 0));
        JPanel outputFormat = new JPanel();
        outputContainer.add(outputFormat, BorderLayout.NORTH);
        JLabel outputHeaderText = new JLabel("Output Format");
        outputFormat.add(outputHeaderText);
        JPanel tableContainer = new JPanel();
        outputContainer.add(tableContainer, BorderLayout.CENTER);

        JPanel mockTable = initializeMockTable();
        tableContainer.add(mockTable);

        JPanel tabContainer = new JPanel();
        outputContainer.add(tabContainer, BorderLayout.SOUTH);
        JComboBox comboBox = initializeDataTypeComboBox();
        tabContainer.add(comboBox);

        return outputWrapper;
    }

    JPanel initializeMockTable() {
        JPanel mockTable = new JPanel();
        mockTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mockTable.setLayout(new GridLayout(4, 4, 0, 0));
        JPanel swapContainer = new JPanel();
        mockTable.add(swapContainer);
        JButton swapAxisButton = new JButton("Swap");
        swapAxisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.swapAxes();
                updateOutputTable();
                frame.revalidate();
                frame.repaint();
            }
        });
        swapContainer.add(swapAxisButton);

        for (JPanel p: columnHeads) {
            mockTable.add(p);
        }
        for (JPanel p: rowHeads) {
            mockTable.add(p);
            mockTable.add(new JPanel());
            mockTable.add(new JPanel());
            mockTable.add(new JPanel());
        }

        return mockTable;
    }

    JComboBox initializeDataTypeComboBox() {
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new DataType[]{DataType.STATISTIC, DataType.STIMULUS, DataType.SUBJECT}));
        comboBox.setSelectedIndex(0);
        comboBox.setMaximumRowCount(3);
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DataType d = (DataType) e.getItem();
                    model.updateSheetType(d);
                    updateOutputTable();
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
        return comboBox;
    }
    /**
     * END OF INITIALIZATION
     */


    void update() {
        //TODO: Maybe find a way around this
        // there should be a way to edit the model instead of constantly setting
        // but since each list is essentially backed by two models,
        // may not be possible

        //CHECKS TEMPORARY FIX FOR AWFUL BUG
        if (model.getListModel(DataType.SUBJECT).size() > 0 || subjectListContent.getModel().getSize() > 0) {
            subjectListContent.setModel(model.getListModel(DataType.SUBJECT));
        }
        if (model.getListModel(DataType.STIMULUS).size() > 0 || stimulusListContent.getModel().getSize() > 0) {
            stimulusListContent.setModel(model.getListModel(DataType.STIMULUS));
        }
        if (model.getListModel(DataType.STATISTIC).size() > 0 || statisticListContent.getModel().getSize() > 0) {
            statisticListContent.setModel(model.getListModel(DataType.STATISTIC));
        }
    }

    void updateOutputTable() {
        DataType rowType = model.getRowType();
        DataType columnType = model.getColumnType();
        for (JPanel p: rowHeads) {
            p.removeAll();
            p.add(new JLabel(rowType.toString()));
        }
        for (JPanel p: columnHeads) {
            p.removeAll();
            p.add(new JLabel(columnType.toString()));
        }
    }

    // this may have to be abstracted to also handle model.remove(), or we just make another class
    class FileAdder extends SwingWorker {
        final JFileChooser fc;
        final int retVal;

        FileAdder(JFileChooser fc, int retVal) {
            this.fc = fc;
            this.retVal = retVal;
        }

        protected Object doInBackground() {
            if (retVal == fc.APPROVE_OPTION) {
                File[] files = fc.getSelectedFiles();
                try {
                    model.addFiles(files);
                    model.updateJListModels();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            update();
                            frame.revalidate();
                            frame.repaint();
                            addFileProgressBar.setVisible(false);
                        }
                    });
                } catch (IOException ex) {
                    // TODO: is this the right way to do it?
                    System.out.println(ex);
                    System.exit(1);
                }
            }
            return null;
        }
    }

    class ExportWorker extends SwingWorker {
        final JFileChooser fc;

        ExportWorker(JFileChooser fc) {
            this.fc = fc;
        }

        protected Object doInBackground() {
            model.export(fc.getSelectedFile());
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    exportProgressBar.setVisible(false);
                }
            });
            return null;
        }
    }

    class FileDeleter extends SwingWorker {

        protected Object doInBackground() {
            //TODO: support remove on back end
            try {
                model.removeSelectedFiles();
            } catch(IOException e) {

            }
            //model.remove(buttonListItem.getFilename(), buttonListItem.getSubject());
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    update();
                    addFileProgressBar.setVisible(false);
                    frame.revalidate();
                    frame.repaint();
                    frame.requestFocus();
                }
            });
            return null;
        }
    }
}