package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import spreadsheet.CycleFound;
import spreadsheet.Spreadsheet;
import spreadsheet.UnpairedParenthesesException;
import tokens.CellToken;

/**
 * Spreadsheet board contains a table of cells in the spreadsheet GUI.
 * 
 * @author Son Pham
 * @version 1.0
 * 
 *          2010-03-03 Miles
 */
@SuppressWarnings("serial")
public class SpreadSheetBoard extends JPanel {
	/**
	 * Focus listener for a cell GUI
	 * 
	 * @author Son Pham
	 * @version 1.0
	 */
	private class CellFocusListener extends FocusAdapter {
		/**
		 * Show the value of the cell when losing focus.
		 */
		@Override
		public void focusLost(FocusEvent the_event) {
			final CellGui cell_gui = (CellGui) the_event.getComponent();
			// cell_gui.setText(my_spreadsheet.cellValueToString(cell_gui.getCellToken()));
			setCellText(cell_gui);
		}

	}

	/**
	 * Keyboard listener class for a cell GUI
	 * 
	 * @author Son Pham
	 * @version 1.0
	 */
	private class CellKeyListener extends KeyAdapter {
		/**
		 * Press enter to commit change to the cell.
		 */
		@Override
		public void keyPressed(KeyEvent the_event) {
			if (the_event.getKeyCode() == KeyEvent.VK_ENTER) {
				final CellGui cell_gui = (CellGui) the_event.getComponent();
				try {
					my_spreadsheet.changeCellFormulaAndRecalculate(cell_gui
							.getCellToken(), cell_gui.getText());
				} catch (CycleFound e) {
					JOptionPane.showMessageDialog(null,
							"Cycle Found! Please re-enter formula",
							"Cycle error", JOptionPane.ERROR_MESSAGE);
					my_spreadsheet.revert(cell_gui.getCellToken());
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane
							.showMessageDialog(
									null,
									"Cell's index out of bound! Please re-enter formula",
									"Cell's out of bound",
									JOptionPane.ERROR_MESSAGE);
					my_spreadsheet.revert(cell_gui.getCellToken());
				} catch (UnpairedParenthesesException e) {
					JOptionPane.showMessageDialog(null,
							"Error in formula: Parentheses do not match! \n"
									+ "Please re-enter formula",
							"Formula Error", JOptionPane.ERROR_MESSAGE);
					my_spreadsheet.revert(cell_gui.getCellToken());
				} catch (ArithmeticException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Arithmetic Error", JOptionPane.ERROR_MESSAGE);
					my_spreadsheet.revert(cell_gui.getCellToken());
				}
				update();
			}
		}
	}

	/**
	 * Mouse listener class for a cell GUI.
	 * 
	 * @author Son Pham
	 * @version 1.0
	 */
	private class CellMouseListener extends MouseAdapter {
		/**
		 * Double click to edit the formula of the cell.
		 */
		@Override
		public void mouseClicked(MouseEvent the_event) {
			if (the_event.getClickCount() == 2) {
				final CellGui cell_gui = (CellGui) the_event.getComponent();
				cell_gui.setText(my_spreadsheet.cellFormulaToString(cell_gui
						.getCellToken()));
				cell_gui.setCaretPosition(cell_gui.getDocument().getLength());
			}
		}
	}

	public static final int DEFAULT_CELL_HEIGHT = 30;

	// Instance fields
	public static final int DEFAULT_CELL_WIDTH = 80;

	/**
	 * The cell array of this spreadsheet.
	 */
	private CellGui[][] my_cell_array;

	/**
	 * The cell's height.
	 */
	private int my_cell_height;

	/**
	 * The cell's width.
	 */
	private int my_cell_width;

	// Constructor

	/**
	 * The columns in this spreadsheet.
	 */
	private int my_columns;

	/**
	 * The rows in this spreadsheet.
	 */
	private int my_rows;

	/**
	 * The spreadsheet.
	 */
	private Spreadsheet my_spreadsheet;

	/**
	 * Construct a spreadsheet board from the input.
	 * 
	 * @param the_spreadsheet
	 *            The spreadsheet.
	 */
	public SpreadSheetBoard(final Spreadsheet the_spreadsheet) {
		super(new GridLayout(the_spreadsheet.getNumRows() + 1, the_spreadsheet
				.getNumColumns() + 1));
		my_spreadsheet = the_spreadsheet;
		my_rows = the_spreadsheet.getNumRows();
		my_columns = the_spreadsheet.getNumColumns();
		my_cell_width = DEFAULT_CELL_WIDTH;
		my_cell_height = DEFAULT_CELL_HEIGHT;
		setPreferredSize(new Dimension(my_columns * my_cell_width, my_rows
				* my_cell_height));
		my_cell_array = new CellGui[my_rows][my_columns];
		init();
	}

	/**
	 * 
	 * @return The cell's height.
	 */
	public int getCellHeight() {
		return my_cell_height;
	}

	/**
	 * 
	 * @return The cell's width.
	 */
	public int getCellWidth() {
		return my_cell_width;
	}

	/**
	 * Initialize the cell array and add the cells into the panel.
	 */
	private void init() {
		for (int i = -1; i < my_rows; i++)
			for (int j = -1; j < my_columns; j++) {
				CellToken cellToken = new CellToken(i, j);

				if (i == -1) {
					if (j == -1)
						add(new JLabel("", null, SwingConstants.CENTER));
					else
						add(new JLabel(cellToken.columnToString(), null,
								SwingConstants.CENTER));
				} else if (j == -1)
					add(new JLabel(Integer.toString(i), null,
							SwingConstants.CENTER));
				else {
					my_cell_array[i][j] = new CellGui(cellToken);
					// my_cell_array[i][j].setText(my_spreadsheet.cellValueToString(my_cell_array[i][j].getCellToken()));
					setCellText(my_cell_array[i][j]);
					my_cell_array[i][j]
							.addMouseListener(new CellMouseListener());
					my_cell_array[i][j].addKeyListener(new CellKeyListener());
					my_cell_array[i][j]
							.addFocusListener(new CellFocusListener());
					// my_cell_array[i][j].setPreferredSize(new
					// Dimension(DEFAULT_CELL_WIDTH, DEFAULT_CELL_HEIGHT));
					add(my_cell_array[i][j]);
				}
			}
	}

	/**
	 * Load from a file
	 * @param canonicalPath
	 */
	public void load(String canonicalPath) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(canonicalPath));
		String line = in.readLine();
		String str_in = new String();
		final String blank = new String(" ");
		StringTokenizer st;
		int lineNum = 0, tokenNum = 0;
		
		if (line != null) {
			reset();
		}
		do {
			tokenNum = 0;
			st = new StringTokenizer(line, ",");
			while(st.hasMoreTokens()) {
			  str_in = st.nextToken();
			  if (str_in.equals(blank)) {
			    tokenNum++;
			  } else {
			    my_spreadsheet.changeCellFormulaAndRecalculate(
				my_cell_array[lineNum][tokenNum].getCellToken(), str_in);
			    tokenNum++;
			  }				
			}
			lineNum++;
		} while ((line = in.readLine()) != null);		
		in.close();
	}

	@Override
	public void paintComponent(final Graphics the_graphics) {
		// super.paintComponent(the_graphics);
		setPreferredSize(new Dimension(my_columns * my_cell_width, my_rows
				* my_cell_height));
	}

	/**
	 * Reset the board. All cells will have value of 0 and blank formulas.
	 */
	public void reset() {
		my_spreadsheet = new Spreadsheet(my_rows, my_columns);
	}

	/**
	 * Set the cell's height.
	 * 
	 * @param the_height
	 *            The cell's height.
	 */
	public void setCellHeight(final int the_height) {
		my_cell_height = the_height;
		setPreferredSize(new Dimension(my_columns * my_cell_width, my_rows
				* my_cell_height));
	}

	/**
	 * Set the text output of the cell. If the cell's formula is not empty, the
	 * cell will show its value. Otherwise, the cell will be blank.
	 * 
	 * @param the_cell
	 */
	private void setCellText(final CellGui the_cell) {
		if (my_spreadsheet.cellFormulaToString(the_cell.getCellToken())
				.isEmpty())
			the_cell.setText("");
		else
			the_cell.setText(my_spreadsheet.cellValueToString(the_cell
					.getCellToken()));
	}

	/**
	 * Set the cell's width.
	 * 
	 * @param the_width
	 *            The cell's width.
	 */
	public void setCellWidth(final int the_width) {
		my_cell_width = the_width;
		setPreferredSize(new Dimension(my_columns * my_cell_width, my_rows
				* my_cell_height));
	}

	/**
	 * Update the cells' values of this spreadsheet.
	 */
	public void update() {
		for (int i = 0; i < my_rows; i++)
			for (int j = 0; j < my_columns; j++)
				setCellText(my_cell_array[i][j]);
	}


	/**
	 * Save to a file.
	 * @param canonicalPath The path.
	 */
	public void save(String canonicalPath) throws Exception {
		BufferedWriter out = new BufferedWriter(new FileWriter(canonicalPath));
		String str_out = new String();
		for(CellGui[] cells : my_cell_array) {
			for(CellGui cell : cells) {
			  if (my_spreadsheet.cellFormulaToString(cell.getCellToken()).isEmpty()) {
			    str_out = " ";
			  }
			  else {
			    str_out = my_spreadsheet.cellFormulaToString(cell.getCellToken());
			  }
				out.write(str_out);
				out.append(',');
			  
			}
			out.newLine();
			out.flush();
		}
		out.close();
	}
}
