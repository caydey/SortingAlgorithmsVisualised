package com.caydey.sortingvisualised.gui.controlpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.algorithms.Sorts;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;

import com.caydey.sortingvisualised.gui.controlpanel.button.*;
import com.caydey.sortingvisualised.gui.combobox.*;

public class ControlPanel extends JPanel {
  private ControlButton startButton;
  private ControlButton resetButton;


  private ControlComboBox sortsComboBox;
  private final String sortsOptionTitle = "Sort";
  private final String sortsDefaultOption = Sorts.QUICK.getName();
  private final String[] sortsOptions = Sorts.getList();

  // Listener
  private ControlPanelListener controlPanelListener;
  public void setControlPanelListener(ControlPanelListener controlPanelListener) {
    this.controlPanelListener = controlPanelListener;
  }

  public ControlPanel() {
    setLayout(new BorderLayout());

    JPanel leftPanel = new JPanel(new GridBagLayout());
      // Start
      startButton = new ControlButton(ButtonType.START);
      startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          controlPanelListener.startAction();
        }
      });
      leftPanel.add(startButton);

      // Reset
      resetButton = new ControlButton(ButtonType.RESET);
      resetButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          controlPanelListener.resetAction();
        }
      });
      leftPanel.add(resetButton);
    add(leftPanel, BorderLayout.LINE_START);

    JPanel rightPanel = new JPanel(new GridBagLayout());
      // Sorts
      sortsComboBox = new ControlComboBox(sortsOptionTitle, sortsOptions, true); // Sorts is an enum listing all sorting algorithms
      sortsComboBox.setSelectedItem(sortsDefaultOption); // Select "Quick Sort" by default
      sortsComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String sortName = (String)((ControlComboBox)e.getSource()).getSelectedItem(); // "Quick Sort", "Cocktail Sort" etc...
          SortingAlgorithm algorithm = Sorts.getSortFromName(sortName).getSortObject(); // get enum of sorting algorithm and then gets sort object from enum
          controlPanelListener.setSortingAlgorithmAction(algorithm); // call to action listener inside WindowFrame
        }
      });
      rightPanel.add(sortsComboBox);
    add(rightPanel, BorderLayout.LINE_END);
  }
}
