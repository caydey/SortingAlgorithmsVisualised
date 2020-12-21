package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class RadixSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    int n = array.length;

    for (int exp = 1; n / exp > 0; exp *= 10) {
      countSort(array, n, exp);
    }
  }

  private void countSort(TrackedArray array, int n, int exp) {
    int[] output = new int[n]; // output array

    // Initialize count occurrences array as zero
    int[] count = new int[10];
    for (int i=0; i<10; i++) { count[i] = 0; }

    // Store count of occurrences
    for (int i = 0; i < n; i++) {
      count[(array.get(i) / exp) % 10]++;
    }

    // Modify count array
    for (int i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }

    // Build output array
    for (int i = n - 1; i >= 0; i--) {
      int element = array.get(i);
      output[count[(element / exp) % 10] - 1] = element;
      count[(element / exp) % 10]--;
    }

    // Copy output array to actual array
    for (int i = 0; i < n; i++) {
      array.set(i, output[i]);
    }
  }
}
