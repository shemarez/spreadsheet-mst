package ADTs;
import tokens.Token;


public class ExpressionTreeNode {
	
	private Token token;
	private ExpressionTreeNode left;
	private ExpressionTreeNode right;
	
	public ExpressionTreeNode(Token token, 
			ExpressionTreeNode left, ExpressionTreeNode right){
		this.token = token;
		this.left = left;
		this.right = right;
	}
	
	public Token getToken(){
		return token;
	}
	public ExpressionTreeNode getLeft(){
		return left;
	}
	public ExpressionTreeNode getRight(){
		return right;
	}
}
