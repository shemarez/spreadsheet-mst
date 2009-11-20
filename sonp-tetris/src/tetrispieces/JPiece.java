package tetrispieces;

import java.awt.Point;

public class JPiece extends TetrisPiece
{
  public JPiece()
  {
    setStartPoint(new Point(-1, -1));
    Point[] jShape = new Point[] {new Point(-1, 1), new Point(-1, 0),
                                  new Point(0, 0), new Point(1, 0)};
    setLocalPosition(jShape);
  }
}
