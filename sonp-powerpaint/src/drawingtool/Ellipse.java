package drawingtool;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Rectangular
{
  public Ellipse()
  {
    super();
  }
  
  public Shape drawShape()
  {    
    setDimension(getStartPoint(), getEndPoint());
    final Ellipse2D.Double ellipse = new Ellipse2D.Double(getX(), getY(), 
                                                           getWidth(), getHeight());
    return ellipse;    
  }
}
