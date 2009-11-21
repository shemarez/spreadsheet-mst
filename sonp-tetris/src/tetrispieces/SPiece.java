/**
 * The S piece of Tetris game.
 * Fall 09.
 */
package tetrispieces;

import java.awt.Point;

/**
 * The S piece of Tetris game.
 * @author Son
 * @version 1.0
 */
public class SPiece extends TetrisPiece
{
  //Constructor
  
  /**
   * Constructs a S piece.
   */
  public SPiece()
  {
    super();
    setStartPoint(new Point(-1, -1));
    final Point[] sShape = new Point[] {new Point(-1, -1), new Point(0, -1),
                                        new Point(0, 0), new Point(1, 0)};
    setLocalPointArray(sShape);
  }
}
