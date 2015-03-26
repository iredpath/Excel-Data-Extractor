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
import javax.swing.AbstractListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

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
        
        JPanel AxisHeader = new JPanel();
        AxisMemberPanel.add(AxisHeader, BorderLayout.NORTH);
        AxisHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JComboBox comboBox_1 = new JComboBox();
        AxisHeader.add(comboBox_1);
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Slide Metric Data", "Lookzone Data"}));
        
        JPanel AxisList = new JPanel();
        AxisMemberPanel.add(AxisList, BorderLayout.CENTER);
        AxisList.setLayout(new BorderLayout(0, 0));
        
        JTabbedPane AxisTabs = new JTabbedPane(JTabbedPane.TOP);
        AxisList.add(AxisTabs, BorderLayout.CENTER);
        
        JPanel Statistics = new JPanel();
        AxisTabs.addTab("Statistics", null, Statistics, null);
        AxisTabs.setEnabledAt(0, true);
        Statistics.setLayout(new BorderLayout(0, 0));
        
        JPanel StatisticToggleContainer = new JPanel();
        Statistics.add(StatisticToggleContainer, BorderLayout.NORTH);
        
        JButton btnNewButton = new JButton("Select All");
        StatisticToggleContainer.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Deselect All");
        StatisticToggleContainer.add(btnNewButton_1);
        
        JScrollPane StatisticScrollPane = new JScrollPane();
        StatisticScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Statistics.add(StatisticScrollPane, BorderLayout.CENTER);
        
        JList StatisticList = new JList();
        StatisticList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        StatisticList.setModel(new AbstractListModel() {
            String[] values = new String[] {"Statistic_1", "Statistic_2"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        StatisticScrollPane.setViewportView(StatisticList);
        
        JPanel Stimuli = new JPanel();
        AxisTabs.addTab("Stimuli", null, Stimuli, null);
        AxisTabs.setEnabledAt(1, true);
        Stimuli.setLayout(new BorderLayout(0, 0));
        
        JScrollPane StimuliScrollPane = new JScrollPane();
        StimuliScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Stimuli.add(StimuliScrollPane, BorderLayout.CENTER);
        
        JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        StimuliScrollPane.setViewportView(list);
        
        JPanel Subjects = new JPanel();
        AxisTabs.addTab("Subjects", null, Subjects, null);
        AxisTabs.setEnabledAt(2, true);
        Subjects.setLayout(new BorderLayout(0, 0));
        
        JScrollPane SubjectList = new JScrollPane();
        SubjectList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Subjects.add(SubjectList, BorderLayout.CENTER);
        
        JList list_1 = new JList();
        list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SubjectList.setViewportView(list_1);
        
        JPanel AxisFooter = new JPanel();
        AxisMemberPanel.add(AxisFooter, BorderLayout.SOUTH);
        
        JButton btnExport = new JButton("Export");
        AxisFooter.add(btnExport);
        
        JPanel Separator = new JPanel();
        AxisMemberPanel.add(Separator, BorderLayout.WEST);
        
        JPanel FilePanel = new JPanel();
        GridBagConstraints gbc_FilePanel = new GridBagConstraints();
        gbc_FilePanel.fill = GridBagConstraints.BOTH;
        gbc_FilePanel.gridx = 0;
        gbc_FilePanel.gridy = 0;
        Center.add(FilePanel, gbc_FilePanel);
        FilePanel.setLayout(new BorderLayout(0, 0));
        
        JPanel FileHeader = new JPanel();
        FilePanel.add(FileHeader, BorderLayout.NORTH);
        
        JButton button = new JButton("Add Files");
        FileHeader.add(button);
        
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
        
        JPanel OutputWrapper = new JPanel();
        FilePanel.add(OutputWrapper, BorderLayout.SOUTH);
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
        
        JPanel MockTable = new JPanel();
        MockTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        TableContainer.add(MockTable);
        MockTable.setLayout(new GridLayout(4, 4, 0, 0));
        
        JPanel SwapContainer = new JPanel();
        MockTable.add(SwapContainer);
        
        JButton SwapAxisButton = new JButton("Swap");
        SwapContainer.add(SwapAxisButton);
        
        JPanel ColHead1 = new JPanel();
        MockTable.add(ColHead1);
        
        JLabel lblVerticleAxisName = new JLabel("Stimulus");
        ColHead1.add(lblVerticleAxisName);
        
        JPanel ColHead2 = new JPanel();
        MockTable.add(ColHead2);
        
        JLabel label_1 = new JLabel("Stimulus");
        ColHead2.add(label_1);
        
        JPanel ColHead3 = new JPanel();
        MockTable.add(ColHead3);
        
        JLabel label_10 = new JLabel("Stimulus");
        ColHead3.add(label_10);
        
        JPanel RowHead1 = new JPanel();
        MockTable.add(RowHead1);
        
        JLabel lblSubject = new JLabel("Subject");
        RowHead1.add(lblSubject);
        
        JPanel DataCell1 = new JPanel();
        MockTable.add(DataCell1);
        
        JLabel label = new JLabel("- - - - - - - -");
        DataCell1.add(label);
        
        JPanel DataCell2 = new JPanel();
        MockTable.add(DataCell2);
        
        JLabel label_2 = new JLabel("- - - - - - - -");
        DataCell2.add(label_2);
        
        JPanel DataCell3 = new JPanel();
        MockTable.add(DataCell3);
        
        JLabel label_3 = new JLabel("- - - - - - - -");
        DataCell3.add(label_3);
        
        JPanel RowHead2 = new JPanel();
        MockTable.add(RowHead2);
        
        JLabel label_11 = new JLabel("Subject");
        RowHead2.add(label_11);
        
        JPanel DataCell4 = new JPanel();
        MockTable.add(DataCell4);
        
        JLabel label_6 = new JLabel("- - - - - - - -");
        DataCell4.add(label_6);
        
        JPanel DataCell5 = new JPanel();
        MockTable.add(DataCell5);
        
        JLabel label_5 = new JLabel("- - - - - - - -");
        DataCell5.add(label_5);
        
        JPanel DataCell6 = new JPanel();
        MockTable.add(DataCell6);
        
        JLabel label_4 = new JLabel("- - - - - - - -");
        DataCell6.add(label_4);
        
        JPanel RowHead3 = new JPanel();
        MockTable.add(RowHead3);
        
        JLabel label_12 = new JLabel("Subject");
        RowHead3.add(label_12);
        
        JPanel DataCell7 = new JPanel();
        MockTable.add(DataCell7);
        
        JLabel label_7 = new JLabel("- - - - - - - -");
        DataCell7.add(label_7);
        
        JPanel DataCell8 = new JPanel();
        MockTable.add(DataCell8);
        
        JLabel label_8 = new JLabel("- - - - - - - -");
        DataCell8.add(label_8);
        
        JPanel DataCell9 = new JPanel();
        MockTable.add(DataCell9);
        
        JLabel label_9 = new JLabel("- - - - - - - -");
        DataCell9.add(label_9);
        
        JPanel TabContainer = new JPanel();
        OutputContainer.add(TabContainer, BorderLayout.SOUTH);
        
        JComboBox comboBox = new JComboBox();
        TabContainer.add(comboBox);
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Statistic", "Stimuli", "Subject"}));
        comboBox.setSelectedIndex(0);
        comboBox.setMaximumRowCount(3);
        
        JPanel Footer = new JPanel();
        frame.getContentPane().add(Footer, BorderLayout.SOUTH);
        
        JPanel LeftSpacer = new JPanel();
        frame.getContentPane().add(LeftSpacer, BorderLayout.WEST);
        
        JPanel RightSpacer = new JPanel();
        frame.getContentPane().add(RightSpacer, BorderLayout.EAST);
    }
}
