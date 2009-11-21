/**
 * The Z piece in Tetris game.
 * Fall 09.
 */
package tetrispieces;

import java.awt.Point;

/**
 * The Z piece in Tetris game.
 * @author Son
 * @version 1.0
 */
public class ZPiece extends TetrisPiece
{
  //Constructor
  
  /**
   * Constructs a Z piece.
   */
  public ZPiece()
  {
    super();
    setStartPoint(new Point(-1, -1));
    final Point[] zShape = new Point[] {new Point(-1, 0), new Point(0, 0),
                                        new Point(0, -1), new Point(1, -1)};
    setLocalPointArray(zShape);
  }
}
