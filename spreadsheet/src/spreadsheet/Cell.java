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
public class Cell
{
  //Instance fields
  
  /**
   * The bad cell tag.
   */
  private static final int BadCell = -1;
  
  /**
   * The formula of this cell.
   */
  private String formula;
  
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

  //Constructor
  
  /**
   * Construct an "empty" cell with all values are set to the initial values.
   */
  public Cell()
  {
    formula = "";
    value = 0;
    expressionTree = new ExpressionTree(null);
    dependents = new LinkedList();
    dependencies = new LinkedList();
    indegree = 0;
    indegreeTemp = 0;

  }

  //Instance methods
  
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
   * Returns the evaluation of this cell's formula. Return "0" if this cell's formula is empty.
   * 
   * @return cell value
   * @author Teddy Doll
   */
  public String printValue() {
    /*if (this.formula.equals(""))
      return "0";
    else*/
      return Integer.toString(value);
  }

  /**
   * Sets this cells formula to the given input string
   * 
   * @param formula
   *          The cells new formula.
   * @param spreadsheet
   *          The spreadsheet.
   */
  public void setFormula(String formula, Spreadsheet spreadsheet) {
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
      Cell cell = spreadsheet
	  .getCell(cellToken.getRow(), cellToken.getColumn());
      cell.dependents.insert(this, cell.dependents.zeroth());
      indegree++;

    }

  }

  /**
   * Remove the cell out of the list of dependents.
   * 
   * @param cell The cell.
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
   * 
   * @author from homework hand-out
   * @param s
   *          Stack to pop tokens from
   */
  private void BuildExpressionTree(Stack s) {
    this.expressionTree.setRoot(GetExpressionTree(s));
    if (!s.isEmpty()) {
      System.out.println("Error in BuildExpressionTree.");
    }
  }

  /**
   * Recursively adds nodes to an expression tree from stack of tokens.
   * 
   * @author from homework hand-out
   * @param s Stack to pop tokens from
   * @return The root of the expression tree.
   */
  private ExpressionTreeNode GetExpressionTree(Stack s) {
    ExpressionTreeNode returnTree;
    Token token;
    if (s.isEmpty())
      return null;
    token = (Token) s.topAndPop(); // need to handle stack underflow
    if (token instanceof CellToken)
      dependencies.insert(token, dependencies.zeroth());
    if ((token instanceof LiteralToken) || (token instanceof CellToken)) {
      // Literals and Cells are leaves in the expression tree
      returnTree = new ExpressionTreeNode(token, null, null);

    } else { // if (token instanceof OperatorToken) {
      // Continue finding tokens that will form the
      // right subtree and left subtree.
      ExpressionTreeNode rightSubtree = GetExpressionTree(s);
      ExpressionTreeNode leftSubtree = GetExpressionTree(s);
      returnTree = new ExpressionTreeNode(token, leftSubtree, rightSubtree);

    }
    return returnTree;
  }

  /**
   * Assuming that the next chars in a String (at the given startIndex) is a
   * cell reference, set cellToken's column and row to the cell's column and
   * row.
   * <p>
   * If the cell reference is invalid, the row and column of the return
   * CellToken are both set to BadCell (which should be a final int that equals
   * -1). Also, return the index of the position in the string after processing
   * the cell reference.
   * <p>
   * (Possible improvement: instead of returning a CellToken with row and column
   * equal to BadCell, throw an exception that indicates a parsing error.)
   * 
   * <p>
   * A cell reference is defined to be a sequence of CAPITAL letters, followed
   * by a sequence of digits (0-9). The letters refer to columns as follows: A =
   * 0, B = 1, C = 2, ..., Z = 25, AA = 26, AB = 27, ..., AZ = 51, BA = 52, ...,
   * ZA = 676, ..., ZZ = 701, AAA = 702. The digits represent the row number.
   * 
   * @param inputString
   *          the input string
   * @param startIndex
   *          the index of the first char to process
   * @param cellToken
   *          a cellToken (essentially a return value)
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
      // reached the end of the string before fully parsing the cell reference
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
   * return a stack of Tokens so that the expression, when read from the bottom
   * of the stack to the top of the stack, is a postfix expression.
   * 
   * <p>
   * A formula is defined as a sequence of tokens that represents a legal infix
   * expression.
   * 
   * <p>
   * A token can consist of a numeric literal, a cell reference, or an operator
   * (+, -, *, /).
   * 
   * <p>
   * Multiplication (*) and division (/) have higher precedence than addition
   * (+) and subtraction (-). Among operations within the same level of
   * precedence, grouping is from left to right.
   * 
   * <p>
   * This algorithm follows the algorithm described in Weiss, pages 105-108.
   * 
   * @param formula The formula.
   * @return A stack of Tokens so that the expression, when read from the bottom
   *         of the stack to the top of the stack, is a postfix expression.
   */
  public static Stack getFormula(String formula) {
    Stack returnStack = new Stack(); // stack of Tokens (representing a postfix
				     // expression)
    boolean error = false;
    char ch = ' ';

    int literalValue = 0;

    CellToken cellToken;

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
	OperatorToken newOperator = new OperatorToken(ch);
	switch (ch) {
	case OperatorToken.PLUS:
	case OperatorToken.MINUS:
	case OperatorToken.MULT:
	case OperatorToken.DIV:
	case OperatorToken.LEFT_PAREN:
	  // push operatorTokens onto the output stack until
	  // we reach an operator on the operator stack that has
	  // lower priority than the current one.
	  OperatorToken stackOperator;
	  while (!operatorStack.isEmpty()) {
	    stackOperator = (OperatorToken) operatorStack.top();
	    if ((stackOperator.priority() >= newOperator.priority())
		&& (stackOperator.getOperatorToken() != OperatorToken.LEFT_PAREN)) {

	      // output the operator to the return stack
	      operatorStack.pop();
	      returnStack.push(stackOperator);
	    } else {
	      break;
	    }
	  }
	  break;

	default:
	  // This case should NEVER happen
	  System.out.println("Error in getFormula.");
	  System.exit(0);
	  break;
	}
	// push the operator on the operator stack
	operatorStack.push(new OperatorToken(ch));

	index++;

      } else if (ch == ')') { // maybe define OperatorToken.RightParen ?
	OperatorToken stackOperator;
	stackOperator = (OperatorToken) operatorStack.topAndPop();
	// This code does not handle operatorStack underflow.
	while (stackOperator.getOperatorToken() != OperatorToken.LEFT_PAREN) {
	  // pop operators off the stack until a LeftParen appears and
	  // place the operators on the output stack
	  returnStack.push(stackOperator);
	  stackOperator = (OperatorToken) operatorStack.topAndPop();
	}

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
	returnStack.push(new LiteralToken(literalValue));

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
	}

      } else {
	error = true;
	break;
      }
    }

    // pop all remaining operators off the operator stack
    while (!operatorStack.isEmpty()) {
      returnStack.push(operatorStack.topAndPop());
    }

    if (error) {
      // a parse error; return the empty stack
      returnStack.makeEmpty();
    }

    return returnStack;
  }
}
