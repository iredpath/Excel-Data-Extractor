package edu.neu.EDE.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class TestGUI2 {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestGUI2 window = new TestGUI2();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public TestGUI2() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1024, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 3, 10, 10));
        
        JPanel FilePanel = new JPanel();
        frame.getContentPane().add(FilePanel);
        FilePanel.setLayout(new BorderLayout(0, 0));
        
        JPanel FileHeader = new JPanel();
        FilePanel.add(FileHeader, BorderLayout.NORTH);
        
        JLabel FileHeaderText = new JLabel("Files");
        FileHeader.add(FileHeaderText);
        
        JPanel LeftSpacer = new JPanel();
        FilePanel.add(LeftSpacer, BorderLayout.WEST);
        
        JPanel FileListWrapper = new JPanel();
        FilePanel.add(FileListWrapper, BorderLayout.CENTER);
        FileListWrapper.setLayout(new GridLayout(0, 1, 0, 0));
        
        JScrollPane FileList = new JScrollPane();
        FileList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        FileList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        FileListWrapper.add(FileList);
        
        JPanel FileListContent = new JPanel();
        FileList.setViewportView(FileListContent);
        
        JPanel File_1 = new JPanel();
        
        JPanel File_0 = new JPanel();
        
        JLabel FileNameText_0 = new JLabel("File Name Goes Here");
        
        JButton RemoveFileButton_0 = new JButton("Remove");
        GroupLayout gl_File_0 = new GroupLayout(File_0);
        gl_File_0.setHorizontalGroup(
            gl_File_0.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_File_0.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(FileNameText_0, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(RemoveFileButton_0)
                    .addContainerGap())
        );
        gl_File_0.setVerticalGroup(
            gl_File_0.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_File_0.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_File_0.createParallelGroup(Alignment.BASELINE)
                        .addComponent(RemoveFileButton_0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FileNameText_0, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        File_0.setLayout(gl_File_0);
        GroupLayout gl_FileListContent = new GroupLayout(FileListContent);
        gl_FileListContent.setHorizontalGroup(
            gl_FileListContent.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_FileListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_FileListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(File_1, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(File_0, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_FileListContent.setVerticalGroup(
            gl_FileListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_FileListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(File_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(File_0, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(527, Short.MAX_VALUE))
        );
        
        JLabel FileNameText_1 = new JLabel("File Name Goes Here");
        
        JButton RemoveFileButton_1 = new JButton("Remove");
        GroupLayout gl_File_1 = new GroupLayout(File_1);
        gl_File_1.setHorizontalGroup(
            gl_File_1.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_File_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(FileNameText_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(RemoveFileButton_1)
                    .addContainerGap())
        );
        gl_File_1.setVerticalGroup(
            gl_File_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_File_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_File_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(RemoveFileButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FileNameText_1, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        File_1.setLayout(gl_File_1);
        FileListContent.setLayout(gl_FileListContent);
        
        JPanel FileFooter = new JPanel();
        FilePanel.add(FileFooter, BorderLayout.SOUTH);
        FileFooter.setLayout(new BorderLayout(0, 0));
        
        JPanel FileFooterContainer = new JPanel();
        FileFooter.add(FileFooterContainer, BorderLayout.CENTER);
        
        JButton AddFilesButton = new JButton("Add Files");
        FileFooterContainer.add(AddFilesButton);
        
        JPanel AxisMemberPanel = new JPanel();
        frame.getContentPane().add(AxisMemberPanel);
        AxisMemberPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel AxisHeader = new JPanel();
        AxisMemberPanel.add(AxisHeader, BorderLayout.NORTH);
        
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
        StatisticsList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        StatisticsList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        StatisticsTab.add(StatisticsList, BorderLayout.CENTER);
        
        JPanel StatisticsListContent = new JPanel();
        StatisticsList.setViewportView(StatisticsListContent);
        
        JPanel Statistic_0 = new JPanel();
        
        JCheckBox StatisticName_0 = new JCheckBox("Statistic Name");
        StatisticName_0.setSelected(true);
        GroupLayout gl_Statistic_0 = new GroupLayout(Statistic_0);
        gl_Statistic_0.setHorizontalGroup(
            gl_Statistic_0.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Statistic_0.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StatisticName_0, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Statistic_0.setVerticalGroup(
            gl_Statistic_0.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Statistic_0.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StatisticName_0, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        Statistic_0.setLayout(gl_Statistic_0);
        
        JPanel Statistic_1 = new JPanel();
        
        JCheckBox StatisticName_1 = new JCheckBox("Statistic Name");
        StatisticName_1.setSelected(true);
        GroupLayout gl_Statistic_1 = new GroupLayout(Statistic_1);
        gl_Statistic_1.setHorizontalGroup(
            gl_Statistic_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Statistic_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StatisticName_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Statistic_1.setVerticalGroup(
            gl_Statistic_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Statistic_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StatisticName_1, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        Statistic_1.setLayout(gl_Statistic_1);
        GroupLayout gl_StatisticsListContent = new GroupLayout(StatisticsListContent);
        gl_StatisticsListContent.setHorizontalGroup(
            gl_StatisticsListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_StatisticsListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_StatisticsListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(Statistic_0, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Statistic_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_StatisticsListContent.setVerticalGroup(
            gl_StatisticsListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_StatisticsListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Statistic_0, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(Statistic_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(529, Short.MAX_VALUE))
        );
        StatisticsListContent.setLayout(gl_StatisticsListContent);
        
        JPanel Stimuli = new JPanel();
        AxisTabs.addTab("Stimuli", null, Stimuli, null);
        AxisTabs.setEnabledAt(1, true);
        Stimuli.setLayout(new BorderLayout(0, 0));
        
        JScrollPane StimuliList = new JScrollPane();
        StimuliList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        StimuliList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Stimuli.add(StimuliList, BorderLayout.CENTER);
        
        JPanel StimuliListContent = new JPanel();
        StimuliList.setViewportView(StimuliListContent);
        
        JPanel Stimulus_0 = new JPanel();
        
        JCheckBox StimulusName_0 = new JCheckBox("Stimulus Name");
        StimulusName_0.setSelected(true);
        GroupLayout gl_Stimulus_0 = new GroupLayout(Stimulus_0);
        gl_Stimulus_0.setHorizontalGroup(
            gl_Stimulus_0.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Stimulus_0.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StimulusName_0, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Stimulus_0.setVerticalGroup(
            gl_Stimulus_0.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Stimulus_0.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StimulusName_0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Stimulus_0.setLayout(gl_Stimulus_0);
        
        JPanel Stimulus_1 = new JPanel();
        
        JCheckBox StimulusName_1 = new JCheckBox("Stimulus Name");
        StimulusName_1.setSelected(true);
        GroupLayout gl_Stimulus_1 = new GroupLayout(Stimulus_1);
        gl_Stimulus_1.setHorizontalGroup(
            gl_Stimulus_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Stimulus_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StimulusName_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Stimulus_1.setVerticalGroup(
            gl_Stimulus_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Stimulus_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StimulusName_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Stimulus_1.setLayout(gl_Stimulus_1);
        GroupLayout gl_StimuliListContent = new GroupLayout(StimuliListContent);
        gl_StimuliListContent.setHorizontalGroup(
            gl_StimuliListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 305, Short.MAX_VALUE)
                .addGroup(gl_StimuliListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_StimuliListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(Stimulus_0, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Stimulus_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_StimuliListContent.setVerticalGroup(
            gl_StimuliListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 618, Short.MAX_VALUE)
                .addGroup(gl_StimuliListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Stimulus_0, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(Stimulus_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(529, Short.MAX_VALUE))
        );
        StimuliListContent.setLayout(gl_StimuliListContent);
        
        JPanel Subjects = new JPanel();
        AxisTabs.addTab("Subjects", null, Subjects, null);
        AxisTabs.setEnabledAt(2, true);
        Subjects.setLayout(new BorderLayout(0, 0));
        
        JScrollPane SubjectList = new JScrollPane();
        SubjectList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SubjectList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Subjects.add(SubjectList, BorderLayout.CENTER);
        
        JPanel SubjectListContent = new JPanel();
        SubjectList.setViewportView(SubjectListContent);
        
        JPanel Subject_0 = new JPanel();
        
        JCheckBox SubjectName_0 = new JCheckBox("Subject Name");
        SubjectName_0.setSelected(true);
        GroupLayout gl_Subject_0 = new GroupLayout(Subject_0);
        gl_Subject_0.setHorizontalGroup(
            gl_Subject_0.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Subject_0.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(SubjectName_0, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Subject_0.setVerticalGroup(
            gl_Subject_0.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Subject_0.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(SubjectName_0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Subject_0.setLayout(gl_Subject_0);
        
        JPanel Subject_1 = new JPanel();
        
        JCheckBox SubjectName_1 = new JCheckBox("Subject Name");
        SubjectName_1.setSelected(true);
        GroupLayout gl_Subject_1 = new GroupLayout(Subject_1);
        gl_Subject_1.setHorizontalGroup(
            gl_Subject_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Subject_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(SubjectName_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Subject_1.setVerticalGroup(
            gl_Subject_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Subject_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(SubjectName_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Subject_1.setLayout(gl_Subject_1);
        GroupLayout gl_SubjectListContent = new GroupLayout(SubjectListContent);
        gl_SubjectListContent.setHorizontalGroup(
            gl_SubjectListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 305, Short.MAX_VALUE)
                .addGroup(gl_SubjectListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_SubjectListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(Subject_0, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Subject_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_SubjectListContent.setVerticalGroup(
            gl_SubjectListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 618, Short.MAX_VALUE)
                .addGroup(gl_SubjectListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Subject_0, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(Subject_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(529, Short.MAX_VALUE))
        );
        SubjectListContent.setLayout(gl_SubjectListContent);
        
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
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Statistic", "Stimuli", "Subject"}));
        comboBox.setSelectedIndex(0);
        comboBox.setMaximumRowCount(3);
        Tab.add(comboBox);
        GroupLayout gl_AxisConfigurationWrapper = new GroupLayout(AxisConfigurationWrapper);
        gl_AxisConfigurationWrapper.setHorizontalGroup(
            gl_AxisConfigurationWrapper.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_AxisConfigurationWrapper.createSequentialGroup()
                    .addGap(102)
                    .addComponent(MockTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(89))
                .addGroup(Alignment.TRAILING, gl_AxisConfigurationWrapper.createSequentialGroup()
                    .addGap(129)
                    .addComponent(Tab, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(117))
        );
        gl_AxisConfigurationWrapper.setVerticalGroup(
            gl_AxisConfigurationWrapper.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_AxisConfigurationWrapper.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MockTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(Tab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(521, Short.MAX_VALUE))
        );
        AxisConfigurationWrapper.setLayout(gl_AxisConfigurationWrapper);
        
        JPanel OutputFooter = new JPanel();
        OutputPanel.add(OutputFooter, BorderLayout.SOUTH);
        
        JButton btnExport = new JButton("Export");
        OutputFooter.add(btnExport);
        
        JPanel RightSpacer = new JPanel();
        OutputPanel.add(RightSpacer, BorderLayout.EAST);
    }
}
