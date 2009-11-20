package tetrispieces;

import java.awt.Point;

public class ZPiece extends TetrisPiece
{
  public ZPiece()
  {
    setStartPoint(new Point(-1, -1));
    Point[] zShape = new Point[] {new Point(-1, 0), new Point(0, 0),
                                  new Point(0, -1), new Point(1, -1)};
    setLocalPosition(zShape);
  }
}
