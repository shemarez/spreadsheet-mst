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
  private static final int WIDTH = 10;
  
  /**
   * The total height of the board.
   */
  private static final int TOTAL_HEIGHT = 24;
  
  /**
   * The middle of the board's width.
   */
  private static final int MIDDLE = 4;
  
  /**
   * The visible height of the board.
   */
  private static final int VISIBLE_HEIGHT = 20;
  
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
   * Construct a game board with the dimension of WIDTH x VISIBLE_HEIGHT.
   */
  public GameBoard()
  {
    super();
    my_rows = new ArrayList<Row>(TOTAL_HEIGHT);
    for (int i = 0; i < TOTAL_HEIGHT; i++)
    {
      my_rows.set(i, new Row(WIDTH));
    }
  }
  
  /**
   * Erase the_piece in the game board.
   * @param the_piece The piece to be erased.
   */
  private void erasePiece(final Piece the_piece)
  {
    for (int i = 0; i < the_piece.blocks().length; i++)
    {
      final int the_x = the_piece.blocks()[i].x();
      final int the_y = the_piece.blocks()[i].y();      
      my_rows.get(the_y).setBlockIndex(the_x, new Block());
    }
  }
  
  /**
   * Add the_piece to the game board.
   * @param the_piece The piece.
   */
  private void addPiece(final Piece the_piece)
  {
    try
    {
      final Piece temp = (Piece) the_piece.clone();
      for (int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++)
      {
        final int the_x = temp.blocks()[i].x();
        final int the_y = temp.blocks()[i].y();
        my_rows.get(the_y).setBlockIndex(the_x, 
                                         new Block(true, temp.color()));
      }
    }
    catch (final CloneNotSupportedException e)
    {
      // This should never happen.
      assert false;
    }    
  }
  
  /**
   * Start the new piece above the visible board.
   * @param the_piece The piece.
   */
  private void startNewPiece(final Piece the_piece)
  {
    final Point the_origin = new Point(MIDDLE, VISIBLE_HEIGHT + 1);
    final Piece temp = the_piece.setOrigin(the_origin);
    addPiece(temp);
  }
  
  /**
   * 
   * @param the_piece The piece.
   * @return True if the position of the_piece on the game board is
   *         legal. False otherwise.
   */
  private boolean isLegalPosition(final Piece the_piece)
  {
    boolean result = true;
    try
    {
      final Piece temp = (Piece) the_piece.clone();      
      for (int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++)
      {
        final int the_x = temp.blocks()[i].x();
        final int the_y = temp.blocks()[i].y();
        result = result && 0 <= the_x && the_x < WIDTH && 
                 0 <= the_y && the_y < VISIBLE_HEIGHT;
        result = result && !(my_rows.get(the_y).getBlockIndex(the_x).isFilled());        
        if (!result)
        {
          return result;
        }
      }
    }
    catch (final CloneNotSupportedException e)
    {
      //This should never happen
      assert false;
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
      if (my_rows.get(i).isCompleteFilled())
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
  
  /**
   * Move the current falling piece to the left by one column.
   */
  public void moveLeft()
  {
    final Piece temp = my_falling_piece.moveLeft();
    if (isLegalPosition(temp))
    {
      erasePiece(my_falling_piece);
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
  }
  
  /**
   * Move the current falling piece to the right by one column.
   */
  public void moveRight()
  {
    final Piece temp = my_falling_piece.moveRight();
    if (isLegalPosition(temp))
    {
      erasePiece(my_falling_piece);
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
  }
  
  /**
   * Move the current falling piece down by one row.
   */
  public void moveDown()
  {
    final Piece temp = my_falling_piece.moveDown();
    if (isLegalPosition(temp))
    {
      erasePiece(my_falling_piece);
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
  }
  
  /**
   * Rotate the current falling piece one step clock-wise.
   */
  public void rotateClockwise()
  {
    final Piece temp = my_falling_piece.rotateClockwise();
    if (isLegalPosition(temp))
    {
      erasePiece(my_falling_piece);
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
  }
  
  /**
   * Rotate the current falling piece one step counter clock-wise.
   */
  public void rotateCounterclockwise()
  {
    final Piece temp = my_falling_piece.rotateCounterclockwise();
    if (isLegalPosition(temp))
    {
      erasePiece(my_falling_piece);
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
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
    if (isLegalPosition(temp))
    {
      erasePiece(my_falling_piece);
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
    else
    {
      clearCompletelyFilledLines();
      my_falling_piece = randomPiece();
      startNewPiece(my_falling_piece);
    }
    setChanged();
    notifyObservers(my_rows);
  }  
}
