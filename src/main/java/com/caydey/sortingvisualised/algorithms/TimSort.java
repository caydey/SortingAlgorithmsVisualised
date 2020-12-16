package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class TimSort implements SortingAlgorithm {
  private final int RUN = 32;

  @Override
  public void sort(TrackedArray array) {
    timSort(array, array.length);
  }

  private void timSort(TrackedArray array, int len) {
    // Insertion sort on 32 length parts of array [0..31] [32..63] [64..95] [96..127]
    for (int i=0; i<len; i+=RUN) {
      insertionSort(array, i, Math.min(i+31, len-1));
    }

    // Merge sorted array parts
    for (int size=RUN; size<len; size*=2) { // size = 32, 64, 128...
      for (int left=0; left<len; left+=2*size) {
        int mid = left+size-1;
        int right = Math.min((left + 2*size - 1), (len-1));

        // [left..mid+1] [mid+1..right]
        merge(array, left, mid, right);
      }
    }
  }

  // Modified method taken from InsertionSort.java
  private void insertionSort(TrackedArray array, int left, int right) {
    for (int i=left+1; i<=right; i++) {
      int key = array.get(i);
      int j = i-1;

      array.logComparedIndexes(j,i);
      while (j>=left && array.get(j) > key) {
        array.logComparedIndexes(j,i);
        array.set(j+1, array.get(j));
        j--;
      }
      array.set(j+1, key);
    }
  }

  // Modified method taken from MergeSort.java
  private void merge(TrackedArray array, int start, int mid, int end) {
    // split array
    int leftLen = mid-start+1;
    int rightLen = end-mid;

    TrackedArray left = array.getSlice(start, start+leftLen);
    TrackedArray right = array.getSlice(mid+1, mid+1+rightLen);

    int i=0, j=0, k=start;
    while (i < leftLen && j < rightLen) {
      array.logComparedIndexes(start+i, mid+j);
      if (left.get(i) <= right.get(j)) {
        array.set(k++, left.get(i++));
      } else {
        array.set(k++, right.get(j++));
      }
    }

    while (i < leftLen) {
      array.set(k++, left.get(i++));
    }
    while (j < rightLen) {
      array.set(k++, right.get(j++));
    }
  }
}
