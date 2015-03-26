package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataGroupType;
import edu.neu.EDE.data_structs.DataType;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ian on 3/24/15.
 */
public class GuiView {

    private GuiModel model;
    private JFrame frame;
    private JPanel fileListContent;
    private JList subjectListContent;
    private JList stimulusListContent;
    private JList statisticListContent;
    private JRadioButton lookZoneButton;
    private JRadioButton slideMetricButton;
    private JProgressBar addFileProgressBar;
    private JProgressBar exportProgressBar;
    private DataGroupType dataGroupType;
    private MouseAdapter checkboxListItemClickHandler;
    private JPanel mockTable;
    private List<JPanel> columnHeads;
    private List<JPanel> rowHeads;

    public GuiView(GuiModel m) {
        this.model = m;
        this.checkboxListItemClickHandler = new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                // Get index of item clicked
                int index = list.locationToIndex(event.getPoint());
                CheckboxListItem item = (CheckboxListItem) list.getModel().getElementAt(index);
                // Toggle selected state
                item.setSelected(!item.isSelected());
                // Repaint cell
                list.repaint(list.getCellBounds(index, index));
            }
        };
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

    void initialize() {

        frame = new JFrame();
        frame.setBounds(0, 0, 1024, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel Header = new JPanel();
        frame.getContentPane().add(Header, BorderLayout.NORTH);
        JPanel Center = new JPanel();
        frame.getContentPane().add(Center, BorderLayout.CENTER);
        GridBagLayout gbl_Center = new GridBagLayout();
        gbl_Center.columnWidths = new int[] {400, 600};
        gbl_Center.rowHeights = new int[] {0};
        gbl_Center.columnWeights = new double[]{1.0, 0.0};
        gbl_Center.rowWeights = new double[]{1.0};
        Center.setLayout(gbl_Center);
        JPanel AxisMemberPanel = new JPanel();
        GridBagConstraints gbc_AxisMemberPanel = new GridBagConstraints();
        gbc_AxisMemberPanel.insets = new Insets(0, 0, 5, 0);
        gbc_AxisMemberPanel.fill = GridBagConstraints.BOTH;
        gbc_AxisMemberPanel.gridx = 1;
        gbc_AxisMemberPanel.gridy = 0;
        Center.add(AxisMemberPanel, gbc_AxisMemberPanel);
        AxisMemberPanel.setLayout(new BorderLayout(0, 0));
        
        
        JPanel axisHeader = new JPanel();
        AxisMemberPanel.add(axisHeader, BorderLayout.NORTH);
        axisHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel AxisList = new JPanel();
        AxisMemberPanel.add(AxisList, BorderLayout.CENTER);
        AxisList.setLayout(new BorderLayout(0, 0));


        ButtonGroup lookZoneOrSlideMetric = new ButtonGroup();
        lookZoneButton = new JRadioButton("Look zone data");
        lookZoneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton b = (AbstractButton) e.getSource();
                if (b.isSelected()) {
                    dataGroupType = DataGroupType.LOOKZONE;
                    updateData();
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
        slideMetricButton = new JRadioButton("Slide metric data");
        slideMetricButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton b = (AbstractButton) e.getSource();
                if (b.isSelected()) {
                    dataGroupType = DataGroupType.SLIDEMETRIC;
                    updateData();
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
        slideMetricButton.setSelected(true);
        lookZoneOrSlideMetric.add(lookZoneButton);
        lookZoneOrSlideMetric.add(slideMetricButton);
        axisHeader.add(slideMetricButton, BorderLayout.CENTER);
        axisHeader.add(lookZoneButton, BorderLayout.CENTER);
        // set default value;
        this.dataGroupType = DataGroupType.SLIDEMETRIC;

        JTabbedPane AxisTabs = new JTabbedPane(JTabbedPane.TOP);
        AxisList.add(AxisTabs, BorderLayout.CENTER);

        JPanel statisticsTab = new JPanel();
        AxisTabs.addTab("Statistics", null, statisticsTab, null);
        AxisTabs.setEnabledAt(0, true);
        statisticsTab.setLayout(new BorderLayout(0, 0));

        JScrollPane statisticsScrollPane = new JScrollPane();
        statisticsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        statisticsTab.add(statisticsScrollPane, BorderLayout.CENTER);

        statisticListContent = new JList();
        statisticListContent.setCellRenderer(new CheckboxListRenderer());
        statisticListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        statisticListContent.addMouseListener(checkboxListItemClickHandler);
        statisticsScrollPane.setViewportView(statisticListContent);

        JPanel stimuliTab = new JPanel();
        AxisTabs.addTab("Stimuli", null, stimuliTab, null);
        AxisTabs.setEnabledAt(1, true);
        stimuliTab.setLayout(new BorderLayout(0, 0));

        JScrollPane stimuliScrollPane = new JScrollPane();
        stimuliScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        stimuliTab.add(stimuliScrollPane, BorderLayout.CENTER);

        stimulusListContent = new JList();
        stimulusListContent.setCellRenderer(new CheckboxListRenderer());
        stimulusListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stimulusListContent.addMouseListener(checkboxListItemClickHandler);
        stimuliScrollPane.setViewportView(stimulusListContent);

        JPanel subjectsTab = new JPanel();
        AxisTabs.addTab("Subjects", null, subjectsTab, null);
        AxisTabs.setEnabledAt(2, true);
        subjectsTab.setLayout(new BorderLayout(0, 0));

        JScrollPane subjectScrollPane = new JScrollPane();
        subjectScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        subjectsTab.add(subjectScrollPane, BorderLayout.CENTER);

        subjectListContent = new JList();
        subjectListContent.setCellRenderer(new CheckboxListRenderer());
        subjectListContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        subjectListContent.addMouseListener(checkboxListItemClickHandler);
        subjectScrollPane.setViewportView(subjectListContent);
        
        JPanel axisFooter = new JPanel();
        axisFooter.setLayout(new BorderLayout(0, 0));
        AxisMemberPanel.add(axisFooter, BorderLayout.SOUTH);

        JButton btnExport = new JButton("Export");
        btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportProgressBar.setVisible(true);
                JFileChooser saveDialog = new JFileChooser();
                int retval = saveDialog.showSaveDialog(new JFrame());
                if (retval == JFileChooser.APPROVE_OPTION) {
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
        
        JPanel Separator = new JPanel();
        AxisMemberPanel.add(Separator, BorderLayout.WEST);
        JPanel filePanel = new JPanel();
        GridBagConstraints gbc_filePanel = new GridBagConstraints();
        gbc_filePanel.fill = GridBagConstraints.BOTH;
        gbc_filePanel.gridx = 0;
        gbc_filePanel.gridy = 0;
        Center.add(filePanel, gbc_filePanel);
        filePanel.setLayout(new BorderLayout(0, 0));
        JPanel fileHeader = new JPanel();
        fileHeader.setLayout(new BorderLayout(0, 0));
        filePanel.add(fileHeader, BorderLayout.NORTH);
        
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

        fileHeader.add(addFilesButton, BorderLayout.WEST);
        addFileProgressBar = new JProgressBar();
        addFileProgressBar.setIndeterminate(true);
        addFileProgressBar.setVisible(false);
        addFileProgressBar.setStringPainted(true);
        fileHeader.add(addFileProgressBar, BorderLayout.EAST);
        
        JPanel fileListWrapper = new JPanel();
        filePanel.add(fileListWrapper, BorderLayout.CENTER);
        fileListWrapper.setLayout(new GridLayout(0, 1, 0, 0));
        JScrollPane fileList = new JScrollPane();
        fileList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        fileList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fileListWrapper.add(fileList);
        fileListContent = new JPanel();
        fileListContent.setLayout(new GridLayout(0, 1, 0, 0));
        fileList.setViewportView(fileListContent);
        JPanel OutputWrapper = new JPanel();
        filePanel.add(OutputWrapper, BorderLayout.SOUTH);
        OutputWrapper.setLayout(new BorderLayout(0, 0));
        JPanel OutputSpacer = new JPanel();
        OutputWrapper.add(OutputSpacer, BorderLayout.CENTER);
        JPanel OutputContainer = new JPanel();
        OutputWrapper.add(OutputContainer, BorderLayout.SOUTH);
        OutputContainer.setLayout(new BorderLayout(0, 0));
        JPanel OutputFormat = new JPanel();
        OutputContainer.add(OutputFormat, BorderLayout.NORTH);
        JLabel OutputHeaderText = new JLabel("Output Format");
        OutputFormat.add(OutputHeaderText);
        JPanel TableContainer = new JPanel();
        OutputContainer.add(TableContainer, BorderLayout.CENTER);
        JPanel mockTable = new JPanel();
        mockTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        TableContainer.add(mockTable);
        mockTable.setLayout(new GridLayout(4, 4, 0, 0));
        JPanel SwapContainer = new JPanel();
        mockTable.add(SwapContainer);
        JButton swapAxisButton = new JButton("Swap");
        swapAxisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.swapAxes();
                updateOutputTable();
                frame.revalidate();
                frame.repaint();
            }
        });
        SwapContainer.add(swapAxisButton);

        for (JPanel p: columnHeads) {
            mockTable.add(p);
        }/*
        JPanel ColHead1 = new JPanel();
        mockTable.add(ColHead1);
        JLabel lblVerticleAxisName = new JLabel("Stimulus");
        ColHead1.add(lblVerticleAxisName);
        JPanel ColHead2 = new JPanel();
        mockTable.add(ColHead2);
        JLabel label_1 = new JLabel("Stimulus");
        ColHead2.add(label_1);
        JPanel ColHead3 = new JPanel();
        mockTable.add(ColHead3);
        JLabel label_10 = new JLabel("Stimulus");
        ColHead3.add(label_10);
        */
        JPanel dataPanel = new JPanel();
        dataPanel.add(new JLabel("- - - - - - - -"));
        for (JPanel p: rowHeads) {
            mockTable.add(p);
            mockTable.add(new JPanel());
            mockTable.add(new JPanel());
            mockTable.add(new JPanel());
        }
        /*
        JPanel RowHead1 = new JPanel();
        mockTable.add(RowHead1);
        JLabel lblSubject = new JLabel("Subject");
        RowHead1.add(lblSubject);
        JPanel DataCell1 = new JPanel();
        mockTable.add(DataCell1);
        JLabel label = new JLabel("- - - - - - - -");
        DataCell1.add(label);
        JPanel DataCell2 = new JPanel();
        mockTable.add(DataCell2);
        JLabel label_2 = new JLabel("- - - - - - - -");
        DataCell2.add(label_2);
        JPanel DataCell3 = new JPanel();
        mockTable.add(DataCell3);
        JLabel label_3 = new JLabel("- - - - - - - -");
        DataCell3.add(label_3);
        JPanel RowHead2 = new JPanel();
        mockTable.add(RowHead2);
        JLabel label_11 = new JLabel("Subject");
        RowHead2.add(label_11);
        JPanel DataCell4 = new JPanel();
        mockTable.add(DataCell4);
        JLabel label_6 = new JLabel("- - - - - - - -");
        DataCell4.add(label_6);
        JPanel DataCell5 = new JPanel();
        mockTable.add(DataCell5);
        JLabel label_5 = new JLabel("- - - - - - - -");
        DataCell5.add(label_5);
        JPanel DataCell6 = new JPanel();
        mockTable.add(DataCell6);
        JLabel label_4 = new JLabel("- - - - - - - -");
        DataCell6.add(label_4);
        JPanel RowHead3 = new JPanel();
        mockTable.add(RowHead3);
        JLabel label_12 = new JLabel("Subject");
        RowHead3.add(label_12);
        JPanel DataCell7 = new JPanel();
        mockTable.add(DataCell7);
        JLabel label_7 = new JLabel("- - - - - - - -");
        DataCell7.add(label_7);
        JPanel DataCell8 = new JPanel();
        mockTable.add(DataCell8);
        JLabel label_8 = new JLabel("- - - - - - - -");
        DataCell8.add(label_8);
        JPanel DataCell9 = new JPanel();
        mockTable.add(DataCell9);
        JLabel label_9 = new JLabel("- - - - - - - -");
        DataCell9.add(label_9);
        */
        JPanel TabContainer = new JPanel();
        OutputContainer.add(TabContainer, BorderLayout.SOUTH);
        JComboBox comboBox = new JComboBox();
        TabContainer.add(comboBox);
        comboBox.setModel(new DefaultComboBoxModel(new DataType[] {DataType.STATISTIC, DataType.STIMULUS, DataType.SUBJECT}));
        comboBox.setSelectedIndex(0);
        comboBox.setMaximumRowCount(3);
        JPanel Footer = new JPanel();
        frame.getContentPane().add(Footer, BorderLayout.SOUTH);
        JPanel LeftSpacer = new JPanel();
        frame.getContentPane().add(LeftSpacer, BorderLayout.WEST);
        JPanel RightSpacer = new JPanel();
        frame.getContentPane().add(RightSpacer, BorderLayout.EAST);

        frame.setVisible(true);

    }


    JPanel makeNewfilePanel(Map.Entry<String, String> pair) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        final String filename = pair.getKey();
        final String subject = pair.getValue();
        label.setText(filename);
        panel.add(label, BorderLayout.CENTER);
        final JButton deleteButton = new JButton();
        deleteButton.setText("x");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: consider putting this work in separate thread, add loading bar
                //TODO: actually support this on backend
                /*
                model.remove(filename, subject);
                update();
                frame.revalidate();
                frame.repaint();
                */
            }
        });
        panel.add(deleteButton, BorderLayout.EAST);
        return panel;
    }

    void update() {
        fileListContent.removeAll();
        for (Map.Entry<String, String> pair: model.getFiles().entrySet()) {
            fileListContent.add(makeNewfilePanel(pair), BorderLayout.NORTH);
        }
        updateData();
    }

    void updateData() {
        subjectListContent.setModel(model.getListModel(dataGroupType, DataType.SUBJECT));
        stimulusListContent.setModel(model.getListModel(dataGroupType, DataType.STIMULUS));
        statisticListContent.setModel(model.getListModel(dataGroupType, DataType.STATISTIC));
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
                    update();
                    frame.revalidate();
                    frame.repaint();
                } catch (IOException ex) {
                    // TODO: is this the right way to do it?
                    System.out.println(ex);
                    System.exit(1);
                }
            }
            return null;
        }

        protected void done() {
            //TODO: THIS IS BAD BAD BAD BAD BAD.  PLEASE FIND A WAY TO DO IT RIGHT
            // turn off progress bar
            addFileProgressBar.setVisible(false);
        }
    }

    class ExportWorker extends SwingWorker {

        final JFileChooser fc;

        ExportWorker(JFileChooser fc) {
            this.fc = fc;
        }

        protected Object doInBackground() {
            model.export(dataGroupType, fc.getSelectedFile());
            return null;
        }

        protected void done() {
            //TODO: THIS IS BAD BAD BAD BAD BAD.  PLEASE FIND A WAY TO DO IT RIGHT
            // turn off progress bar
            exportProgressBar.setVisible(false);
        }
    }
}
