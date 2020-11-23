package com.caydey.sortingvisualised.gui.arraypanel;

public interface ArrayOperationListener {
  public void swapAction(int indexA, int indexB);
  public void compareAction(int indexA, int indexB);
  public void getAction(int index);
  public void setAction(int index);
  public void sortedAction();
}
