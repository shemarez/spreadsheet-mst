/**
 * Block class for Tetris game board.
 * Fall 09.
 */
package tetris.board;

import java.awt.Color;

/**
 * Block class for Tetris game board.
 * @author Son Pham.
 * @version 1.0
 */
public class Block
{
  //Instance fields
  
  /**
   * Boolean value of whether this block is filled.
   */
  private boolean my_is_filled;
  
  /**
   * The color of this block.
   */
  private Color my_color;
  
  //Constructors
  
  /**
   * Construct a block with my_boolean = false and my_color = Color.WHITE.
   */
  public Block()
  {
    my_is_filled = false;
    my_color = Color.WHITE;
  }
  
  /**
   * Construct a block with specified value the_boolean and the_color.
   * @param the_boolean The boolean.
   * @param the_color The color.
   */
  public Block(final boolean the_boolean, final Color the_color)
  {
    my_is_filled = the_boolean;
    my_color = the_color;
  }
  
  /**
   * Set whether this block is filled or not.
   * @param the_boolean The boolean.
   */
  public void setIsFilled(final boolean the_boolean)
  {
    my_is_filled = the_boolean;
  }
  
  /**
   * Set the color of this block.
   * @param the_color The color.
   */
  public void setColor(final Color the_color)
  {
    my_color = the_color;
  }
  
  /**
   * Return the boolean value of whether this block is filled.
   * @return The boolean value of whether this block is filled.
   */
  public boolean isFilled()
  {
    return my_is_filled;
  }
  
  /**
   * Return the color of this block.
   * @return The color of this block.
   */
  public Color color()
  {
    return my_color;
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals(final Object the_other)
  {
    boolean result = this == the_other;
    if (!result && the_other != null && the_other.getClass() == getClass())
    {
      final Block other_block = (Block) the_other;
      result = other_block.color().equals(color());
      result = other_block.isFilled() == isFilled();
    }
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  public int hashCode()
  {
    return my_color.hashCode();
  }
}
