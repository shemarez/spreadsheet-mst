package tetrispieces;

import java.awt.Point;

public class TetrisPiece
{
  /**
   * The start point of the local board.
   */
  private Point my_start_point;
  
  /**
   * The set of points represent the object in the local board.
   */
  private Point[] my_local_position;
  
  /**
   * The set of points represent the object in the game board.
   */
  private Point[] my_board_position;
  
  public void setStartPoint(Point the_point)
  {
    my_start_point = the_point;
  }
  
  public void setLocalPosition(Point[] the_position)
  {
    my_local_position = the_position;
  }
  
  public void setX(int index, int the_x)
  {
    Point indexPoint = my_local_position[index];
    my_local_position[index].setLocation(the_x, indexPoint.y);
  }
  
  public void setY(int index, int the_y)
  {
    Point indexPoint = my_local_position[index];
    my_local_position[index].setLocation(indexPoint.x, the_y);
  }
  
  public Point getStartPoint()
  {
    return my_start_point;
  }
  
  /*
  public Point[] getLocalPosition()
  {
    return my_local_position;
  }
  
  public Point[] getBoardPosition()
  {
    return my_board_position;
  }
  */
  
  public void rotateCW()
  {
    TetrisPiece tempPiece = new TetrisPiece();
    tempPiece.my_local_position = my_local_position;
    
    for (int i = 0; i < 4; i++)
    {
      setX(i, tempPiece.my_local_position[i].y);
      setY(i, - tempPiece.my_local_position[i].x);
    }
  }
  
  public void rotateCounterCW()
  {
    TetrisPiece tempPiece = new TetrisPiece();
    tempPiece.my_local_position = my_local_position;
    
    for (int i = 0; i < 4; i++)
    {
      setX(i, - tempPiece.my_local_position[i].y);
      setY(i, tempPiece.my_local_position[i].x);
    }
  }
  
  /**
   * Move this piece into a location on the game board.
   * @param the_start_point The start location of this piece's 
   * local board in the game board.
   */
  public void moveInGameBoard(final Point the_start_point)
  {
    for (int i = 0; i < 4; i++)
    {
      int dx = my_local_position[i].x - my_start_point.x;
      int dy = my_local_position[i].y - my_start_point.y;
      Point pointInGameBoard = new Point(the_start_point.x + dx,
                                         the_start_point.y + dy);
      my_board_position[i] = pointInGameBoard;      
    }
  }
}
