import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import powerpaint.gui.PaintPanel;
import powerpaint.gui.PowerPaintGUI;


public class PowerPaintMain extends JFrame
{
  public static void main(String... agrs)
  {
    PowerPaintGUI powerPaint = new PowerPaintGUI();
    powerPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    powerPaint.setVisible(true);
  }
}
