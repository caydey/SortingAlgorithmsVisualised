package com.caydey.sortingvisualised.gui;


import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;

public class SortArray implements Runnable {
  private TrackedArray array;
  private SortingAlgorithm algorithm;

  private boolean isTerminating;

  // casting sort to SortingAlgorithm throws comp error, bug with something (online research)
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
      array.sorted();
    }
  }
}
