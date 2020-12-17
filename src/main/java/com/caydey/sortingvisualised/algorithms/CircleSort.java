package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class CircleSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    int len = array.length;
    // loop untill no swaps
    while (circleSortRec(array, 0, len-1));
  }

  private boolean circleSortRec(TrackedArray array, int low, int high) {
    boolean swapped = false;
    if (low == high) {
      return false;
    }

    int lo = low;
    int hi = high;

    while (lo < hi) {
      if (array.compareIndexes(lo, hi) > 0) {
        array.swap(lo, hi);
        swapped = true;
      }
      lo++;
      hi--;
    }

    if (lo == hi && array.compareIndexes(lo, hi+1) > 0) {
      array.swap(low, hi+1);
      swapped = true;
    }

    int mid = (high-low) / 2;
    boolean firstHalf = circleSortRec(array, low, low+mid);
    boolean secondHalf = circleSortRec(array, low+mid+1, high);

    return (swapped || firstHalf || secondHalf);
  }

}
