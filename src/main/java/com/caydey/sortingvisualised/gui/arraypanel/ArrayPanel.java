package com.caydey.sortingvisualised.gui.arraypanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.array.TrackedArray;

public class ArrayPanel extends JPanel implements ArrayOperationListener {
  private TrackedArray trackedArray;
  private int scale;
  private int panelSize;

  // the index of elements that had an operation done on it
  private int[] lastCompared =  new int[2];
  private int[] lastSwaped = new int[2];
  private int lastGet;
  private int lastSet;

  private boolean isArraySorted;

  // show operations
  private boolean showSwaps;
  private boolean showComparisons;
  private boolean showGets;
  private boolean showSets;

  public ArrayPanel(int panelSize) {
    this.panelSize = panelSize;
    setSize(panelSize, panelSize);
    // set last operations to null (-1)
    resetLastOperations();

    // dont show operations (default)
    showSwaps = false;
    showComparisons = false;
    showGets = false;
    showSets = false;
  }
  private void resetLastOperations() {
    lastCompared[0] = -1;
    lastCompared[1] = -1;
    lastSwaped[0] = -1;
    lastSwaped[1] = -1;
    lastGet = -1;
    lastSet = -1;
  }

  public void setTrackedArray(TrackedArray trackedArray) {
    isArraySorted = false; // given new array, assume its not sorted
    trackedArray.setOperationListener(this);  // so trackedarray can comunicate to this what is being compared with what etc

    this.trackedArray = trackedArray;
    scale = panelSize / trackedArray.length;

    resetLastOperations(); // operations that were done on previous array
    repaint();
  }
  public void setSorted() {
    isArraySorted = true;
    // set last operations to null
    resetLastOperations();
    repaint();
  }

  public void updatePanelSize(int panelSize) {
    this.panelSize = panelSize;
    scale = panelSize / trackedArray.length;
  }

  public void setShowSwaps(boolean showSwaps) { this.showSwaps = showSwaps; }
  public void setShowComparisons(boolean showComparisons) { this.showComparisons = showComparisons; }
  public void setShowGets(boolean showGets) { this.showGets = showGets; }
  public void setShowSets(boolean showSets) { this.showSets = showSets; }

  // Action listeners
  @Override
  public void swapAction(int indexA, int indexB) {
    if (showSwaps) {
      lastSwaped[0] = indexA;
      lastSwaped[1] = indexB;
      repaint();
    }
  }
  @Override
  public void compareAction(int indexA, int indexB) {
    if (showComparisons) {
      lastCompared[0] = indexA;
      lastCompared[1] = indexB;
      repaint();
    }
  }
  @Override
  public void getAction(int index) {
    if (showGets) {
      lastGet = index;
      repaint();
    }
  }
  @Override
  public void setAction(int index) {
    if (showSets) {
      lastSet = index;
    }
    // always repaint when an element is set
    repaint();
  }

  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D)g;

    if (trackedArray == null) { // if array is not defined
      return;
    }

    // clear
    g2d.clearRect(0,0, panelSize,panelSize);

    // bars
    g2d.setColor(Color.BLACK);
    int[] array = trackedArray.getArray();
    for (int i=0; i<array.length; i++) {
      // color
      if (isArraySorted) {
        g2d.setColor(Color.GREEN);
      } else {
        g2d.setColor(Color.BLACK);
        if (i == lastCompared[0] || i == lastCompared[1]) {
          g2d.setColor(Color.BLUE);
        } else if (i == lastSwaped[0] || i == lastSwaped[1]) {
          g2d.setColor(Color.RED);
        } else if (i == lastSet) {
          g2d.setColor(Color.RED);
        } else if (i == lastGet) {
          g2d.setColor(Color.YELLOW);
        }
      }
      // array element
      g2d.fillRect(i*scale,panelSize-((array[i]+1)*scale), scale,((array[i]+1)*scale));
    }
  }
}
