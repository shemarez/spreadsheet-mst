package ADTs;
import spreadsheet.Spreadsheet;
import tokens.CellToken;
import tokens.LiteralToken;
import tokens.OperatorToken;
import tokens.Token;

/**
 * The ExpressionTree class. 
 * @author Teddy Doll
 * @version 1.0
 */
public class ExpressionTree {
  	//Instance field
  
  	/**
  	 * The root of this tree.
  	 */
	ExpressionTreeNode root;
	
	//Constructor
	
	/**
	 * Construct an expression tree contains only its root.
	 */
	public ExpressionTree(ExpressionTreeNode root){
		this.root = root;
	}
	
	//Instance methods
	
	/**
	 * Make the tree empty.
	 */
	public void makeEmpty(){
		this.root = null;
	}
	
	/**
	 * Print out the tree.
	 */
	public void printTree(){
		;
	}
	
	/**
	 * 
	 * @return The root of this tree.
	 */
	public ExpressionTreeNode getRoot(){
		return this.root;
	}
	
	/**
	 * Set the root of this tree to be the_root.
	 * @param the_root The root.
	 */
	public void setRoot(ExpressionTreeNode the_root){
		this.root = the_root;
	}
	
	/**
	 * 
	 * @param spreadsheet The spreadsheet.
	 * @return 
	 */
	public int Evaluate(Spreadsheet spreadsheet){
		return evaluteTree(spreadsheet, this.root);	
	}
	
	/**
	 * 
	 * @param spreadsheet The spreadsheet.
	 * @param the_root The root.
	 * @return
	 */
	private int evaluteTree(Spreadsheet spreadsheet, ExpressionTreeNode the_root){
		if (the_root == null) {
			return 0;
		}
		Token rootToken = (Token)the_root.getToken();
		if( rootToken instanceof LiteralToken )
			return ((LiteralToken) the_root.getToken()).getValue();

		else if(rootToken instanceof CellToken){
			return spreadsheet.getCellValue( ( (CellToken)rootToken ) ) ;
		}
		else {
			int leftTreeValue;
			int rightTreeValue;
			int returnValue = 0;
			switch(((OperatorToken)rootToken).getOperatorToken()){
				case OperatorToken.PLUS:
					leftTreeValue = evaluteTree(spreadsheet, the_root.getLeft());
					rightTreeValue = evaluteTree(spreadsheet, the_root.getRight());
					returnValue = leftTreeValue + rightTreeValue; break;
				case OperatorToken.MINUS:
					leftTreeValue = evaluteTree(spreadsheet, the_root.getLeft());
					rightTreeValue = evaluteTree(spreadsheet, the_root.getRight());
					returnValue = leftTreeValue - rightTreeValue; break;
				case OperatorToken.MULT:
					leftTreeValue = evaluteTree(spreadsheet, the_root.getLeft());
					rightTreeValue = evaluteTree(spreadsheet, the_root.getRight());
					returnValue = leftTreeValue * rightTreeValue; break;
				case OperatorToken.DIV:
					leftTreeValue = evaluteTree(spreadsheet, the_root.getLeft());
					rightTreeValue = evaluteTree(spreadsheet, the_root.getRight());
					returnValue = leftTreeValue / rightTreeValue; break;
				default:
					//Should never happen
					System.out.println("error in tree eval");
					break;
			}
			return returnValue;
		}
			
		
	}

}
