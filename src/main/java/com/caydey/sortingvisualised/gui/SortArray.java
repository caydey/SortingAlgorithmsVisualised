package com.caydey.sortingvisualised.gui;


import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.algorithms.*;

public class SortArray implements Runnable {
  private TrackedArray array;

  public SortArray(TrackedArray array) {
    this.array = array;
  }

  @Override
  public void run() {
    // SortingAlgorithm sortingAlgorithm = new InsertionSort();
    // sortingAlgorithm.sort(array);
    InsertionSort.sort(array);
  }
}
