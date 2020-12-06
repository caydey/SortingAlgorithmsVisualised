package com.caydey.sortingvisualised.gui.toolbarpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.algorithms.Sorts;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;

import com.caydey.sortingvisualised.gui.controlpanel.button.*;
import com.caydey.sortingvisualised.gui.controlpanel.combobox.*;

public class ToolbarPanel extends JPanel {
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

  // Listener
  private ToolbarPanelListener toolbarPanelListener;
  public void setToolbarPanelListener(ToolbarPanelListener toolbarPanelListener) {
    this.toolbarPanelListener = toolbarPanelListener;
  }

  public ToolbarPanel() {

    // layout
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // Delay [1ms, 2ms, 5ms, 10ms, 20ms, 100ms, 500ms]
    delayComboBox = new ControlComboBox(delayOptionTitle, delayOptions, false);
    delayComboBox.setSelectedIndex(delayDefaultOption); // Select "10ms" by default
    delayComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String delayStr = (String)((ControlComboBox)e.getSource()).getSelectedItem();
        int delay = Integer.parseInt(delayStr.substring(0,delayStr.length()-2));  // 10ms -> 10
        toolbarPanelListener.setDelayAction(delay);
      }
    });
    add(delayComboBox);

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
        toolbarPanelListener.setOperationsShownAction(operation, state);
      }
    });
    add(shownComboCheckBox);

    // Order [Randomized, Sorted, Reversed]
    orderComboBox = new ControlComboBox(orderOptionTitle, orderOptions, false);
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
        toolbarPanelListener.setArrayOrderAction(order);
      }
    });
    add(orderComboBox);

    // Size [8, 16, 64, 128, 256, 512, 1024, 2048, 4096]
    sizeComboBox = new ControlComboBox(sizeOptionTitle, sizeOptions, false);
    sizeComboBox.setSelectedIndex(sizeDefaultOption); // Select "512" by default
    sizeComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String orderStr = (String)((ControlComboBox)e.getSource()).getSelectedItem();
        int order = Integer.parseInt(orderStr);
        toolbarPanelListener.setArraySizeAction(order);
      }
    });
    add(sizeComboBox);
  }
}
