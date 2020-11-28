package com.caydey.sortingvisualised.gui.controlpanel.combobox;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ControlComboBox extends JComboBox<String> {
  private JLabel titleLabel;
  public ControlComboBox(String title, String[] contents) {
    super(contents);

    // title
    titleLabel = new JLabel(title);

    // Labels
    addLabels();

    // set height to 20px
    Dimension dimension = getPreferredSize();
    dimension.height = 20;
    setPreferredSize(dimension);

    // look
    setRenderer(new ComboBoxRenderer());
    setForeground(Color.BLACK);
    setBackground(new Color(224,255,255)); // Light Cyan
    setFocusable(false);

  }

  private void addLabels() {
    // tooltip for selected item
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selectedItem = (String)getSelectedItem();  // selected item
        setToolTipText(selectedItem); // set tooltip to selected item
      }
    });
  }

  private class ComboBoxRenderer implements ListCellRenderer<String> {
    public ComboBoxRenderer() {
      setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList list, String value, int index, boolean isSelected, boolean cellHasFocus) {
      // set title
      if (index == -1) {
        return titleLabel;
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
