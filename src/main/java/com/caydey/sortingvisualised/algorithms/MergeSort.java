package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class MergeSort implements SortingAlgorithm {
  public static void sort(TrackedArray array) {
    mergeSort(array, array.length);
  }

  private static void mergeSort(TrackedArray array, int end) {
    if (end >= 2) {
      // split array
      int mid = end / 2;
      TrackedArray left = array.getSlice(0, mid);
      TrackedArray right = array.getSlice(end-mid, array.length);

      // recursive
      mergeSort(left, mid);
      mergeSort(right, end-mid);

      merge(array, left, right, mid, end-mid);
    }
  }

  private static void merge(TrackedArray array, TrackedArray left, TrackedArray right, int leftPos, int rightPos) {
    int i=0, j=0, k=0;
    while (i < leftPos && j < rightPos) {
      // make into trackedArray method left.greaterthanequals(i, right, j)
      array.incComparisons();
      if (left.get(i) <= right.get(j)) {
        array.set(k++, left.get(i++));
      } else {
        array.set(k++, right.get(j++));
      }
    }

    while (i < leftPos) {
      array.set(k++, left.get(i++));
    }
    while (j < rightPos) {
      array.set(k++, right.get(j++));
    }
  }

}
