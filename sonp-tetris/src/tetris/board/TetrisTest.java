package tetris.board;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class TetrisTest
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
  
  private static final Block[] BLOCKS = new Block[] {new Block(true, Color.GREEN), new Block(false, Color.WHITE),
    new Block(true, Color.GREEN), new Block(true, Color.GREEN),
    new Block(true, Color.GREEN), new Block(true, Color.GREEN),
    new Block(false, Color.WHITE), new Block(true, Color.GREEN),
    new Block(true, Color.GREEN), new Block(true, Color.GREEN)};
  private static final String THE_STRING = "+ ++++ +++";
  
  public static void main(final String[] the_args)
  {
    final Row the_row = new Row(BLOCKS);
    String result = the_row.toString();
    System.out.println(result);
    System.out.println(result.equals(THE_STRING));
    for (int i = 2; i < 7; i++)
    {
      the_row.setBlockIndex(i, new Block(true, Color.WHITE));
    }   
    result = "\n" + the_row.toString();
    System.out.println(result);
    GameBoard board = new GameBoard();
    String board_string = board.toString();
    System.out.println(board_string);
    for (int i = 0; i < 4; i++)
    {
      board.update();
    }
    System.out.println("Game after 4 updates: ");
    System.out.println(board.toString());
    board.rotateClockwise();
    System.out.println("Rotate clock wise 1 time");
    System.out.println(board.toString());
    
    
    
    GameBoard board2 = new GameBoard();
    List<Row> the_rows = new ArrayList<Row>(GameBoard.TOTAL_HEIGHT);

    for (int i = 0; i < GameBoard.VISIBLE_HEIGHT; i++)
    {
      the_rows.add(i, new Row(GameBoard.WIDTH));
    }
    the_rows.add(GameBoard.VISIBLE_HEIGHT, ROW1);
    the_rows.add(GameBoard.VISIBLE_HEIGHT + 1, ROW2);
    the_rows.add(GameBoard.VISIBLE_HEIGHT + 2, ROW2);
    the_rows.add(GameBoard.VISIBLE_HEIGHT + 3, new Row(GameBoard.WIDTH));
    final List<Row> board_rows = board2.rows();
    for (int i = 0; i < GameBoard.TOTAL_HEIGHT; i++)
    {
      Row the_rows_i = the_rows.get(i);
      Row board_rows_i = board_rows.get(i);
      System.out.println(the_rows_i.toString());
      System.out.println(board_rows_i.toString());
      System.out.println(the_rows_i.equals(board_rows_i));
    }
  }
}
