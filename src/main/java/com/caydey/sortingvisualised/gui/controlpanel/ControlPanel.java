package com.caydey.sortingvisualised.gui.controlpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.algorithms.Sorts;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;

import com.caydey.sortingvisualised.gui.controlpanel.button.*;
import com.caydey.sortingvisualised.gui.controlpanel.combobox.*;

public class ControlPanel extends JPanel {
  private ControlButton startButton;
  private ControlButton resetButton;

  private ControlComboBox delayComboBox;
  private final String delayOptionTitle = "Delay";
  private final int delayDefaultOption = 3;
  private final String[] delayOptions = {
    "1ms",
    "2ms",
    "5ms",
    "10ms",
    "20ms",
    "100ms",
    "500ms"
  };
  ControlComboCheckBox shownComboCheckBox;
  private final String shownOptionTitle = "Shown";
  private final int shownDefaultOption = 1;
  private final String[] shownOptions = new String[] {
    "Swaps",
    "Comparisons",
    "Gets",
    "Sets"
  };
  private ControlComboBox orderComboBox;
  private final String orderOptionTitle = "Order";
  private final int orderDefaultOption = 0;
  private final String[] orderOptions = {
    "Random",
    "Reversed",
    "Sorted"
  };
  private ControlComboBox sizeComboBox;
  private final String sizeOptionTitle = "Size";
  private final int sizeDefaultOption = 5;
  private final String[] sizeOptions = {
    "8",
    "16",
    "64",
    "128",
    "256",
    "512",
    "1024",
    "2048",
    "4096"
  };
  private ControlComboBox sortsComboBox;
  private final String sortsOptionTitle = "Sort";
  private final String sortsDefaultOption = Sorts.QUICK.getName();
  private final String[] sortsOptions = Sorts.getList();

  // Listener
  private ControlPanelListener controlPanelListener;

  public ControlPanel(ControlPanelListener controlPanelListener) {
    this.controlPanelListener = controlPanelListener;
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
      // Delay [1ms, 2ms, 5ms, 10ms, 20ms, 100ms, 500ms]
      delayComboBox = new ControlComboBox(delayOptionTitle, delayOptions);
      delayComboBox.setSelectedIndex(delayDefaultOption); // Select "10ms" by default
      delayComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String delayStr = (String)((ControlComboBox)e.getSource()).getSelectedItem();
          int delay = Integer.parseInt(delayStr.substring(0,delayStr.length()-2));  // 10ms -> 10
          controlPanelListener.setDelayAction(delay);
        }
      });
      rightPanel.add(delayComboBox);

      // Shown [Swaps, Comparisons, Gets, Sets]
      shownComboCheckBox = new ControlComboCheckBox(shownOptionTitle, ControlComboCheckBox.createCheckBoxes(shownOptions));
      shownComboCheckBox.setSelectedIndex(shownDefaultOption);
      shownComboCheckBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JCheckBox checkBox = ((ControlComboCheckBox)e.getSource()).getSelection();
          boolean state = !checkBox.isSelected(); // inversed as this action event is called before the state change action is
          String operationName = checkBox.getText();
          OperationType operation = OperationType.SWAPS;
          if (operationName.equals("Comparisons")) {
            operation = OperationType.COMPARISONS;
          } else if (operationName.equals("Gets")) {
            operation = OperationType.GETS;
          } else if (operationName.equals("Sets")) {
            operation = OperationType.SETS;
          }
          controlPanelListener.setOperationsShownAction(operation, state);
        }
      });
      rightPanel.add(shownComboCheckBox);

      // Order [Randomized, Sorted, Reversed]
      orderComboBox = new ControlComboBox(orderOptionTitle, orderOptions);
      orderComboBox.setSelectedIndex(orderDefaultOption); // Select "Randomized" by default
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

      // Size [8, 16, 64, 128, 256, 512, 1024, 2048, 4096]
      sizeComboBox = new ControlComboBox(sizeOptionTitle, sizeOptions);
      sizeComboBox.setSelectedIndex(sizeDefaultOption); // Select "512" by default
      sizeComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String orderStr = (String)((ControlComboBox)e.getSource()).getSelectedItem();
          int order = Integer.parseInt(orderStr);
          controlPanelListener.setArraySizeAction(order);
        }
      });
      rightPanel.add(sizeComboBox);

      // Sorts
      sortsComboBox = new ControlComboBox(sortsOptionTitle, sortsOptions); // Sorts is an enum listing all sorting algorithms
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
