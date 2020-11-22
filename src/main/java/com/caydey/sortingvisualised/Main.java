package com.caydey.sortingvisualised;


import com.caydey.sortingvisualised.array.*;
import com.caydey.sortingvisualised.algorithms.*;

public class Main {
  public static void main(String[] args) {

    TrackedArray array = new TrackedArray(16, ArrayOrder.RANDOMIZED);
    System.out.println(array);
    // CocktailSort.sort(array);
    // CombSort.sort(array);
    // BubbleSort.sort(array);
    // PancakeSort.sort(array);
    // InsertionSort.sort(array);
    // BogoSort.sort(array);
    // MergeSort.sort(array);
    // QuickSort.sort(array);
    // HeapSort.sort(array);
    System.out.println(array);

    System.out.println(String.format("Swaps: %d, Comparisons: %d, Reads: %d, Writes: %d", array.getSwaps(), array.getComparisons(), array.getReads(), array.getWrites()));
  }
}
