package com.caydey.sortingvisualised.array;

import java.util.Random;

public abstract class Array {
  private int[] array;
  public final int length;

  public Array(int length, ArrayOrder order) {
    array = new int[length];
    this.length = length;

    switch (order) {
      case SORTED:
        sorted();
        break;
      case REVERSED:
        reversed();
        break;
      case RANDOMIZED:
        randomized();
        break;
    }
  }

  public Array(int[] array) {
    this.array = array;
    this.length = array.length;
  }

  // protected void swapElements(int a, int b) {
  //   int tmp = array[a];
  //   array[a] = array[b];
  //   array[b] = tmp;
  // }

  protected int getElement(int index) {
    return array[index];
  }
  protected void setElement(int index, int val) {
    array[index] = val;
  }

  private void sorted() {
    for (int i=0; i<array.length; i++) {
      array[i] = i;
    }
  }
  private void reversed() {
    for (int i=0; i<array.length; i++) {
      array[i] = array.length-i;
    }
  }
  private void randomized() {
    // get sorted array then shuffle it
    sorted();

    // Fisher–Yates shuffle
    Random rnd = new Random();
    for (int i=array.length-1; i>0; i--){
      int index = rnd.nextInt(i+1); // random int between 0 & i+1
      // swapElements(index, i);
      int tmp = array[index];
      array[index] = array[i];
      array[i] = tmp;
    }
  }

  @Override
  public String toString() {
    String string = "";

    // for (int i=0; i<array.length; i++) {
    //   for (int j=array.length-1; j>=0; j--) {
    //     if (array[j] <= i) {
    //       string += "#";
    //     } else {
    //       string += " ";
    //     }
    //   }
    //   string +="\n";
    // }

    string += "[";
    for (int i=0; i<array.length; i++) {
      string += array[i];
      if (i != array.length-1) { string += ", "; } // No trailing comma
    }
    string += "]";

    return string;
  }

  public int[] getArray() {
    return array;
  }
}
