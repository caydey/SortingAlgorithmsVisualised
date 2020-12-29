package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class InsertionSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    insertionSort(array, 0, array.length);
  }

  public static void insertionSort(TrackedArray array, int start, int end) {
    for (int i=start+1; i<end; ++i) {
      int key = array.get(i);
      int j = i-1;

      array.logComparedIndexes(j,i);
      while (j>=0 && array.get(j) > key) {
        array.set(j+1, array.get(j));
        j--;
        array.logComparedIndexes(j,i);
      }
      array.set(j+1, key);
    }
  }
}
