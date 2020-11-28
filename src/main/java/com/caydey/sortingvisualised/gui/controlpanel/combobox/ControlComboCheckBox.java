package com.caydey.sortingvisualised.gui.controlpanel.combobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class ControlComboCheckBox extends JComboBox<JCheckBox> {
  private JLabel titleLabel;

  public ControlComboCheckBox(String title, JCheckBox[] checkBoxes) {
    super(checkBoxes);

    // height & width
    setPreferredSize(new Dimension(120,20));


    titleLabel = new JLabel(title);
    setForeground(Color.BLACK);
    setBackground(new Color(224,255,255)); // Light Cyan
    setFocusable(false);

    addCheckBoxes();
  }
  public JCheckBox getSelection() {
    return (JCheckBox)getSelectedItem();
  }

  public static JCheckBox[] createCheckBoxes(String[] checkBoxTitles) {
    JCheckBox[] checkBoxes = new JCheckBox[checkBoxTitles.length];
    for (int i=0; i<checkBoxTitles.length; i++) {
      checkBoxes[i] = new JCheckBox(checkBoxTitles[i]);
    }
    return checkBoxes;
  }

  private void addCheckBoxes() {
    setRenderer(new ComboBoxRenderer());
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        itemSelected();
      }
    });
  }
  private void itemSelected() {
    JCheckBox checkBox = (JCheckBox)getSelectedItem();
    checkBox.setSelected(!checkBox.isSelected()); // toggle selection
  }

  private class ComboBoxRenderer implements ListCellRenderer<JCheckBox> {
    public ComboBoxRenderer() {
      setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList list, JCheckBox value, int index, boolean isSelected, boolean cellHasFocus) {
      // set title
      if (index == -1) {
        return titleLabel;
      }

      Component c = (Component)value;

      // highlight checkbox as mouse hovers over it
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
