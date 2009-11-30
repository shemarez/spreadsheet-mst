package tetris.board;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
   * List of pre-defined pieces.
   */
  private final List<Piece> my_list_of_pieces = new ArrayList<Piece>();
  
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
   * Set up the list of pre-defined pieces.
   */
  @Before
  public void initializeVariable()
  {
    /*
    final Block[] Blocks1 = 
      new Block[] {new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                   new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                   new Block(true, Color.BLUE), new Block(true, Color.BLUE),
                   new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                   new Block(false, Color.WHITE), new Block(false, Color.WHITE)};
    final Block[] Blocks2 = 
      new Block[] {new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                   new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                   new Block(false, Color.WHITE), new Block(true, Color.BLUE),
                   new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                   new Block(false, Color.WHITE), new Block(false, Color.WHITE)};
    Row1 = new Row(Blocks1);
    Row2 = new Row(Blocks2);
    piece_list_1 = new ArrayList<Piece>();
    piece_list_1.add(new JPiece());
    piece_list_2 = new ArrayList<Piece>();*/
    
    Piece temp_piece = new SPiece();
    temp_piece = temp_piece.setOrigin(new Point(2, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = temp_piece.setOrigin(new Point(0, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new TPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(1, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.setOrigin(new Point(4, 0));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = temp_piece.setOrigin(new Point(7, 0));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(4, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = rotateCWnTimes(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(8, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = rotateCCWnTimes(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(2, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new TPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = temp_piece.setOrigin(new Point(8, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(2, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(4, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new SPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(6, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = temp_piece.setOrigin(new Point(7, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(0, 20));    
    my_list_of_pieces.add(temp_piece);
    temp_piece = new SPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = rotateCWnTimes(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(8, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(1, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = rotateCWnTimes(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(0, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.setOrigin(new Point(1, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(7, 20));
    my_list_of_pieces.add(temp_piece);
    temp_piece = new TPiece();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    my_list_of_pieces.add(temp_piece);
  }
  
  /**
   * Run the auto simulate Tetris game with the pre-defined pieces.
   */
  @Test
  public void testConstructor()
  {
    final GameBoard board = new GameBoard(my_list_of_pieces);
    final Row the_row = 
      new Row(new Block[] {new Block(), new Block(), new Block(), new Block(), new Block(),
                           new Block(true, Color.MAGENTA.darker()),
                           new Block(true, Color.MAGENTA.darker()),
                           new Block(true, Color.MAGENTA.darker()), new Block(), new Block()});
    final List<Row> expectedResultRows = new ArrayList<Row>(GameBoard.TOTAL_HEIGHT);
    expectedResultRows.add(the_row);
    for (int i = 1; i < GameBoard.TOTAL_HEIGHT; i++)
    {
      expectedResultRows.add(new Row(GameBoard.WIDTH));
    }
    assertEquals("The final game board", expectedResultRows, board.rows());
  }
}
