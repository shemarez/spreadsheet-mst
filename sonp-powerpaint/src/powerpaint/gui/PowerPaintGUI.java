/*
 * PowerPaintGUI
 * Fall 09
 */
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

/**
 * PowerPaintGUI.
 * @author Son
 * @version 1.0
 */
public class PowerPaintGUI extends JFrame
{
  //Instance fields
  
  /**
   * My panel.
   */
  private PaintPanel my_panel;
  
  /**
   * My tool bar.
   */
  private JToolBar my_tool_bar;
  
  /**
   * My color action.
   */
  private Action my_color_action;
  
  /**
   * My color button.
   */
  private JButton my_color_button;
  
  /**
   * My current tool.
   */
  private DrawingTool my_current_tool;
  
  /**
   * My drawing tool actions list.
   */
  private List<DrawingToolAction> my_drawing_tool_actions;
  
  /**
   * My thickness actions list.
   */
  private List<ThicknessAction> my_thickness_actions;
  
  /**
   * My file actions list.
   */
  private List<Action> my_file_actions;
  
  /**
   * My drawing tool button group.
   */
  private final ButtonGroup my_drawing_tool_group = new ButtonGroup();
  
  /**
   * My thickness button group.
   */
  private final ButtonGroup my_thickness_group = new ButtonGroup();
    
  //Constructor
  
  /**
   * Construct a powerpaintgui object.
   */
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
  
  /**
   * ThicknessAction.
   * @author Son
   * @version 1.0
   */
  private class ThicknessAction extends AbstractAction
  {
    //Instance fields
    
    /**
     * The stroke size.
     */
    private int my_inner_stroke;
    
    //Constructor
    
    /**
     * Construct a ThicknessAction object
     * @param the_name The name of the object.
     *        the_stroke The stroke size.
     * 
     */
    public ThicknessAction(final String the_name, final int the_stroke)
    {
      super(the_name);
      my_inner_stroke = the_stroke;
    }
    
    //Instance fields
    
    /**
     * Set the stroke of my_panel.
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      my_panel.setStroke(my_inner_stroke);
    }
    
    /**
     * Return the thickness of this object.
     * @return The thickness of this object.
     */
    public int getThickness()
    {
      return my_inner_stroke;
    }
  }
  
  /**
   * Create a file menu of the menu bar.
   * @return A file menu of the menu bar.
   */
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
  
  /**
   * Return an option menu in the menu bar.
   * @return An option menu in the menu bar.
   */
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
  
  /**
   * Return a tool menu in the menu bar.
   * @return A tool menu in the menu bar.
   */
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
  
  /**
   * Return a help menu in the menu bar.
   * @return A help menu in the menu bar.
   */
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
  
  /**
   * Setup the drawing actions.
   */
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
  
  /**
   * Setup the thickness actions.
   */
  private void setupThicknessActions()
  {
    my_thickness_actions = new ArrayList<ThicknessAction>();
    my_thickness_actions.add(new ThicknessAction("1", 1));
    my_thickness_actions.add(new ThicknessAction("2", 2));
    my_thickness_actions.add(new ThicknessAction("4", 4));
  }
  
  /**
   * Return a tool bar.
   * @return A tool bar.
   */
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
  
  /**
   * Return a menu bar for the panel.
   * @return
   */
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
}
