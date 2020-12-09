package com.caydey.sortingvisualised.gui.arraypanel.renderer;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

import java.awt.geom.Arc2D;

public class ColorWheelRenderer extends ArrayRenderer {
  private int imgSize;
  
  @Override
  protected void initializeGraphics() {
    imgSize = (panelSize < arrayLength) ? arrayLength : panelSize;  // best resolution
    img = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
    imgGraphics = img.createGraphics();  // convert to Graphics2D object
  }

  @Override
  public void paintComponent(Graphics g) {
    double angleUnit = 360.0 / arrayLength;

    if (trackedArray != null) { // if array is defined
      Graphics2D g2d = (Graphics2D)g;

      // background
      imgGraphics.setColor(COLOR_BACKGROUND);
      imgGraphics.fillRect(0,0, imgSize, imgSize);

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
        Arc2D arc = new Arc2D.Double(0, 0, imgSize, imgSize, i*angleUnit, angleUnit, Arc2D.PIE);
        imgGraphics.fill(arc);
      }
      // streach image to fit on window
      g2d.drawImage(img, 0,0, panelSize,panelSize, null);
    }
  }


}
