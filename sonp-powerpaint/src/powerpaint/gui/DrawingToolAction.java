package powerpaint.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import drawingtool.DrawingTool;

public class DrawingToolAction extends AbstractAction
{
  private final DrawingTool my_tool;
  private final PaintPanel my_panel;
  
  public DrawingToolAction(final String the_name, final Icon the_icon,
                           final DrawingTool the_tool, final PaintPanel the_panel)
  {
    super(the_name, the_icon);
    putValue(Action.SHORT_DESCRIPTION, "Select the " + the_name + " tool");
    putValue(Action.SELECTED_KEY, true);
    my_tool = the_tool;
    my_panel = the_panel;
  }
  
  public void actionPerformed(final ActionEvent the_event)
  {
    my_panel.setTool(my_tool);
    my_panel.repaint();
  }
  
  public DrawingTool getDrawingTool()
  {
    return my_tool;
  }
}
