package com.caydey.sortingvisualised.gui.arraypanel.renderer;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ColorGridRenderer extends ArrayRenderer {
  private int imgSize;

  @Override
  protected void initializeGraphics() {
    double sqrt = Math.sqrt(arrayLength);
    int sqrtInt = (int)sqrt;
    imgSize = (sqrtInt < sqrt) ? sqrtInt+1 : sqrtInt;

    img = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
    imgGraphics = img.createGraphics();  // convert to Graphics2D object
  }

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
        int[] gridPos = getGridPosition(i);
        imgGraphics.drawLine(gridPos[0],gridPos[1], gridPos[0],gridPos[1]);
      }
      // streach image to fit on window
      g2d.drawImage(img, 0,0, panelSize,panelSize, null);
    }
  }

  private int[] getGridPosition(int index) {
    int[] gridPos = new int[2];
    gridPos[0] = index % imgSize;
    gridPos[1] = index / imgSize;
    return gridPos;
  }
}
