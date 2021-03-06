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

    // resize panel as window is resized
    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent componentEvent) {
        int width = getWidth();
        int height = getHeight();
        int min = (width < height) ? width : height;

        setPanelSize(min);
      }
    });
  }

  private void setPanelSize(int panelSize) {
    renderer.setPanelSize(panelSize); // update size for renderer
    setPreferredSize(new Dimension(panelSize, panelSize));  // update size of JPanel
  }

  public void setSorted() {
    renderer.startSortedAnimation();
  }

  public void setTrackedArray(TrackedArray trackedArray) {
    renderer.setTrackedArray(trackedArray);
    repaint();
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
