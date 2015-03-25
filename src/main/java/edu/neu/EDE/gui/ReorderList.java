package edu.neu.EDE.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ReorderList {

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {

         public void run() {
            new ReorderList().makeUI();
         }
      });
   }

   public void makeUI() {
      Object[] data = {"One", "Two", "Three", "Four", "Five",
         "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve"
      };
      DefaultListModel model = new DefaultListModel();
      for (Object object : data) {
         model.addElement(object);
      }
      JList list = new JList(model);
      MouseAdapter listener = new ReorderListener(list);
      list.addMouseListener(listener);
      list.addMouseMotionListener(listener);

      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(200, 200);
      frame.add(new JScrollPane(list));
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
}

class ReorderListener extends MouseAdapter {

   private JList list;
   private int pressIndex = 0;
   private int releaseIndex = 0;

   public ReorderListener(JList list) {
      if (!(list.getModel() instanceof DefaultListModel)) {
         throw new IllegalArgumentException("List must have a DefaultListModel");
      }
      this.list = list;
   }

   @Override
   public void mousePressed(MouseEvent e) {
      pressIndex = list.locationToIndex(e.getPoint());
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      releaseIndex = list.locationToIndex(e.getPoint());
      if (releaseIndex != pressIndex && releaseIndex != -1) {
         reorder();
      }
   }

   @Override
   public void mouseDragged(MouseEvent e) {
      mouseReleased(e);
      pressIndex = releaseIndex;      
   }

   private void reorder() {
      DefaultListModel model = (DefaultListModel) list.getModel();
      Object dragee = model.elementAt(pressIndex);
      model.removeElementAt(pressIndex);
      model.insertElementAt(dragee, releaseIndex);
   }
}