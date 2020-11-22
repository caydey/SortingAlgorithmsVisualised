package com.caydey.sortingvisualised.gui.arraypanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.array.TrackedArray;

public class ArrayPanel extends JPanel {
  private TrackedArray trackedArray;
  private final int scale;

  public ArrayPanel(TrackedArray trackedArray) {
    this.trackedArray = trackedArray;
    scale = 500/(trackedArray.length);
  }


  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D)g;

    g2d.setColor(Color.WHITE);
    g2d.fillRect(0,0, 500, 500);

    g2d.setColor(Color.BLACK);
    int[] array = trackedArray.getArray();
    for (int i=0; i<array.length; i++) {
      g2d.setColor(Color.BLACK);
      g2d.fillRect(i*scale,500-((array[i]+1)*scale), scale,((array[i]+1)*scale));
    }
  }
}
