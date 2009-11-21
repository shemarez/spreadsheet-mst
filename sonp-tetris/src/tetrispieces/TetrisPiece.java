/**
 * Tetris pieces for Tetris game.
 * Fall 09.
 */
package tetrispieces;

import java.awt.Point;

/**
 * Tetris Piece class for Tetris game.
 * @author Son Pham
 * @version 1.0
 */
public class TetrisPiece
{
  //Constant field
  
  /**
   * Number of blocks in a piece.
   */
  private static final int PIECE_BLOCKS = 4;
  
  //Instance fields

  /**
   * The start point of the local board.
   */
  private Point my_local_start_point;
  
  /**
   * The start point of the piece's board in the game board.
   */
  private Point my_board_start_point;
  /**
   * The set of points represent the object in the local board.
   */
  private Point[] my_local_point_array;
  
  /**
   * The set of points represent the object in the game board.
   */
  private Point[] my_board_point_array;
  
  //Constructor
  
  /**
   * Constructs a TetrisPiece.
   */
  public TetrisPiece()
  {
    my_local_start_point = new Point(0, 0);
    my_local_point_array = new Point[PIECE_BLOCKS];
    my_board_point_array = new Point[PIECE_BLOCKS];
  }
  
  //Instance methods
  
  /**
   * Return a clone of the input array.
   * @param the_array The input array.
   * @return A clone of the input array.
   */
  private Point[] clonePointArray(final Point[] the_array)
  {
    final Point[] result = new Point[the_array.length];
    
    for (int i = 0; i < the_array.length; i++)
    {
      result[i] = (Point) the_array[i].clone();
    }
    
    return result;
  }
  
  /**
   * Set the start point of the local board.
   * @param the_point The point.
   */
  public void setStartPoint(final Point the_point)
  {
    my_local_start_point = the_point;
  }
  
  /**
   * Set the point array for the local board.
   * @param the_position The point array.
   */
  public void setLocalPointArray(final Point[] the_position)
  {
    final Point[] clone = clonePointArray(the_position);
    my_local_point_array = clone;
  }
  
  /**
   * Set the x value of the point at the specific index
   * of the point array.
   * @param the_index The index.
   * @param the_x The x.
   */
  public void setX(final int the_index, final int the_x)
  {
    final Point indexPoint = my_local_point_array[the_index];
    my_local_point_array[the_index].setLocation(the_x, indexPoint.y);
  }
  
  /**
   * Set the y value of the point at the specific index
   * of the point array.
   * @param the_index The index.
   * @param the_y The y.
   */
  public void setY(final int the_index, final int the_y)
  {
    final Point indexPoint = my_local_point_array[the_index];
    my_local_point_array[the_index].setLocation(indexPoint.x, the_y);
  }
  
  /**
   * Get the start point of the local board.
   * @return The start point of the local board.
   */
  public Point getStartPoint()
  {
    return my_local_start_point;
  }
  
  /**
   * Get the start point of the local board at the game board.
   * @return The start point of the local board at the game board.
   */
  public Point getBoardStartPoint()
  {
    return my_board_start_point;
  }
  
  /**
   * Get the point array of the local board.
   * @return The point array of the local board.
   */
  public Point[] getLocalPointArray()
  {
    return clonePointArray(my_local_point_array);
  }
  
  /**
   * Get the point array of the local board at the game board.
   * @return The point array of the local board at the game board.
   */
  public Point[] getBoardPointArray()
  {
    return clonePointArray(my_board_point_array);
  }
  
  /**
   * Rotate the piece clock-wise.
   */
  public void rotateCW()
  {
    final TetrisPiece tempPiece = new TetrisPiece();
    tempPiece.my_local_point_array = clonePointArray(my_local_point_array);
    
    for (int i = 0; i < PIECE_BLOCKS; i++)
    {
      setX(i, tempPiece.my_local_point_array[i].y);
      setY(i, -tempPiece.my_local_point_array[i].x);
    }
  }
  
  /**
   * Rotate the piece counter clock_wise.
   */
  public void rotateCounterCW()
  {
    final TetrisPiece tempPiece = new TetrisPiece();
    tempPiece.my_local_point_array = clonePointArray(my_local_point_array);
    
    for (int i = 0; i < PIECE_BLOCKS; i++)
    {
      setX(i, -tempPiece.my_local_point_array[i].y);
      setY(i, tempPiece.my_local_point_array[i].x);
    }
  }
  
  /**
   * Move this piece into a location on the game board.
   * @param the_start_point The start location of this piece's 
   * local board in the game board.
   */
  public void moveInGameBoard(final Point the_start_point)
  {
    my_board_start_point = the_start_point;
    for (int i = 0; i < PIECE_BLOCKS; i++)
    {
      final int dx = my_local_point_array[i].x - my_local_start_point.x;
      final int dy = my_local_point_array[i].y - my_local_start_point.y;
      final Point pointInGameBoard = new Point(my_board_start_point.x + dx,
                                         my_board_start_point.y + dy);
      my_board_point_array[i] = pointInGameBoard;      
    }
  }
}
