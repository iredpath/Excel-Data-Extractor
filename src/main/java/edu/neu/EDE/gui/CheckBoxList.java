package edu.neu.EDE.gui;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;


public class CheckBoxList
{  
   public static void main(String args[])
   {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create a list containing CheckListItem's
      
      JList list = new JList(new CheckBoxListItem[] {
            new CheckBoxListItem("apple apple apple apple apple apple apple apple"), 
            new CheckBoxListItem("orange"), 
            new CheckBoxListItem("mango"), 
            new CheckBoxListItem("paw paw"), 
            new CheckBoxListItem("banana")});
      
      // Use a CheckListRenderer (see below) 
      // to renderer list cells
      
      list.setCellRenderer(new CheckBoxListRenderer());
      list.setSelectionMode(
         ListSelectionModel.SINGLE_SELECTION);
      
      // Add a mouse listener to handle changing selection
      
      list.addMouseListener(new MouseAdapter()
      {
         public void mouseClicked(MouseEvent event)
         {
            JList list = (JList) event.getSource();
            
            // Get index of item clicked
            
            int index = list.locationToIndex(event.getPoint());
            CheckBoxListItem item = (CheckBoxListItem)
               list.getModel().getElementAt(index);
            
            // Toggle selected state
            
            item.setSelected(! item.isSelected());
            
            // Repaint cell
            
            list.repaint(list.getCellBounds(index, index));
         }
      });   

      frame.getContentPane().add(new JScrollPane(list));
      frame.pack();
       frame.setVisible(true);
   } 
}

// Represents items in the list that can be selected

class CheckBoxListItem
{
   private String  label;
   private boolean isSelected = false;

   public CheckBoxListItem(String label)
   {
      this.label = label;
   }

   public boolean isSelected()
   {
      return isSelected;
   }

   public void setSelected(boolean isSelected)
   {
      this.isSelected = isSelected;
   }

   public String toString()
   {
      return label;
   }
}
 
// Handles rendering cells in the list using a check box

class CheckBoxListRenderer extends JCheckBox
   implements ListCellRenderer
{
   public Component getListCellRendererComponent(
         JList list, Object value, int index, 
         boolean isSelected, boolean hasFocus)
   {
      setEnabled(list.isEnabled());
      setSelected(((CheckBoxListItem)value).isSelected());
      setFont(list.getFont());
      setBackground(list.getBackground());
      setForeground(list.getForeground());
      setText(value.toString());
      return this;
   }
} 
