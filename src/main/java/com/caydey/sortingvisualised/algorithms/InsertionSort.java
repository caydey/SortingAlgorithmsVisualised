package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class InsertionSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    for (int i=1; i<array.length; ++i) {
      int key = array.get(i);
      int j = i-1;

      array.logComparedIndexes(j,i);
      while (j>=0 && array.get(j) > key) {
        array.logComparedIndexes(j,i);
        array.set(j+1, array.get(j));
        j--;
      }
      array.set(j+1, key);
    }
  }
}
