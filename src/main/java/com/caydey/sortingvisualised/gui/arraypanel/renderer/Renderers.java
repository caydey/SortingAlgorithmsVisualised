package com.caydey.sortingvisualised.gui.arraypanel.renderer;


public enum Renderers {
  BARS("Bars"),
  COLORWHEEL("Color Wheel"),
  RAINBOWBARS("Rainbow Bars"),
  SPECTRUM("Spectrum");

  private String name;

  Renderers(String name) {
    this.name = name;
  }

  public static String[] getList() {
    Renderers[] renderers = Renderers.values();
    String[] list = new String[renderers.length];
    for (int i=0; i<renderers.length; i++) {
      list[i] = renderers[i].name;
    }
    return list;
  }

  public static Renderers getRendererFromName(String name) {
    for (Renderers renderer : Renderers.values()) {
      if (renderer.name.equals(name)) {
        return renderer;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public ArrayRenderer getRendererObject() {
    switch (this) {
      case COLORWHEEL:
        return new ColorWheelRenderer();
      case BARS:
        return new BarRenderer();
      case RAINBOWBARS:
        return new RainbowBarRenderer();
      case SPECTRUM:
        return new SpectrumRenderer();
      default:
        return null;
    }
  }
}
