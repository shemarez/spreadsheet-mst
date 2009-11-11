package powerpaint.gui;

import java.awt.Color;
import java.awt.Point;

import drawingtool.DrawingTool;
import drawingtool.Line;

public class DrawingShape
{
  private DrawingTool my_tool;
  private Color my_color;
  private int my_stroke;
  private Point my_start_point;
  private Point my_end_point;
  
  public DrawingShape()
  {
    my_tool = new Line();
    my_color = Color.BLACK;
    my_stroke = 2;
    my_start_point = new Point();
    my_end_point = new Point();
  }
  
  public void setDrawingTool(DrawingTool the_drawing_tool)
  {
    my_tool = the_drawing_tool;
  }
  
  public void setColor(Color the_color)
  {
    my_color = the_color;
  }
  
  public void setStroke(int the_stroke)
  {
    my_stroke = the_stroke;
  }
  
  public void setStartPoint(Point the_start_point)
  {
    my_start_point = the_start_point;
  }
  
  public void setEndPoint(Point the_end_point)
  {
    my_end_point = the_end_point;
  }
  
  public DrawingTool getDrawingTool()
  {
    return my_tool;
  }
  
  public Color getColor()
  {
    return my_color;
  }
  
  public int getStroke()
  {
    return my_stroke;
  }
  
  public Point getStartPoint()
  {
    return my_start_point;
  }
  
  public Point getEndPoint()
  {
    return my_end_point;
  }
}
