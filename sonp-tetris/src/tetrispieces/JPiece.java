/**
 * The J piece class for Tetris game.
 * Fall 09.
 */
package tetrispieces;

import java.awt.Point;

/**
 * The J piece class for Tetris game.
 * @author Son
 * @version 1.0
 */
public class JPiece extends TetrisPiece
{
  //Constructor
  
  /**
   * Constructs a J piece.
   */
  public JPiece()
  {
    super();
    setStartPoint(new Point(-1, -1));
    final Point[] jShape = new Point[] {new Point(-1, 1), new Point(-1, 0),
                                        new Point(0, 0), new Point(1, 0)};
    setLocalPointArray(jShape);
  }
}
