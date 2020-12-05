package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

public class GnomeSort implements SortingAlgorithm {
  @Override
  public void sort(TrackedArray array) {
    int index = 0;

    while (index < array.length) {
      if (index == 0) {
        index++;
      }
      if (array.compareIndexes(index, index-1) >= 0) {
        index++;
      } else {
        array.swap(index, index-1);
        index--;
      }
    }
  }
}
