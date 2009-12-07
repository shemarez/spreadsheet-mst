/**
 * GameBoard class for Tetris game.
 * Fall 09.
 */
package tetris.board;

import java.util.ArrayList;
import java.util.Iterator;
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
   * The new line character.
   */
  public static final String NEW_LINE = "\n";
  
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
  private final List<Row> /* @ non_null */ my_rows;
  
  /**
   * The current falling piece.
   */
  private Piece /* @ non_null */ my_falling_piece;
  
  /**
   * The next piece in progress.
   */
  private Piece /* @ non_null */ my_next_piece;
  
  /**
   * Is the game over?
   */
  private boolean my_game_over;
  
  //Constructors
  
  //@ ensures my_rows != null;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  //@ ensures forall int i = 0; i < VISIBLE_HEIGHT; i++; my_rows[i] = new Row(WIDTH);
  //@ ensures my_falling_piece = randomPiece();
  //@ ensures my_falling_piece starts off at the top of the game board (in the invisible
  //          area).
  /**
   * Construct an empty game board with the dimension of WIDTH x VISIBLE_HEIGHT. 
   * And also pick randomly a new piece to be the current falling piece.
   */
  public GameBoard()
  {
    super();
    my_game_over = false;
    my_rows = new ArrayList<Row>(TOTAL_HEIGHT);
    for (int i = 0; i < TOTAL_HEIGHT; i++)
    {
      my_rows.add(i, new Row(WIDTH));
    }
    my_next_piece = randomPiece();
    startNewPiece(my_next_piece);
  }
  
  //@ requires the_piece_list != null
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  //@ ensures my_falling_piece = the_piece_list.get(the_piece_list.size() - 1);
  //@ ensures Result is a game board after feeding one piece at a time all of the pieces 
  //          in the_piece_list in the order they have in the list. For each feeding
  //          period, the piece is place onto the game board as their specification and
  //          is letting to drop down until it can't anymore. Then a next piece is loaded
  //          onto the game board.
  /**
   * Construct a simulated Tetris game. First, an empty game board is created. 
   * Then, the first element in the_piece_list is set as a new piece in progress.
   * The piece then go down until it can't anymore. The process repeats with the
   * next element in the_piece_list is set as a new piece in progress. 
   * @param the_piece_list The list of pieces.
   */
  public GameBoard(final List<Piece> /* @ non_null */ the_piece_list)
  {
    super();
    my_rows = new ArrayList<Row>(TOTAL_HEIGHT);
    for (int i = 0; i < TOTAL_HEIGHT; i++)
    {
      my_rows.add(i, new Row(WIDTH));
    }
    final Iterator<Piece> the_iter = the_piece_list.iterator();
    while (the_iter.hasNext())
    {      
      my_falling_piece = the_iter.next();
      addPiece(my_falling_piece);
      updateForTesting();
    } 
  }
  
  //Private methods
  
  //@ requires the_piece != null;
  //@ requies my_rows.size() = TOTAL_HEIGHT;
  //@ ensures \forall int i = 0; i < the_piece.blocks().length; i++;
  //          the point the_piece.absolutePosition(i) on the game board
  //          will have the Row.EMMPTY_COLOR;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  //@ ensures my_falling_piece is unchanged;
  /**
   * Erase the_piece in the game board.
   * @param the_piece The piece to be erased.
   */
  private void erasePiece(final Piece /* @ non_null */ the_piece)
  {
    for (int i = 0; i < the_piece.blocks().length; i++)
    {
      final int the_x = the_piece.absolutePosition(i).x();
      final int the_y = the_piece.absolutePosition(i).y();      
      my_rows.get(the_y).setColorIndex(the_x, Row.EMPTY_COLOR);
    }
  }
  
  //@ requires the_piece != null.
  //@ requires my_rows.size() = TOTAL_HEIGHT;
  //@ requires \forall int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++;
  //            the Point the_piece.absolutePosition(i) is inside the 
  //            boundary of the board.
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  //@ ensures \forall int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++;
  //          the point the_piece.absolutePosition(i) on the game board
  //          is set to the color of the_piece.color;
  /**
   * Add the_piece to the game board.
   * @param the_piece The piece.
   */
  private void addPiece(final Piece /* @ non_null */ the_piece)
  {
    for (int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++)
    {
      final int the_x = the_piece.absolutePosition(i).x();
      final int the_y = the_piece.absolutePosition(i).y();
      my_rows.get(the_y).setColorIndex(the_x, the_piece.color());
    }   
  }
  
  //@ requires the_piece != null;
  //@ requires my_rows.size() = TOTAL_HEIGHT;
  //@ ensures my_falling_piece = the_piece if the position of the_piece in the game
  //          board is legal && update my_falling_piece's shape on the game board;
  //@ ensures The observers are notified if there's change in the game board;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  /**
   * Replace my_falling_piece by the_piece if the_piece's position on the game board 
   * is legal. And update my_falling_piece's new shape on the game board. Otherwise 
   * my_falling_piece stay the same. Notify the observers if the result game board is 
   * different with before.
   * @param the_piece The piece.
   */
  private void replaceCurrentFallingPiece(final Piece /* @ non_null */the_piece)
  {
    if (!the_piece.equals(my_falling_piece))
    {
      erasePiece(my_falling_piece);
      if (isLegalPosition(the_piece))
      {      
        my_falling_piece = the_piece;
        addPiece(my_falling_piece);
        setChanged();
        notifyObservers(this);
      }
      else
      {
        addPiece(my_falling_piece);
      }
    }
  }
  
  //@ requires the_piece != null;
  //@ requires my_rows.size() = TOTAL_HEIGHT;
  //@ requires the height of the piece <= TOTAL_HEIGHT - VISIBLE_HEIGHT;
  //@ ensures my_falling_piece = the_piece.setOrigin(new Point(MIDDLE, VISIBLE_HEIGHT)
  //          && update my_falling_piece's new shape onto the game board;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  //@ ensures my_next_piece = randomPiece();
  /**
   * Start a new piece above the visible board. And also set the my_falling_piece
   * to be the new piece. Pick a random piece to be my_next_piece.
   * @param the_piece The piece.
   */
  private void startNewPiece(final Piece /* @ non_null */ the_piece)
  {
    final Point the_origin = new Point(MIDDLE, VISIBLE_HEIGHT);
    my_falling_piece = the_piece.setOrigin(the_origin);
    my_next_piece = randomPiece();
    addPiece(my_falling_piece);
  }
  
  //@ requires my_rows.size() = TOTAL_HEIGHT;
  //@ requires the_piece != null;
  //@ ensures \forall int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++;
  //          Result = 0 <= the_piece.absolutePosition(i).x() < WIDTH
  //                   && 0 <= the_piece.absolutePosition(i).y() < TOTAL_HEIGHT
  //                   && the point the_piece.absolutePosition(i) on the game board
  //                   has an EMPTY_COLOR;
  //@ ensures my_rows is unchanged;
  //@ ensures my_falling_piece is unchanged;
  /**
   * @param the_piece The piece.
   * @return True if the position of the_piece on the game board is
   *         legal: 0 <= the_piece.x() < WIDTH && 0 <= the_piece.y < TOTAL_HEIGHT. 
   *         False otherwise.
   */
  private boolean /* @ pure */ isLegalPosition(final Piece /* @ non_null */ the_piece)
  {
    boolean result = true;      
    for (int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++)
    {
      final int the_x = the_piece.absolutePosition(i).x();
      final int the_y = the_piece.absolutePosition(i).y();
      result = result && 0 <= the_x && the_x < WIDTH && 
               0 <= the_y && the_y < TOTAL_HEIGHT;
      result = result && 
        my_rows.get(the_y).getColorIndex(the_x).equals(Row.EMPTY_COLOR);        
      if (!result)
      {
        return result;
      }
    }  
    return result;
  }
  
  //@ requires my_rows != null;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  //@ ensures All the completely filled lines will be deleted. All the lines above
  //          will drop down by that number of lines being deleted.
  //@ ensures my_falling_piece is unchanged;
  //@ ensures hasChanged == true if my_rows has been changed;
  /**
   * Clear all completely filled lines and move all above lines down.
   */
  private void clearCompletelyFilledLines()
  {
    int count = 0;
    for (int i = 0; i < VISIBLE_HEIGHT; i++)
    {
      while (my_rows.get(i).isCompletelyFilled())
      {
        count = count + 1;
        my_rows.remove(i);
        my_rows.add(new Row(WIDTH));
      }
    }
    if (count > 0)
    {
      setChanged();
    }
  }
  
  //@ ensures Result = a piece that is randomly picked from the BASIC_PIECES;
  /**
   * Generate a random piece from the 7 basic pieces.
   * @return A random piece from the 7 basic pieces.
   */
  private Piece /* @ pure */ randomPiece()
  {
    final Random rand = new Random();
    final Piece the_piece;
    final int i = rand.nextInt(6);
    the_piece = BASIC_PIECES[i];
    return the_piece;
  }
  
  //@ requires my_rows.size() = TOTAL_HEIGHT;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  //@ ensures my_falling_piece move down until it can't;
  //@ ensures all completed lines are deleted.
  /**
   * Update the game board by one "time-tick" step until it can't anymore. Update 
   * causes the current falling piece to move down by one row, and might cause 
   * addition changes to the game board as follow:
   * <p>
   *  _When the current_falling_piece is resting on top of existing filled squares and
   *  then attempts to move down one more, it freezes and becomes part of the board's
   *  grid of filled squares.
   * <p>
   *  _When the current_falling_piece freezes, if any lines are completely filled, they
   *  are cleared from the board. All squares above a filled line drop downward by one row.
   *  (note that up to 4 lines may be cleared at a time).
   *  
   */
  private void updateForTesting()
  {
    Piece temp = my_falling_piece.moveDown();
    erasePiece(my_falling_piece);
    while (isLegalPosition(temp))
    {
      my_falling_piece = temp;
      addPiece(my_falling_piece);
      temp = my_falling_piece.moveDown();
      erasePiece(my_falling_piece);
    }    
    addPiece(my_falling_piece);
    clearCompletelyFilledLines();
  }
  
  //Instance methods.
  
  /**
   * @return The next piece in progress.
   */
  public Piece nextPiece()
  {
    return my_next_piece;
  }
  
  //@ ensures Result = my_rows;
  /**
   * @return The list of rows in the game board.
   */
  public List<Row> /* @ pure */ rows()
  {
    final List<Row> result = new ArrayList<Row>(TOTAL_HEIGHT);
    for (Row r : my_rows)
    {
      result.add(r);
    }    
    return result;
  }
  
  //@ requires my_falling_piece != null;
  //@ ensures my_falling_piece.moveLeft() if it's not illegal && 
  //          my_falling_piece = my_falling_piece.moveLeft() &&
  //          update my_falling_piece's position on the game board;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  /**
   * Move the current falling piece to the left by one column.
   */
  public void moveLeft()
  {
    final Piece temp = my_falling_piece.moveLeft();
    replaceCurrentFallingPiece(temp);
  }
  
  //@ requires my_falling_piece != null;
  //@ ensures my_falling_piece.moveRight() if it's not illegal && 
  //          my_falling_piece = my_falling_piece.moveRight() &&
  //          update my_falling_piece's position on the game board;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  /**
   * Move the current falling piece to the right by one column.
   */
  public void moveRight()
  {
    final Piece temp = my_falling_piece.moveRight();
    replaceCurrentFallingPiece(temp);
  }
  
  //@ requires my_falling_piece != null;
  //@ ensures my_falling_piece.moveDown() if it's not illegal && 
  //          my_falling_piece = my_falling_piece.moveDown() &&
  //          update my_falling_piece's position on the game board;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  /**
   * Move the current falling piece down by one row.
   */
  public void moveDown()
  {
    final Piece temp = my_falling_piece.moveDown();
    replaceCurrentFallingPiece(temp);
  }
  
  /**
   * Move the current falling piece down until it can't anymore.
   */
  public void moveDownToBottom()
  {
    updateForTesting();
    //setChanged();
    notifyObservers(this);
  }
  
  //@ requires my_falling_piece != null;
  //@ ensures my_falling_piece.rotateClockwise() if it's not illegal && 
  //          my_falling_piece = my_falling_piece.rotateClockwise() &&
  //          update my_falling_piece's position on the game board;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  /**
   * Rotate the current falling piece one step clock-wise.
   */
  public void rotateClockwise()
  {
    final Piece temp = my_falling_piece.rotateClockwise();
    replaceCurrentFallingPiece(temp);
  }
  
  //@ requires my_falling_piece != null;
  //@ ensures my_falling_piece.rotateCounterclockwise() if it's not illegal && 
  //          my_falling_piece = my_falling_piece.rotateCounterclockwise() &&
  //          update my_falling_piece's position on the game board;
  //@ ensures my_rows.size() = TOTAL_HEIGHT;
  /**
   * Rotate the current falling piece one step counter clock-wise.
   */
  public void rotateCounterclockwise()
  {
    final Piece temp = my_falling_piece.rotateCounterclockwise();
    replaceCurrentFallingPiece(temp);
  }
  
  /**
   * The game is over when the first invisible row is not empty when a new piece is
   * about to drop.
   * @return Whether or not the game is over.
   */
  public boolean isGameOver()
  {
    return my_game_over;
  }
  
  //@ requires my_rows != null;
  //@ requires my_falling_piece != null;
  //@ ensures my_falling_piece = my_falling_piece.moveDown() if it's legal;
  //@ ensures my_falling_piece = new random piece from the BASIC_PIECES if it's
  //          can't move down anymore && any completed lines are deleted;
  //@ ensures notify observers if my_rows get changed;
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
    setChanged();
    if (isLegalPosition(temp))
    {      
      my_falling_piece = temp;
      addPiece(my_falling_piece);
    }
    else
    {
      addPiece(my_falling_piece);
      if (!(rows().get(VISIBLE_HEIGHT).isEmpty()))
      {
        my_game_over = true;
        notifyObservers(this);
      }
      clearCompletelyFilledLines();
      startNewPiece(my_next_piece);
    }
    notifyObservers(this);
  }
  
  /**
   * @return The printable representation of this GameBoard object.
   */
  public /* @ non_null */ String toString()
  {
    final StringBuffer sb = new StringBuffer(150);
    if (!my_rows.get(VISIBLE_HEIGHT).isEmpty())
    {
      sb.append("Below is a piece in progress on top of the visible board.\n");
      for (int i = TOTAL_HEIGHT - 1; i >= VISIBLE_HEIGHT; i--)
      {
        final Row temp_row = my_rows.get(i);
        sb.append(BORDER + temp_row.toString() + BORDER + NEW_LINE);
      }
    }
    sb.append("\nBelow is the visible tetris game board." + NEW_LINE);
    for (int i = VISIBLE_HEIGHT - 1; i >= 0; i--)
    {
      final Row temp_row = my_rows.get(i);
      sb.append(BORDER + temp_row.toString() + BORDER + NEW_LINE);
    }
    return sb.toString();
  }
}
