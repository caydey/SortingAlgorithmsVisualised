package com.caydey.sortingvisualised.gui.controlpanel.combobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class ControlComboCheckBox extends JComboBox<JCheckBox> {
  private JLabel titleLabel;

  private ArrayList<String> selectedItems;

  public ControlComboCheckBox(String title, JCheckBox[] checkBoxes) {
    super(checkBoxes);

    // title
    titleLabel = new JLabel(title);

    // height & width
    setPreferredSize(new Dimension(120,20));

    // CheckBoxes
    setCheckBoxes();

    // look
    setRenderer(new ComboBoxRenderer());
    setForeground(Color.BLACK);
    setBackground(new Color(224,255,255)); // Light Cyan
    setFocusable(false);
  }

  private void setCheckBoxes() {
    selectedItems = new ArrayList<String>();  // tooltip
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // toggle selection
        JCheckBox checkBox = (JCheckBox)getSelectedItem();
        checkBox.setSelected(!checkBox.isSelected());

        // tooltip
        if (checkBox.isSelected()) { selectedItems.add(checkBox.getText()); }
        else { selectedItems.remove(checkBox.getText()); }

        String toolTipText = String.join(", ", selectedItems);
        setToolTipText(toolTipText);
      }
    });
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
