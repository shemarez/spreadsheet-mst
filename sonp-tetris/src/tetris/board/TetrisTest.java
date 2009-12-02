package tetris.board;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * "Visual" test version of GameBoardTest and some other tests.
 * @author Son
 *
 */
public class TetrisTest
{
  /*
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
    new Block(true, Color.GREEN), new Block(true, Color.GREEN)};*/
  
  private static final String THE_STRING = "+ ++++ +++";
  
  private final static Piece[] my_piece_array =
    new Piece[] {new TPiece().setOrigin(new Point(0, 20)),
                 new OPiece().setOrigin(new Point(2, 20)),
                 new LPiece().setOrigin(new Point(4, 20)),
                 new OPiece().setOrigin(new Point(6, 20)),
                 rotateCW_n_times(new LPiece(), 2).setOrigin(new Point(8, 20)),
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
                 rotateCW_n_times(new LPiece(), 2).setOrigin(new Point(6, 20)),
                 rotateCCW_n_times(new JPiece(), 2).setOrigin(new Point(-1, 20)),
                 rotateCCW_n_times(new LPiece(), 2).setOrigin(new Point(1, 20)),
                 new TPiece().setOrigin(new Point(3, 20)),
                 new ZPiece().rotateClockwise().setOrigin(new Point(-1, 20)),
                 new IPiece().setOrigin(new Point(2, 20)),
                 new TPiece().setOrigin(new Point(7, 20)),
                 new LPiece().rotateClockwise().setOrigin(new Point(6, 20)),
                 new JPiece().rotateCounterclockwise().setOrigin(new Point(7, 20))
                 };
  
  public static void main(final String[] the_args)
  {
    /*
    final Row the_row = new Row(BLOCKS);
    String result = the_row.toString();
    System.out.println(result);
    System.out.println(result.equals(THE_STRING));
    for (int i = 2; i < 7; i++)
    {
      the_row.setColorIndex(i, new Block(true, Color.WHITE));
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
    System.out.println(board.toString()); */
    
    final Piece[] piece_array = new Piece[]{new LPiece().rotateCounterclockwise()};
    List<Piece> piece_list = Arrays.asList(my_piece_array);
    GameBoard board2 = new GameBoard(piece_list);
    GameBoard board3 = new GameBoard(Arrays.asList(piece_array));
    System.out.println(board2.toString());    
    System.out.println("Number of pieces have been used: " + piece_list.size());
    System.out.println(board3.toString());
    board3.moveLeft();
    System.out.println("Try to move the pice to the right. Here the outcome.");
    System.out.println(board3.toString());
  }
  
  private static Piece rotateCW_n_times(final Piece the_piece, final int n)
  {
    Piece temp = the_piece;
    for (int i = 0; i < n; i++)
    {
      temp = temp.rotateClockwise();
    }
    return temp;
  }
  
  private static Piece rotateCCW_n_times(final Piece the_piece, final int n)
  {
    Piece temp = the_piece;
    for (int i = 0; i < n; i++)
    {
      temp = temp.rotateCounterclockwise();
    }
    return temp;
  }
}
