package drawingtool;

import java.awt.Graphics;
import java.awt.Point;

public class Rectangular extends DrawingTool
{
  private double my_x;
  private double my_y;
  private double my_width;
  private double my_height;
  
  public Rectangular()
  {
    super();
    my_x = 0;
    my_y = 0;
    my_width = 0;
    my_height = 0;
  }
  
  public void setX(double the_x)
  {
    my_x = the_x;
  }
  
  public void setY(double the_y)
  {
    my_y = the_y;
  }
  
  public void setWidth(double the_width)
  {
    my_width = the_width;
  }
  
  public void setHeight(double the_height)
  {
    my_height = the_height;
  }
  
  /**
   * Return my_x of the Rectangular object.
   * @return my_x The x_coordinate of the starting point
   */
  public double getX()
  {
    return my_x;
  }
  
  /**
   * Return my_y of the Rectangular object.
   * @return my_y The y_coordinate of the starting point
   */
  public double getY()
  {
    return my_y;
  }
  
  public double getWidth()
  {
    return my_width;
  }
  
  public double getHeight()
  {
    return my_height;
  }
  
  public void setDimension(Point the_start_point, Point the_end_point)
  {
    setWidth(Math.abs(the_end_point.x - the_start_point.x));
    setHeight(Math.abs(the_end_point.y - the_start_point.y));
    setX(Math.min(the_start_point.x, the_end_point.x));
    setY(Math.min(the_start_point.y, the_end_point.y));
  }
}
