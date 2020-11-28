package com.caydey.sortingvisualised.gui.arraypanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

import com.caydey.sortingvisualised.array.TrackedArray;

public class ArrayPanel extends JPanel implements ArrayOperationListener {
  private TrackedArray trackedArray;
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

    resetLastOperations(); // reset operations that were done on previous array
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
  }

  public void setShowSwaps(boolean showSwaps) {
    this.showSwaps = showSwaps;
    // reset swaps
    lastSwaped[0] = -1;
    lastSwaped[1] = -1;
  }
  public void setShowComparisons(boolean showComparisons) {
    this.showComparisons = showComparisons;
    // reset comparisons
    lastCompared[0] = -1;
    lastCompared[1] = -1;
  }
  public void setShowGets(boolean showGets) {
    this.showGets = showGets;
    // reset gets
    lastGet = -1;
  }
  public void setShowSets(boolean showSets) {
    this.showSets = showSets;
    // reset sets
    lastSet = -1;
  }

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

    // if array is not defined
    if (trackedArray == null) { return; }


    int arrayLength = trackedArray.length;

    // create square image, arrayLength x arrayLength
    BufferedImage img = new BufferedImage(arrayLength, arrayLength, BufferedImage.TYPE_INT_RGB);
    Graphics2D imgGraphics = img.createGraphics();  // convert to Graphics2D object

    // clear
    imgGraphics.setColor(Color.WHITE);
    imgGraphics.fillRect(0,0, arrayLength, arrayLength);

    // bars
    int[] array = trackedArray.getArray();
    for (int i=0; i<array.length; i++) {
      // color
      if (isArraySorted) {
        imgGraphics.setColor(Color.GREEN);
      } else {
        imgGraphics.setColor(Color.BLACK);
        if (i == lastCompared[0] || i == lastCompared[1]) {
          imgGraphics.setColor(Color.BLUE);
        } else if (i == lastSwaped[0] || i == lastSwaped[1]) {
          imgGraphics.setColor(Color.RED);
        } else if (i == lastSet) {
          imgGraphics.setColor(Color.RED);
        } else if (i == lastGet) {
          imgGraphics.setColor(Color.YELLOW);
        }
      }
     // draw array item
     imgGraphics.fillRect(i,arrayLength-((array[i]+1)), 1,((array[i]+1)));
    }
    // streach image to fit on window
    g2d.drawImage(img, 0,0, panelSize,panelSize, null);
  }
}
