/**
 * Test for TetrisPiece class.
 * Fall 09.
 */
package tetrispieces;

import static org.junit.Assert.assertArrayEquals;

import java.awt.Point;

import org.junit.Test;

/**
 * TetrisPieceTest class.
 * @author Son Pham
 * @version 1.0
 */
public class TetrisPieceTest
{  
  //Instance fields
  
  /**
   * The initial point array of the L piece in the local board.
   */
  private final Point[] my_l_shape = new Point[] {new Point(-1, 0), new Point(0, 0),
                                                  new Point(1, 0), new Point(1, 1)};
  
  /**
   * The initial point array of the J piece in the local board.
   */
  private final Point[] my_j_shape = new Point[] {new Point(-1, 1), new Point(-1, 0),
                                                  new Point(0, 0), new Point(1, 0)};
  
  /**
   * The initial point array of the I piece in the local board.
   */
  private final Point[] my_i_shape = new Point[] {new Point(0, -1), new Point(0, 0),
                                                  new Point(0, 1), new Point(0, 2)};
  
  /**
   * The initial point array of the O piece in the local board.
   */
  private final Point[] my_o_shape = new Point[] {new Point(0, 0), new Point(1, 0),
                                                  new Point(1, 1), new Point(0, 1)};
  
  /**
   * The initial point array of the S piece in the local board.
   */
  private final Point[] my_s_shape = new Point[] {new Point(-1, -1), new Point(0, -1),
                                                  new Point(0, 0), new Point(1, 0)};
  
  /**
   * The initial point array of the T piece in the local board.
   */
  private final Point[] my_t_shape = new Point[] {new Point(-1, -1), new Point(0, -1),
                                                  new Point(0, 0), new Point(1, -1)};
  
  /**
   * The initial point array of the Z piece in the local board.
   */
  private final Point[] my_z_shape = new Point[] {new Point(-1, 0), new Point(0, 0),
                                                  new Point(0, -1), new Point(1, -1)};
  
  /**
   * Test construction of the 7 Tetris pieces.
   */
  @Test
  public void validConstructor()
  {   
    final TetrisPiece the_LPiece = new LPiece();
    final TetrisPiece the_JPiece = new JPiece();
    final TetrisPiece the_SPiece = new SPiece();
    final TetrisPiece the_ZPiece = new ZPiece();
    final TetrisPiece the_TPiece = new TPiece();
    final TetrisPiece the_IPiece = new IPiece();
    final TetrisPiece the_OPiece = new OPiece();
    
    assertArrayEquals("LPiece", my_l_shape, the_LPiece.getLocalPointArray());
    assertArrayEquals("JPiece", my_j_shape, the_JPiece.getLocalPointArray());
    assertArrayEquals("TPiece", my_t_shape, the_TPiece.getLocalPointArray());
    assertArrayEquals("SPiece", my_s_shape, the_SPiece.getLocalPointArray());
    assertArrayEquals("ZPiece", my_z_shape, the_ZPiece.getLocalPointArray());
    assertArrayEquals("IPiece", my_i_shape, the_IPiece.getLocalPointArray());
    assertArrayEquals("OPiece", my_o_shape, the_OPiece.getLocalPointArray());
  }
  
  /**
   * Test the property of rotate a piece clock_wise 4 times will get back
   * to it's original state.
   */
  @Test
  public void rotateCW4Times()
  {
    final TetrisPiece the_LPiece = new LPiece();
    final TetrisPiece the_JPiece = new JPiece();
    final TetrisPiece the_SPiece = new SPiece();
    final TetrisPiece the_ZPiece = new ZPiece();
    final TetrisPiece the_TPiece = new TPiece();
    final TetrisPiece the_IPiece = new IPiece();
    final TetrisPiece the_OPiece = new OPiece();
    
    for (int i = 0; i < 4; i++)
    {
      the_LPiece.rotateCW();
      the_JPiece.rotateCW();
      the_SPiece.rotateCW();
      the_ZPiece.rotateCW();
      the_TPiece.rotateCW();
      the_IPiece.rotateCW();
      the_OPiece.rotateCW();
    }
    
    assertArrayEquals("LPiece", my_l_shape, the_LPiece.getLocalPointArray());
    assertArrayEquals("JPiece", my_j_shape, the_JPiece.getLocalPointArray());
    assertArrayEquals("TPiece", my_t_shape, the_TPiece.getLocalPointArray());
    assertArrayEquals("SPiece", my_s_shape, the_SPiece.getLocalPointArray());
    assertArrayEquals("ZPiece", my_z_shape, the_ZPiece.getLocalPointArray());
    assertArrayEquals("IPiece", my_i_shape, the_IPiece.getLocalPointArray());
    assertArrayEquals("OPiece", my_o_shape, the_OPiece.getLocalPointArray());
  }
  
  /**
   * Test the property of rotate a piece counter clock_wise 4 times will 
   * get back to it's original state.
   */
  @Test
  public void rotateCounterCW4Times()
  {
    final TetrisPiece the_LPiece = new LPiece();
    final TetrisPiece the_JPiece = new JPiece();
    final TetrisPiece the_SPiece = new SPiece();
    final TetrisPiece the_ZPiece = new ZPiece();
    final TetrisPiece the_TPiece = new TPiece();
    final TetrisPiece the_IPiece = new IPiece();
    final TetrisPiece the_OPiece = new OPiece();
    
    for (int i = 0; i < 4; i++)
    {
      the_LPiece.rotateCounterCW();
      the_JPiece.rotateCounterCW();
      the_SPiece.rotateCounterCW();
      the_ZPiece.rotateCounterCW();
      the_TPiece.rotateCounterCW();
      the_IPiece.rotateCounterCW();
      the_OPiece.rotateCounterCW();
    }
    
    assertArrayEquals("LPiece", my_l_shape, the_LPiece.getLocalPointArray());
    assertArrayEquals("JPiece", my_j_shape, the_JPiece.getLocalPointArray());
    assertArrayEquals("TPiece", my_t_shape, the_TPiece.getLocalPointArray());
    assertArrayEquals("SPiece", my_s_shape, the_SPiece.getLocalPointArray());
    assertArrayEquals("ZPiece", my_z_shape, the_ZPiece.getLocalPointArray());
    assertArrayEquals("IPiece", my_i_shape, the_IPiece.getLocalPointArray());
    assertArrayEquals("OPiece", my_o_shape, the_OPiece.getLocalPointArray());
  }
  
  /**
   * Test that clock_wise rotation cancels counter clock_wise rotation.
   */
  @Test
  public void validCancellationRotate()
  {
    final TetrisPiece the_LPiece = new LPiece();
    final TetrisPiece the_SPiece = new SPiece();
    final TetrisPiece the_TPiece = new TPiece();
    final TetrisPiece the_IPiece = new IPiece();
    final TetrisPiece the_OPiece = new OPiece();
    
    for (int i = 0; i < 2; i++)
    {
      the_LPiece.rotateCW();
      the_SPiece.rotateCW();
      the_TPiece.rotateCW();
      the_IPiece.rotateCW();
      the_OPiece.rotateCW();
    }
    
    for (int i = 0; i < 2; i++)
    {
      the_LPiece.rotateCounterCW();
      the_SPiece.rotateCounterCW();
      the_TPiece.rotateCounterCW();
      the_IPiece.rotateCounterCW();
      the_OPiece.rotateCounterCW();
    }
    
    assertArrayEquals("LPiece", my_l_shape, the_LPiece.getLocalPointArray());
    assertArrayEquals("TPiece", my_t_shape, the_TPiece.getLocalPointArray());
    assertArrayEquals("SPiece", my_s_shape, the_SPiece.getLocalPointArray());
    assertArrayEquals("IPiece", my_i_shape, the_IPiece.getLocalPointArray());
    assertArrayEquals("OPiece", my_o_shape, the_OPiece.getLocalPointArray());
  }
  
  /**
   * Test various rotations of different pieces.
   */
  @Test
  public void validRotate()
  {
    final TetrisPiece the_JPiece = new JPiece();
    final TetrisPiece the_ZPiece = new ZPiece();
    final TetrisPiece the_TPiece = new TPiece();
    final TetrisPiece the_IPiece = new IPiece();
    final TetrisPiece the_OPiece = new OPiece();
    
    // Test for rotation of OPiece.
    for (int i = 0; i < 3; i++)
    {
      the_OPiece.rotateCW();
    }
    
    assertArrayEquals("OPiece doesn't occupy the same position when rotate",
                      my_o_shape, the_OPiece.getLocalPointArray());
    
    // Test for JPiece rotateCounterCW 1 time.
    the_JPiece.rotateCounterCW();
    final Point[] new_jShape = new Point[] {new Point(-1, -1), new Point(0, -1),
                                            new Point(0, 0), new Point(0, 1)};
    assertArrayEquals("JPiece counterCW rotation", new_jShape,
                      the_JPiece.getLocalPointArray());
    
    // Test for ZPiece rotateCW 2 times.
    for (int i = 0; i < 2; i++)
    {
      the_ZPiece.rotateCW();
    }
    final Point[] new_zShape = new Point[] {new Point(1, 0), new Point(0, 0),
                                            new Point(0, 1), new Point(-1, 1)};
    assertArrayEquals("ZPiece CW rotation", new_zShape,
                      the_ZPiece.getLocalPointArray());
    
    // Test for TPiece rotateCounterCW 3 times
    for (int i = 0; i < 3; i++)
    {
      the_TPiece.rotateCounterCW();
    }
    final Point[] new_tShape = new Point[] {new Point(-1, 1), new Point(-1, 0),
                                            new Point(0, 0), new Point(-1, -1)};
    assertArrayEquals("TPiece CounterCW rotation", new_tShape,
                      the_TPiece.getLocalPointArray());
    
    // Test for IPiece rotateCW 3 times
    for (int i = 0; i < 3; i++)
    {
      the_IPiece.rotateCW();
    }
    final Point[] new_iShape = new Point[] {new Point(1, 0), new Point(0, 0),
                                            new Point(-1, 0), new Point(-2, 0)};
    assertArrayEquals("IPiece CW rotation", new_iShape,
                      the_IPiece.getLocalPointArray());
  }
  
  /**
   * Test various movements of different pieces.
   */
  @Test
  public void testMovement()
  {
    final TetrisPiece the_LPiece = new LPiece();
    final TetrisPiece the_SPiece = new SPiece();
    final TetrisPiece the_TPiece = new TPiece();
    final TetrisPiece the_IPiece = new IPiece();
    final TetrisPiece the_OPiece = new OPiece();
    
    the_LPiece.moveInGameBoard(new Point(2, 3));
    final Point[] lShapeInGameBoard = new Point[] {new Point(2, 4), new Point(3, 4),
                                                   new Point(4, 4), new Point(4, 5)};
    assertArrayEquals("LPiece", lShapeInGameBoard,
                      the_LPiece.getBoardPointArray());
    
    the_SPiece.moveInGameBoard(new Point(100, 55));
    final Point[] sShapeInGameBoard = new Point[] {new Point(100, 55), new Point(101, 55),
                                                   new Point(101, 56), new Point(102, 56)};
    assertArrayEquals("SPiece", sShapeInGameBoard,
                      the_SPiece.getBoardPointArray());
    
    the_TPiece.moveInGameBoard(new Point(-10, -8));
    final Point[] tShapeInGameBoard = new Point[] {new Point(-10, -8), new Point(-9, -8),
                                                   new Point(-9, -7), new Point(-8, -8)};
    assertArrayEquals("TPiece", tShapeInGameBoard,
                      the_TPiece.getBoardPointArray());
    
    the_OPiece.moveInGameBoard(new Point(22, 31));
    final Point[] oShapeInGameBoard = new Point[] {new Point(22, 31), new Point(23, 31),
                                                   new Point(23, 32), new Point(22, 32)};
    assertArrayEquals("OPiece", oShapeInGameBoard,
                      the_OPiece.getBoardPointArray());
    
    the_IPiece.moveInGameBoard(new Point(11, 0));
    final Point[] iShapeInGameBoard = new Point[] {new Point(13, 1), new Point(13, 2),
                                                   new Point(13, 3), new Point(13, 4)};
    assertArrayEquals("IPiece", iShapeInGameBoard,
                      the_IPiece.getBoardPointArray());
    
  }

}
