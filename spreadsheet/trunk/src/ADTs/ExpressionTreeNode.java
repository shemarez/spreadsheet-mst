package ADTs;

import tokens.Token;

/**
 * ExpressionTreeNode class for the spreadsheet.
 * 
 * @author Teddy Doll
 * @version 1.0
 */
public class ExpressionTreeNode {
	// Instance fields

	/**
	 * The token.
	 */
	private Token token;

	/**
	 * The left node.
	 */
	private ExpressionTreeNode left;

	/**
	 * The right node.
	 */
	private ExpressionTreeNode right;

	// Constructor

	/**
	 * Construct an expression tree node.
	 * 
	 * @param token
	 *            The token.
	 * @param left
	 *            The left node.
	 * @param right
	 *            The right node.
	 */
	public ExpressionTreeNode(Token token, ExpressionTreeNode left,
			ExpressionTreeNode right) {
		this.token = token;
		this.left = left;
		this.right = right;
	}

	// Instance methods

	/**
	 * @return The token of this node.
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * 
	 * @return The left node of this node.
	 */
	public ExpressionTreeNode getLeft() {
		return left;
	}

	/**
	 * 
	 * @return The right node of this node.
	 */
	public ExpressionTreeNode getRight() {
		return right;
	}
}
