package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

import java.util.ArrayList;

public class IntroSort implements SortingAlgorithm {
  private ArrayList<int[]> sortPoints = new ArrayList<int[]>();
  @Override
  public void sort(TrackedArray array) {
    int depth = getDepth(array.length);

    sortPoints.clear();

    // perform quicksort
    introSort(array, 0, array.length, depth);

    // perform insertion / heap sort on remaining parts
    for (int[] range : sortPoints) {
      if (range[1] - range[0] < 16) {
        InsertionSort.insertionSort(array, range[0], range[1]);
      } else {
        HeapSort.heapSort(array, range[0], range[1]);
      }
    }
  }

  private void introSort(TrackedArray array, int start, int end, int depth) {
    int size = end-start;
    // InsertionSort
    if (size < 16) {
      // InsertionSort.insertionSort(array, start, end);
      // perform insertionsort on points after quicksort (only for animation purposes)
      sortPoints.add(new int[] {start, end});
      return;
    }

    // HeapSort
    if (depth == 0) {
      // HeapSort.heapSort(array, start, end);
      // perform heapsort on points after quicksort (only for animation purposes)
      sortPoints.add(new int[] {start, end});
      return;
    }

    int partitionIndex = partition(array, start, end);
    introSort(array, start, partitionIndex, depth-1);
    introSort(array, partitionIndex+1, end, depth-1);
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
