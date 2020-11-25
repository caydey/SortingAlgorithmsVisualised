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
  private int[] lastCompared;
  private int[] lastSwaped;
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
    // set last operations to null
    lastCompared = new int[] {-1,-1};
    lastSwaped = new int[] {-1,-1};
    lastGet = -1;
    lastSet = -1;

    // dont show operations (default)
    showSwaps = false;
    showComparisons = false;
    showGets = false;
    showSets = false;
  }

  public void setTrackedArray(TrackedArray trackedArray) {
    isArraySorted = false; // given new array, assume its not sorted
    trackedArray.setOperationListener(this);  // so trackedarray can comunicate to this what is being compared with what etc
    this.trackedArray = trackedArray;

    scale = 500/(trackedArray.length);

    repaint();
  }
  public void sortedAction() {
    isArraySorted = true;
    repaint();
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

    // clean
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0,0, 500, 500);

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
      g2d.fillRect(i*scale,500-((array[i]+1)*scale), scale,((array[i]+1)*scale));
    }
  }
}
