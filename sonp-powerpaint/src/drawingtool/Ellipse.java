/*
 * Ellipse class.
 * Fall 09
 */
package drawingtool;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * An Ellipse class.
 * @author Son
 * @version 1.0
 */
public class Ellipse extends Rectangular
{  
  /**
   * Return the shape is drawn by this ellipse.
   * @return  The shape is drawn by this ellipse.
   */
  public Shape drawShape()
  {    
    setDimension(getStartPoint(), getEndPoint());
    final Ellipse2D.Double ellipse = new Ellipse2D.Double(getX(), getY(), 
                                                           getWidth(), getHeight());
    return ellipse;    
  }
}
