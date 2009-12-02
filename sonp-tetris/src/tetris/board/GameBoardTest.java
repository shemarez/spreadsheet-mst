package tetris.board;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tetris.entities.Point;
import tetris.entities.pieces.IPiece;
import tetris.entities.pieces.JPiece;
import tetris.entities.pieces.LPiece;
import tetris.entities.pieces.OPiece;
import tetris.entities.pieces.Piece;
import tetris.entities.pieces.SPiece;
import tetris.entities.pieces.TPiece;
import tetris.entities.pieces.ZPiece;

/**
 * A test for GameBoard class.
 * @author Son Pham
 * @version 1.0
 */
public class GameBoardTest
{
  /**
   * Pre-defined piece array to test.
   */
  private final Piece[] my_piece_array = 
    new Piece[] {new TPiece().setOrigin(new Point(0, 20)),
                new OPiece().setOrigin(new Point(2, 20)),
                new LPiece().setOrigin(new Point(4, 20)),
                new OPiece().setOrigin(new Point(6, 20)),
                rotateCWnTimes(new LPiece(), 2).setOrigin(new Point(8, 20)),
                new JPiece().rotateClockwise().setOrigin(new Point(0, 20)),
                new IPiece().setOrigin(new Point(1, 20)),
                new SPiece().rotateCounterclockwise().setOrigin(new Point(3, 20)),
                new JPiece().rotateClockwise().setOrigin(new Point(7, 20)),
                new IPiece().rotateClockwise().setOrigin(new Point(4, 20)),
                new ZPiece().setOrigin(new Point(7, 20)),
                new TPiece().rotateClockwise().setOrigin(new Point(8, 20)),
                new JPiece().rotateCounterclockwise().setOrigin(new Point(1, 20)),
                new OPiece().setOrigin(new Point(4, 20)),
                new LPiece().rotateCounterclockwise().setOrigin(new Point(1, 20)),
                new TPiece().setOrigin(new Point(3, 20)),
                new SPiece().rotateCounterclockwise().setOrigin(new Point(5, 20)),
                new ZPiece().rotateClockwise().setOrigin(new Point(7, 20)),
                new LPiece().setOrigin(new Point(0, 20)),
                new ZPiece().rotateClockwise().setOrigin(new Point(1, 20)),
                new IPiece().rotateClockwise().setOrigin(new Point(-2, 20)),
                new ZPiece().rotateClockwise().setOrigin(new Point(3, 20)),
                rotateCWnTimes(new LPiece(), 2).setOrigin(new Point(6, 20)),
                rotateCCWnTimes(new JPiece(), 2).setOrigin(new Point(-1, 20)),
                rotateCCWnTimes(new LPiece(), 2).setOrigin(new Point(1, 20)),
                new TPiece().setOrigin(new Point(3, 20)),
                new ZPiece().rotateClockwise().setOrigin(new Point(-1, 20)),
                new IPiece().setOrigin(new Point(2, 20)),
                new TPiece().setOrigin(new Point(7, 20)),
                new LPiece().rotateClockwise().setOrigin(new Point(6, 20)),
                new JPiece().rotateCounterclockwise().setOrigin(new Point(7, 20))};
  
  /**
   * The second piece array for testing.
   */
  private final Piece[] my_piece_array_2 = 
    new Piece[] {new IPiece().rotateClockwise().setOrigin(new Point(7, 4))}; 
  
  /**
   * What piece results from rotating the_piece clock-wise n times.
   * @param the_piece The piece.
   * @param the_n Number of times.
   * @return A piece results from rotating the_piece clock-wise n times.
   */
  private static Piece rotateCWnTimes(final Piece the_piece, final int the_n)
  {
    Piece temp = the_piece;
    for (int i = 0; i < the_n; i++)
    {
      temp = temp.rotateClockwise();
    }
    return temp;
  }
  
  /**
   * What piece results from rotating the_piece counter clock-wise n times.
   * @param the_piece The piece.
   * @param the_n Number of times.
   * @return A piece results from rotating the_piece counter clock-wise n times.
   */
  private static Piece rotateCCWnTimes(final Piece the_piece, final int the_n)
  {
    Piece temp = the_piece;
    for (int i = 0; i < the_n; i++)
    {
      temp = temp.rotateCounterclockwise();
    }
    return temp;
  }
  
  /**
   * Run the auto simulate Tetris game with the pre-defined pieces.
   */
  @Test
  public void testConstructor()
  {
    final List<Piece> list_of_pieces = Arrays.asList(my_piece_array);
    final GameBoard board = new GameBoard(list_of_pieces);
    final Row the_row = 
      new Row(new Color[] {Row.EMPTY_COLOR, Color.RED, Row.EMPTY_COLOR,  
                           Row.EMPTY_COLOR, Row.EMPTY_COLOR, Row.EMPTY_COLOR, 
                           Row.EMPTY_COLOR, Color.BLUE, Color.BLUE, Color.BLUE});
                           
    final List<Row> expectedResultRows = new ArrayList<Row>(GameBoard.TOTAL_HEIGHT);
    expectedResultRows.add(the_row);
    for (int i = 1; i < GameBoard.TOTAL_HEIGHT; i++)
    {
      expectedResultRows.add(new Row(GameBoard.WIDTH));
    }
    assertEquals("The final game board", expectedResultRows, board.rows());
  }
  
  /**
   * Test some illegal movements like moving the piece to the right when it's already at
   * the right edge of the game board, etc.
   */
  @Test
  public void testIllegalMovement()
  {
    final Piece[] piece_array = new Piece[]{new LPiece().rotateCounterclockwise()};
    final GameBoard board = new GameBoard(Arrays.asList(piece_array));
    final GameBoard new_board = new GameBoard(Arrays.asList(piece_array));
    new_board.moveLeft();
    assertEquals("The new board should be equal to the old board", board.toString(), 
                 new_board.toString());
    
    final GameBoard board2 = new GameBoard(Arrays.asList(my_piece_array_2));
    final GameBoard new_board_2 = new GameBoard(Arrays.asList(my_piece_array_2));
    new_board_2.rotateClockwise();
    assertEquals("The new_board_2 should be equal to the board_2", board2.toString(), 
                 new_board_2.toString());
  }
}
