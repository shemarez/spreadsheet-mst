package powerpaint.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PowerPaintGUI extends JPanel
{
  /**
   * The width of the panel.
   */
  public static final int WIDTH = 400;
  
  /**
   * The height of the panel.
   */
  public static final int HEIGHT = 300;
  
  public PowerPaintGUI()
  {
    super();
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }
}
