package com.caydey.sortingvisualised.gui;


import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.algorithms.*;

public class SortArray implements Runnable {
  private TrackedArray array;
  private SortingAlgorithm algorithm;

  // casting sort to SortingAlgorithm throws comp error, bug with something (online research)
  public SortArray(TrackedArray array, SortingAlgorithm algorithm) {
    this.array = array;
    this.algorithm = algorithm;
  }

  @Override
  public void run() {
    algorithm.sort(array);
  }
}
