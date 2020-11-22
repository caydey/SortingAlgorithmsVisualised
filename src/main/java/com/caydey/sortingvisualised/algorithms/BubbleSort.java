package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class BubbleSort implements SortingAlgorithm {
  public static void sort(TrackedArray array) {
    boolean swapped = true;
    int j=0;
    while (swapped) {
      swapped = false;
      j++;
      for (int i=0; i<array.length-j; i++) {
        if (array.greaterThan(i, i+1)) {
          array.swap(i, i+1);
          swapped = true;
        }
      }
    }
  }

}
