package tetris.board;

import java.awt.Color;

public class TetrisTest
{
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
    final GameBoard board = new GameBoard();
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
  }
}
