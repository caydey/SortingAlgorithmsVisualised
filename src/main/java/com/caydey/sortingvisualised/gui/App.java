package com.caydey.sortingvisualised.gui;

import javax.swing.SwingUtilities;

public class App {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        WindowFrame frame = new WindowFrame();
      }
    });
  }
}
