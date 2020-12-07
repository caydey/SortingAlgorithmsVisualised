package com.caydey.sortingvisualised.gui.controlpanel.button;

import javax.swing.*;
import java.awt.*;

public class ControlButton extends JButton {
  public ControlButton(ButtonType type) {
    setPreferredSize(new Dimension(20,20));


    String iconPath = "/start.png";
    Color color = Color.GREEN;
    if (type == type.RESET) {
      iconPath = "/reset.png";
      color = Color.RED;
    }
    Icon icon = new ImageIcon(getClass().getResource(iconPath));
    setIcon(icon);
    setBackground(color);

    setFocusPainted(false);
    setBorder(null);
  }
}
