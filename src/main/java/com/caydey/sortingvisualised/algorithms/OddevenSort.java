package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class OddevenSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    int len = array.length;
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i=1; i<len-1; i+=2) {
        if (array.compareIndexes(i, i+1) > 0) {
          array.swap(i, i+1);
          sorted = false;
        }
      }
      for (int i=0; i<len-1; i+=2) {
        if (array.compareIndexes(i, i+1) > 0) {
          array.swap(i, i+1);
          sorted = false;
        }
      }
    }
  }
}
