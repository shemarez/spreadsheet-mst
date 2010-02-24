package ADTs;
import spreadsheet.Spreadsheet;
import tokens.CellToken;
import tokens.LiteralToken;
import tokens.OperatorToken;
import tokens.Token;


public class ExpressionTree {
	ExpressionTreeNode root;
	
	public ExpressionTree(ExpressionTreeNode root){
		this.root = root;
	}
	
	public void makeEmpty(){
		this.root = null;
	}
	
	public void printTree(){
		;
	}
	public ExpressionTreeNode getRoot(){
		return this.root;
	}
	public void setRoot(ExpressionTreeNode the_root){
		this.root = the_root;
	}
	
	public int Evaluate(Spreadsheet spreadsheet){
		return evaluteTree(spreadsheet, this.root);	
	}
	
	private int evaluteTree(Spreadsheet spreadsheet, ExpressionTreeNode the_root){
		if (the_root == null)
			return 0;
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
