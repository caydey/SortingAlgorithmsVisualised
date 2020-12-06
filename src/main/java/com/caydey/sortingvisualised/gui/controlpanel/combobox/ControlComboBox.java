package com.caydey.sortingvisualised.gui.controlpanel.combobox;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ControlComboBox extends JComboBox<String> {
  private JLabel titleLabel;
  private String title;
  private boolean titleInToolTip;

  public ControlComboBox(String title, String[] contents, boolean titleInToolTip) {
    super(contents);

    // what to put in tooltip (show title in tooltip and selected item in label, or title in label and selected item in tooltip)
    this.title = title;
    this.titleInToolTip = titleInToolTip;

    // title
    titleLabel = new JLabel(title);


    // Labels
    addLabels();

    // set height to 20px
    Dimension dimension = getPreferredSize();
    dimension.height = 20;
    setPreferredSize(dimension);

    // look
    setMaximumRowCount(12);  // dropdown size
    setRenderer(new ComboBoxRenderer());
    setForeground(Color.BLACK);
    setBackground(new Color(224,255,255)); // Light Cyan
    setFocusable(false);

  }

  private void addLabels() {
    // tooltip for selected item
    if (!titleInToolTip) { // selected item in Tooltip
      addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String selectedItem = (String)getSelectedItem();  // selected item
          setToolTipText("<html><strong>"+selectedItem+"</strong></html>"); // set tooltip to selected item
        }
      });
    } else {
      setToolTipText("<html><strong>"+title+"</strong></html>");
    }
  }

  private class ComboBoxRenderer implements ListCellRenderer<String> {
    public ComboBoxRenderer() {
      setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList list, String value, int index, boolean isSelected, boolean cellHasFocus) {
      if (!titleInToolTip) { // combo box title as title
        // set title of combobox to title
        if (index == -1) {
          return titleLabel;
        }
      }

      JLabel labelElement = new JLabel(value);
      labelElement.setOpaque(true);
      labelElement.setForeground(Color.BLACK);

      Component c = (Component)(labelElement);
      // highlight label as mouse hovers over it
      if (isSelected) {
        c.setBackground(list.getSelectionBackground());
        c.setForeground(list.getSelectionForeground());
      } else {
        c.setBackground(list.getBackground());
        c.setForeground(list.getForeground());
      }
      return c;
    }
  }
}
