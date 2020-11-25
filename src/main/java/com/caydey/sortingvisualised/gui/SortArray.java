package com.caydey.sortingvisualised.gui;


import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;

import com.caydey.sortingvisualised.algorithms.BogoSort;

public class SortArray implements Runnable {
  private TrackedArray array;
  private SortingAlgorithm algorithm;

  private boolean isTerminating;

  private ArraySortedListener arraySortedListener;
  public void setArraySortedListener(ArraySortedListener arraySortedListener) {
    this.arraySortedListener = arraySortedListener;
  }

  public SortArray(TrackedArray array, SortingAlgorithm algorithm) {
    this.array = array;
    this.algorithm = algorithm;
    isTerminating = false;
  }
  public void setTerminating() {
    isTerminating = true;
  }

  @Override
  public void run() {
    algorithm.sort(array);
    if (!isTerminating) { // if sort is terminated dont sent sorted signal to array
      arraySortedListener.arraySortedAction();
    }
  }
}
