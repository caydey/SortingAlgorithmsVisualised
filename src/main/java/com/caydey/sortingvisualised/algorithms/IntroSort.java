package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class IntroSort implements SortingAlgorithm {
  // initialize HeapSort object as global
  private static HeapSort heapSort = new HeapSort();
  @Override
  public void sort(TrackedArray array) {
    int depth = getDepth(array.length);

    introSort(array, 0, array.length, depth);
  }

  private void introSort(TrackedArray array, int start, int end, int depth) {
    if (end-start <= 1) {
      return;
    }
    if (depth == 0) {
      heapSort.sort(array.getSlice(start, end));
    } else {
      int partitionIndex = partition(array, start, end);
      introSort(array, start, partitionIndex, depth-1);
      introSort(array, partitionIndex+1, end, depth-1);
    }
  }

  private int partition(TrackedArray array, int start, int end) {
    int pivot = array.get(end-1);
    int i = start-1;

    for (int j=start; j<end; j++) {
      array.logComparedIndexes(j, end); // only needed to log comparison
      if (array.get(j) <= pivot) {
        i++;
        array.swap(i, j);
      }
    }

    return i;
  }

  private int getDepth(int arrayLength) {
    return (int)(Math.floor(Math.log(arrayLength)/Math.log(2)));
  }
}
