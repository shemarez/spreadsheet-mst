/*
 * Rectangle.
 * Fall 09
 */
package drawingtool;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Rectangle class.
 * @author Son
 * @version 1.0
 */
public class Rectangle extends Rectangular
{
  /**
   * Return the shape is drawn by this rectangle.
   * @return The shape is drawn by this rectangle.
   */
  public Shape drawShape()
  {   
    setDimension(getStartPoint(), getEndPoint());                                                                                                                                                                                                   
    final Rectangle2D.Double rect = new Rectangle2D.Double(getX(), getY(), 
                                                           getWidth(), getHeight());
    
    return rect;
  }
}
