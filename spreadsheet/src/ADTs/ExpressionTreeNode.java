package ADTs;
import tokens.Token;

/**
 * ExpressionTreeNode class to use in the ExpressionTree class.
 * @author Teddy Toll
 * @version 1.0
 */
public class ExpressionTreeNode {
	//Instance fields
  
  	/**
  	 * The token of this node.
  	 */
	private Token token;
	
	/**
	 * Left node.
	 */
	private ExpressionTreeNode left;
	
	/**
	 * Right node.
	 */
	private ExpressionTreeNode right;
	
	//Constructor
	
	/**
	 * Construct an ExpressionTreeNode with inputs from user.
	 */
	public ExpressionTreeNode(Token token, ExpressionTreeNode left, 
	    			  ExpressionTreeNode right){
		this.token = token;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * 
	 * @return The token of this node.
	 */
	public Token getToken(){
		return token;
	}
	
	/**
	 * 
	 * @return The left node of this node.
	 */
	public ExpressionTreeNode getLeft(){
		return left;
	}
	
	/**
	 * 
	 * @return The right node of this node.
	 */
	public ExpressionTreeNode getRight(){
		return right;
	}
}
