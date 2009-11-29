/**
 * GameBoard class for Tetris game.
 * Fall 09.
 */
package tetris.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

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
 * GameBoard class for Tetris game.
 * @author Son Pham
 * @version 1.0
 */
public class GameBoard extends Observable
{
  //Static fields
  
  /**
   * The width of the board.
   */
  public static final int WIDTH = 10;
  
  /**
   * The total height of the board.
   */
  public static final int TOTAL_HEIGHT = 24;
  
  /**
   * The middle of the board's width.
   */
  public static final int MIDDLE = 4;
  
  /**
   * The visible height of the board.
   */
  public static final int VISIBLE_HEIGHT = 20;
  
  /**
   * The border character of the game board.
   */
  public static final String BORDER = "|";
  
  /**
   * 7 basic pieces.
   */
  private static final Piece[] BASIC_PIECES = new Piece[]{new IPiece(), new JPiece(),
                                                          new LPiece(), new OPiece(),
                                                          new SPiece(), new TPiece(),
                                                          new ZPiece()};
  
  //Instance fields
  
  /**
   * My list of rows of the game board.
   */
  private final List<Row> my_rows;
  
  /**
   * The current falling piece.
   */
  private Piece my_falling_piece;
  
  //Constructor
  
  /**
   * Construct an empty game board with the dimension of WIDTH x VISIBLE_HEIGHT. 
   * And also pick randomly a new piece to be the current falling piece.
   */
  public GameBoard()
  {
    super();
    my_rows = new ArrayList<Row>(TOTAL_HEIGHT);
    for (int i = 0; i < TOTAL_HEIGHT; i++)
    {
      my_rows.add(i, new Row(WIDTH));
    }
    startNewPiece(BASIC_PIECES[1]);
    //startNewPiece(randomPiece());
  }
  
  //Private methods
  
  /**
   * Erase the_piece in the game board.
   * @param the_piece The piece to be erased.
   */
  private void erasePiece(final Piece the_piece)
  {
    for (int i = 0; i < the_piece.blocks().length; i++)
    {
      final int the_x = the_piece.absolutePosition(i).x();
      final int the_y = the_piece.absolutePosition(i).y();      
      my_rows.get(the_y).setBlockIndex(the_x, new Block());
    }
  }
  
  /**
   * Add the_piece to the game board.
   * @param the_piece The piece.
   */
  private void addPiece(final Piece the_piece)
  {
    for (int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++)
    {
      final int the_x = the_piece.absolutePosition(i).x();
      final int the_y = the_piece.absolutePosition(i).y();
      my_rows.get(the_y).setBlockIndex(the_x, 
                                       new Block(true, the_piece.color()));
    }   
  }
  
  /**
   * Start a new piece above the visible board. And also set the my_falling_piece
   * to be the new piece.
   * @param the_piece The piece.
   */
  private void startNewPiece(final Piece the_piece)
  {
    final Point the_origin = new Point(MIDDLE, VISIBLE_HEIGHT);
    my_falling_piece = the_piece.setOrigin(the_origin);
    addPiece(my_falling_piece);
  }
  
  /**
   * @param the_piece The piece.
   * @return True if the position of the_piece on the game board is
   *         legal. False otherwise.
   */
  private boolean isLegalPosition(final Piece the_piece)
  {
    boolean result = true;      
    for (int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++)
    {
      final int the_x = the_piece.absolutePosition(i).x();
      final int the_y = the_piece.absolutePosition(i).y();
      result = result && 0 <= the_x && the_x < WIDTH && 
               0 <= the_y && the_y < TOTAL_HEIGHT;
      result = result && !(my_rows.get(the_y).getBlockIndex(the_x).isFilled());        
      if (!result)
      {
        return result;
      }
    }  
    return result;
  }
  
  /**
   * Clear all completely filled lines and move all above lines down.
   */
  private void clearCompletelyFilledLines()
  {
    for (int i = 0; i < VISIBLE_HEIGHT; i++)
    {
      if (my_rows.get(i).isCompletelyFilled())
      {
        my_rows.remove(i);
        my_rows.add(new Row(WIDTH));
      }
    }
  }
  
  /**
   * Generate a random piece from the 7 basic pieces.
   * @return A random piece from the 7 basic pieces.
   */
  private Piece randomPiece()
  {
    final Random rand = new Random();
    final Piece the_piece;
    final int i = rand.nextInt(6);
    the_piece = BASIC_PIECES[i];
    return the_piece;
  }
  
  //Instance methods.
  
  /**
   * @return The list of rows in the game board.
   */
  public List<Row> rows()
  {
    return my_rows;
  }
  
  /**
   * Move the current falling piece to the left by one column.
   */
  public void moveLeft()
  {
    final Piece temp = my_falling_piece.moveLeft();
    erasePiece(my_falling_piece);
    if (isLegalPosition(temp))
    {      
      my_falling_piece = temp;
    }
    addPiece(my_falling_piece);
  }
  
  /**
   * Move the current falling piece to the right by one column.
   */
  public void moveRight()
  {
    final Piece temp = my_falling_piece.moveRight();
    erasePiece(my_falling_piece);
    if (isLegalPosition(temp))
    {      
      my_falling_piece = temp;      
    }
    addPiece(my_falling_piece);
  }
  
  /**
   * Move the current falling piece down by one row.
   */
  public void moveDown()
  {
    final Piece temp = my_falling_piece.moveDown();
    erasePiece(my_falling_piece);
    if (isLegalPosition(temp))
    {      
      my_falling_piece = temp;      
    }
    addPiece(my_falling_piece);
  }
  
  /**
   * Rotate the current falling piece one step clock-wise.
   */
  public void rotateClockwise()
  {
    final Piece temp = my_falling_piece.rotateClockwise();
    erasePiece(my_falling_piece);
    if (isLegalPosition(temp))
    {      
      my_falling_piece = temp;      
    }
    addPiece(my_falling_piece);
  }
  
  /**
   * Rotate the current falling piece one step counter clock-wise.
   */
  public void rotateCounterclockwise()
  {
    final Piece temp = my_falling_piece.rotateCounterclockwise();
    erasePiece(my_falling_piece);
    if (isLegalPosition(temp))
    {      
      my_falling_piece = temp;      
    }
    addPiece(my_falling_piece);
  }
  
  /**
   * Update the game board by one "time-tick" step. Update causes the current falling
   * piece to move down by one row, and might cause addition changes to the game board
   * as follow:
   * <p>
   *  _When the current_falling_piece is resting on top of existing filled squares and
   *  then attempts to move down one more, it freezes and becomes part of the board's
   *  grid of filled squares.
   * <p>
   *  _When the current_falling_piece freezes, if any lines are completely filled, they
   *  are cleared from the board. All squares above a filled line drop downward by one row.
   *  (note that up to 4 lines may be cleared at a time).
   * <p>
   *  _When the current_falling_piece freezes, a new current_falling_piece is chosen
   *  randomly and appears above the middle of the board.
   */
  public void update()
  {
    final Piece temp = my_falling_piece.moveDown();
    erasePiece(my_falling_piece);
    if (isLegalPosition(temp))
    {      
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
    else
    {
      addPiece(my_falling_piece);
      clearCompletelyFilledLines();
      my_falling_piece = randomPiece();
      startNewPiece(my_falling_piece);
    }
    setChanged();
    notifyObservers(my_rows);
  }
  
  /**
   * @return The printable representation of this GameBoard object.
   */
  public String toString()
  {
    final StringBuffer sb = new StringBuffer(150);
    if (!my_rows.get(VISIBLE_HEIGHT).isEmpty())
    {
      sb.append("Below is a piece in progress on top of the visible board.\n");
      for (int i = TOTAL_HEIGHT - 1; i >= VISIBLE_HEIGHT; i--)
      {
        final Row temp_row = my_rows.get(i);
        sb.append(BORDER + temp_row.toString() + BORDER + "\n");
      }
    }
    sb.append("\nBelow is the visible tetris game board.\n");
    for (int i = VISIBLE_HEIGHT - 1; i >= 0; i--)
    {
      final Row temp_row = my_rows.get(i);
      sb.append(BORDER + temp_row.toString() + BORDER + "\n");
    }
    return sb.toString();
  }
}
