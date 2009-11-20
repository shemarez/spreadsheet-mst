package tetrispieces;

import java.awt.Point;

public class SPiece extends TetrisPiece
{
  public SPiece()
  {
    setStartPoint(new Point(-1, -1));
    Point[] sShape = new Point[] {new Point(-1, -1), new Point(0, -1),
                                  new Point(0, 0), new Point(1, 0)};
    setLocalPosition(sShape);
  }
}
