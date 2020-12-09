package com.caydey.sortingvisualised.gui.toolbarpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.algorithms.Sorts;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;

import com.caydey.sortingvisualised.gui.arraypanel.renderer.Renderers;
import com.caydey.sortingvisualised.gui.arraypanel.renderer.ArrayRenderer;

import com.caydey.sortingvisualised.gui.combobox.*;

public class ToolbarPanel extends JPanel {
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
  private ControlComboCheckBox shownComboCheckBox;
  private final String shownOptionTitle = "Shown";
  private final int shownDefaultOption = 1;
  private final String[] shownOptions = {
    "Swaps",
    "Comparisons",
    "Gets",
    "Sets"
  };
  private ControlComboBox visualisationComboBox;
  private final String visualisationOptionTitle = "Visualisation";
  private final int visualisationDefaultOption = 1;
  private final String[] visualisationOptions = Renderers.getList();


  private final static Color COLOR_BACKGROUND = new Color(24,24,24);  // dark dark grey

  // Listener
  private ToolbarPanelListener toolbarPanelListener;
  public void setToolbarPanelListener(ToolbarPanelListener toolbarPanelListener) {
    this.toolbarPanelListener = toolbarPanelListener;
  }

  public ToolbarPanel() {

    // layout
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    // Order [Randomized, Sorted, Reversed]
    c.gridx=0;
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
    add(orderComboBox, c);

    // Size [8, 16, 64, 128, 256, 512, 1024, 2048, 4096]
    c.gridx=1;
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
    add(sizeComboBox, c);

    // Delay [1ms, 2ms, 5ms, 10ms, 20ms, 100ms, 500ms]
    c.gridx=2;
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
    add(delayComboBox, c);

    // Shown [Swaps, Comparisons, Gets, Sets]
    c.gridx=3;
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
    add(shownComboCheckBox, c);

    // Visualisation
    c.gridx=4;
    visualisationComboBox = new ControlComboBox(visualisationOptionTitle, visualisationOptions, false);
    visualisationComboBox.setSelectedItem(visualisationDefaultOption);
    visualisationComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String rendererName = (String)((ControlComboBox)e.getSource()).getSelectedItem();
        ArrayRenderer arrayRenderer = Renderers.getRendererFromName(rendererName).getRendererObject();
        toolbarPanelListener.setArrayRendererAction(arrayRenderer);
      }
    });
    add(visualisationComboBox, c);

    // force all previous items to the left
    c.gridx=5;
    c.weightx=1.0;
    JLabel paddLabel = new JLabel();
    add(paddLabel,c);

    setBackground(COLOR_BACKGROUND);
  }
}
