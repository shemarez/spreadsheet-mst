/**
 * Row class for Tetris game board.
 * Fall 09
 */
package tetris.board;

import java.awt.Color;

import tetris.entities.pieces.Piece;

/**
 * Array of colors.
 * @author Son Pham.
 * @version 1.0
 */
public class Row implements Cloneable
{
  // Static fields
  
  /**
   * The color represents empty space.
   */
  public static final Color EMPTY_COLOR = Color.WHITE;
  
  //Instance fields
  
  /**
   * The array of blocks in a row.
   */
  private Color[] /* @ non_null */my_colors;
  
  //Constructors
  
  //@ requires the_size >= 0;
  //@ ensures my_colors != null;
  //@ ensures my_colors.length >= 0;
  /**
   * Construct an empty row with the specific size for the block array.
   * @param the_size The size.
   */
  public Row(final int /* @ non_null */ the_size)
  {
    my_colors = new Color[the_size];
    for (int i = 0; i < the_size; i++)
    {
      my_colors[i] = EMPTY_COLOR;
    }
  }
  
  //@ ensures my_colors != null;
  //@ ensures my_colors.length >= 0;
  //@ ensures my_colors = the_colors;
  /**
   * Construct a row with specified color array the_colors.
   * @param the_colors The colors.
   */
  public Row(final Color[] /* @ non_null */ the_colors)
  {
    my_colors = the_colors.clone();
  }
  
  //Instance methods
  
  //@ ensures \result.equals(my_colors);
  /**
   * @return The color array which forms this row.
   */
  public /*@ pure */ Color[] colors()
  {
    return my_colors.clone();
  }
  
  //@ requires 0 <= the_index < my_colors.length;
  //@ requires my_colors != null;
  //@ ensures my_colors[the_index].equals(the_color);
  /**
   * Set the value of the color that is at the_index.
   * @param the_index The index.
   * @param the_color The color.
   * @throws ArrayIndexOutOfBoundsException Throw array out of bounds exception.
   */
  public void setColorIndex(final int /* @ non_null */ the_index, 
                            final Color /* @ non_null */ the_color)
    throws ArrayIndexOutOfBoundsException
  {
    my_colors[the_index] = the_color;
  }
  
  //@ requires 0 <= the_index < my_colors.length;
  //@ ensures \result.equals(my_colors[the_index]);
  /**
   * Return the color at the specific index.
   * @param the_index The index.
   * @return The color at the specific index.
   * @throws ArrayIndexOutOfBoundsException Array index out of bounds exception.
   */
  public Color getColorIndex(final int /* @ non_null */the_index)
    throws ArrayIndexOutOfBoundsException
  {
    return my_colors[the_index];
  }
  
  //@ requires my_colors != null;
  //@ ensures Result is true if the row is completely filled. Result is false if the
  //          row is not completely filled.
  /**
   * Return whether the row is completely filled or not.
   * @return True if the row is completely filled. False otherwise.
   */
  public boolean /* @ pure */ isCompletelyFilled()
  {
    boolean result = true;
    for (int i = 0; i < my_colors.length; i++)
    {
      result = result && !my_colors[i].equals(EMPTY_COLOR);
    }
    return result;
  }
  
  //@ requires my_colors != null;
  //@ ensures Result is true if the row is empty. Result is false if the row is not empty.
  /**
   * Return whether the row is empty or not.
   * @return True if the row is empty. False otherwise.
   */
  public boolean isEmpty()
  {
    boolean result = true;
    for (int i = 0; i < my_colors.length; i++)
    {
      result = result && my_colors[i].equals(EMPTY_COLOR);
    }
    return result;
  }
  
  /**
   * @return The printable representation of this Row object.
   */
  public /* @ non_null */ String toString()
  {
    final StringBuffer sb = new StringBuffer();
    for (Color c : my_colors)
    {
      if (c.equals(EMPTY_COLOR))
      {
        sb.append(Piece.EMPTY_BLOCK_CHAR);
      }
      else
      {
        sb.append(Piece.FULL_BLOCK_CHAR);
      }
    }
    return sb.toString();
  }
  
  /**
   * {@inheritDoc}
   */
  public Object /* @ pure non_null */ clone() throws CloneNotSupportedException
  {
    final Row result = (Row) super.clone();
    result.my_colors = my_colors.clone();
    return result;
  }
  
  /*
   * @ requires the_other != null && \typeof(the_other) ==
   * \typeof(this); 
   * @ ensures \result <==> ((Row) the_other).colors().length == colors().length
   * && (\forall int i = 0; i < colors().length; i++; 
   * colors()[i].equals((Row) the_other).colors()[i]); 
   */
  
  /**
   * {@inheritDoc}
   */
  public boolean /* @ pure */ equals(final Object the_other)
  {
    boolean result = this == the_other;
    if (!result && the_other != null && the_other.getClass() == getClass())
    {
      final Row other_row = (Row) the_other;
      result = other_row.colors().length == colors().length;
      for (int i = 0; i < colors().length; i++)
      {
        result = result && colors()[i].equals(other_row.colors()[i]);
        if (!result)
        {
          return result;
        }
      }
    }
    return result;
  }
  
  /*
   * @ public represents theHashCode = colors().length;
   */
  /**
   * {@inheritDoc}
   */
  public int hashCode()
  {
    return colors().length;
  }
}
