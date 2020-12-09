package com.caydey.sortingvisualised.gui.arraypanel.renderer;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.gui.arraypanel.ArrayOperationListener;

public abstract class ArrayRenderer extends JPanel implements ArrayOperationListener {
  protected TrackedArray trackedArray;
  protected int arrayLength;
  protected int panelSize;
  protected int imgSize;

  // Colors
  protected static final Color COLOR_BACKGROUND = Color.BLACK;
  protected static final Color COLOR_ELEMENT = Color.WHITE;
  protected static final Color COLOR_SORTED = Color.GREEN;
  protected static final Color COLOR_COMPARE = Color.BLUE;
  protected static final Color COLOR_SWAP = Color.RED;
  protected static final Color COLOR_GET = Color.CYAN;
  protected static final Color COLOR_SET = Color.CYAN;

  // Index of elements that had an operation done on it
  protected int[] lastCompared =  new int[2];
  protected int[] lastSwaped = new int[2];
  protected int lastGet;
  protected int lastSet;

  // Show operations
  protected boolean showSwaps;
  protected boolean showComparisons;
  protected boolean showGets;
  protected boolean showSets;

  // Graphics
  protected BufferedImage img;
  protected Graphics2D imgGraphics;

  public ArrayRenderer() {
    // set shown operations to false
    resetShownOperations();
  }

  private void resetLastOperations() {
    lastCompared[0] = -1;
    lastCompared[1] = -1;
    lastSwaped[0] = -1;
    lastSwaped[1] = -1;
    lastGet = -1;
    lastSet = -1;
  }
  private void resetShownOperations() {
    showSwaps = false;
    showComparisons = false;
    showGets = false;
    showSets = false;
  }

  protected void initializeGraphics() {
    img = new BufferedImage(trackedArray.length, trackedArray.length, BufferedImage.TYPE_INT_RGB);
    imgGraphics = img.createGraphics();  // convert to Graphics2D object
  }

  protected Color getRainbowColor(int val) {
    float percVal = (float)(val/(arrayLength*1.0));
    Color color = Color.getHSBColor(percVal,1,1);
    return color;
  }

  public void setPanelSize(int panelSize) {
    this.panelSize = panelSize;
  }

  public void setTrackedArray(TrackedArray trackedArray) {
    this.trackedArray = trackedArray;
    this.arrayLength = trackedArray.length;

    // set last operations to null (-1)
    resetLastOperations();

    // Initialize Graphics - create square image, arrayLength x arrayLength
    initializeGraphics();

    repaint();
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

  public abstract void paintComponent(Graphics g);
}
