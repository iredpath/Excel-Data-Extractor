package edu.neu.EDE.gui;

import edu.neu.EDE.data_structs.DataType;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Ian on 3/24/15.
 */
public class GuiView {

    private GuiModel model;
    private JFrame frame;
    private JPanel fileListContent;
    private JPanel subjectListContent;
    private JPanel stimulusListContent;
    private JPanel statisticListContent;
    private JRadioButton lookZoneButton;
    private JRadioButton slideMetricButton;
    private JProgressBar addFileProgressBar;
    private JProgressBar exportProgressBar;

    public GuiView(GuiModel m) {
        this.model = m;
    }

    void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1024, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 3, 10, 10));

        JPanel filePanel = new JPanel();
        frame.getContentPane().add(filePanel);
        filePanel.setLayout(new BorderLayout(0, 0));

        JPanel fileHeader = new JPanel();
        filePanel.add(fileHeader, BorderLayout.NORTH);

        JLabel fileHeaderText = new JLabel("Files");
        fileHeader.add(fileHeaderText);

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

        //GroupLayout gl_FileListContent = new GroupLayout(fileListContent);
        //fileListContent.setLayout(gl_FileListContent);

        JPanel fileFooter = new JPanel();
        filePanel.add(fileFooter, BorderLayout.SOUTH);
        fileFooter.setLayout(new BorderLayout(0, 0));

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

        fileFooter.add(addFilesButton, BorderLayout.WEST);
        addFileProgressBar = new JProgressBar();
        addFileProgressBar.setIndeterminate(true);
        addFileProgressBar.setVisible(false);
        addFileProgressBar.setStringPainted(true);
        fileFooter.add(addFileProgressBar, BorderLayout.EAST);






        JPanel AxisMemberPanel = new JPanel();
        frame.getContentPane().add(AxisMemberPanel);
        AxisMemberPanel.setLayout(new BorderLayout(0, 0));

        JPanel AxisHeader = new JPanel();
        AxisHeader.setLayout(new GridLayout(0, 1, 0, 0));
        AxisMemberPanel.add(AxisHeader, BorderLayout.NORTH);

        ButtonGroup lookZoneOrSlideMetric = new ButtonGroup();
        lookZoneButton = new JRadioButton("Look zone data");
        lookZoneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton b = (AbstractButton) e.getSource();
                if (b.isSelected()) {
                    updateData("lookZone");
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
                    updateData("slideMetric");
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
        slideMetricButton.setSelected(true);
        lookZoneOrSlideMetric.add(lookZoneButton);
        lookZoneOrSlideMetric.add(slideMetricButton);
        AxisHeader.add(slideMetricButton);
        AxisHeader.add(lookZoneButton);

        JLabel AxisHeaderText = new JLabel("Axis Members");
        AxisHeader.add(AxisHeaderText);

        JPanel AxisList = new JPanel();
        AxisMemberPanel.add(AxisList, BorderLayout.CENTER);
        AxisList.setLayout(new BorderLayout(0, 0));

        JTabbedPane AxisTabs = new JTabbedPane(JTabbedPane.TOP);
        AxisList.add(AxisTabs, BorderLayout.CENTER);

        JPanel StatisticsTab = new JPanel();
        AxisTabs.addTab("Statistics", null, StatisticsTab, null);
        AxisTabs.setEnabledAt(0, true);
        StatisticsTab.setLayout(new BorderLayout(0, 0));

        JScrollPane StatisticsList = new JScrollPane();
        //StatisticsList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        StatisticsList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        StatisticsTab.add(StatisticsList, BorderLayout.CENTER);

        statisticListContent = new JPanel();
        StatisticsList.setViewportView(statisticListContent);

        JPanel Stimuli = new JPanel();
        AxisTabs.addTab("Stimuli", null, Stimuli, null);
        AxisTabs.setEnabledAt(1, true);
        Stimuli.setLayout(new BorderLayout(0, 0));

        JScrollPane StimuliList = new JScrollPane();
        StimuliList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //StimuliList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Stimuli.add(StimuliList, BorderLayout.CENTER);

        stimulusListContent = new JPanel();
        StimuliList.setViewportView(stimulusListContent);

        JPanel Subjects = new JPanel();
        AxisTabs.addTab("Subjects", null, Subjects, null);
        AxisTabs.setEnabledAt(2, true);
        Subjects.setLayout(new BorderLayout(0, 0));

        JScrollPane SubjectList = new JScrollPane();
        SubjectList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //SubjectList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Subjects.add(SubjectList, BorderLayout.CENTER);

        subjectListContent = new JPanel();
        SubjectList.setViewportView(subjectListContent);

        JPanel AxisFooter = new JPanel();
        AxisMemberPanel.add(AxisFooter, BorderLayout.SOUTH);

        JPanel OutputPanel = new JPanel();
        frame.getContentPane().add(OutputPanel);
        OutputPanel.setLayout(new BorderLayout(0, 0));

        JPanel OutputHeader = new JPanel();
        OutputPanel.add(OutputHeader, BorderLayout.NORTH);

        JLabel OutputHeaderText = new JLabel("Output Format");
        OutputHeader.add(OutputHeaderText);

        JPanel OutputWrapper = new JPanel();
        OutputPanel.add(OutputWrapper, BorderLayout.CENTER);
        OutputWrapper.setLayout(new BorderLayout(0, 0));

        JPanel AxisConfigurationWrapper = new JPanel();
        OutputWrapper.add(AxisConfigurationWrapper);

        JPanel MockTable = new JPanel();
        MockTable.setLayout(new BorderLayout(0, 0));

        JPanel TableHeader = new JPanel();
        MockTable.add(TableHeader, BorderLayout.NORTH);
        TableHeader.setLayout(new BorderLayout(0, 0));

        JPanel SwapContainer = new JPanel();
        TableHeader.add(SwapContainer, BorderLayout.WEST);

        JButton btnSwap = new JButton("Swap");
        SwapContainer.add(btnSwap);

        JPanel ColumnTitles = new JPanel();
        TableHeader.add(ColumnTitles, BorderLayout.CENTER);

        JLabel ColumnTitleLabel = new JLabel("Axis Label");
        ColumnTitles.add(ColumnTitleLabel);

        JPanel Data = new JPanel();
        MockTable.add(Data, BorderLayout.CENTER);

        JLabel label_1 = new JLabel("- - - - - - - - -");
        Data.add(label_1);

        JPanel RowTitles = new JPanel();
        MockTable.add(RowTitles, BorderLayout.WEST);

        JLabel lblVerticleAxisName = new JLabel("Axis Label");
        RowTitles.add(lblVerticleAxisName);

        JPanel Tab = new JPanel();

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Statistic", "Stimuli", "Subject"}));
        comboBox.setSelectedIndex(0);
        comboBox.setMaximumRowCount(3);
        Tab.add(comboBox);
        GroupLayout gl_AxisConfigurationWrapper = new GroupLayout(AxisConfigurationWrapper);
        gl_AxisConfigurationWrapper.setHorizontalGroup(
                gl_AxisConfigurationWrapper.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, gl_AxisConfigurationWrapper.createSequentialGroup()
                                .addContainerGap(102, Short.MAX_VALUE)
                                .addGroup(gl_AxisConfigurationWrapper.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(Tab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(MockTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(89))
        );
        gl_AxisConfigurationWrapper.setVerticalGroup(
                gl_AxisConfigurationWrapper.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_AxisConfigurationWrapper.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MockTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Tab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(351, Short.MAX_VALUE))
        );
        AxisConfigurationWrapper.setLayout(gl_AxisConfigurationWrapper);

        JPanel OutputFooter = new JPanel();
        OutputFooter.setLayout(new BorderLayout(0, 0));
        OutputPanel.add(OutputFooter, BorderLayout.SOUTH);

        JButton btnExport = new JButton("Export");
        btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportProgressBar.setVisible(true);
                ExportWorker exportWorker = new ExportWorker();
                exportWorker.execute();
            }
        });
        OutputFooter.add(btnExport, BorderLayout.EAST);

        exportProgressBar = new JProgressBar();
        exportProgressBar.setIndeterminate(true);
        exportProgressBar.setVisible(false);
        exportProgressBar.setStringPainted(true);
        OutputFooter.add(exportProgressBar, BorderLayout.WEST);

        JPanel RightSpacer = new JPanel();
        OutputPanel.add(RightSpacer, BorderLayout.EAST);


        subjectListContent.setLayout(new GridLayout(0, 1, 0, 0));
        stimulusListContent.setLayout(new GridLayout(0, 1, 0, 0));
        statisticListContent.setLayout(new GridLayout(0, 1, 0, 0));

        frame.setVisible(true);
    }


    JPanel makeNewFilePanel(Map.Entry<String, String> pair) {
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
            fileListContent.add(makeNewFilePanel(pair), BorderLayout.NORTH);
        }
        String which = lookZoneButton.isSelected() ? "lookZone" : "slideMetric";
        updateData(which);
    }

    JPanel makeNewDataTypePanel(final String name, final DataType type) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(name);
        panel.add(label, BorderLayout.WEST);
        JCheckBox checkbox = new JCheckBox();
        if (!model.getDataFor(lookZoneButton.isSelected() ? "lookZone" : "slideMetric").get(type).contains(name)) {
            checkbox.setSelected(true);
        }
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton b = (AbstractButton) e.getSource();
                if (b.isSelected()) {
                    model.setAsSelected(name, type, lookZoneButton.isSelected() ? "lookZone" : "slideMetric");
                } else {
                    model.setAsDeselected(name, type, lookZoneButton.isSelected() ? "lookZone" : "slideMetric");
                }
            }
        });
        panel.add(checkbox, BorderLayout.EAST);
        return panel;
    }

    void updateData(String whichData) {
        subjectListContent.removeAll();
        for (String subject: model.getSubjects(whichData)) {
            subjectListContent.add(makeNewDataTypePanel(subject, DataType.SUBJECT), BorderLayout.NORTH);
        }
        stimulusListContent.removeAll();
        for (String stimulus: model.getStimuli(whichData)) {
            stimulusListContent.add(makeNewDataTypePanel(stimulus, DataType.STIMULUS), BorderLayout.NORTH);
        }
        statisticListContent.removeAll();
        for (String statistic: model.getStatistics(whichData)) {
            statisticListContent.add(makeNewDataTypePanel(statistic, DataType.STATISTIC), BorderLayout.NORTH);
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

        protected Object doInBackground() {
            model.export(lookZoneButton.isSelected() ? "lookZone" : "slideMetric");
            return null;
        }

        protected void done() {
            //TODO: THIS IS BAD BAD BAD BAD BAD.  PLEASE FIND A WAY TO DO IT RIGHT
            // turn off progress bar
            exportProgressBar.setVisible(false);
        }
    }
}
