package powerpaint.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

public class PowerPaintGUI extends JFrame
{
  public PowerPaintGUI()
  {
    super("PowerPaint");
  }
  
  private static JToolBar createToolBar()
  {
    JToolBar toolBar = new JToolBar();
    
    JButton button = new JButton("Color...");
    button.setMnemonic('C');
    button.setBackground(Color.black);
    toolBar.add(button);
    button = new JButton("Pencil");
    button.setMnemonic('P');
    button.setIcon(new ImageIcon("src/powerpaint/gui/pencil_bw.gif"));
    toolBar.add(button);
    button = new JButton("Line");
    button.setMnemonic('L');
    button.setIcon(new ImageIcon("src/powerpaint/gui/line_bw.gif"));
    toolBar.add(button);
    button = new JButton("Rectangle");
    button.setMnemonic('R');
    button.setIcon(new ImageIcon("src/powerpaint/gui/rectangle_bw.gif"));
    toolBar.add(button);
    button = new JButton("Ellipse");
    button.setMnemonic('E');
    button.setIcon(new ImageIcon("src/powerpaint/gui/ellipse_bw.gif"));
    toolBar.add(button);
    
    return toolBar;
  }
  
  private static JMenuBar createMenuBar()
  {
    JMenuBar menuBar;
    JMenu menu, subMenu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;
    
    //Create the menu bar
    menuBar = new JMenuBar();
    
    //Create file menu
    
    menu = new JMenu("File");
    menu.setMnemonic('F');
    menuBar.add(menu);
    
    menuItem = new JMenuItem("Clear", 'C');
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem("Quit", 'Q');
    menu.add(menuItem);
    
    //Create options menu
    
    menu = new JMenu("Options");
    menu.setMnemonic('O');
    menuBar.add(menu);
    
    cbMenuItem = new JCheckBoxMenuItem("Grid");
    cbMenuItem.setMnemonic('G');
    menu.add(cbMenuItem);
    
    subMenu = new JMenu("Thickness");
    subMenu.setMnemonic('T');
    ButtonGroup group = new ButtonGroup();
    rbMenuItem = new JRadioButtonMenuItem("1");
    rbMenuItem.setMnemonic('1');    
    group.add(rbMenuItem);
    subMenu.add(rbMenuItem);
    rbMenuItem = new JRadioButtonMenuItem("2");
    rbMenuItem.setMnemonic('2');
    rbMenuItem.setSelected(true);
    group.add(rbMenuItem);
    subMenu.add(rbMenuItem);
    rbMenuItem = new JRadioButtonMenuItem("4");
    rbMenuItem.setMnemonic('4');
    group.add(rbMenuItem);
    subMenu.add(rbMenuItem);
    menu.add(subMenu);
    
    //Create tools menu
    
    menu = new JMenu("Tools");
    menu.setMnemonic('T');
    menuBar.add(menu);
    
    menuItem = new JMenuItem("Color...", 'C');
    menu.add(menuItem);
    menu.addSeparator();
    group = new ButtonGroup();    
    
    rbMenuItem = new JRadioButtonMenuItem("Pencil", new ImageIcon("src/powerpaint/gui/pencil_bw.gif"));
    rbMenuItem.setMnemonic('P');
    rbMenuItem.setSelected(true);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    
    rbMenuItem = new JRadioButtonMenuItem("Line", new ImageIcon("src/powerpaint/gui/line_bw.gif"));
    rbMenuItem.setMnemonic('L');
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    
    rbMenuItem = new JRadioButtonMenuItem("Rectangle", new ImageIcon("src/powerpaint/gui/rectangle_bw.gif"));
    rbMenuItem.setMnemonic('R');
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    
    rbMenuItem = new JRadioButtonMenuItem("Ellipse", new ImageIcon("src/powerpaint/gui/ellipse_bw.gif"));
    rbMenuItem.setMnemonic('E');
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    
    //Create help menu
    
    menu = new JMenu("Help");
    menu.setMnemonic('H');
    menuBar.add(menu);
    
    menuItem = new JMenuItem("About...", 'A');
    menu.add(menuItem);
    
    return menuBar;
  }
  
  public void setupComponents()
  {
    PowerPaintGUI powerPaint = new PowerPaintGUI();
    JPanel panel = new JPanel(new BorderLayout());
    PaintPanel paintPanel = new PaintPanel();
    JMenuBar menuBar = createMenuBar();
    JToolBar toolBar = createToolBar();
    
    panel.add(menuBar, BorderLayout.NORTH);
    panel.add(paintPanel, BorderLayout.CENTER);
    panel.add(toolBar, BorderLayout.SOUTH);
    powerPaint.add(panel);
    powerPaint.pack();
    powerPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    powerPaint.setVisible(true);
  }
}
