package tetrispieces;

import java.awt.Point;

public class TPiece extends TetrisPiece
{
  public TPiece()
  {
    setStartPoint(new Point(-1, -1));
    Point[] tShape = new Point[] {new Point(-1, -1), new Point(0, -1),
                                  new Point(0, 0), new Point(1, -1)};
    setLocalPosition(tShape);
  }
}
