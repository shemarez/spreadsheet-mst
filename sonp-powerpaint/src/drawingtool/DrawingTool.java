/*
 * DrawingTool
 * Fall 09
 */
package drawingtool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

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
  
  public DrawingTool()
  {
    my_color = Color.BLACK;
    my_stroke = 2;
  }
  
  public void setColor(Color the_color)
  {
    my_color = the_color;
  }
  
  public void setStroke(int the_stroke)
  {
    my_stroke = the_stroke;
  }
  
  public Color getColor()
  {
    return my_color;
  }
  
  public int getStroke()
  {
    return my_stroke;
  }
  
  public void drawShape(Graphics the_graphics, Point the_start_point,
                        Point the_end_point){}; 
}
