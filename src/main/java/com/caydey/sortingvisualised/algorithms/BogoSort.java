package com.caydey.sortingvisualised.algorithms;

import com.caydey.sortingvisualised.array.TrackedArray;

import java.util.Random;

public class BogoSort implements SortingAlgorithm {
  // need stop variable and method because while loop causes some problems when terminating BogoSort Thread
  private boolean stop;
  public void stop() { stop = true; }
  @Override
  public void sort(TrackedArray array) {
    stop = false;
    while (!isSorted(array) && !stop) {
      shuffle(array);
    }
  }

  private void shuffle(TrackedArray array) {
    Random rnd = new Random();
    for (int i=array.length-1; i>0; i--){
      int index = rnd.nextInt(i+1); // random int between 0 & i+1
      array.swap(index, i);
    }
  }

  private boolean isSorted(TrackedArray array) {
    for (int i=0; i<array.length-1; i++) {
      if (array.greaterThan(i, i+1)) {
        return false;
      }
    }
    return true;
  }
}
