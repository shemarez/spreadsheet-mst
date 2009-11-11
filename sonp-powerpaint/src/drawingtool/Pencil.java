package drawingtool;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

public class Pencil extends DrawingTool
{
  private GeneralPath my_path;
  
  public Pencil()
  {
    super();
    my_path = new GeneralPath();
    my_path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
  }
  
  public void setPath(GeneralPath the_path)
  {
    my_path = the_path;
  }
  
  public void drawShape(Graphics the_graphic, Point the_start_point,
                             Point the_end_point)
  {
    final Graphics2D g2d = (Graphics2D) the_graphic;
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setPaint(getColor());
    g2d.setStroke(new BasicStroke(getStroke()));
    g2d.draw(my_path);
  }
}
