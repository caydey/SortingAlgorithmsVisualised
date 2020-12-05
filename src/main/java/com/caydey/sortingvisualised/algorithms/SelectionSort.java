package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class SelectionSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    for (int i=0; i<array.length-1; i++) {
      int smallest = i;
      for (int j=i+1; j<array.length; j++) {
        if (array.compareIndexes(j, smallest) < 0) {
          smallest = j;
        }
      }
      array.swap(i, smallest);
    }
  }
}
