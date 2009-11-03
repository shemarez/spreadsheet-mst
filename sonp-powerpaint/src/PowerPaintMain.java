import javax.swing.JFrame;

import powerpaint.gui.PowerPaintGUI;


public class PowerPaintMain
{
  public static void main(String... args)
  {
    PowerPaintGUI paintPanel = new PowerPaintGUI();
    final JFrame frame = new JFrame("PowerPaint");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(paintPanel);
    frame.pack();
    frame.setVisible(true);
  }
}
