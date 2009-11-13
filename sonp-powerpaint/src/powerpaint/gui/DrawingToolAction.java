/*
 * DrawingToolAction
 * Fall 09
 */
package powerpaint.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import drawingtool.DrawingTool;

/**
 * DrawingToolAction.
 * @author Son
 * @version 1.0
 */
public class DrawingToolAction extends AbstractAction
{
  //Instance fields
  
  /**
   * The drawing tool.
   */
  private final DrawingTool my_tool;
  
  /**
   * The paint panel.
   */
  private final PaintPanel my_panel;
  
  //Constructor
  
  /**
   * Construct a DrawingToolAction object.
   * @param the_name The name.
   *        the_icon The icon.
   *        the_tool The tool.
   *        the_panel The drawing panel.
   */
  public DrawingToolAction(final String the_name, final Icon the_icon,
                           final DrawingTool the_tool, final PaintPanel the_panel)
  {
    super(the_name, the_icon);
    putValue(Action.SHORT_DESCRIPTION, "Select the " + the_name + " tool");
    putValue(Action.SELECTED_KEY, true);
    my_tool = the_tool;
    my_panel = the_panel;
  }
  
  //Instance methods
  
  /**
   * Update the drawing tool and its stroke and repaint the panel.
   * @param the_event The event, ignored.
   */
  public void actionPerformed(final ActionEvent the_event)
  {
    my_panel.setTool(my_tool);
    my_tool.setStroke(my_panel.getStroke());
    my_panel.repaint();
  }
  
  /**
   * Return the drawing tool of this object.
   * @return The drawing tool of this object.
   */
  public DrawingTool getDrawingTool()
  {
    return my_tool;
  }
}
