package com.caydey.sortingvisualised.array;


import com.caydey.sortingvisualised.gui.arraypanel.ArrayOperationListener;


public class TrackedArray extends Array {
  protected ArrayOperations arrayOperations;

  private int delay;

  private final int relativeIndex;
  private TrackedArray masterArray;
  private boolean isMaster;

  // listener
  protected ArrayOperationListener operationListener;
  public void setOperationListener(ArrayOperationListener operationListener) {
    this.operationListener = operationListener;
  }

  public TrackedArray(int length, ArrayOrder order) {
    super(length, order);
    arrayOperations = new ArrayOperations(); // counts swaps, gets, sets & comparisons
    delay = 10; // 10ms delay by default

    // this is the master array, vars used for merging and splitting array
    relativeIndex = 0; // master array, for merging
    masterArray = this; // this is the master array
    isMaster = true; // this is the master array
  }

  // for when aray is split (mergeSort)
  private TrackedArray(int relativeIndex, int[] array, ArrayOperations arrayOperations, TrackedArray masterArray) {
    super(array); // set array vals
    this.arrayOperations = arrayOperations;
    this.relativeIndex = relativeIndex;
    this.masterArray = masterArray;
    this.isMaster = false;  // not master array
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }
  private void sleep() {
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  // operations
  public void swap(int indexA, int indexB) {
    operationListener.swapAction(indexA, indexB); // call to action listener
    arrayOperations.incSwaps(); // update operations count
    // swap
    int tmp = get(indexA);
    set(indexA, get(indexB));
    set(indexB, tmp);
  }

  public int get(int index) {
    operationListener.getAction(index); // call to action listener
    arrayOperations.incReads(); // update operations count
    // get
    return getElement(index);
  }

  public void set(int index, int val) {
    sleep();
    operationListener.setAction(index+relativeIndex); // call to action listener "set"Action as in an action that something has been set to something
    arrayOperations.incWrites(); // update operations count

    // set
    setElement(index, val);
    // set for master array
    if (!isMaster) { // spliced array then update master
      masterArray.set(index+relativeIndex, val);
    }
  }

  // Comparison
  public int compareIndexes(int a, int b) {
    sleep();  // delay
    operationListener.compareAction(a+relativeIndex, b+relativeIndex); // call to action listener
    arrayOperations.incComparisons(); // update operations count
    return (get(a) - get(b));
  }
  public void logComparedIndexes(int a, int b) {
    sleep();  // delay
    operationListener.compareAction(a+relativeIndex, b+relativeIndex); // call to action listener
    arrayOperations.incComparisons(); // update operations count
  }

  // for mergesort
  public TrackedArray getSlice(int start, int end) {
    // create int[] array slice
    int[] arraySlice = new int[end-start];
    for (int i=start; i<end; i++) {
      arraySlice[i-start] = get(i);
    }

    // convert int[] to TrackedArray
    TrackedArray trackedArraySlice = new TrackedArray(start+relativeIndex, arraySlice, this.arrayOperations, this.masterArray);
    trackedArraySlice.setOperationListener(this.operationListener);
    return trackedArraySlice;
  }

  // arrayOperations getters
  public int getSwaps() { return arrayOperations.getSwaps(); }
  public int getComparisons() { return arrayOperations.getComparisons(); }
  public int getReads() { return arrayOperations.getReads(); }
  public int getWrites() { return arrayOperations.getWrites(); }
}
