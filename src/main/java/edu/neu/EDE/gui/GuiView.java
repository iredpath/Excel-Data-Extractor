package edu.neu.EDE.gui;

/**
 * GuiView - maintain the GUI
 */

import edu.neu.EDE.data_structs.DataGroupType;
import edu.neu.EDE.data_structs.DataType;
import edu.neu.EDE.gui.checkbox_list.CheckboxListItemClickHandler;
import edu.neu.EDE.gui.checkbox_list.CheckboxListItemMoveHandler;
import edu.neu.EDE.gui.checkbox_list.CheckboxListRenderer;

import javax.swing.*;
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
 * @author Ian Redpath
 * @author Teddy Stoddard
 * @version 3/24/2015
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
    private JButton removeSelectedFilesButton;
    private JButton addFilesButton;
    private JButton exportDataButton;
    private File defaultOutput;

    /**
     * basic constructor
     * @param m the model backing this view
     */
    public GuiView(GuiModel m) {
        this.model = m;
    }

    /**
     * START OF INITIALIZATION
     */
    /**
     * setup the initial gui settings and display
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

    /**
     * initialize the basic parent window
     * @return the parent window
     */
    JFrame initializeFrame() {
        JFrame frame = new JFrame("Excel Data Extractor");
        frame.setBounds(0, 0, 1024, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel header = new JPanel();
        frame.getContentPane().add(header, BorderLayout.NORTH);
        return frame;
    }

    /**
     * initialize the central panel of the GUI
     * @return the central panel
     */
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

    /**
     * initialize the main panel for data type item manipulation
     * @return the initialized panel
     */
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

    /**
     * create the drop down for selecting the data group type
     * (slide metric vs lookzone)
     */
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

    /**
     * initialize the tab panel for data type items
     * @return the initialized tab panel
     */
    JTabbedPane initializeTabPane() {
        //TODO: can we refactor this to one method we do three times?
        JTabbedPane axisTabs = new JTabbedPane(JTabbedPane.TOP);
        checkboxListItemClickHandler = new CheckboxListItemClickHandler();

        JPanel statisticsTab = new JPanel();
        axisTabs.addTab("Statistics", null, statisticsTab, null);
        axisTabs.setEnabledAt(0, true);
        statisticsTab.setLayout(new BorderLayout(0, 0));

        JPanel statisticsButtonsPanel = initializeDataTypeButtons(DataType.STATISTIC);
        statisticsTab.add(statisticsButtonsPanel, BorderLayout.NORTH);

        JScrollPane statisticsScrollPane = new JScrollPane();
        statisticsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        statisticsTab.add(statisticsScrollPane, BorderLayout.CENTER);

        statisticListContent = initializeDataTypeListContent(DataType.STATISTIC);
        statisticsScrollPane.setViewportView(statisticListContent);

        JPanel stimuliTab = new JPanel();
        axisTabs.addTab("Stimuli", null, stimuliTab, null);
        axisTabs.setEnabledAt(1, true);
        stimuliTab.setLayout(new BorderLayout(0, 0));

        JPanel stimuliButtonsPanel = initializeDataTypeButtons(DataType.STIMULUS);
        stimuliTab.add(stimuliButtonsPanel, BorderLayout.NORTH);

        JScrollPane stimuliScrollPane = new JScrollPane();
        stimuliScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        stimuliTab.add(stimuliScrollPane, BorderLayout.CENTER);

        stimulusListContent = initializeDataTypeListContent(DataType.STIMULUS);
        stimuliScrollPane.setViewportView(stimulusListContent);

        JPanel subjectsTab = new JPanel();
        axisTabs.addTab("Subjects", null, subjectsTab, null);
        axisTabs.setEnabledAt(2, true);
        subjectsTab.setLayout(new BorderLayout(0, 0));

        JPanel subjectsButtonsPanel = initializeDataTypeButtons(DataType.SUBJECT);
        subjectsTab.add(subjectsButtonsPanel, BorderLayout.NORTH);

        JScrollPane subjectScrollPane = new JScrollPane();
        subjectScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        subjectsTab.add(subjectScrollPane, BorderLayout.CENTER);

        subjectListContent = initializeDataTypeListContent(DataType.SUBJECT);
        subjectScrollPane.setViewportView(subjectListContent);

        return axisTabs;
    }

    /**
     * initialize the button panel for data type item pane
     * @param type the data type of this pane
     * @return the initialized panel
     */
    JPanel initializeDataTypeButtons(final DataType type) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        JButton selectAll = new JButton("Select All");
        buttonsPanel.add(selectAll);
        selectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.selectAll(type);
                frame.revalidate();
                frame.repaint();
            }
        });

        JButton deselectAll = new JButton("Deselect All");
        buttonsPanel.add(deselectAll);
        deselectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.deselectAll(type);
                frame.revalidate();
                frame.repaint();
            }
        });
        return buttonsPanel;
    }

    /**
     * initialize the list for the given data type
     * @param type the type of the list
     */
    JList initializeDataTypeListContent(DataType type) {
        JList content = new JList(model.getActiveModel(type));
        content.setCellRenderer(new CheckboxListRenderer());
        content.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        content.addMouseListener(checkboxListItemClickHandler);
        CheckboxListItemMoveHandler statisticMoveHandler = new CheckboxListItemMoveHandler(content);
        content.addMouseListener(statisticMoveHandler);
        content.addMouseMotionListener(statisticMoveHandler);
        return content;
    }

    /**
     * initialize the footer for the central panel
     * @return the initialized footer
     */
    JPanel initializeAxisFooter() {
        JPanel axisFooter = new JPanel();
        axisFooter.setLayout(new BorderLayout(0, 0));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        axisFooter.add(contentPanel, BorderLayout.CENTER);
        exportDataButton = new JButton("Export");
        exportDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportProgressBar.setVisible(true);
                exportDataButton.setVisible(false);
                JFileChooser saveDialog = new JFileChooser();
                if (defaultOutput != null) {
                    saveDialog.setCurrentDirectory(defaultOutput);
                }
                int retVal = saveDialog.showSaveDialog(new JFrame());
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    ExportWorker exportWorker = new ExportWorker(saveDialog);
                    exportWorker.execute();
                } else {
                    exportProgressBar.setVisible(false);
                    exportDataButton.setVisible(true);
                }
            }
        });
        contentPanel.add(exportDataButton);

        exportProgressBar = new JProgressBar();
        exportProgressBar.setIndeterminate(true);
        exportProgressBar.setVisible(false);
        exportProgressBar.setStringPainted(true);
        contentPanel.add(exportProgressBar);

        return axisFooter;
    }

    /**
     * initialize the panel for the list of added files
     * @return the initialized panel
     */
    JPanel initializeFilePanel() {
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout(0, 0));
        JPanel fileHeader = new JPanel();
        fileHeader.setLayout(new BorderLayout(0, 0));
        filePanel.add(fileHeader, BorderLayout.NORTH);

        JPanel headerButtonPanel = new JPanel();
        headerButtonPanel.setLayout(new FlowLayout());
        headerButtonPanel.add(initializeAddFileButton());

        addFileProgressBar = new JProgressBar();
        addFileProgressBar.setIndeterminate(true);
        addFileProgressBar.setVisible(false);
        addFileProgressBar.setStringPainted(true);
        headerButtonPanel.add(addFileProgressBar);

        headerButtonPanel.add(initializeRemoveSelectedFilesButton());
        fileHeader.add(headerButtonPanel, BorderLayout.NORTH);
        fileHeader.add(new JPanel(), BorderLayout.SOUTH);

        JPanel fileListWrapper = initializeFileListWrapper();
        filePanel.add(fileListWrapper, BorderLayout.CENTER);

        JPanel outputWrapper = initializeOutputWrapper();
        filePanel.add(outputWrapper, BorderLayout.SOUTH);

        return filePanel;
    }

    /**
     * initialize the button to remove selected files
     * @return the initialized button
     */
    JButton initializeRemoveSelectedFilesButton() {
        removeSelectedFilesButton = new JButton("Remove Selected Files");
        removeSelectedFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFilesButton.setVisible(false);
                removeSelectedFilesButton.setVisible(false);
                addFileProgressBar.setVisible(true);

                FileDeleter deleter = new FileDeleter();
                deleter.execute();
            }
        });
        return removeSelectedFilesButton;
    }

    /**
     * initialize the button to add new files
     * @return the initialized button
     */
    JButton initializeAddFileButton() {
        addFilesButton = new JButton("Add Files");
        addFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFilesButton.setVisible(false);
                removeSelectedFilesButton.setVisible(false);
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

    /**
     * initialize the wrapper panel for the file list
     * @return the initialized panel
     */
    JPanel initializeFileListWrapper() {
        JPanel fileListWrapper = new JPanel();
        fileListWrapper.setLayout(new GridLayout(0, 1, 0, 0));
        JScrollPane fileList = new JScrollPane();
        fileList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        fileList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fileListWrapper.add(fileList);
        fileListContent = new JList(model.getFileListModel());
        fileListContent.setCellRenderer(new CheckboxListRenderer());
        fileListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileListContent.addMouseListener(new CheckboxListItemClickHandler());
        fileList.setViewportView(fileListContent);

        return fileListWrapper;
    }

    /**
     * initialize the panel for the output format
     * @return the initialized panel
     */
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

    /**
     * initialize the table displaying the output format
     * @return the initialized table
     */
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

        columnHeads = new ArrayList<JPanel>();
        rowHeads = new ArrayList<JPanel>();
        for (int i = 0; i < 3; i++) {
            JPanel col = new JPanel();
            col.add(new JLabel(model.getColumnType().toString()));
            columnHeads.add(col);

            JPanel row = new JPanel();
            row.add(new JLabel(model.getRowType().toString()));
            rowHeads.add(row);
        }

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

    /**
     * initialize the dropdown for selecting the tab data type
     * @return the initialized dropdown
     */
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

    /**
     * update the subject/stimulus/statistic models
     */
    void update() {
        //TODO: Maybe find a way around this
        // there should be a way to edit the model instead of constantly setting
        // but since each list is essentially backed by two models,
        // may not be possible

        //CHECKS TEMPORARY FIX FOR AWFUL BUG
        //THESE MAY NOT BE NECESSARY ANYMORE, I'LL TRY TO TEST A FEW TIMES
        if (model.getActiveModel(DataType.SUBJECT).size() > 0 || subjectListContent.getModel().getSize() > 0) {
            subjectListContent.setModel(model.getActiveModel(DataType.SUBJECT));
        }
        if (model.getActiveModel(DataType.STIMULUS).size() > 0 || stimulusListContent.getModel().getSize() > 0) {
            stimulusListContent.setModel(model.getActiveModel(DataType.STIMULUS));
        }
        if (model.getActiveModel(DataType.STATISTIC).size() > 0 || statisticListContent.getModel().getSize() > 0) {
            statisticListContent.setModel(model.getActiveModel(DataType.STATISTIC));
        }
    }

    /**
     * update the output format table
     */
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

    /**
     * a helper class
     * creates a new thread to execute file adding logic
     */
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
                            addFileProgressBar.setVisible(false);
                            addFilesButton.setVisible(true);
                            removeSelectedFilesButton.setVisible(true);
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                } catch (IOException ex) {
                    // TODO: is this the right way to do it?
                    System.out.println(ex);
                    System.exit(1);
                }
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        addFileProgressBar.setVisible(false);
                        addFilesButton.setVisible(true);
                        removeSelectedFilesButton.setVisible(true);
                    }
                });
            }
            return null;
        }
    }

    /**
     * a helper class
     * creates a new thread to handle exporting logic
     */
    class ExportWorker extends SwingWorker {
        final JFileChooser fc;

        ExportWorker(JFileChooser fc) {
            this.fc = fc;
        }

        protected Object doInBackground() {
            final String result = model.export(fc.getSelectedFile());
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    exportProgressBar.setVisible(false);
                    exportDataButton.setVisible(true);
                    JOptionPane.showMessageDialog(frame, result);
                    defaultOutput = fc.getCurrentDirectory();
                }
            });
            return null;
        }
    }

    /**
     * a helper class
     * creates a new thread to handle file deletion logic
     */
    class FileDeleter extends SwingWorker {

        protected Object doInBackground() {
            try {
                model.removeSelectedFiles();
            } catch(IOException e) {

            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    addFileProgressBar.setVisible(false);
                    addFilesButton.setVisible(true);
                    removeSelectedFilesButton.setVisible(true);
                    frame.revalidate();
                    frame.repaint();
                    frame.requestFocus();
                }
            });
            return null;
        }
    }
}