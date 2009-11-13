/*
 * PowerPaintMain
 * Fall 09
 */

import javax.swing.JFrame;
import powerpaint.gui.PowerPaintGUI;

/**
 * PowerPaintMain.
 * @author Son
 * @version 1.0
 */
public class PowerPaintMain extends JFrame
{
  /**
   * Main method of PowerPaintMain.
   * @param the_agrs Comment line argument (ignored).
   */
  public static void main(final String... the_agrs)
  {
    final PowerPaintGUI powerPaint = new PowerPaintGUI();
    powerPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    powerPaint.setVisible(true);
  }
}
