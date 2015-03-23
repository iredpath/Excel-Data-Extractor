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
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;

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
        
        JPanel panel_3 = new JPanel();
        
        JLabel label_1 = new JLabel("File Name Goes Here");
        
        JButton button_1 = new JButton("Remove");
        GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        gl_panel_3.setHorizontalGroup(
            gl_panel_3.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_3.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_1)
                    .addContainerGap())
        );
        gl_panel_3.setVerticalGroup(
            gl_panel_3.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_3.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_1, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_3.setLayout(gl_panel_3);
        
        JPanel panel_4 = new JPanel();
        
        JLabel label_2 = new JLabel("File Name Goes Here");
        
        JButton button_2 = new JButton("Remove");
        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
            gl_panel_4.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_4.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_2, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_2)
                    .addContainerGap())
        );
        gl_panel_4.setVerticalGroup(
            gl_panel_4.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_4.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_2, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_4.setLayout(gl_panel_4);
        
        JPanel panel_7 = new JPanel();
        
        JLabel label_3 = new JLabel("File Name Goes Here");
        
        JButton button_3 = new JButton("Remove");
        GroupLayout gl_panel_7 = new GroupLayout(panel_7);
        gl_panel_7.setHorizontalGroup(
            gl_panel_7.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_7.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_3, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_3)
                    .addContainerGap())
        );
        gl_panel_7.setVerticalGroup(
            gl_panel_7.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_7.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_3, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_7.setLayout(gl_panel_7);
        
        JPanel panel_8 = new JPanel();
        
        JLabel label_4 = new JLabel("File Name Goes Here");
        
        JButton button_4 = new JButton("Remove");
        GroupLayout gl_panel_8 = new GroupLayout(panel_8);
        gl_panel_8.setHorizontalGroup(
            gl_panel_8.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_8.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_4, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_4)
                    .addContainerGap())
        );
        gl_panel_8.setVerticalGroup(
            gl_panel_8.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_8.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_4, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_8.setLayout(gl_panel_8);
        
        JPanel panel_9 = new JPanel();
        
        JLabel label_5 = new JLabel("File Name Goes Here");
        
        JButton button_5 = new JButton("Remove");
        GroupLayout gl_panel_9 = new GroupLayout(panel_9);
        gl_panel_9.setHorizontalGroup(
            gl_panel_9.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_9.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_5, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_5)
                    .addContainerGap())
        );
        gl_panel_9.setVerticalGroup(
            gl_panel_9.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_9.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_5, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_9.setLayout(gl_panel_9);
        
        JPanel panel_10 = new JPanel();
        
        JLabel label_6 = new JLabel("File Name Goes Here");
        
        JButton button_6 = new JButton("Remove");
        GroupLayout gl_panel_10 = new GroupLayout(panel_10);
        gl_panel_10.setHorizontalGroup(
            gl_panel_10.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_10.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_6, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_6)
                    .addContainerGap())
        );
        gl_panel_10.setVerticalGroup(
            gl_panel_10.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_10.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_6, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_10.setLayout(gl_panel_10);
        
        JPanel panel_11 = new JPanel();
        
        JLabel label_7 = new JLabel("File Name Goes Here");
        
        JButton button_7 = new JButton("Remove");
        GroupLayout gl_panel_11 = new GroupLayout(panel_11);
        gl_panel_11.setHorizontalGroup(
            gl_panel_11.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_11.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_7, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_7)
                    .addContainerGap())
        );
        gl_panel_11.setVerticalGroup(
            gl_panel_11.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_11.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_7, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_11.setLayout(gl_panel_11);
        
        JPanel panel_12 = new JPanel();
        
        JLabel label_8 = new JLabel("File Name Goes Here");
        
        JButton button_8 = new JButton("Remove");
        GroupLayout gl_panel_12 = new GroupLayout(panel_12);
        gl_panel_12.setHorizontalGroup(
            gl_panel_12.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_12.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_8, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_8)
                    .addContainerGap())
        );
        gl_panel_12.setVerticalGroup(
            gl_panel_12.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_12.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_8, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_12.setLayout(gl_panel_12);
        
        JPanel panel_13 = new JPanel();
        
        JLabel label_9 = new JLabel("File Name Goes Here");
        
        JButton button_9 = new JButton("Remove");
        GroupLayout gl_panel_13 = new GroupLayout(panel_13);
        gl_panel_13.setHorizontalGroup(
            gl_panel_13.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_13.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_9, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_9)
                    .addContainerGap())
        );
        gl_panel_13.setVerticalGroup(
            gl_panel_13.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_13.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_9, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_13.setLayout(gl_panel_13);
        
        JPanel panel_14 = new JPanel();
        
        JLabel label_10 = new JLabel("File Name Goes Here");
        
        JButton button_10 = new JButton("Remove");
        GroupLayout gl_panel_14 = new GroupLayout(panel_14);
        gl_panel_14.setHorizontalGroup(
            gl_panel_14.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 280, Short.MAX_VALUE)
                .addGroup(gl_panel_14.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label_10, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(button_10)
                    .addContainerGap())
        );
        gl_panel_14.setVerticalGroup(
            gl_panel_14.createParallelGroup(Alignment.LEADING)
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(gl_panel_14.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_14.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_10, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGap(149))
        );
        panel_14.setLayout(gl_panel_14);
        GroupLayout gl_FileListContent = new GroupLayout(FileListContent);
        gl_FileListContent.setHorizontalGroup(
            gl_FileListContent.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_FileListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_FileListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(File, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        gl_FileListContent.setVerticalGroup(
            gl_FileListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_FileListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(File, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE))
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
        
        JPanel panel_5 = new JPanel();
        
        JCheckBox checkBox_1 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_5 = new GroupLayout(panel_5);
        gl_panel_5.setHorizontalGroup(
            gl_panel_5.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_5.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_5.setVerticalGroup(
            gl_panel_5.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_5.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_1, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_5.setLayout(gl_panel_5);
        
        JPanel panel_15 = new JPanel();
        
        JCheckBox checkBox_2 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_15 = new GroupLayout(panel_15);
        gl_panel_15.setHorizontalGroup(
            gl_panel_15.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_15.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_2, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_15.setVerticalGroup(
            gl_panel_15.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_15.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_2, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_15.setLayout(gl_panel_15);
        
        JPanel panel_16 = new JPanel();
        
        JCheckBox checkBox_3 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_16 = new GroupLayout(panel_16);
        gl_panel_16.setHorizontalGroup(
            gl_panel_16.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_16.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_3, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_16.setVerticalGroup(
            gl_panel_16.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_16.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_3, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_16.setLayout(gl_panel_16);
        
        JPanel panel_17 = new JPanel();
        
        JCheckBox checkBox_4 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_17 = new GroupLayout(panel_17);
        gl_panel_17.setHorizontalGroup(
            gl_panel_17.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_17.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_4, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_17.setVerticalGroup(
            gl_panel_17.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_17.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_4, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_17.setLayout(gl_panel_17);
        
        JPanel panel_18 = new JPanel();
        
        JCheckBox checkBox_5 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_18 = new GroupLayout(panel_18);
        gl_panel_18.setHorizontalGroup(
            gl_panel_18.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_18.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_5, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_18.setVerticalGroup(
            gl_panel_18.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_18.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_5, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_18.setLayout(gl_panel_18);
        
        JPanel panel_19 = new JPanel();
        
        JCheckBox checkBox_6 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_19 = new GroupLayout(panel_19);
        gl_panel_19.setHorizontalGroup(
            gl_panel_19.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_19.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_6, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_19.setVerticalGroup(
            gl_panel_19.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_19.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_6, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_19.setLayout(gl_panel_19);
        
        JPanel panel_20 = new JPanel();
        
        JCheckBox checkBox_7 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_20 = new GroupLayout(panel_20);
        gl_panel_20.setHorizontalGroup(
            gl_panel_20.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_20.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_7, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_20.setVerticalGroup(
            gl_panel_20.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_20.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_7, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_20.setLayout(gl_panel_20);
        
        JPanel panel_21 = new JPanel();
        
        JCheckBox checkBox_8 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_21 = new GroupLayout(panel_21);
        gl_panel_21.setHorizontalGroup(
            gl_panel_21.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_21.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_8, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_21.setVerticalGroup(
            gl_panel_21.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_21.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_8, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_21.setLayout(gl_panel_21);
        
        JPanel panel_22 = new JPanel();
        
        JCheckBox checkBox_9 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_22 = new GroupLayout(panel_22);
        gl_panel_22.setHorizontalGroup(
            gl_panel_22.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_22.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_9, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_22.setVerticalGroup(
            gl_panel_22.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_22.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_9, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_22.setLayout(gl_panel_22);
        
        JPanel panel_23 = new JPanel();
        
        JCheckBox checkBox_10 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_23 = new GroupLayout(panel_23);
        gl_panel_23.setHorizontalGroup(
            gl_panel_23.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_23.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_10, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_23.setVerticalGroup(
            gl_panel_23.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_23.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_10, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_23.setLayout(gl_panel_23);
        
        JPanel panel_24 = new JPanel();
        
        JCheckBox checkBox_11 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_24 = new GroupLayout(panel_24);
        gl_panel_24.setHorizontalGroup(
            gl_panel_24.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_24.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_11, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_24.setVerticalGroup(
            gl_panel_24.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_24.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_11, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_24.setLayout(gl_panel_24);
        
        JPanel panel_25 = new JPanel();
        
        JCheckBox checkBox_12 = new JCheckBox("Statistic Name");
        GroupLayout gl_panel_25 = new GroupLayout(panel_25);
        gl_panel_25.setHorizontalGroup(
            gl_panel_25.createParallelGroup(Alignment.LEADING)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGap(0, 285, Short.MAX_VALUE)
                .addGroup(gl_panel_25.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_12, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_25.setVerticalGroup(
            gl_panel_25.createParallelGroup(Alignment.LEADING)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(gl_panel_25.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkBox_12, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(40))
        );
        panel_25.setLayout(gl_panel_25);
        GroupLayout gl_StatisticListContent = new GroupLayout(StatisticListContent);
        gl_StatisticListContent.setHorizontalGroup(
            gl_StatisticListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_StatisticListContent.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_StatisticListContent.createParallelGroup(Alignment.LEADING)
                        .addComponent(Statistic, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_18, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_19, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_20, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_22, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_StatisticListContent.setVerticalGroup(
            gl_StatisticListContent.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_StatisticListContent.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Statistic, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_18, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_19, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_20, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_22, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE))
        );
        StatisticListContent.setLayout(gl_StatisticListContent);
        
        JPanel Stimuli = new JPanel();
        AxisTabs.addTab("Stimuli", null, Stimuli, null);
        AxisTabs.setEnabledAt(1, true);
        Stimuli.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Stimuli.add(scrollPane_1, BorderLayout.CENTER);
        
        JPanel Subjects = new JPanel();
        AxisTabs.addTab("Subjects", null, Subjects, null);
        AxisTabs.setEnabledAt(2, true);
        Subjects.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Subjects.add(scrollPane_2, BorderLayout.CENTER);
        
        JPanel AxisFooter = new JPanel();
        AxisMemberPanel.add(AxisFooter, BorderLayout.SOUTH);
        
        JPanel OutputPanel = new JPanel();
        frame.getContentPane().add(OutputPanel);
        OutputPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel OutputWrapper = new JPanel();
        OutputPanel.add(OutputWrapper, BorderLayout.CENTER);
        
        JPanel OutputHeader = new JPanel();
        OutputPanel.add(OutputHeader, BorderLayout.NORTH);
        
        JPanel OutputFooter = new JPanel();
        OutputPanel.add(OutputFooter, BorderLayout.SOUTH);
        
        JPanel RightSpacer = new JPanel();
        OutputPanel.add(RightSpacer, BorderLayout.EAST);
    }
}
