package com.caydey.sortingvisualised.array;


import com.caydey.sortingvisualised.gui.arraypanel.ArrayOperationListener;


import java.util.Timer;
import java.util.TimerTask;

public class TrackedArray extends Array {
  private ArrayOperations arrayOperations;

  private int counter = 0;

  // listener
  private ArrayOperationListener operationListener;
  public void setOperationListener(ArrayOperationListener operationListener) {
    this.operationListener = operationListener;
  }

  public TrackedArray(int length, ArrayOrder order) {
    super(length, order);
    arrayOperations = new ArrayOperations();
  }

  // for when aray is split (mergeSort)
  private TrackedArray(int[] array, ArrayOperations arrayOperations) {
    super(array);
    this.arrayOperations = arrayOperations;
  }

  private void sleep(int delay) {
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  // operations
  public void swap(int indexA, int indexB) {
    sleep(10);
    operationListener.swapAction(indexA, indexB);
    // System.out.println(this);
    arrayOperations.incSwaps();
    int tmp = get(indexA);
    set(indexA, get(indexB));
    set(indexB, tmp);
  }

  public int get(int index) {
    arrayOperations.incReads();
    return getElement(index);
  }
  public void set(int index, int val) {
    arrayOperations.incWrites();
    setElement(index, val);
  }


  public TrackedArray getSlice(int start, int end) {
    // create int[] array slice
    int[] arraySlice = new int[end-start];
    for (int i=start; i<end; i++) {
      arraySlice[i-start] = get(i);
    }

    // convert int[] to TrackedArray
    TrackedArray trackedArraySlice = new TrackedArray(arraySlice, arrayOperations);
    return trackedArraySlice;
  }


  // Comparisons
  public boolean greaterThan(int a, int b) {
    arrayOperations.incComparisons();
    return (get(a) > get(b));
  }
  public boolean lessThan(int a, int b) {
    arrayOperations.incComparisons();
    return (get(a) < get(b));
  }
  public boolean greaterThanEquals(int a, int b) {
    arrayOperations.incComparisons();
    return (get(a) >= get(b));
  }
  public boolean lessThanEquals(int a, int b) {
    arrayOperations.incComparisons();
    return (get(a) <= get(b));
  }

  public void incComparisons() {
    arrayOperations.incComparisons();
  }


  // arrayOperations getters
  public int getSwaps() { return arrayOperations.getSwaps(); }
  public int getComparisons() { return arrayOperations.getComparisons(); }
  public int getReads() { return arrayOperations.getReads(); }
  public int getWrites() { return arrayOperations.getWrites(); }
}
