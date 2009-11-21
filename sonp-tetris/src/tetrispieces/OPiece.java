/**
 * The O piece for Tetris game.
 * Fall 09
 */
package tetrispieces;

import java.awt.Point;

/**
 * The O piece for Tetris game.
 * @author Son
 * @version 1.0
 */
public class OPiece extends TetrisPiece
{
  //Constructor
  
  /**
   * Constructs an O piece.
   */
  public OPiece()
  {
    super();
    setStartPoint(new Point(0, 0));
    final Point[] oShape = new Point[] {new Point(0, 0), new Point(1, 0),
                                        new Point(1, 1), new Point(0, 1)};
    setLocalPointArray(oShape);
  }
  
  //Instance methods
  
  /**
   * Rotate the O piece clock-wise.
   */
  public void rotateCW(){};
  
  /**
   * Rotate the O piece counter clock_wise.
   */
  public void rotateCounterCW(){};
}
