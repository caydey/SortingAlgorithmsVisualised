package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class HeapSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    heapSort(array, 0, array.length);
  }

  public static void heapSort(TrackedArray array, int low, int high) {
    int length = high-low;
    // building heap
    for (int i = length/2 - 1; i >= 0; i--) {
      heapify(array, length, low, i);
    }
    // Extract elements from heap
    for (int i=length-1; i>=0; i--) {
      array.swap(low, low+i);
      heapify(array, i, low, 0);
    }
  }

  private static void heapify(TrackedArray array, int length, int low, int index) {
    int largest = index;
    int left = 2*index + 1;// left of binary tree
    int right = 2*index + 2;// right of binary tree

    // set left to root-index if greater
    if (left < length && array.compareIndexes(low+left, low+largest) > 0) {
      largest = left;
    }
    // set right to root-index if greater
    if (right < length && array.compareIndexes(low+right, low+largest) > 0) {
      largest = right;
    }

    if (largest != index) {
      array.swap(low+index, low+largest);
      // Recursively heapify the affected sub-tree
      heapify(array, length, low, largest);
    }
  }
}
