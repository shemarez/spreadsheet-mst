package powerpaint.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import drawingtool.DrawingTool;
import drawingtool.Ellipse;
import drawingtool.Line;
import drawingtool.Pencil;
import drawingtool.Rectangle;

public class PowerPaintGUI extends JFrame
{
  private PaintPanel my_panel;
  private JToolBar my_tool_bar;
  //private JMenuBar my_menu_bar;
  private DrawingTool my_current_tool;
  private List<DrawingToolAction> my_drawing_tool_actions;
  private List<ThicknessAction> my_thickness_actions;
  private List<Action> my_file_actions;
  private final ButtonGroup my_drawing_tool_group = new ButtonGroup();
  private final ButtonGroup my_thickness_group = new ButtonGroup();
    
  public PowerPaintGUI()
  {
    super("PowerPaint");
    my_panel = new PaintPanel();
    
    setupDrawingActions();
    setupThicknessActions();
    
    setJMenuBar(createMenuBar());
    //add(my_menu_bar, BorderLayout.NORTH);
    add(my_panel, BorderLayout.CENTER);
    pack();
    my_tool_bar = createToolBar();
    add(my_tool_bar, BorderLayout.SOUTH); 
    my_drawing_tool_actions.get(0).actionPerformed(null);
    my_thickness_actions.get(1).actionPerformed(null);
  }
  
  private class ThicknessAction extends AbstractAction
  {
    private int my_inner_stroke;
    
    public ThicknessAction(final String the_name, final int the_stroke)
    {
      super(the_name);
      my_inner_stroke = the_stroke;
    }
    
    public void actionPerformed(final ActionEvent the_event)
    {
      my_panel.setStroke(my_inner_stroke);
    }
    
    public int getThickness()
    {
      return my_inner_stroke;
    }
  }
  
  private JMenu createFileMenu()
  {
    JMenu menu = new JMenu("File");
    menu.setMnemonic('F');
    JMenuItem menu_item;
    
    Action quit_action = 
      new AbstractAction("Quit")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          dispose();
        }
      };
    quit_action.putValue(Action.SHORT_DESCRIPTION, "Quit PowerPaint");
    menu_item = new JMenuItem(quit_action);
    menu_item.setMnemonic('Q');
    menu.add(menu_item);
    
    Action clear_action =
      new AbstractAction("Clear")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          my_panel.clearBufferedImage();
          my_panel.repaint();
        }      
      };
    clear_action.putValue(Action.SHORT_DESCRIPTION, "Clear the drawing canvas");
    menu_item = new JMenuItem(clear_action);
    menu.addSeparator();
    menu.add(menu_item);
    
    return menu;
  }
  
  private JMenu createOptionMenu()
  {
    JMenu menu = new JMenu("Options");
    menu.setMnemonic('Q');
    JCheckBoxMenuItem cbMenuItem;
    JRadioButtonMenuItem rbMenuItem;
    
    cbMenuItem = new JCheckBoxMenuItem("Grid");
    cbMenuItem.setMnemonic('G');
    menu.add(cbMenuItem);
    
    JMenu subMenu = new JMenu("Thickness");
    subMenu.setMnemonic('T');
    
    int tempCount = 0;  //Use to choose a specific action to set selected as default
    for (ThicknessAction a : my_thickness_actions)
    {
      rbMenuItem = new JRadioButtonMenuItem(a);
      final String buttonName = (String) a.getValue(Action.NAME);
      rbMenuItem.setMnemonic(buttonName.charAt(0));
      if (tempCount == 1)
      {
        rbMenuItem.setSelected(true);
      }
      my_thickness_group.add(rbMenuItem);
      subMenu.add(rbMenuItem);
      tempCount++;
    }
    
    menu.add(subMenu);
    
    return menu;
  }
  
  private void setupDrawingActions()
  {
    DrawingTool the_tool;
    my_drawing_tool_actions = new ArrayList<DrawingToolAction>();
     
    the_tool = new Pencil();
    my_drawing_tool_actions.add(new DrawingToolAction("Pencil", new ImageIcon("src/powerpaint/gui/pencil_bw.gif"),
                                                      the_tool, my_panel));
    the_tool = new Line();
    my_drawing_tool_actions.add(new DrawingToolAction("Line", new ImageIcon("src/powerpaint/gui/line_bw.gif"),
                                                      the_tool, my_panel));
    the_tool = new Rectangle();
    my_drawing_tool_actions.add(new DrawingToolAction("Rectangle", new ImageIcon("src/powerpaint/gui/rectangle_bw.gif"),
                                                      the_tool, my_panel));
    the_tool = new Ellipse();
    my_drawing_tool_actions.add(new DrawingToolAction("Ellipse", new ImageIcon("src/powerpaint/gui/ellipse_bw.gif"),
                                                      the_tool, my_panel));
  }
  
  private void setupThicknessActions()
  {
    my_thickness_actions = new ArrayList<ThicknessAction>();
    my_thickness_actions.add(new ThicknessAction("1", 1));
    my_thickness_actions.add(new ThicknessAction("2", 2));
    my_thickness_actions.add(new ThicknessAction("4", 4));
  }
  
  private JToolBar createToolBar()
  {
    JToolBar toolBar = new JToolBar();
    
    JButton button = new JButton("Color...");
    button.setMnemonic('C');
    button.setBackground(Color.BLACK);
    toolBar.add(button);
    
    for (DrawingToolAction a : my_drawing_tool_actions)
    {
      final JToggleButton tButton = new JToggleButton(a);
      toolBar.add(tButton);
      my_drawing_tool_group.add(tButton);
    }
    /*toolBar.add(button);
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
    toolBar.add(button);*/
    
    return toolBar;
  }
  
  private JMenuBar createMenuBar()
  {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu, subMenu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;
    
    menu = createFileMenu();
    menuBar.add(menu);
    
    /*
        
    //Create file menu
    
    menu = new JMenu("File");
    menu.setMnemonic('F');
    menuBar.add(menu);    
    
    menuItem = new JMenuItem("Clear", 'C');
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem("Quit", 'Q');
    menu.add(menuItem); */
    
    //Create options menu
    
    menu = new JMenu("Options");
    menu.setMnemonic('O');
    menuBar.add(menu);
    
    cbMenuItem = new JCheckBoxMenuItem("Grid");
    cbMenuItem.setMnemonic('G');
    menu.add(cbMenuItem);
    
    subMenu = new JMenu("Thickness");
    subMenu.setMnemonic('T');
    
    int tempCount = 0;  //Use to choose a specific action to set selected as default
    for (ThicknessAction a : my_thickness_actions)
    {
      rbMenuItem = new JRadioButtonMenuItem(a);
      final String buttonName = (String) a.getValue(Action.NAME);
      rbMenuItem.setMnemonic(buttonName.charAt(0));
      if (tempCount == 1)
      {
        rbMenuItem.setSelected(true);
      }
      my_thickness_group.add(rbMenuItem);
      subMenu.add(rbMenuItem);
      tempCount++;
    }
    
    /*
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
    subMenu.add(rbMenuItem); */
    menu.add(subMenu);
    
    //Create tools menu
    
    menu = new JMenu("Tools");
    menu.setMnemonic('T');
    menuBar.add(menu);
    
    menuItem = new JMenuItem("Color...", 'C');
    menu.add(menuItem);
    menu.addSeparator();  
    
    for (DrawingToolAction a : my_drawing_tool_actions)
    {
      rbMenuItem = new JRadioButtonMenuItem(a);
      final String buttonName = (String) a.getValue(Action.NAME);
      rbMenuItem.setMnemonic(buttonName.charAt(0));
      menu.add(rbMenuItem);
    }
    /*
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
    menu.add(rbMenuItem);  */
    
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
