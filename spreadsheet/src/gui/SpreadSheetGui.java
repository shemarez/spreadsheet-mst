package gui;

import javax.swing.JFrame;

import spreadsheet.Spreadsheet;

/**
 * Spreadsheet GUI.
 * @author Son Pham
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SpreadSheetGui extends JFrame
{
  /**
   * Name of this spreadsheet GUI frame.
   */
  public static final String FRAME_NAME = "TCSS-342 Simple SpreadSheet";
  
  /**
   * The cell table in the spreadsheet GUI.
   */
  private SpreadSheetBoard my_board;
  
  //Constructor
  
  /**
   * Construct a SpreadSheetGui.
   * @param the_spreadsheet The spreadsheet.
   */
  public SpreadSheetGui(final Spreadsheet the_spreadsheet)
  {
    super(FRAME_NAME);
    my_board = new SpreadSheetBoard(the_spreadsheet);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  /**
   * Setup the GUI.
   */
  public void setUp()
  {
    add(my_board);
    pack();
    setVisible(true);
  }

  /**
   * The main method of SpreadSheetGui.
   * 
   * @param the_args The command line argument (ignored).
   */
  public static void main(final String... the_args)
  {
    final SpreadSheetGui the_frame = new SpreadSheetGui(new Spreadsheet(2,3));
    the_frame.setUp();
  }
}
