package com.caydey.sortingvisualised.gui.toolbarpanel;


import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.array.ArrayOrder;
import com.caydey.sortingvisualised.gui.arraypanel.renderer.ArrayRenderer;

public interface ToolbarPanelListener {
  public void setArrayOrderAction(ArrayOrder order);
  public void setArraySizeAction(int size);
  public void setDelayAction(int ms);
  public void setOperationsShownAction(OperationType operation, boolean shown);
  public void setArrayRendererAction(ArrayRenderer arrayRenderer);
}
