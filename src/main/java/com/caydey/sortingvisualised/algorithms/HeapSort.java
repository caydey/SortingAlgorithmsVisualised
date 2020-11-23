package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class HeapSort implements SortingAlgorithm {
  public void sort(TrackedArray array) {
    int length = array.length;

    // Build heap (rearrange array)
    for (int i=(length/2)-1; i>=0; i--) {
      heapify(array, length, i);
    }

    // One by one extract an element from heap
    for (int i=length-1; i>0; i--) {
      // Move current root to end
      array.swap(0, i);

      // call max heapify on the reduced heap
      heapify(array, i, 0);
    }
  }

  // To heapify a subtree rooted with node i which is
  // an index in arr[]. n is size of heap
  private void heapify(TrackedArray array, int length, int i) {
    int largest = i; // Initialize largest as root
    int left = (2*i)+1;
    int right = (2*i)+2;

    // If left child is larger than root
    if (left < length && array.greaterThan(left, largest)) {
      largest = left;
    }

    // If right child is larger than largest so far
    if (right < length && array.greaterThan(right, largest)) {
      largest = right;
    }

    // If largest is not root
    if (largest != i) {
      array.swap(i, largest);

      // Recursively heapify the affected sub-tree
      heapify(array, length, largest);
    }
  }
}
