/*
 * Line.
 * Fall 09
 */
package drawingtool;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Line class.
 * @author Son
 * @version 1.0
 */
public class Line extends DrawingTool
{
  /**
   * Return the shape drawn by this line.
   * @return The shape drawn by this line.
   */
  public Shape drawShape()
  {
    return new Line2D.Double(getStartPoint(), getEndPoint());
  }
}
