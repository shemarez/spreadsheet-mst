/**
 * Row class for Tetris game board.
 * Fall 09
 */
package tetris.board;

import tetris.entities.pieces.Piece;

/**
 * Row class for Tetris game board.
 * @author Son Pham.
 * @version 1.0
 */
public class Row implements Cloneable
{
  //Instance fields
  
  /**
   * The array of blocks in a row.
   */
  private Block[] my_row_blocks;
  
  //Constructors
  
  /**
   * Construct an empty row with the specific size for the block array.
   * @param the_size The size.
   */
  public Row(final int the_size)
  {
    my_row_blocks = new Block[the_size];
    for (int i = 0; i < the_size; i++)
    {
      my_row_blocks[i] = new Block();
    }
  }
  
  /**
   * Construct a row with specified block array the_blocks.
   * @param the_blocks The blocks.
   */
  public Row(final Block[] the_blocks)
  {
    my_row_blocks = the_blocks.clone();
  }
  
  //Instance methods
  
  /**
   * @return The block array which forms this row.
   */
  public Block[] blocks()
  {
    return my_row_blocks.clone();
  }
  
  /**
   * Set the value of the block that is at the_index.
   * @param the_index The index.
   * @param the_block The block.
   * @throws ArrayIndexOutOfBoundsException Throw array out of bounds exception.
   */
  public void setBlockIndex(final int the_index, final Block the_block)
    throws ArrayIndexOutOfBoundsException
  {
    my_row_blocks[the_index] = the_block;
  }
  
  /**
   * Return the block at the specific index.
   * @param the_index The index.
   * @return The block at the specific index.
   * @throws ArrayIndexOutOfBoundsException Array index out of bounds exception.
   */
  public Block getBlockIndex(final int the_index)
    throws ArrayIndexOutOfBoundsException
  {
    return my_row_blocks[the_index];
  }
  
  /**
   * Return whether the row is completely filled or not.
   * @return True if the block is completely filled. False otherwise.
   */
  public boolean isCompletelyFilled()
  {
    boolean result = true;
    for (int i = 0; i < my_row_blocks.length; i++)
    {
      result = result && my_row_blocks[i].isFilled();
      if (!result)
      {
        return result;
      }
    }
    return result;
  }
  
  /**
   * Return whether the row is empty or not.
   * @return True if the row is empty. False otherwise.
   */
  public boolean isEmpty()
  {
    boolean result = true;
    for (int i = 0; i < my_row_blocks.length; i++)
    {
      result = result && !my_row_blocks[i].isFilled();
      if (!result)
      {
        return result;
      }
    }
    return result;
  }
  
  /**
   * @return The printable representation of this Row object.
   */
  public String toString()
  {
    final StringBuffer sb = new StringBuffer();
    for (int i = 0; i < my_row_blocks.length; i++)
    {
      if (getBlockIndex(i).isFilled())
      {
        sb.append(Piece.FULL_BLOCK_CHAR);
      }
      else
      {
        sb.append(Piece.EMPTY_BLOCK_CHAR);
      }
    }
    return sb.toString();
  }
  
  /**
   * {@inheritDoc}
   */
  public Object clone() throws CloneNotSupportedException
  {
    final Row result = (Row) super.clone();
    result.my_row_blocks = my_row_blocks.clone();
    return result;
  }
}
