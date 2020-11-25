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
  private int arrayDelay;
  private ArrayOrder arrayOrder;

  private SortArray sortingRunnable;
  private Thread sortingThread;

  private boolean isSorting;
  private boolean isArraySorted;

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

    // initialize TrackedArray as randomized of 500 elements (default)
    arraySize = 500;
    arrayOrder = ArrayOrder.RANDOMIZED;
    trackedArray = new TrackedArray(arraySize, arrayOrder);
    arrayPanel.setTrackedArray(trackedArray);
    // initialize TrackedArray delay to 10ms
    arrayDelay = 10;

    // initialize SortingAlgorithm as QuickSort
    sortingAlgorithm = new QuickSort();

    isSorting = false; // true when array is being sorted
    isArraySorted = false; // true when array has had sorting algorithm applied to it


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
      if (isArraySorted) {  // if array has been sorted and user wants to sort it again, reshuffle array
        resetAction();
      }
      isSorting = true;
      sortingRunnable = new SortArray(trackedArray, sortingAlgorithm);
      sortingRunnable.setArraySortedListener(new ArraySortedListener() {
        @Override
        public void arraySortedAction() {
          isSorting = false;
          isArraySorted = true;
          arrayPanel.sortedAction();  // call to ArrayPanel to highlight array green
        }
      });
      sortingThread = new Thread(sortingRunnable);
      sortingThread.start();
    }
  }
  @Override
  public void resetAction() {
    if (isSorting) { // if array is being sorted, terminate that bitch
      sortingRunnable.setTerminating(); // tell SortArray that its terminating

      // terminate
      sortingThread.interrupt();  // interrupt thread
      while (sortingThread.isAlive()) { } // halt entire program code execution untill thread is kill and in a shallow grave

      isSorting = false; // not sorting array
      isArraySorted = false; // array has not been sorted
    }

    // create new array, (sorting algorithm is defined in startAction)
    trackedArray = new TrackedArray(arraySize, arrayOrder);
    trackedArray.setDelay(arrayDelay);
    arrayPanel.setTrackedArray(trackedArray);
  }
  @Override
  public void setDelayAction(int ms) {
    arrayDelay = ms;
    trackedArray.setDelay(ms);
  }
  @Override
  public void setSortingAlgorithmAction(SortingAlgorithm sortingAlgorithm) {
    this.sortingAlgorithm = sortingAlgorithm;
  }
  @Override
  public void setArrayOrderAction(ArrayOrder order) {
    if (arrayOrder != order) {
      arrayOrder = order;
      resetAction();
    }
  }
  @Override
  public void setArraySizeAction(int size) {
    if (arraySize != size) {
      arraySize = size;
      resetAction();
    }
  }
}
