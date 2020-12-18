package com.caydey.sortingvisualised.algorithms;

public enum Sorts {
  BITONIC("Bitonic Sort"),
  BOGO("Bogo Sort"),
  BUBBLE("Bubble Sort"),
  CIRCLE("Circle Sort"),
  COCKTAIL("Cocktail Sort"),
  COMB("Comb Sort"),
  GNOME("Gnome Sort"),
  HEAP("Heap Sort"),
  INSERTION("Insertion Sort"),
  INTRO("Intro Sort"),
  MERGE("Merge Sort"),
  ODDEVEN("Odd-even Sort"),
  PANCAKE("Pancake Sort"),
  QUICK("Quick Sort"),
  SELECTION("Selection Sort"),
  SHELL("Shell Sort"),
  TIM("Tim Sort");

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
      if (sort.name.equals(name)) {
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
      case BITONIC:
        return new BitonicSort();
      case BOGO:
        return new BogoSort();
      case BUBBLE:
        return new BubbleSort();
      case CIRCLE:
        return new CircleSort();
      case COCKTAIL:
        return new CocktailSort();
      case COMB:
        return new CombSort();
      case GNOME:
        return new GnomeSort();
      case HEAP:
        return new HeapSort();
      case INSERTION:
        return new InsertionSort();
      case INTRO:
        return new IntroSort();
      case MERGE:
        return new MergeSort();
      case ODDEVEN:
        return new OddevenSort();
      case PANCAKE:
        return new PancakeSort();
      case QUICK:
        return new QuickSort();
      case SELECTION:
        return new SelectionSort();
      case SHELL:
        return new ShellSort();
      case TIM:
        return new TimSort();
      default:
        return null;
    }
  }

}
