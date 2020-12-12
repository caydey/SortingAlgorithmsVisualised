package com.caydey.sortingvisualised.gui.arraypanel.renderer;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class ImageRenderer extends ArrayRenderer {
  private int imgSize;
  private BufferedImage customImage;
  private File imageFile;

  @Override
  protected void initializeGraphics() {
    double sqrt = Math.sqrt(arrayLength);
    int sqrtInt = (int)sqrt;
    imgSize = (sqrtInt < sqrt) ? sqrtInt+1 : sqrtInt;

    img = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
    imgGraphics = img.createGraphics();  // convert to Graphics2D object

    initializeImage();
  }

  @Override
  public void paintComponent(Graphics g) {
    if (trackedArray != null) { // if array is defined
      Graphics2D g2d = (Graphics2D)g;

      // background
      imgGraphics.setColor(COLOR_BACKGROUND);
      imgGraphics.fillRect(0,0, imgSize, imgSize);

      // pixels
      int[] array = trackedArray.getArray();
      for (int i=0; i<array.length; i++) {
        // x,y coords of pixel

        // any comparisons color is white since rainbow
        if (i == lastCompared[0] || i == lastCompared[1]
        || i == lastSwaped[0] || i == lastSwaped[1]
        || i == lastSet || i == lastGet) {
          imgGraphics.setColor(Color.WHITE);
        } else {
          int[] elementGridPos = getGridPosition(array[i]);
          imgGraphics.setColor(getPixelColor(elementGridPos[0], elementGridPos[1]));
        }

        // draw array pixel
        int[] gridPos = getGridPosition(i);
        imgGraphics.drawLine(gridPos[0],gridPos[1], gridPos[0],gridPos[1]);
      }
      // streach image to fit on window
      g2d.drawImage(img, 0,0, panelSize,panelSize, null);
      // display operations count
      updateDisplayedOperations(g2d);
    }
  }

  private void initializeImage() {
    BufferedImage readImg = null;
    try {
      if (imageFile == null) {
        imageFile = getUserFile();
      }

      boolean invalidImg = (imageFile == null);
      if (!invalidImg) {
        readImg = ImageIO.read(imageFile);
        invalidImg = (readImg == null);
      }

      if (invalidImg) { // invalid image, set to default image
        readImg = ImageIO.read(getClass().getResource("/icon.png"));
      }
    } catch(IOException e) { /* */ }

    // scale image
    Image tmp = readImg.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
    customImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2dImg = customImage.createGraphics();
    g2dImg.drawImage(tmp, 0, 0, null);
    g2dImg.dispose();
  }

  private File getUserFile() {
    JFileChooser fc = new JFileChooser();

    // default file [working directory]
    File workingDirectory = new File(System.getProperty("user.dir"));
    fc.setSelectedFile(workingDirectory);

    int ret = fc.showOpenDialog(null);
    if (ret == 0) { // "Open" option choosen
      File selectedFile = fc.getSelectedFile();
      return selectedFile;
    }

    return null;
  }

  private int[] getGridPosition(int index) {
    int[] gridPos = new int[2];
    gridPos[0] = index % imgSize;
    gridPos[1] = index / imgSize;
    return gridPos;
  }

  private Color getPixelColor(int x, int y) {
    int pixel = customImage.getRGB(x,y);  // get pixel value
    Color color = new Color(pixel);  // convert pixel value to Color
    return color;
  }
}
