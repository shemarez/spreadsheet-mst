package tetris.board;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

/**
 * A test for the Row class.
 * @author Son Pham
 * @version 1.0
 */
public class RowTest
{
  /**
   * A block array constant for testing.
   */
  private static final Color[] COLORS = 
    new Color[] {Color.GREEN, Color.WHITE, Color.GREEN, Color.GREEN, Color.GREEN,
                 Color.GREEN, Color.WHITE, Color.GREEN, Color.GREEN, Color.GREEN};
  
  /**
   * An block array which contains only empty block for testing.
   */
  private static final Color[] EMPTY_COLORS = 
    new Color[] {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
                 Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};
  
  /**
   * The string "+ ++++ +++".
   */
  private static final String THE_STRING = "+ ++++ +++";
  
  /**
   * A row constant created with initial size of 10.
   */
  private static final Row A_ROW = new Row(10);
  
  /**
   * A row constant created with the block array COLORS.
   */
  private static final Row ANOTHER_ROW = new Row(COLORS);
  
  /**
   * Test the constructors.
   */
  @Test
  public void testConstructor()
  {
    assertArrayEquals(A_ROW.colors(), EMPTY_COLORS);
    assertArrayEquals(ANOTHER_ROW.colors(), COLORS);
  }
  
  /**
   * Test other methods like setBlockIndex, getBlockIndex, toString.
   * @throws CloneNotSupportedException Clone Not Supported Exception.
   */
  @Test
  public void testModifyingRow() throws CloneNotSupportedException
  {
    final Row temp_another_row = (Row) ANOTHER_ROW.clone();
    assertNotSame("temp_another_row should not be the same with ANOTHER_ROW", 
                  temp_another_row, ANOTHER_ROW);
    assertEquals("temp_another_row should be equal to ANOTHER_ROW", 
                 temp_another_row, ANOTHER_ROW);
    temp_another_row.setColorIndex(1, Color.GREEN);
    assertEquals("block of index 1 of temp_another_row", 
                 temp_another_row.getColorIndex(1), Color.GREEN);
    temp_another_row.setColorIndex(6, Color.GREEN);
    assertTrue("temp_another_row should be completely filled", 
               temp_another_row.isCompletelyFilled());
    assertEquals("Test toString method", 
                 THE_STRING, ANOTHER_ROW.toString());
  }
}
