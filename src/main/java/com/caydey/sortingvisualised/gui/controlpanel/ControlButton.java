package com.caydey.sortingvisualised.gui.controlpanel;

import javax.swing.*;
import java.awt.*;


public class ControlButton extends JButton {
  public ControlButton(String title, Color color) {
    super(title);
    setForeground(Color.BLACK);
    setBackground(color);
    setFocusPainted(false);
    setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
  }
}
