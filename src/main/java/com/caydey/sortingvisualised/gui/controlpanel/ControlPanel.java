package com.caydey.sortingvisualised.gui.controlpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.algorithms.Sorts;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;

public class ControlPanel extends JPanel {
  private ControlButton startButton;
  private ControlButton resetButton;

  private ControlComboBox delayComboBox;
  private final String[] delayOptions = {
    "1ms",
    "2ms",
    "5ms",
    "10ms",
    "100ms",
    "1000ms"
  };
  private ControlComboBox orderComboBox;
  private final String[] orderOptions = {
    "Randomized",
    "Reversed",
    "Sorted"
  };
  private ControlComboBox sizeComboBox;
  private final String[] sizeOptions = {
    "8",
    "16",
    "64",
    "100",
    "250",
    "500"
  };
  private ControlComboBox sortsComboBox;

  // Listener
  private ControlPanelListener controlPanelListener;

  public ControlPanel(ControlPanelListener controlPanelListener) {
    this.controlPanelListener = controlPanelListener;
    setLayout(new BorderLayout());

    JPanel leftPanel = new JPanel(new GridBagLayout());
      startButton = new ControlButton("Start", new Color(44,238,144)); // LIGHT GREEN
      startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          controlPanelListener.startAction();
        }
      });
      leftPanel.add(startButton);

      resetButton = new ControlButton("Reset", new Color(250,128,114)); // SALMON
      resetButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
          controlPanelListener.resetAction();
        }
      });
      leftPanel.add(resetButton);
    add(leftPanel, BorderLayout.LINE_START);

    JPanel rightPanel = new JPanel(new GridBagLayout());
      delayComboBox = new ControlComboBox(delayOptions);
      delayComboBox.setSelectedItem("10ms"); // Select "10ms" by default
      delayComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String delayStr = (String)((ControlComboBox)e.getSource()).getSelectedItem();
          int delay = Integer.parseInt(delayStr.substring(0,delayStr.length()-2));  // 10ms -> 10
          controlPanelListener.setDelayAction(delay);
        }
      });
      rightPanel.add(delayComboBox);

      orderComboBox = new ControlComboBox(orderOptions);
      orderComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String orderName = (String)((ControlComboBox)e.getSource()).getSelectedItem();
          ArrayOrder order = ArrayOrder.RANDOMIZED;
          if (orderName.equals("Sorted")) {
            order = ArrayOrder.SORTED;
          } else if (orderName.equals("Reversed")) {
            order = ArrayOrder.REVERSED;
          }
          controlPanelListener.setArrayOrderAction(order);
        }
      });
      rightPanel.add(orderComboBox);

      sizeComboBox = new ControlComboBox(sizeOptions);
      sizeComboBox.setSelectedItem("500"); // Select "500" by default
      sizeComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String orderStr = (String)((ControlComboBox)e.getSource()).getSelectedItem();
          int order = Integer.parseInt(orderStr);
          controlPanelListener.setArraySizeAction(order);
        }
      });
      rightPanel.add(sizeComboBox);

      sortsComboBox = new ControlComboBox(Sorts.getList()); // Sorts is an enum listing all sorting algorithms
      sortsComboBox.setSelectedItem(Sorts.QUICK.getName()); // Select "Quick Sort" by default
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
