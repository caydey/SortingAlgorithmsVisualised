package com.caydey.sortingvisualised.gui.controlpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.algorithms.Sorts;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;

public class ControlPanel extends JPanel {
  private ControlButton startButton;
  private ControlButton resetButton;

  private JComboBox delayComboBox;
  private final String[] delayOptions = {
    "1ms",
    "2ms",
    "5ms",
    "10ms",
    "100ms"
  };

  private JComboBox sortsComboBox;

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
      delayComboBox = new JComboBox<String>(delayOptions);
      delayComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println("ms");
        }
      });
      rightPanel.add(delayComboBox);



      sortsComboBox = new JComboBox<String>(Sorts.getList()); // Sorts is an enum listing all sorting algorithms
      sortsComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String sortName = (String)((JComboBox)e.getSource()).getSelectedItem();
          SortingAlgorithm algorithm = Sorts.getSortFromName(sortName).getSortObject(); // get enum of sorting algorithm and then gets sort object from enum
          controlPanelListener.setSortingAlgorithmAction(algorithm); // call to action listener inside WindowFrame
        }
      });
      sortsComboBox.setSelectedItem(Sorts.QUICK.getName()); // Select "Quick Sort" by default
      rightPanel.add(sortsComboBox);

    add(rightPanel, BorderLayout.LINE_END);

  }
}
