package com.caydey.sortingvisualised.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.array.ArrayOrder;
import com.caydey.sortingvisualised.algorithms.*;

import com.caydey.sortingvisualised.gui.arraypanel.*;


public class WindowFrame extends JFrame {
  private ArrayPanel arrayPanel;
  public WindowFrame() {
    // title
    super("Sorting Algorithms Visualised");

    // layout
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // canvas
    c.gridx = 1; c.gridy = 0;
    c.weightx = 1.0; c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    TrackedArray trackedArray = new TrackedArray(32, ArrayOrder.RANDOMIZED);
    arrayPanel = new ArrayPanel(trackedArray);
    trackedArray.setOperationListener(new ArrayOperationListener() {
      @Override
      public void swapAction(int indexA, int indexB) {
        arrayPanel.repaint();
        System.out.println("swap");
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
      public void setAction(int index) {
        System.out.println("set");
      }
    });
    add(arrayPanel, c);


    // problem: need to make InsertionSort halt its execution everytime it calls TrackedArray.swap
    // so TrackedArray can call for canvas to be repainted
    Runnable runnable = new SortArray(trackedArray);
    Thread thread = new Thread(runnable);
    thread.start();

    // setResizable(false);
    setSize(500,530); // 500, 500+30(titlebar compensation)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

}
