package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class BitonicSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    bitonicSort(array, 0, array.length, true);
  }

  public void bitonicSort(TrackedArray array, int left, int length, boolean isAscending) {
    if (length > 1) {
      int k = length/2;
      bitonicSort(array, left, k, true);
      bitonicSort(array, left+k, k, false);

      // recursive
      merge(array, left, length, isAscending);
    }
  }

  void compareSwap(TrackedArray array, int a, int b, boolean isAscending) {
    if ((array.compareIndexes(a,b) > 0 && isAscending) || (array.compareIndexes(a,b) < 0 && !isAscending)) {
      array.swap(a,b);
    }
  }

  void merge(TrackedArray array, int left, int length, boolean isAscending) {
    if (length > 1) {
      int k = length/2;
      for (int i=left; i<left+k; i++) {
        compareSwap(array, i, i+k, isAscending);
      }
      merge(array, left, k, isAscending);
      merge(array, left+k, k, isAscending);
    }
  }
}
