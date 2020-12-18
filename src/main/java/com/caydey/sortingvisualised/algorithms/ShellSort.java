package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class ShellSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    int n = array.length;

    for (int gap=n/2; gap>0; gap/=2) {
      for (int i=gap; i<n; i++) {
        int key = array.get(i);
        int j = i;

        array.logComparedIndexes(j-gap,i);
        while (j >= gap && array.get(j-gap) >  key) {
          array.set(j, array.get(j-gap));
          j -= gap;
          array.logComparedIndexes(j-gap,i);
        }
        array.set(j, key);
      }
    }
  }
}
