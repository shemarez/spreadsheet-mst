package drawingtool;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangular
{
  public Rectangle()
  {
    super();
  }
  
  public Shape drawShape()
  {   
    setDimension(getStartPoint(), getEndPoint());
    final Rectangle2D.Double rect = new Rectangle2D.Double(getX(), getY(), 
                                                           getWidth(), getHeight());
    
    return rect;
  }
}
