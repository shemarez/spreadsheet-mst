package spreadsheet;

import tokens.CellToken;
import tokens.LiteralToken;
import tokens.OperatorToken;
import tokens.Token;

import ADTs.ExpressionTree;
import ADTs.ExpressionTreeNode;
import ADTs.LinkedList;
import ADTs.Stack;

/**
 * Represents a cell in a spreadsheet
 * 
 * @author Teddy Doll
 * 
 */
public class Cell {
	// Instance fields

	/**
	 * The bad cell tag.
	 */
	private static final int BadCell = -1;

	/**
	 * The formula of this cell.
	 */
	private String formula;

	/**
	 * Last good formula of this cell, in case a cycle is found.
	 */
	private String lastFormula;

	/**
	 * The value of this cell.
	 */
	private int value;

	// the expression tree below represents the formula
	/**
	 * The expression tree represents the formula.
	 */
	private ExpressionTree expressionTree;

	/**
	 * Cells that depend on this cell.
	 */
	LinkedList dependents;

	/**
	 * Cells that this cell depends on.
	 */
	private LinkedList dependencies;

	/**
	 * The indegree of this cell.
	 */
	int indegree;

	/**
	 * The temporary indegree of this cell.
	 */
	int indegreeTemp;

	// Constructor

	/**
	 * Construct an "empty" cell with all values are set to the initial values.
	 */
	public Cell() {
		formula = "";
		value = 0;
		expressionTree = new ExpressionTree(null);
		dependents = new LinkedList();
		dependencies = new LinkedList();
		indegree = 0;
		indegreeTemp = 0;

	}

	// Instance methods

	/**
	 * Returns the evaluated value of this cell.
	 * 
	 * @return this cell's value
	 */
	public int getValue() {
		return this.value;
	}

	/*
	 * public Stack getDependencies(){ Stack returnStack = new Stack(); Stack
	 * tempStack = new Stack(); while(!dependencies.isEmpty()){
	 * tempStack.push(dependencies.topAndPop()); } while(!tempStack.isEmpty()){
	 * Token token = (Token) tempStack.topAndPop(); returnStack.push(token);
	 * dependencies.push(token); } return returnStack; }
	 */

	/**
	 * Evaluate the value of this cell.
	 */
	public void Evaluate(Spreadsheet spreadsheet) {
		this.value = expressionTree.Evaluate(spreadsheet);
	}

	/**
	 * Returns a infix ordered String of this cells formula.
	 * 
	 * @return The formula of this cell.
	 * @author Teddy Doll
	 */
	public String printFormula() {
		return formula;
	}

	/**
	 * Returns the evaluation of this cell's formula. Return "0" if this cell's
	 * formula is empty.
	 * 
	 * @return cell value
	 * @author Teddy Doll
	 */
	public String printValue() {
		/*
		 * if (this.formula.equals("")) return "0"; else
		 */
		return Integer.toString(value);
	}

	/**
	 * The reinstates this cells old formula
	 * 
	 * @param spreadsheet
	 *            the spreadsheet this cell belongs to
	 * @throws UnpairedParenthesesException 
	 */
	public void revert(Spreadsheet spreadsheet) throws UnpairedParenthesesException {
		this.setFormula(lastFormula, spreadsheet);
	}

	/**
	 * Sets this cells formula to the given input string
	 * 
	 * @param formula
	 *            The cells new formula.
	 * @param spreadsheet
	 *            The spreadsheet this cell belongs to.
	 * @throws UnpairedParenthesesException 
	 */
	public void setFormula(String formula, Spreadsheet spreadsheet) throws UnpairedParenthesesException {
		this.lastFormula = this.formula;
		this.formula = formula;

		// Create a temporary dependencies list
		LinkedList tempDependencies = new LinkedList();
		LinkedList.Iterator iter1 = dependencies.iterator();
		while (iter1.hasNext()) {
			tempDependencies.insert(iter1.next(), tempDependencies.zeroth());
		}

		// Build expression and get dependencies
		// and reset indegree
		dependencies.makeEmpty();
		indegree = 0;
		indegreeTemp = 0;
		
		BuildExpressionTree(getFormula(formula));

		iter1 = dependencies.iterator();
		LinkedList.Iterator iter2 = tempDependencies.iterator();
		while (iter2.hasNext()) {
			CellToken cellToken = (CellToken) iter2.next();
			spreadsheet.getCell(cellToken.getRow(), cellToken.getColumn())
					.removeDependent(this);
		}
		while (iter1.hasNext()) {
			CellToken cellToken = (CellToken) iter1.next();
			Cell cell = spreadsheet.getCell(cellToken.getRow(), cellToken
					.getColumn());
			cell.dependents.insert(this, cell.dependents.zeroth());
			indegree++;

		}

	}

	/**
	 * Remove the cell out of the list of dependents.
	 * 
	 * @param cell
	 *            The cell.
	 */
	private void removeDependent(Cell cell) {
		LinkedList.Iterator iter = dependents.iterator();
		LinkedList.Iterator prev = dependents.zeroth();
		boolean found = false;
		while (!found && iter.hasNext()) {
			if (cell == iter.next()) {
				dependents.remove(prev);
				found = true;
			} else {
				prev.next();
			}
		}
	}

	// Build an expression tree from a stack of ExpressionTreeTokens
	/**
	 * Builds an expression tree from a stack of tokens
	 * Note: Possibly should be moved to ExpressionTree Class.
	 * 
	 * @author from homework hand-out
	 * @param s Stack to pop tokens from
	 *            
	 */
	private void BuildExpressionTree(Stack s) {
		this.expressionTree.setRoot(GetExpressionTree(s));
	}

	/**
	 * Recursively adds nodes to an expression tree from stack of tokens. There
	 * are three separate recursive cases: 1)Token is a cell or literal 2)Token
	 * is an unary minus 3)Token is operator but not unary minus
 	 * Note: Possibly should be moved to ExpressionTree Class.

	 * @author from homework hand-out/Teddy Doll
	 * @param s Stack to pop tokens from
	 *            
	 * @return The root of the expression tree.
	 */
	private ExpressionTreeNode GetExpressionTree(Stack s) {
		ExpressionTreeNode returnTree;
		Token token;
		// first base case stack is empty.
		if (s.isEmpty())
			return null;
		token = (Token) s.topAndPop();
		// populate dependencies list.
		if (token instanceof CellToken)
			dependencies.insert(token, dependencies.zeroth());

		// second base case: token is literal or cell.
		if ((token instanceof LiteralToken) || (token instanceof CellToken)) {
			// Literals and Cells are leaves in the expression tree
			returnTree = new ExpressionTreeNode(token, null, null);
		}
		// recursive case were token is unary minus
		// continue finding tokens for for the right subtree.
		else if (token instanceof OperatorToken
				&& ((OperatorToken) token).getOperatorToken() == OperatorToken.U_MINUS) {
			ExpressionTreeNode rightSubtree = GetExpressionTree(s);
			returnTree = new ExpressionTreeNode(token, null, rightSubtree);

		}
		// recursive case were token is any other operator
		// Continue finding tokens that will form the
		// right subtree and left subtree.
		else {

			ExpressionTreeNode rightSubtree = GetExpressionTree(s);
			ExpressionTreeNode leftSubtree = GetExpressionTree(s);
			returnTree = new ExpressionTreeNode(token, leftSubtree,
					rightSubtree);

		}
		return returnTree;
	}

	/**
	 * Assuming that the next chars in a String (at the given startIndex) is a
	 * cell reference, set cellToken's column and row to the cell's column and
	 * row.
	 * <p>
	 * If the cell reference is invalid, the row and column of the return
	 * CellToken are both set to BadCell (which should be a final int that
	 * equals -1). Also, return the index of the position in the string after
	 * processing the cell reference.
	 * <p>
	 * (Possible improvement: instead of returning a CellToken with row and
	 * column equal to BadCell, throw an exception that indicates a parsing
	 * error.)
	 * 
	 * <p>
	 * A cell reference is defined to be a sequence of CAPITAL letters, followed
	 * by a sequence of digits (0-9). The letters refer to columns as follows: A
	 * = 0, B = 1, C = 2, ..., Z = 25, AA = 26, AB = 27, ..., AZ = 51, BA = 52,
	 * ..., ZA = 676, ..., ZZ = 701, AAA = 702. The digits represent the row
	 * number.
	 * 
	 * @param inputString the input string
	 *            
	 * @param startIndex the index of the first char to process
	 *            
	 * @param cellToken a cellToken (essentially a return value)
	 *            
	 * @return index corresponding to the position in the string just after the
	 *         cell reference
	 */
	public static int getCellToken(String inputString, int startIndex,
			CellToken cellToken) {
		char ch;
		int column = 0;
		int row = 0;
		int index = startIndex;

		// handle a bad startIndex
		if ((startIndex < 0) || (startIndex >= inputString.length())) {
			cellToken.setColumn(BadCell);
			cellToken.setRow(BadCell);
			return index;
		}

		// get rid of leading whitespace characters
		while (index < inputString.length()) {
			ch = inputString.charAt(index);
			if (!Character.isWhitespace(ch)) {
				break;
			}
			index++;
		}
		if (index == inputString.length()) {
			// reached the end of the string before finding a capital letter
			cellToken.setColumn(BadCell);
			cellToken.setRow(BadCell);
			return index;
		}

		// ASSERT: index now points to the first non-whitespace character

		ch = inputString.charAt(index);
		// process CAPITAL alphabetic characters to calculate the column
		if (!Character.isUpperCase(ch)) {
			cellToken.setColumn(BadCell);
			cellToken.setRow(BadCell);
			return index;
		} else {
			column = ch - 'A';
			index++;
		}

		while (index < inputString.length()) {
			ch = inputString.charAt(index);
			if (Character.isUpperCase(ch)) {
				column = ((column + 1) * 26) + (ch - 'A');
				index++;
			} else {
				break;
			}
		}
		if (index == inputString.length()) {
			// reached the end of the string before fully parsing the cell
			// reference
			cellToken.setColumn(BadCell);
			cellToken.setRow(BadCell);
			return index;
		}

		// ASSERT: We have processed leading whitespace and the
		// capital letters of the cell reference

		// read numeric characters to calculate the row
		if (Character.isDigit(ch)) {
			row = ch - '0';
			index++;
		} else {
			cellToken.setColumn(BadCell);
			cellToken.setRow(BadCell);
			return index;
		}

		while (index < inputString.length()) {
			ch = inputString.charAt(index);
			if (Character.isDigit(ch)) {
				row = (row * 10) + (ch - '0');
				index++;
			} else {
				break;
			}
		}

		// successfully parsed a cell reference
		cellToken.setColumn(column);
		cellToken.setRow(row);
		return index;
	}

	/**
	 * Given a string that represents a formula that is an infix expression,
	 * return a stack of Tokens so that the expression, when read from the
	 * bottom of the stack to the top of the stack, is a postfix expression.
	 * 
	 * <p>
	 * A formula is defined as a sequence of tokens that represents a legal
	 * infix expression.
	 * 
	 * <p>
	 * A token can consist of a numeric literal, a cell reference, or an
	 * operator (+, -, *, /, ^).
	 * 
	 * 
	 * <p>
	 * Multiplication (*) and division (/) have higher precedence than addition
	 * (+) and subtraction (-). Among operations within the same level of
	 * precedence, grouping is from left to right.
	 * 
	 * <p>
	 * This algorithm follows the algorithm described in Weiss, pages 105-108.
	 * 
	 * <p>
	 * Algorithm was modified to parse unary minus by Teddy Doll
	 * 
	 * @param formula
	 *            The formula.
	 * @return A stack of Tokens so that the expression, when read from the
	 *         bottom of the stack to the top of the stack, is a postfix
	 *         expression.
	 * @throws UnpairedParenthesesException 
	 */
	public static Stack getFormula(String formula) throws UnpairedParenthesesException {
		Stack returnStack = new Stack(); // stack of Tokens (representing a
		// postfix
		// expression)
		boolean error = false;
		char ch = ' ';

		int literalValue = 0;

		CellToken cellToken;
		Token lastToken = null;
		int index = 0; // index into formula
		Stack operatorStack = new Stack(); // stack of operators

		while (index < formula.length()) {
			// get rid of leading whitespace characters
			while (index < formula.length()) {
				ch = formula.charAt(index);
				if (!Character.isWhitespace(ch)) {
					break;
				}
				index++;
			}

			if (index == formula.length()) {
				error = true;
				break;
			}

			// ASSERT: ch now contains the first character of the next token.
			if (OperatorToken.isOperator(ch)) {
				// We found an operator token
				OperatorToken newOperator;
				if (ch == '-'
						&& !(lastToken instanceof CellToken || lastToken instanceof LiteralToken)) {
					newOperator = new OperatorToken(OperatorToken.U_MINUS);
				} else {
					newOperator = new OperatorToken(ch);
				}
				lastToken = newOperator;
				OperatorToken stackOperator;
				
				while (!operatorStack.isEmpty()) {
					//Push Operators from operator stack on return stack until
					//find operator with less priority or a left paren.
					stackOperator = (OperatorToken) operatorStack.top();
					if ((stackOperator.priority() > newOperator.priority())
							&& (stackOperator.getOperatorToken() != OperatorToken.LEFT_PAREN)) {

						// output the operator to the return stack
						operatorStack.pop();
						returnStack.push(stackOperator);
					} else {
						break;
					}
				}
				operatorStack.push(newOperator);
				
				index++;

			} else if (ch == ')') {
				OperatorToken stackOperator;
				if(operatorStack.isEmpty())
					throw new UnpairedParenthesesException();
				
				stackOperator = (OperatorToken) operatorStack.topAndPop();
				
				while (!operatorStack.isEmpty() && 
						stackOperator.getOperatorToken() != 
							OperatorToken.LEFT_PAREN) {
					// pop operators off the stack until a LeftParen appears and
					// place the operators on the output stack
					returnStack.push(stackOperator);
					stackOperator = (OperatorToken) operatorStack.topAndPop();
				}
				if (stackOperator.getOperatorToken() != OperatorToken.LEFT_PAREN)
					throw new UnpairedParenthesesException();
				index++;
			} else if (Character.isDigit(ch)) {
				// We found a literal token
				literalValue = ch - '0';
				index++;
				while (index < formula.length()) {
					ch = formula.charAt(index);
					if (Character.isDigit(ch)) {
						literalValue = (literalValue * 10) + (ch - '0');
						index++;
					} else {
						break;
					}
				}
				// place the literal on the output stack
				returnStack.push( lastToken = new LiteralToken(literalValue));

			} else if (Character.isUpperCase(ch)) {
				// We found a cell reference token
				cellToken = new CellToken();
				index = getCellToken(formula, index, cellToken);
				if (cellToken.getRow() == BadCell) {
					error = true;
					break;
				} else {
					// place the cell reference on the output stack
					returnStack.push(cellToken);
					lastToken = cellToken;
				}

			} else {
				error = true;
				break;
			}
		}

		// pop all remaining operators off the operator stack
		while (!operatorStack.isEmpty()) {
			OperatorToken stackOperator = (OperatorToken) operatorStack.topAndPop();
			if(stackOperator.getOperatorToken() == OperatorToken.LEFT_PAREN)
				throw new UnpairedParenthesesException();
			else
				returnStack.push(stackOperator);
		}

		if (error) {
			// a parse error; return the empty stack
			returnStack.makeEmpty();
		}

		return returnStack;
	}
}
