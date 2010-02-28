package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import spreadsheet.Spreadsheet;

public class SpreadSheetBoard extends JPanel
{
  //Instance fields
  
  /**
   * The spreadsheet.
   */
  private Spreadsheet my_spread_sheet;
  
  /**
   * The rows in this spreadsheet.
   */
  private int my_rows;
  
  /**
   * The columns in this spreadsheet.
   */
  private int my_columns;
  
  private JPanel my_panel;
  
  //Constructor
  
  /**
   * Construct a spreadsheet board from the input.
   */
  public SpreadSheetBoard(final Spreadsheet the_spread_sheet)
  {
    my_rows = the_spread_sheet.getNumRows();
    my_columns = the_spread_sheet.getNumColumns();
    my_panel = new JPanel(new GridLayout(my_rows, my_columns));
  }
  
  
}
