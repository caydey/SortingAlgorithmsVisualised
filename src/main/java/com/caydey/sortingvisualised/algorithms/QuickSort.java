package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class QuickSort implements SortingAlgorithm {
  public static void sort(TrackedArray array) {
    quickSort(array, 0, array.length-1);
  }

  private static void quickSort(TrackedArray array, int start, int end) {
    if (start < end) {
      int partitionIndex = partition(array, start, end);

      // recursive
      quickSort(array, start, partitionIndex-1);
      quickSort(array, partitionIndex+1, end);
    }
  }
  private static int partition(TrackedArray array, int start, int end) {
    int pivot = array.get(end);

    int i = start-1;

    for (int j=start; j<end; j++) {
      array.incComparisons();
      if (array.get(j) <= pivot) {
        i++;
        array.swap(i, j);
      }
    }
    array.swap(i+1, end);

    return (i+1);
  }
}
