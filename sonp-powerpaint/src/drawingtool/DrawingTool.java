/*
 * DrawingTool
 * Fall 09
 */
package drawingtool;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;

/**
 * A drawing tool class.
 * 
 * @author Son
 * @version 1.0
 */
public abstract class DrawingTool
{
  //Instance fields
  
  /**
   * The color of this drawing tool.
   */
  private Color my_color;
  
  /**
   * The stroke size of this drawing tool.
   */
  private int my_stroke;
  
  /**
   * The start point.
   */
  private Point my_start_point;
  
  /**
   * The end point.
   */
  private Point my_end_point;
  
  //Constructor
  
  /**
   * Construct a new drawing tool with initial color is black.
   */
  public DrawingTool()
  {
    my_color = Color.BLACK;
  }
  
  //Instance methods
  
  /**
   * Set the color of this drawing tool.
   * @param the_color The input color.
   */
  public void setColor(final Color the_color)
  {
    my_color = the_color;
  }
  
  /**
   * Set the stroke size of this drawing tool.
   * @param the_stroke The new stroke.
   */
  public void setStroke(final int the_stroke)
  {
    my_stroke = the_stroke;
  }
  
  /**
   * Set the start point.
   * @param the_point The start point.
   */
  public void setStartPoint(final Point the_point)
  {
    my_start_point = the_point;
  }
  
  /**
   * Set the end point.
   * @param the_point The end point.
   */
  public void setEndPoint(final Point the_point)
  {
    my_end_point = the_point;
  }
  
  /**
   * Return the color of this drawing tool.
   * @return The color of this drawing tool.
   */
  public Color getColor()
  {
    return my_color;
  }
  
  /**
   * Return the stroke size of this drawing tool.
   * @return The stroke size of this drawing tool.
   */
  public int getStroke()
  {
    return my_stroke;
  }
  
  /**
   * Return the starting point of this drawing tool.
   * @return The starting point of this drawing tool.
   */
  public Point getStartPoint()
  {
    return my_start_point;
  }
  
  /**
   * Return the ending point of this drawing tool.
   * @return The ending point of this drawing tool.
   */
  public Point getEndPoint()
  {
    return my_end_point;
  }
  
  /**
   * Return the shape of this drawing tool.
   * @return The shape of this drawing tool.
   */
  public abstract Shape drawShape();
}
