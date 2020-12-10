package com.caydey.sortingvisualised.gui.arraypanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

import com.caydey.sortingvisualised.array.TrackedArray;

import com.caydey.sortingvisualised.gui.arraypanel.renderer.*;

import com.caydey.sortingvisualised.gui.WindowFrame;

public class ArrayPanel extends JPanel {
  private ArrayRenderer renderer;

  public ArrayPanel(ArrayRenderer renderer) {
    // layout
    setLayout(new BorderLayout());
    this.renderer = renderer;

    add(renderer, BorderLayout.CENTER); // fill
  }

  public void setSorted() {
    renderer.sortedAnimation();
  }

  public void setTrackedArray(TrackedArray trackedArray) {
    renderer.setTrackedArray(trackedArray);
    repaint();
  }
  public void setPanelSize(int panelSize) {
    renderer.setPanelSize(panelSize); // update size for renderer
    setSize(panelSize, panelSize);  // update size of JPanel
  }

  public void setArrayRenderer(ArrayRenderer newRenderer) {
    remove(renderer); // remove current renderer from panel

    // add new renderer to panel
    add(newRenderer, BorderLayout.CENTER);

    // transfer field values of current renderer to new renderer (if swaps are shown, size, array details, ...)
    renderer.transferPropertiesTo(newRenderer);

    validate(); // update panel

    renderer = newRenderer; // set new renderer to current
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
