package tetris.board;

import java.awt.Color;
import java.util.ArrayList;
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
    
    
    List<Piece> piece_list = new ArrayList<Piece>();
    Piece temp_piece = new SPiece();
    temp_piece = temp_piece.setOrigin(new Point(2, 20));
    piece_list.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = temp_piece.setOrigin(new Point(0, 20));
    piece_list.add(temp_piece);
    temp_piece = new TPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(1, 20));
    piece_list.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.setOrigin(new Point(4, 0));
    piece_list.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = temp_piece.setOrigin(new Point(7, 0));
    piece_list.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(4, 20));
    piece_list.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = rotateCW_n_times(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(8, 20));
    piece_list.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = rotateCCW_n_times(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    piece_list.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    piece_list.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    piece_list.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    piece_list.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    piece_list.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(2, 20));
    piece_list.add(temp_piece);
    temp_piece = new TPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    piece_list.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = temp_piece.setOrigin(new Point(8, 20));
    piece_list.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    piece_list.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(2, 20));
    piece_list.add(temp_piece);
    temp_piece = new ZPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(4, 20));
    piece_list.add(temp_piece);
    temp_piece = new SPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(6, 20));
    piece_list.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = temp_piece.setOrigin(new Point(7, 20));
    piece_list.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(0, 20));    
    piece_list.add(temp_piece);
    temp_piece = new SPiece();
    temp_piece = temp_piece.rotateCounterclockwise();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    piece_list.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = rotateCW_n_times(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(8, 20));
    piece_list.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    piece_list.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(-1, 20));
    piece_list.add(temp_piece);
    temp_piece = new OPiece();
    temp_piece = temp_piece.setOrigin(new Point(1, 20));
    piece_list.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = rotateCW_n_times(temp_piece, 2);
    temp_piece = temp_piece.setOrigin(new Point(3, 20));
    piece_list.add(temp_piece);
    temp_piece = new JPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(0, 20));
    piece_list.add(temp_piece);
    temp_piece = new IPiece();
    temp_piece = temp_piece.setOrigin(new Point(1, 20));
    piece_list.add(temp_piece);
    temp_piece = new LPiece();
    temp_piece = temp_piece.rotateClockwise();
    temp_piece = temp_piece.setOrigin(new Point(7, 20));
    piece_list.add(temp_piece);
    temp_piece = new TPiece();
    temp_piece = temp_piece.setOrigin(new Point(5, 20));
    piece_list.add(temp_piece);
    GameBoard board2 = new GameBoard(piece_list);
    System.out.println(board2.toString());    
    System.out.println("Number of pieces: " + piece_list.size());
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
