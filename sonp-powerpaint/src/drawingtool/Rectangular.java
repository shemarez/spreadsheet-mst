/*
 * Rectangular.
 * Fall 09
 */
package drawingtool;

import java.awt.Point;

/**
 * Rectangular class.
 * @author Son
 * @version 1.0
 */
public abstract class Rectangular extends DrawingTool
{
  //Instance fields
  
  /**
   * The x_coordinate of the starting point.
   */
  private double my_x;
  
  /**
   * The y_coordinate of the starting point.
   */
  private double my_y;
  
  /**
   * The width of this rectangular.
   */
  private double my_width;
  
  /**
   * The height of this rectangular.
   */
  private double my_height;
  
  //Constructor
  
  /**
   * Construct a rectangular object.
   */
  public Rectangular()
  {
    super();
    my_x = 0;
    my_y = 0;
    my_width = 0;
    my_height = 0;
  }
  
  //Instance methods
  
  /**
   * Set the value of the x_coordinate of the starting point.
   * @param the_x The x_coordinate of the starting point.
   */
  public void setX(final double the_x)
  {
    my_x = the_x;
  }
  
  /**
   * Set the value of the y_coordinate of the starting point.
   * @param the_y The y_coordinate of the starting point.
   */
  public void setY(final double the_y)
  {
    my_y = the_y;
  }
  
  /**
   * Set the value of width of this rectangular.
   * @param the_width The width.
   */
  public void setWidth(final double the_width)
  {
    my_width = the_width;
  }
  
  /**
   * Set the value of height of this rectangular.
   * @param the_height The height.
   */
  public void setHeight(final double the_height)
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
  
  /**
   * Return the width of this rectangular.
   * @return The width of this rectangular.
   */
  public double getWidth()
  {
    return my_width;
  }
  
  /**
   * Return the height of this rectangular.
   * @return The height of this rectangular.
   */
  public double getHeight()
  {
    return my_height;
  }
  
  /**
   * Set the dimension of this rectangular.
   * @param the_start_point The start point.
   * @param the_end_point The end point.
   */
  public void setDimension(final Point the_start_point, final Point the_end_point)
  {
    setWidth(Math.abs(the_end_point.x - the_start_point.x));
    setHeight(Math.abs(the_end_point.y - the_start_point.y));
    setX(Math.min(the_start_point.x, the_end_point.x));
    setY(Math.min(the_start_point.y, the_end_point.y));
  }
}
