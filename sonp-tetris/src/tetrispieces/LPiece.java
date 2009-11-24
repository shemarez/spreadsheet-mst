/**
 * The L piece for Tetris game.
 * Fall 09.
 */
package tetrispieces;

import java.awt.Point;

/**
 * The L piece class for Tetris game.
 * @author Son
 * @version 1.0
 */
public class LPiece extends TetrisPiece
{
  //Constructor
  
  /**
   * Constructs a L piece.
   */
  public LPiece()
  {
    super();
    setStartPoint(new Point(-1, -1));
    final Point[] lShape = new Point[] {new Point(-1, 0), new Point(0, 0),
                                        new Point(1, 0), new Point(1, 1)};
    setLocalPointArray(lShape);
  }
  
  public String toString()
  {
    String result = "    *\n";
    result       += "* * *";
    return result;
  }
}
