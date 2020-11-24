package com.caydey.sortingvisualised.gui.controlpanel;


import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;

public interface ControlPanelListener {
  public void startAction();
  public void resetAction();
  public void setDelayAction(int ms);
  public void setSortingAlgorithmAction(SortingAlgorithm sortingAlgorithm);
  public void setArrayOrderAcion(ArrayOrder order);
  public void setArraySizeAction(int size);
}
