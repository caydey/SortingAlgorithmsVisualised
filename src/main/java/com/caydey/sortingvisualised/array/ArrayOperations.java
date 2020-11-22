package com.caydey.sortingvisualised.array;

public class ArrayOperations {
  private int comparisons;
  private int swaps;
  private int reads;
  private int writes;

  public ArrayOperations() {
    comparisons = 0;
    swaps = 0;
    reads = 0;
    writes = 0;
  }

  public void incSwaps() { swaps++; }
  public void incComparisons() { comparisons++; }
  public void incReads() { reads++; }
  public void incWrites() { writes++; }

  public int getComparisons() { return comparisons; }
  public int getSwaps() { return swaps; }
  public int getReads() { return reads; }
  public int getWrites() { return writes; }
}
