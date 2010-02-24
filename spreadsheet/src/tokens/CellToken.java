package tokens;

/**
 * Cell token for the spreadsheet.
 * @author Son Pham.
 * @version 1.0
 */
public class CellToken extends Token
{
  /**
   * The column of this cell in the spreadsheet.
   */
  private int my_column;
  
  /**
   * The row of this cell in the spreadsheet.
   */
  private int my_row;
  
  //Constructor
  
  /**
   * Construct an default cell with the row and column are both 0. 
   */
  public CellToken()
  {
    my_column = 0;
    my_row = 0;
  }
  
  //Instance methods
  
  /**
   * @return The column of this cell in the spreadsheet.
   */
  public int getColumn()
  {
    return my_column;
  }
  
  /**
   * @return The row of this cell in the spreadsheet.
   */
  public int getRow()
  {
    return my_row;
  }
  
  /**
   * Set the column of this cell.
   */
  public void setColumn(final int col)
  {
    my_column = col;
  }
  
  /**
   * Set the row of this cell.
   */
  public void setRow(final int row)
  {
    my_row = row;
  }
}
