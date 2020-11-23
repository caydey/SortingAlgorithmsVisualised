package com.caydey.sortingvisualised.gui.controlpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ControlPanel extends JPanel {
  private ControlButton startButton;
  private ControlButton resetButton;

  // Listener
  private ControlPanelListener controlPanelListener;
  public void setControlPanelListener(ControlPanelListener controlPanelListener) {
    this.controlPanelListener = controlPanelListener;
  }


  public ControlPanel() {
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


    

  }
}
