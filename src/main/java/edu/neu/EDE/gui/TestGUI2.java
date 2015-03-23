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
        
        JPanel File = new JPanel();
        
        JPanel panel = new JPanel();
        
        JLabel label = new JLabel("File Name Goes Here");
        
        JButton button = new JButton("Remove");
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button)
                    .addContainerGap())
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel.setLayout(gl_panel);
        GroupLayout gl_FileListContent = new GroupLayout(FileListContent);
        gl_FileListContent.setHorizontalGroup(
            gl_FileListContent.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_FileListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_FileListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(File, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        gl_FileListContent.setVerticalGroup(
            gl_FileListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_FileListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(File, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(527, Short.MAX_VALUE))
        );
        
        JLabel FileNameText = new JLabel("File Name Goes Here");
        
        JButton RemoveFileButton = new JButton("Remove");
        GroupLayout gl_File = new GroupLayout(File);
        gl_File.setHorizontalGroup(
            gl_File.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_File.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(FileNameText, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(RemoveFileButton)
                    .addContainerGap())
        );
        gl_File.setVerticalGroup(
            gl_File.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_File.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_File.createParallelGroup(Alignment.BASELINE)
                        .addComponent(RemoveFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FileNameText, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        File.setLayout(gl_File);
        FileListContent.setLayout(gl_FileListContent);
        
        JPanel FileFooter = new JPanel();
        FilePanel.add(FileFooter, BorderLayout.SOUTH);
        FileFooter.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_6 = new JPanel();
        FileFooter.add(panel_6, BorderLayout.CENTER);
        
        JButton AddFilesButton = new JButton("Add Files");
        panel_6.add(AddFilesButton);
        
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
        
        JPanel StatisticListContent = new JPanel();
        StatisticsList.setViewportView(StatisticListContent);
        
        JPanel Statistic = new JPanel();
        
        JCheckBox StatisticCheckBox = new JCheckBox("Statistic Name");
        GroupLayout gl_Statistic = new GroupLayout(Statistic);
        gl_Statistic.setHorizontalGroup(
            gl_Statistic.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Statistic.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StatisticCheckBox, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Statistic.setVerticalGroup(
            gl_Statistic.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Statistic.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(StatisticCheckBox, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        Statistic.setLayout(gl_Statistic);
        
        JPanel panel_1 = new JPanel();
        
        JCheckBox checkBox = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_1.setLayout(gl_panel_1);
        GroupLayout gl_StatisticListContent = new GroupLayout(StatisticListContent);
        gl_StatisticListContent.setHorizontalGroup(
            gl_StatisticListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_StatisticListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_StatisticListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(Statistic, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_StatisticListContent.setVerticalGroup(
            gl_StatisticListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_StatisticListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Statistic, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(529, Short.MAX_VALUE))
        );
        StatisticListContent.setLayout(gl_StatisticListContent);
        
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
        
        JPanel Stimuli_1 = new JPanel();
        
        JCheckBox chckbxStimulusName = new JCheckBox("Stimulus Name");
        GroupLayout gl_Stimuli_1 = new GroupLayout(Stimuli_1);
        gl_Stimuli_1.setHorizontalGroup(
            gl_Stimuli_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Stimuli_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxStimulusName, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Stimuli_1.setVerticalGroup(
            gl_Stimuli_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Stimuli_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxStimulusName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Stimuli_1.setLayout(gl_Stimuli_1);
        
        JPanel Stimuli_2 = new JPanel();
        
        JCheckBox chckbxStimulusName_1 = new JCheckBox("Stimulus Name");
        GroupLayout gl_Stimuli_2 = new GroupLayout(Stimuli_2);
        gl_Stimuli_2.setHorizontalGroup(
            gl_Stimuli_2.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Stimuli_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxStimulusName_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Stimuli_2.setVerticalGroup(
            gl_Stimuli_2.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Stimuli_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxStimulusName_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Stimuli_2.setLayout(gl_Stimuli_2);
        GroupLayout gl_StimuliListContent = new GroupLayout(StimuliListContent);
        gl_StimuliListContent.setHorizontalGroup(
            gl_StimuliListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 305, Short.MAX_VALUE)
                .addGroup(gl_StimuliListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_StimuliListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(Stimuli_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Stimuli_2, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_StimuliListContent.setVerticalGroup(
            gl_StimuliListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 618, Short.MAX_VALUE)
                .addGroup(gl_StimuliListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Stimuli_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(Stimuli_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
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
        
        JPanel Subject_1 = new JPanel();
        
        JCheckBox chckbxSubjectName = new JCheckBox("Subject Name");
        GroupLayout gl_Subject_1 = new GroupLayout(Subject_1);
        gl_Subject_1.setHorizontalGroup(
            gl_Subject_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Subject_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxSubjectName, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Subject_1.setVerticalGroup(
            gl_Subject_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Subject_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxSubjectName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Subject_1.setLayout(gl_Subject_1);
        
        JPanel Subject_2 = new JPanel();
        
        JCheckBox chckbxSubjectName_1 = new JCheckBox("Subject Name");
        GroupLayout gl_Subject_2 = new GroupLayout(Subject_2);
        gl_Subject_2.setHorizontalGroup(
            gl_Subject_2.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_Subject_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxSubjectName_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_Subject_2.setVerticalGroup(
            gl_Subject_2.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_Subject_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chckbxSubjectName_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40))
        );
        Subject_2.setLayout(gl_Subject_2);
        GroupLayout gl_SubjectListContent = new GroupLayout(SubjectListContent);
        gl_SubjectListContent.setHorizontalGroup(
            gl_SubjectListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 305, Short.MAX_VALUE)
                .addGroup(gl_SubjectListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_SubjectListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(Subject_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Subject_2, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_SubjectListContent.setVerticalGroup(
            gl_SubjectListContent.createParallelGroup(Alignment.LEADING)
                .addGap(0, 618, Short.MAX_VALUE)
                .addGroup(gl_SubjectListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Subject_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(Subject_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
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
                    .addContainerGap(102, Short.MAX_VALUE)
                    .addGroup(gl_AxisConfigurationWrapper.createParallelGroup(Alignment.LEADING)
                        .addComponent(Tab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(MockTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(89))
        );
        gl_AxisConfigurationWrapper.setVerticalGroup(
            gl_AxisConfigurationWrapper.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_AxisConfigurationWrapper.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MockTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(Tab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(351, Short.MAX_VALUE))
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
