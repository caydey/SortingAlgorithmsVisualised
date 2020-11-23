package com.caydey.sortingvisualised.gui.arraypanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.array.TrackedArray;

public class ArrayPanel extends JPanel implements ArrayOperationListener {
  private TrackedArray trackedArray;
  private final int scale;

  @Override
  public void swapAction(int indexA, int indexB) {
    repaint();
    // System.out.println("swap");
  }
  @Override
  public void compareAction(int indexA, int indexB) {
    System.out.print("compare");
  }
  @Override
  public void getAction(int index) {
    System.out.println("get");
  }
  @Override
  public void setAction(int index, int value) {
    System.out.println("set");
  }


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
