package powerpaint.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
  private Action my_color_action;
  private JButton my_color_button;
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
    
    Action grid =
      new AbstractAction("Grid")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          AbstractButton grid_button = (AbstractButton) the_event.getSource();
          boolean selected = grid_button.getModel().isSelected();
          if (selected)
          {
            my_panel.setMyGridBoolean(true);
            my_panel.repaint();
          }
          else
          {
            my_panel.setMyGridBoolean(false);
            my_panel.repaint();
          }
        }
      };
    cbMenuItem = new JCheckBoxMenuItem(grid);
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
  
  private JMenu createToolMenu()
  {
    JMenu menu = new JMenu("Tools");
    menu.setMnemonic('T');
    JRadioButtonMenuItem rbMenuItem;
    
    my_color_action = 
      new AbstractAction("Color...")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          final Color color =
            JColorChooser.showDialog(null, "Select the drawing color", 
                                     my_color_button.getBackground());
          if (color != null)
          {
            my_panel.setColor(color);
            my_color_button.setBackground(color);
          }
        }
      };
    JMenuItem menu_item = new JMenuItem(my_color_action);
    menu_item.setMnemonic('C');
    menu.add(menu_item);
    menu.addSeparator();
    
    for (DrawingToolAction a : my_drawing_tool_actions)
    {
      rbMenuItem = new JRadioButtonMenuItem(a);
      final String buttonName = (String) a.getValue(Action.NAME);
      rbMenuItem.setMnemonic(buttonName.charAt(0));
      menu.add(rbMenuItem);
    }
    
    return menu;
  }
  
  private JMenu createHelpMenu()
  {
    JMenu menu = new JMenu("Help");
    menu.setMnemonic('H');
    
    Action about = 
      new AbstractAction("About...")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint, v1.0", "PowerPaint", 
                                        JOptionPane.INFORMATION_MESSAGE);
        }
      };
    JMenuItem menu_item = new JMenuItem(about);
    menu.add(menu_item);
    return menu;
  }
  
  private void setupDrawingActions()
  {
    DrawingTool the_tool;
    my_drawing_tool_actions = new ArrayList<DrawingToolAction>();
     
    the_tool = new Pencil();
    my_drawing_tool_actions.add(new DrawingToolAction("Pencil", 
                                                      new ImageIcon("src/powerpaint/gui/pencil_bw.gif"),
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
    
    my_color_button = new JButton(my_color_action);
    my_color_button.setMnemonic('C');
    my_color_button.setBackground(Color.BLACK);
    toolBar.add(my_color_button);
    
    for (DrawingToolAction a : my_drawing_tool_actions)
    {
      final JToggleButton tButton = new JToggleButton(a);
      toolBar.add(tButton);
      my_drawing_tool_group.add(tButton);
    }
        
    return toolBar;
  }
  
  private JMenuBar createMenuBar()
  {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    
    //Create file menu
    
    menu = createFileMenu();
    menuBar.add(menu);
    
    //Create options menu
    
    menu = createOptionMenu();
    menuBar.add(menu);
        
    //Create tools menu
    
    menu = createToolMenu();
    menuBar.add(menu);
         
    //Create help menu
    
    menu = createHelpMenu();
    menuBar.add(menu);
    
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
