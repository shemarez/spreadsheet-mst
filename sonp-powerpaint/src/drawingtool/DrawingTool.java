/*
 * DrawingTool
 * Fall 09
 */
package drawingtool;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;

/**
 * A drawing tool.
 * 
 * @author Son
 * @version 1.0
 */
public abstract class DrawingTool
{
  private Color my_color;
  private int my_stroke;
  private Point my_start_point;
  private Point my_end_point;
  
  public DrawingTool()
  {
    my_color = Color.BLACK;
  }
  
  public void setColor(Color the_color)
  {
    my_color = the_color;
  }
  
  public void setStroke(int the_stroke)
  {
    my_stroke = the_stroke;
  }
  
  public void setStartPoint(Point the_point)
  {
    my_start_point = the_point;
  }
  
  public void setEndPoint(Point the_point)
  {
    my_end_point = the_point;
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
  
  public abstract Shape drawShape();
}
