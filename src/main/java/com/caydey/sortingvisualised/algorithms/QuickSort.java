package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class QuickSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    quickSort(array, 0, array.length-1);
  }

  private void quickSort(TrackedArray array, int start, int end) {
    if (start < end) {
      int partitionIndex = partition(array, start, end);

      // recursive
      quickSort(array, start, partitionIndex-1);
      quickSort(array, partitionIndex+1, end);
    }
  }

  private int partition(TrackedArray array, int start, int end) {
    int pivot = array.get(end);
    int i = start;

    for (int j=start; j<end; j++) {
      array.logComparedIndexes(j, end); // only needed to log comparison
      if (array.get(j) <= pivot) {
        array.swap(i, j);
        i++;
      }
    }
    array.swap(i, end);

    return i;
  }
}
