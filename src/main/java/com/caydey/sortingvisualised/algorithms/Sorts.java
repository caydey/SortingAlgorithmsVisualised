package com.caydey.sortingvisualised.algorithms;

public enum Sorts {
  BOGO("Bogo Sort"),
  BUBBLE("Bubble Sort"),
  COCKTAIL("Cocktail Sort"),
  COMB("Comb Sort"),
  HEAP("Heap Sort"),
  INSERTION("Insertion Sort"),
  PANCAKE("Pancake Sort"),
  QUICK("Quick Sort");

  private String name;

  Sorts(String name) {
    this.name = name;
  }

  public static String[] getList() {
    Sorts[] sorts = Sorts.values();
    String[] list = new String[sorts.length];
    for (int i=0; i<sorts.length; i++) {
      list[i] = sorts[i].name;
    }
    return list;
  }

  public static Sorts getSortFromName(String name) {
    for (Sorts sort : Sorts.values()) {
      if (sort.getName().equals(name)) {
        return sort;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public SortingAlgorithm getSortObject() {
    switch (this) {
      case BOGO:
        return new BogoSort();
      case BUBBLE:
        return new BubbleSort();
      case COCKTAIL:
        return new CocktailSort();
      case COMB:
        return new CombSort();
      case HEAP:
        return new HeapSort();
      case INSERTION:
        return new InsertionSort();
      case PANCAKE:
        return new PancakeSort();
      case QUICK:
        return new QuickSort();
      default:
        return null;
    }
  }

}
