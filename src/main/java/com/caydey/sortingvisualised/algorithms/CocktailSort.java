package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class CocktailSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    boolean swapped = true;
    int start = 0;
    int end = array.length;

    while (swapped) {
      swapped = false;
      for (int i=start; i<end-1; ++i) {
        if (array.greaterThan(i, i+1)) {
          array.swap(i, i+1);
          swapped = true;
        }
      }
      end--;

      if (!swapped) {
        break;
      }

      swapped = false;
      for (int i=end-1; i>=start; i--) {
        if (array.greaterThan(i, i+1)) {
          array.swap(i, i+1);
          swapped = true;
        }
      }
      start++;
    }
  }
}
