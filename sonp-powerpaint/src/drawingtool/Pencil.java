package drawingtool;

import java.awt.Point;
import java.awt.Shape;
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
  
  public void setStartPoint(Point the_start_point)
  {
    my_path = new GeneralPath();
    my_path.moveTo(the_start_point.x, the_start_point.y);
  }
  
  public Shape drawShape()
  {
    Point next_point = getEndPoint();
    my_path.lineTo(next_point.x, next_point.y);
    return my_path;
  }
}
