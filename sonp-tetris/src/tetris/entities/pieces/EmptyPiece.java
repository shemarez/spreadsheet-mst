package tetris.entities.pieces;

import java.awt.Color;
import java.util.Arrays;

import tetris.board.Row;
import tetris.entities.Point;

/**
 * Empty piece class for Tetris game.
 * @author Son Pham
 * @version 1.0
 */
public class EmptyPiece extends Piece
{
  /**
   * The color of an Empty piece.
   */
  public static final Color COLOR = Row.EMPTY_COLOR;

  // @constraint Rotation 0 has positions ((1, 1), (1, 2), (2, 1), (2, 2)).
  /**
   * Rotation 0 of an Empty piece.
   */
  private static final Point[] ROTATION_ZERO =
      new Point[] {new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2)};

  /**
   * Constructs an Empty piece.
   */
  public EmptyPiece()
  {
    super(COLOR, Arrays.asList(new Rotation[] {new Rotation(ROTATION_ZERO)}));
  }
}
