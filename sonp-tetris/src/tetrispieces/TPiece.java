/**
 * The T piece for Tetris game.
 * Fall 09.
 */
package tetrispieces;

import java.awt.Point;

/**
 * The T piece for Tetris game.
 * @author Son
 * @version 1.0
 */
public class TPiece extends TetrisPiece
{
  //Constructor
  
  /**
   * The T piece for Tetris game.
   */
  public TPiece()
  {
    super();
    setStartPoint(new Point(-1, -1));
    final Point[] tShape = new Point[] {new Point(-1, -1), new Point(0, -1),
                                        new Point(0, 0), new Point(1, -1)};
    setLocalPointArray(tShape);
  }
}
