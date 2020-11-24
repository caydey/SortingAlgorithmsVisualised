package com.caydey.sortingvisualised.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.array.ArrayOrder;

import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.algorithms.QuickSort;

import com.caydey.sortingvisualised.gui.arraypanel.ArrayPanel;
import com.caydey.sortingvisualised.gui.controlpanel.ControlPanel;
import com.caydey.sortingvisualised.gui.controlpanel.ControlPanelListener;


public class WindowFrame extends JFrame implements ControlPanelListener {
  private ArrayPanel arrayPanel;
  private ControlPanel controlPanel;

  private TrackedArray trackedArray;
  private SortingAlgorithm sortingAlgorithm;
  private int arraySize;
  private ArrayOrder arrayOrder;

  private Runnable sortingRunnable;
  private Thread sortingThread;

  private boolean isSorting;

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


    // control panel (start, stop ... delay,sort,order)
    c.gridx = 1; c.gridy = 1;
    c.weightx = 1.0; c.weighty = 0.0;
    c.fill = GridBagConstraints.HORIZONTAL;
    controlPanel = new ControlPanel(this); // pass ControlPanelListener
    add(controlPanel, c);

    // initialize randomized array of 500 elements (default)
    arraySize = 500;
    arrayOrder = ArrayOrder.RANDOMIZED;
    trackedArray = new TrackedArray(arraySize, arrayOrder);
    arrayPanel.setTrackedArray(trackedArray);

    // true when array is being sorted
    isSorting = false;


    // setResizable(false);
    setSize(504,554); // 500+4(padding), 500+30(titlebar)+24(buttons)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }


  @Override
  public void startAction() {
    // start sorting in thread so it can sleep and run
    // in parrallel without effecting porgram execution
    if (!isSorting) { // dont sort an array thats already being sorted
      sortingRunnable = new SortArray(trackedArray, sortingAlgorithm);
      sortingThread = new Thread(sortingRunnable);
      sortingThread.start();
      isSorting = true;
    }
  }
  @Override
  public void resetAction() {
    if (isSorting) { // if array is being sorted, terminate that bitch
      ((SortArray)sortingRunnable).setTerminating(); // tell SortArray that its terminating
      sortingThread.interrupt();  // terminate
      isSorting = false; // not sorting array
    }

    // create new array, (sorting algorithm is defined in startAction)
    trackedArray = new TrackedArray(arraySize, arrayOrder);
    arrayPanel.setTrackedArray(trackedArray);
  }
  @Override
  public void setDelayAction(int ms) {
    trackedArray.setDelay(ms);
  }
  @Override
  public void setSortingAlgorithmAction(SortingAlgorithm sortingAlgorithm) {
    this.sortingAlgorithm = sortingAlgorithm;
  }
  @Override
  public void setArrayOrderAction(ArrayOrder arrayOrder) {
    this.arrayOrder = arrayOrder;
    resetAction();
  }
  @Override
  public void setArraySizeAction(int arraySize) {
    this.arraySize = arraySize;
    resetAction();
  }
}
