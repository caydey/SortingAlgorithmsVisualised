package com.caydey.sortingvisualised.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.caydey.sortingvisualised.array.TrackedArray;
import com.caydey.sortingvisualised.array.ArrayOrder;

import com.caydey.sortingvisualised.algorithms.SortingAlgorithm;
import com.caydey.sortingvisualised.algorithms.QuickSort;

import com.caydey.sortingvisualised.gui.arraypanel.renderer.ArrayRenderer;
import com.caydey.sortingvisualised.gui.arraypanel.renderer.BarRenderer;

import com.caydey.sortingvisualised.gui.arraypanel.ArrayPanel;

import com.caydey.sortingvisualised.gui.controlpanel.ControlPanel;
import com.caydey.sortingvisualised.gui.controlpanel.ControlPanelListener;

import com.caydey.sortingvisualised.gui.toolbarpanel.ToolbarPanel;
import com.caydey.sortingvisualised.gui.toolbarpanel.OperationType;
import com.caydey.sortingvisualised.gui.toolbarpanel.ToolbarPanelListener;


public class WindowFrame extends JFrame implements ControlPanelListener, ToolbarPanelListener {
  private ToolbarPanel toolbarPanel;
  private ArrayPanel arrayPanel;
  private ControlPanel controlPanel;

  private static final int PADDING_X = 4;  // 4 window manager border
  private static final int PADDING_Y = 70; // 30 titlebar, 20 toolbar, 20 controlpanel

  private TrackedArray trackedArray;
  private SortingAlgorithm sortingAlgorithm;
  private int arraySize;
  private int arrayDelay;
  private ArrayOrder arrayOrder;

  private SortArray sortingRunnable;
  private Thread sortingThread;

  private boolean isSorting;
  private boolean isArraySorted;

  public WindowFrame() {
    // title
    super("Sorting Algorithms Visualised");

    // icon
    ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
    setIconImage(icon.getImage());

    // layout
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // toolbar
    c.gridx = 0; c.gridy = 0;
    c.weightx = 1.0; c.weighty = 0.0;
    c.fill = GridBagConstraints.HORIZONTAL;
    toolbarPanel = new ToolbarPanel();
    toolbarPanel.setToolbarPanelListener(this); // pass ToolbarPanelListener
    add(toolbarPanel, c);

    // canvas
    c.gridx = 0; c.gridy = 1;
    c.weightx = 1.0; c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    ArrayRenderer arrayRenderer = new BarRenderer(); // initialize ArrayRenderer as BarRenderer by default
    arrayPanel = new ArrayPanel(arrayRenderer);
    arrayPanel.setShowComparisons(true);  // show comparisons (default)
    arrayPanel.setPanelSize(512); // 512x512px
    add(arrayPanel, c);

    // control panel (start, stop ... sorting-algorithm)
    c.gridx = 0; c.gridy = 2;
    c.weightx = 1.0; c.weighty = 0.0;
    c.fill = GridBagConstraints.HORIZONTAL;
    controlPanel = new ControlPanel();
    controlPanel.setControlPanelListener(this); // pass ControlPanelListener
    add(controlPanel, c);

    // initialize TrackedArray as randomized of 500 elements (default)
    arraySize = 512;
    arrayOrder = ArrayOrder.RANDOMIZED;
    // initialize TrackedArray delay to 10ms
    arrayDelay = 10;
    trackedArray = new TrackedArray(arraySize, arrayOrder);
    arrayPanel.setTrackedArray(trackedArray);

    // initialize SortingAlgorithm as QuickSort by default
    sortingAlgorithm = new QuickSort();


    isSorting = false; // true when array is being sorted
    isArraySorted = false; // true when array has had sorting algorithm applied to it

    // window size
    setSize(512+PADDING_X, 512+PADDING_Y);
    setMinimumSize(new Dimension(512+PADDING_X, 512+PADDING_Y));
    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent componentEvent) {
        int width = getWidth() - PADDING_X;
        int height = getHeight() - PADDING_Y;

        int min = (width < height) ? width : height;
        int newPanelSize = min;
        setSize(newPanelSize+PADDING_X, newPanelSize+PADDING_Y);  // snap window size

        arrayPanel.setPanelSize(newPanelSize);
        // System.out.println(newPanelSize);
      }
    });

    // ToolTip
    ToolTipManager.sharedInstance().setInitialDelay(100); // popup delay
    UIManager.put("ToolTip.foreground", Color.BLACK);
    UIManager.put("ToolTip.background", new Color(224,255,255)); // Light Cyan

    setLocationRelativeTo(null);  // center window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }


  @Override
  public void startAction() {
    // start sorting in thread so it can sleep and run
    // in parrallel without effecting program execution
    if (!isSorting) { // dont sort an array thats currently being sorted
      if (isArraySorted) {  // if array has been sorted and user wants to sort it again, reshuffle array
        resetAction();
      }
      isSorting = true; // Array is being sorted
      sortingRunnable = new SortArray(trackedArray, sortingAlgorithm);
      // Listener for when algorithm has sorted array
      sortingRunnable.setArraySortedListener(new ArraySortedListener() {
        @Override
        public void arraySortedAction() {
          isSorting = false;
          isArraySorted = true;
          arrayPanel.setSorted();  // call to ArrayPanel to highlight array green
        }
      });
      // create and start sorting algorithm thread
      sortingThread = new Thread(sortingRunnable);
      sortingThread.start();
    }
  }
  @Override
  public void resetAction() {
    if (isSorting) { // if array is being sorted, terminate that bitch
      sortingRunnable.setTerminating(); // tell SortArray that its terminating

      // terminate
      sortingThread.interrupt();  // interrupt thread
      while (sortingThread.isAlive()) { } // halt entire program code execution untill thread is killed and in a shallow grave

      isSorting = false; // not sorting array
      isArraySorted = false; // array has not been sorted
    }

    // create new array, (sorting algorithm is defined in startAction)
    trackedArray = new TrackedArray(arraySize, arrayOrder);
    trackedArray.setDelay(arrayDelay);
    arrayPanel.setTrackedArray(trackedArray);
  }
  @Override
  public void setDelayAction(int ms) {
    arrayDelay = ms;
    trackedArray.setDelay(ms);
  }
  @Override
  public void setSortingAlgorithmAction(SortingAlgorithm sortingAlgorithm) {
    this.sortingAlgorithm = sortingAlgorithm;
  }
  @Override
  public void setArrayOrderAction(ArrayOrder order) {
    if (arrayOrder != order) {
      arrayOrder = order;
      resetAction();
    }
  }
  @Override
  public void setArraySizeAction(int size) {
    if (arraySize != size) {
      arraySize = size;
      resetAction();
    }
  }
  @Override
  public void setOperationsShownAction(OperationType operation, boolean shown) {
    switch (operation) {
      case SWAPS:
        arrayPanel.setShowSwaps(shown);
        break;
      case COMPARISONS:
        arrayPanel.setShowComparisons(shown);
        break;
      case GETS:
        arrayPanel.setShowGets(shown);
        break;
      case SETS:
        arrayPanel.setShowSets(shown);
        break;
    }
  }
  @Override
  public void setArrayRendererAction(ArrayRenderer renderer) {
    arrayPanel.setArrayRenderer(renderer);
  }
}
