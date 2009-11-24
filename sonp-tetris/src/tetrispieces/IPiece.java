/**
 * The I piece class for Tetris game.
 * Fall  09
 */
package tetrispieces;

import java.awt.Point;
/**
 * The I piece class for Tetris game.
 * @author Son
 * @version 1.0
 */
public class IPiece extends TetrisPiece
{
  //Constructor
  
  /**
   * Constructs an I piece object.
   */
  public IPiece()
  {
    super();
    setStartPoint(new Point(-2, -2));
    final Point[] iShape = new Point[] {new Point(0, -1), new Point(0, 0),
                                        new Point(0, 1), new Point(0, 2)};
    setLocalPointArray(iShape);
  }
  
  public String toString()
  {
    return "* * * *";
  }
}
