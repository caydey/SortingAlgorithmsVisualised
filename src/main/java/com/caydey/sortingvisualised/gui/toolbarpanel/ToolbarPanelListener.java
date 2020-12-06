package com.caydey.sortingvisualised.gui.toolbarpanel;


import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;

public interface ToolbarPanelListener {
  public void setDelayAction(int ms);
  public void setArrayOrderAction(ArrayOrder order);
  public void setArraySizeAction(int size);
  public void setOperationsShownAction(OperationType operation, boolean shown);
}
