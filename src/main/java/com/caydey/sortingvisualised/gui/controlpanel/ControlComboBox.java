package com.caydey.sortingvisualised.gui.controlpanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class ControlComboBox extends JComboBox<String> {
  public ControlComboBox(String[] contents) {
    super(contents);
    setForeground(Color.BLACK);
    setBackground(new Color(224,255,255)); // Light Cyan
    setFocusable(false);
    setBorder(null);
  }
}
