package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class InsertionSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    for (int i=1; i<array.length; i++) {
      for (int j=i; j>0; j--) {
        if (array.compareIndexes(j, j-1) < 0) { // j < j-1
          array.swap(j, j-1);
        }
      }
    }
  }
}
