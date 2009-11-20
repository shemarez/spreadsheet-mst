package tetrispieces;

import java.awt.Point;

public class IPiece extends TetrisPiece
{
  public IPiece()
  {
    setStartPoint(new Point(-2, -2));
    Point[] iShape = new Point[] {new Point(0, -1), new Point(0, 0),
                                  new Point(0, 1), new Point(0, 2)};
    setLocalPosition(iShape);
  }
}
