package com.caydey.sortingvisualised.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.array.ArrayOrder;
import com.caydey.sortingvisualised.algorithms.*;

import com.caydey.sortingvisualised.gui.arraypanel.ArrayPanel;
import com.caydey.sortingvisualised.gui.controlpanel.ControlPanel;
import com.caydey.sortingvisualised.gui.controlpanel.ControlPanelListener;


public class WindowFrame extends JFrame implements ControlPanelListener {
  private ArrayPanel arrayPanel;
  private ControlPanel controlPanel;

  private TrackedArray trackedArray;
  private SortingAlgorithm sortingAlgorithm;

  private Runnable sortingRunnable;
  private Thread sortingThread;

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
    arrayPanel = new ArrayPanel(500); // 500x500
    // arrayPanel.setTrackedArray(trackedArray);
    // arrayPanel.setShowSwaps(true);
    // arrayPanel.setShowGets(true);
    // arrayPanel.setShowSets(true);
    // arrayPanel.setShowComparisons(true);
    add(arrayPanel, c);


    // control panel (start, stop, delay)
    c.gridx = 1; c.gridy = 1;
    c.weightx = 1.0; c.weighty = 0.0;
    c.fill = GridBagConstraints.HORIZONTAL;
    controlPanel = new ControlPanel();
    controlPanel.setControlPanelListener(this);
    add(controlPanel, c);


    // default
    trackedArray = new TrackedArray(500, ArrayOrder.RANDOMIZED);
    arrayPanel.setTrackedArray(trackedArray);

    sortingAlgorithm = new QuickSort();

    sortingRunnable = new SortArray(trackedArray, sortingAlgorithm);
    sortingThread = new Thread(sortingRunnable);




    // setResizable(false);
    setSize(504,552); // 500+4(padding), 500+30(titlebar)+22(buttons)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }


  @Override
  public void startAction() {
    // start sorting in thread so it can sleep and run in parrallel
    // without effecting porgram execution

    sortingRunnable = new SortArray(trackedArray, sortingAlgorithm);
    sortingThread = new Thread(sortingRunnable);
    sortingThread.start();
  }
  @Override
  public void resetAction() {

    ((SortArray)sortingRunnable).setTerminating(); // tell SortArray that its terminating
    sortingThread.interrupt();  // terminate

    trackedArray = new TrackedArray(500, ArrayOrder.RANDOMIZED);
    arrayPanel.setTrackedArray(trackedArray);

  }
  @Override
  public void setDelayAction(int ms) {
    System.out.println("delay: "+ms);
  }
  @Override
  public void setSortingAlgorithmAction(SortingAlgorithm algorithm) {
    System.out.println("SortingAlgorithm");
  }
  @Override
  public void setArrayOrder(ArrayOrder order) {
    System.out.println("ArrayOrder");
  }
}
