package drawingtool;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class Line extends DrawingTool
{
  public Line()
  {
    super();
  }
  
  public Shape drawShape()
  {
    Line2D.Double line = new Line2D.Double(getStartPoint(), getEndPoint());
    return line;
  }
}
