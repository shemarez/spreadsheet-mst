package tetris.board;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tetris.entities.Point;
import tetris.entities.pieces.JPiece;
import tetris.entities.pieces.Piece;

/**
 * A test for GameBoard class.
 * @author Son Pham
 * @version 1.0
 */
public class GameBoardTest
{
  private static final Block[] BLOCKS1 = 
    new Block[] {new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                 new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                 new Block(true, Color.BLUE), new Block(true, Color.BLUE),
                 new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                 new Block(false, Color.WHITE), new Block(false, Color.WHITE)};
  private static final Block[] BLOCKS2 = 
    new Block[] {new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                 new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                 new Block(false, Color.WHITE), new Block(true, Color.BLUE),
                 new Block(false, Color.WHITE), new Block(false, Color.WHITE),
                 new Block(false, Color.WHITE), new Block(false, Color.WHITE)};
  private static final Row ROW1 = new Row(BLOCKS1);
  private static final Row ROW2 = new Row(BLOCKS2);
  
  @Test
  public void testConstructor()
  {
    final GameBoard board = new GameBoard();
    List<Row> the_rows = new ArrayList<Row>(GameBoard.TOTAL_HEIGHT);

    for (int i = 0; i < GameBoard.VISIBLE_HEIGHT; i++)
    {
      the_rows.add(i, new Row(GameBoard.WIDTH));
    }
    the_rows.add(GameBoard.VISIBLE_HEIGHT, ROW1);
    the_rows.add(GameBoard.VISIBLE_HEIGHT + 1, ROW2);
    the_rows.add(GameBoard.VISIBLE_HEIGHT + 2, ROW2);
    the_rows.add(GameBoard.VISIBLE_HEIGHT + 3, new Row(GameBoard.WIDTH));
    final List<Row> board_rows = board.rows();
    assertEquals(board_rows, the_rows);
  }
}
