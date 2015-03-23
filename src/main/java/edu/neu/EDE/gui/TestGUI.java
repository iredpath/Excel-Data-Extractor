package edu.neu.EDE.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class TestGUI extends JFrame {
    public static void main(String args[]) {
        new TestGUI();
    }
    
    public TestGUI() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setSize(1366, 768);
        this.setLocation(0, 0);
        //this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TestGUI");
        
        
        // pannel
        JPanel jp1 = new JPanel();


        // table
        JTable jt1 = new JTable();
        
    }
}
