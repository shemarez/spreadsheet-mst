/*
 * Pencil.
 * Fall 09
 */
package drawingtool;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 * Pencil class.
 * @author Son
 * @version 1.0
 */
public class Pencil extends DrawingTool
{
  //Instance fields
  
  /**
   * The general path.
   */
  private GeneralPath my_path;
  
  //Constructor
  
  /**
   * Create a Pencil object.
   */
  public Pencil()
  {
    super();
    my_path = new GeneralPath();
    my_path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
  }
  
  //Instance methods
  
  /**
   * Set the starting point of this Pencil.
   * @param the_start_point The starting point.
   */
  public void setStartPoint(final Point the_start_point)
  {
    my_path = new GeneralPath();
    my_path.moveTo(the_start_point.x, the_start_point.y);
  }
  
  /**
   * Return the shape is drawn by this Pencil.
   * @return The shape is drawn by this Pencil.
   */
  public Shape drawShape()
  {
    final Point next_point = getEndPoint();
    my_path.lineTo(next_point.x, next_point.y);
    return my_path;
  }
}
