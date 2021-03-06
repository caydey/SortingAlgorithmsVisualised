package com.caydey.sortingvisualised.gui.arraypanel.renderer;

import java.awt.*;
import javax.swing.*;

public class BubbleRenderer extends ArrayRenderer {
  @Override
  public void paintComponent(Graphics g) {
    if (trackedArray != null) { // if array is defined
      Graphics2D g2d = (Graphics2D)g;

      // background
      imgGraphics.setColor(COLOR_BACKGROUND);
      imgGraphics.fillRect(0,0, arrayLength, arrayLength);

      // bars
      int[] array = trackedArray.getArray();
      for (int i=0; i<array.length; i++) {
        // color
        imgGraphics.setColor(COLOR_ELEMENT);
        if (i == lastCompared[0] || i == lastCompared[1]) {
          imgGraphics.setColor(COLOR_COMPARE);
        } else if (i == lastSwaped[0] || i == lastSwaped[1]) {
          imgGraphics.setColor(COLOR_SWAP);
        } else if (i == lastSet) {
          imgGraphics.setColor(COLOR_SET);
        } else if (i == lastGet) {
          imgGraphics.setColor(COLOR_GET);
        }

        // sorted animation (turn array elements green)
        if (i <= sortedAnimationIndex) {
          imgGraphics.setColor(COLOR_SORTED);
        }

        // draw array element
        imgGraphics.drawLine(i,arrayLength-array[i]-1, i,arrayLength-array[i]-1);
      }

      // streach image to fit on window
      g2d.drawImage(img, 0,0, panelSize,panelSize, null);
      // display operations count
      updateDisplayedOperations(g2d);
    }
  }
}
