package com.caydey.sortingvisualised.gui.arraypanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

import com.caydey.sortingvisualised.array.TrackedArray;

import com.caydey.sortingvisualised.gui.arraypanel.renderer.*;

public class ArrayPanel extends JPanel {
  ArrayRenderer renderer;

  public ArrayPanel() {
    // layout
    setLayout(new BorderLayout());

    renderer = new RainbowBarRenderer();
    // renderer = new BarRenderer();
    add(renderer, BorderLayout.CENTER); // fill
  }

  public void setSorted() {
    System.out.println("sorted");
  }

  public void setTrackedArray(TrackedArray trackedArray) {
    trackedArray.setOperationListener(renderer);  // so trackedarray can comunicate to this what is being compared with what etc
    renderer.setTrackedArray(trackedArray);
  }
  public void setPanelSize(int panelSize) {
    renderer.setPanelSize(panelSize); // update size for renderer
    setSize(panelSize, panelSize);  // update size of JPanel
  }

  public void setShowSwaps(boolean showSwaps) {
    renderer.setShowSwaps(showSwaps);
  }
  public void setShowComparisons(boolean showComparisons) {
    renderer.setShowComparisons(showComparisons);
  }
  public void setShowGets(boolean showGets) {
    renderer.setShowGets(showGets);
  }
  public void setShowSets(boolean showSets) {
    renderer.setShowSets(showSets);
  }
}
