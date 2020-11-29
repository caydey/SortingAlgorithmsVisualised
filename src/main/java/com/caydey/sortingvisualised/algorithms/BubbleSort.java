package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class BubbleSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    boolean swapped = true;
    int j=0;
    while (swapped) {
      swapped = false;
      j++;
      for (int i=0; i<array.length-j; i++) {
        if (array.compareIndexes(i, i+1) > 0) { // i > i+1
          array.swap(i, i+1);
          swapped = true;
        }
      }
    }
  }

}
