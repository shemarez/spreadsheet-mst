package drawingtool;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

public class Line extends DrawingTool
{
  public Line()
  {
    super();
  }
  
  public void drawShape(Graphics the_graphics, Point the_start_point,
                             Point the_end_point)
  {
    final Graphics2D g2d = (Graphics2D) the_graphics;
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setPaint(getColor());
    g2d.setStroke(new BasicStroke(getStroke()));
    g2d.drawLine(the_start_point.x, the_start_point.y, the_end_point.x, the_end_point.y);
  }
}
