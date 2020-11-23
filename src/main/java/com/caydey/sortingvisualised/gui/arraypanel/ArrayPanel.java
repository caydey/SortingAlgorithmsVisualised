package com.caydey.sortingvisualised.gui.arraypanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.array.TrackedArray;

public class ArrayPanel extends JPanel implements ArrayOperationListener {
  private TrackedArray trackedArray;
  private final int scale;

  private int[] lastCompared = new int[2]; // the 2 elements that were last compared with eachother
  private int[] lastSwaped = new int[2]; // the 2 elements that were last swaped


  @Override
  public void swapAction(int indexA, int indexB) {
    lastSwaped[0] = indexA;
    lastSwaped[1] = indexB;
    repaint();
    System.out.println("swap");
  }
  @Override
  public void compareAction(int indexA, int indexB) {
    lastCompared[0] = indexA;
    lastCompared[1] = indexB;
    repaint();
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
      if (i == lastCompared[0] || i == lastCompared[1]) {
        g2d.setColor(Color.BLUE);
      } else if (i == lastSwaped[0] || i == lastSwaped[1]) {
        g2d.setColor(Color.RED);
      }
      g2d.fillRect(i*scale,500-((array[i]+1)*scale), scale,((array[i]+1)*scale));
    }
  }
}
