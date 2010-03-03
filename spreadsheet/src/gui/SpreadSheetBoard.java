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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import spreadsheet.CycleFound;
import spreadsheet.Spreadsheet;
import tokens.CellToken;

/**
 * Spreadsheet board contains a table of cells in the spreadsheet GUI.
 * 
 * @author Son Pham
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SpreadSheetBoard extends JPanel {
	// Instance fields
	public static final int DEFAULT_CELL_WIDTH = 80;

	public static final int DEFAULT_CELL_HEIGHT = 30;

	/**
	 * The spreadsheet.
	 */
	private Spreadsheet my_spreadsheet;

	/**
	 * The cell array of this spreadsheet.
	 */
	private CellGui[][] my_cell_array;

	/**
	 * The rows in this spreadsheet.
	 */
	private int my_rows;

	/**
	 * The columns in this spreadsheet.
	 */
	private int my_columns;

	/**
	 * The cell's width.
	 */
	private int my_cell_width;

	/**
	 * The cell's height.
	 */
	private int my_cell_height;

	// Constructor

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
	 * @return The cell's width.
	 */
	public int getCellWidth() {
		return my_cell_width;
	}

	/**
	 * 
	 * @return The cell's height.
	 */
	public int getCellHeight() {
		return my_cell_height;
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
	 * Update the cells' values of this spreadsheet.
	 */
	public void update() {
		for (int i = 0; i < my_rows; i++) {
			for (int j = 0; j < my_columns; j++) {
				my_cell_array[i][j].setText(my_spreadsheet
						.cellValueToString(my_cell_array[i][j].getCellToken()));
			}
		}
	}

	/**
	 * Initialize the cell array and add the cells into the panel.
	 */
	private void init() {
		for (int i = -1; i < my_rows; i++) {
			for (int j = -1; j < my_columns; j++) {
				CellToken cellToken = new CellToken(i, j);

				if (i == -1) {
					if (j == -1)
						add(new JLabel("", null, JLabel.CENTER));
					else
						add(new JLabel(cellToken.columnToString(), null,
								JLabel.CENTER));
				} else if (j == -1) {
					add(new JLabel(Integer.toString(i), null, JLabel.CENTER));
				} else {
					my_cell_array[i][j] = new CellGui(cellToken);
					my_cell_array[i][j].setText(my_spreadsheet
							.cellValueToString(my_cell_array[i][j]
									.getCellToken()));
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
		public void mouseClicked(MouseEvent the_event) {
			if (the_event.getClickCount() == 2) {
				final CellGui cell_gui = (CellGui) the_event.getComponent();
				cell_gui.setText(my_spreadsheet.cellFormulaToString(cell_gui
						.getCellToken()));
				cell_gui.setCaretPosition(cell_gui.getDocument().getLength());
			}
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
		public void keyPressed(KeyEvent the_event) {
			// TODO Auto-generated method stub
			if (the_event.getKeyCode() == KeyEvent.VK_ENTER) {
				// System.err.println("Entered");
				final CellGui cell_gui = (CellGui) the_event.getComponent();
				try {
					my_spreadsheet.changeCellFormulaAndRecalculate(cell_gui
							.getCellToken(), cell_gui.getText());
					cell_gui.setText(my_spreadsheet.cellValueToString(cell_gui
							.getCellToken()));
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
				}
				update();
			}
		}
	}

	private class CellFocusListener extends FocusAdapter {
		/**
		 * Show the value of the cell when losing focus.
		 */
		public void focusLost(FocusEvent the_event) {
			// TODO Auto-generated method stub
			final CellGui cell_gui = (CellGui) the_event.getComponent();
			cell_gui.setText(my_spreadsheet.cellValueToString(cell_gui
					.getCellToken()));
		}

	}
}
