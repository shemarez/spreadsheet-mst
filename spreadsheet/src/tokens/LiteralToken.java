package tokens;

/**
 * Literal token for the spreadsheet.
 * 
 * @author Son Pham
 * @version 1.0
 */
public class LiteralToken extends Token {
	/**
	 * The value of this literal.
	 */
	private int my_value;

	// Constructor

	/**
	 * Construct a literal token and set its value to be the input value.
	 * 
	 * @param the_value
	 *            The value.
	 */
	public LiteralToken(final int the_value) {
		my_value = the_value;
	}

	// Instance method

	/**
	 * @return The value of this literal.
	 */
	public int getValue() {
		return my_value;
	}
}
