package ADTs;

import spreadsheet.Spreadsheet;
import tokens.CellToken;
import tokens.LiteralToken;
import tokens.OperatorToken;
import tokens.Token;

/**
 * ExpressionTree for the spreadsheet.
 * 
 * @author Teddy Doll
 * @version 1.0
 */
public class ExpressionTree {
	// Instance field

	/**
	 * The root of this tree.
	 */
	ExpressionTreeNode root;

	// Constructor

	/**
	 * Construct an expression tree contains only its root.
	 * 
	 * @param root
	 *            The root.
	 */
	public ExpressionTree(ExpressionTreeNode root) {
		this.root = root;
	}

	// Instance methods

	/**
	 * Make the tree empty.
	 */
	public void makeEmpty() {
		this.root = null;
	}

	/**
	 * Print the tree to the console.
	 */
	public void printTree() {
		;
	}

	/**
	 * 
	 * @return The root of this tree.
	 */
	public ExpressionTreeNode getRoot() {
		return this.root;
	}

	/**
	 * Set the root of this tree to be the new root.
	 * 
	 * @param the_root
	 *            The root.
	 */
	public void setRoot(ExpressionTreeNode the_root) {
		this.root = the_root;
	}

	/**
	 * 
	 * @param spreadsheet
	 *            The spreadsheet.
	 * @return The value of this tree.
	 */
	public int Evaluate(Spreadsheet spreadsheet) {
		return evaluteTree(spreadsheet, this.root);
	}

	/**
	 * Recursively evaluate this ExpressionTree and return the value.
	 * 
	 * @param spreadsheet
	 *            The spreadsheet.
	 * @param the_root
	 *            The root.
	 * @return The value of this tree whose root is the_root.
	 */
	private int evaluteTree(Spreadsheet spreadsheet, ExpressionTreeNode the_root) {
		if (the_root == null)
			return 0;
		Token rootToken = (Token) the_root.getToken();
		if (rootToken instanceof LiteralToken) {
			return ((LiteralToken) the_root.getToken()).getValue();
		} else if (rootToken instanceof CellToken) {
			return spreadsheet.getCellValue((CellToken) rootToken);
		} else {
			int leftTreeValue;
			int rightTreeValue;
			int returnValue = 0;
			leftTreeValue = evaluteTree(spreadsheet, the_root.getLeft());
			rightTreeValue = evaluteTree(spreadsheet, the_root.getRight());
			switch (((OperatorToken) rootToken).getOperatorToken()) {
			case OperatorToken.PLUS:
				returnValue = leftTreeValue + rightTreeValue;
				break;
			case OperatorToken.MINUS:
				returnValue = leftTreeValue - rightTreeValue;
				break;
			case OperatorToken.MULT:
				returnValue = leftTreeValue * rightTreeValue;
				break;
			case OperatorToken.DIV:
				returnValue = leftTreeValue / rightTreeValue;
				break;
			case OperatorToken.EXP:
				returnValue = (int) Math.pow(leftTreeValue, rightTreeValue);
				break;
			case OperatorToken.U_MINUS:
				returnValue = rightTreeValue * -1;
				break;
			default:
				// Should never happen
				System.out.println("error in tree eval");
				break;
			}
			return returnValue;
		}
	}

}
