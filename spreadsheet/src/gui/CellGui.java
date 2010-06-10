package gui;

import javax.swing.JTextField;

import tokens.CellToken;

/**
 * CellGui is a text field that hold cell token reference to a spreadsheet.
 * 
 * @author Son Pham
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CellGui extends JTextField {
	/**
	 * The cell token of this object.
	 */
	private CellToken my_cell_token;

	// Constructors

	/**
	 * Construct a CellGui with the input cell token.
	 * 
	 * @param the_token
	 *            The cell token.
	 */
	public CellGui(final CellToken the_token) {
		my_cell_token = the_token;
	}

	/**
	 * Construct a default CellGui.
	 */
	public CellGui() {
		my_cell_token = new CellToken(0, 0);
	}

	/**
	 * 
	 * @return The cell token of this CellGui.
	 */
	public CellToken getCellToken() {
		return my_cell_token;
	}

	/**
	 * Set the cell token of this CellGui.
	 * 
	 * @param the_token
	 *            The cell token.
	 */
	public void setCellToken(final CellToken the_token) {
		my_cell_token = the_token;
	}
}
