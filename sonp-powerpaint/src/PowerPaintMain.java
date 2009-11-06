import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import powerpaint.gui.PowerPaintGUI;


public class PowerPaintMain
{
  public static void main(String... args)
  {
    PowerPaintGUI paintPanel = new PowerPaintGUI();
    //final JFrame frame = new JFrame("PowerPaint");
    paintPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.add(paintPanel);
    //frame.pack();
    //frame.setJMenuBar(setMenuBar());
    paintPanel.setVisible(true);
  }
  
  private static JMenuBar setMenuBar()
  {
    JMenu file = new JMenu("File");
    JMenu options = new JMenu("Options");
    JMenu tools = new JMenu("Tools");
    JMenu helps = new JMenu("Helps");
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(file);
    menuBar.add(options);
    menuBar.add(tools);
    menuBar.add(helps);
    return menuBar;
  }
}
