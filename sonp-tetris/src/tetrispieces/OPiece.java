package tetrispieces;

import java.awt.Point;

public class OPiece extends TetrisPiece
{
  public OPiece()
  {
    setStartPoint(new Point(0, 0));
    Point[] oShape = new Point[] {new Point(0, 0), new Point(1, 0),
                                  new Point(1, 1), new Point(0, 1)};
    setLocalPosition(oShape);
  }
  
  public void rotateCW() {};
  public void rotateCounterCW() {};
}
