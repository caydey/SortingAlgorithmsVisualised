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
    TrackedArray trackedArray = new TrackedArray(500, ArrayOrder.RANDOMIZED);
    arrayPanel = new ArrayPanel(trackedArray);
    trackedArray.setOperationListener(arrayPanel);
    add(arrayPanel, c);


    // start sorting in thread so it can sleep and run in parrallel
    Runnable runnable = new SortArray(trackedArray, new QuickSort());
    Thread thread = new Thread(runnable);
    thread.start();

    // setResizable(false);
    setSize(500,530); // 500, 500+30(titlebar compensation)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

}
