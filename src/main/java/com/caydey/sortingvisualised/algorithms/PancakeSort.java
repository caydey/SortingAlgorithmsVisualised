package com.caydey.sortingvisualised.algorithms;


import com.caydey.sortingvisualised.array.TrackedArray;

public class PancakeSort implements SortingAlgorithm {
  public static void sort(TrackedArray array) {
    int n = array.length;
    for (int curr_size = n; curr_size>1; --curr_size) {
      int max = getMax(array, curr_size);

      // Move max element to curr_size
      if (max != curr_size-1) {
        flip(array, max);
        flip(array, curr_size-1);
      }
    }
  }

  // Flip array [0..end]
  private static void flip(TrackedArray array, int end) {
    int start = 0;
    while (start < end) {
      array.swap(start++, end--);
    }
  }

  // Index of maximum element between [0..end-1]
  private static int getMax(TrackedArray array, int end) {
    int max = 0;
    for (int i=1; i<end; i++) {
      if (array.greaterThan(i, max)) {
        max = i;
      }
    }
    return max;
  }
}
