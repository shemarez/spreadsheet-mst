package tetrispieces;

import java.awt.Point;

public class LPiece extends TetrisPiece
{
  public LPiece()
  {
    setStartPoint(new Point(-1, -1));
    Point[] lShape = new Point[] {new Point(-1, 0), new Point(0, 0),
                                  new Point(1, 0), new Point(1, 1)};
    setLocalPosition(lShape);
  }
}
