package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class CombSort implements SortingAlgorithm {
  public void sort(TrackedArray array) {
    int gap = array.length;

    boolean swapped = true;
    while (gap != 1 || swapped) {
      // gap
      gap = (gap*10)/13;
      if (gap < 1) { gap = 1; }

      swapped = false;
      for (int i=0; i<array.length-gap; i++) {
        if (array.greaterThan(i, i+gap)) {
          array.swap(i, i+gap);
          swapped = true;
        }
      }
    }
  }
}
