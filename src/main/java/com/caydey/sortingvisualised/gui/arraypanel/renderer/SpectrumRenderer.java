package com.caydey.sortingvisualised.gui.arraypanel.renderer;

import java.awt.*;
import javax.swing.*;

public class SpectrumRenderer extends ArrayRenderer {
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
        // any comparisons color is white since rainbow
        if (i == lastCompared[0] || i == lastCompared[1]
        || i == lastSwaped[0] || i == lastSwaped[1]
        || i == lastSet || i == lastGet) {
          imgGraphics.setColor(Color.WHITE);
        } else {
          imgGraphics.setColor(getRainbowColor(array[i]));
        }
        // draw array element
        imgGraphics.fillRect(i,0, 1, arrayLength);
      }
      // streach image to fit on window
      g2d.drawImage(img, 0,0, panelSize,panelSize, null);
    }
  }
}
