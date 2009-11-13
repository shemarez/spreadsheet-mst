package powerpaint.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import drawingtool.DrawingTool;

public class ThicknessAction extends AbstractAction
{
  private int my_thickness;
  private DrawingTool my_tool;
  
  public ThicknessAction(final String the_name, final int the_thickness,
                         final DrawingTool the_tool)
  {
    super(the_name);
    my_thickness = the_thickness;
    my_tool = the_tool;
  }
  
  public void actionPerformed(final ActionEvent the_event)
  {
    my_tool.setStroke(my_thickness);
  }
  
  public int getThickness()
  {
    return my_thickness;
  }
}
