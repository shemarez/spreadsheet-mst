package spreadsheet;
import ADTs.LinkedList;
import ADTs.Overflow;
import ADTs.QueueAr;
import tokens.CellToken;


public class Spreadsheet {

	private Cell[][] cellData;
	private int rows;
	private int columns;

	public Spreadsheet(int size){
		this.rows = size;
		this.columns = size;
		cellData = new Cell[rows][columns];
		populateCells();
	}
	public Spreadsheet(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		cellData = new Cell[rows][columns];
		populateCells();
	}
	private void populateCells(){
		for ( int row = 0; row < this.rows; row++){
			for (int col = 0; col < this.columns; col++){
				cellData[row][col] = new Cell();
			}
		}
	}
	/**
	 * Returns the cell at the row and column specified, of this spreadsheet
	 * @param row the row of the cell
	 * @param column the column of the cell
	 * @return a cell at row and column
	 */
	public Cell getCell(int row, int column){
		return cellData[row][column];
	}
	
	/**
	 * Update a cell formula and recalculate Spreadsheet
	 * @param cellToken the cell to update
	 * @param inputFormula new formula for cell
	 */
	public void changeCellFormulaAndRecalculate(CellToken cellToken, 
			String inputFormula){
		cellData[cellToken.getRow()]
		         [cellToken.getColumn()].setFormula(inputFormula, this);
		
		try {
			topSort();
		} catch (CycleFound e) {
			System.out.println("A Cycle was found, " +
					"you computer will now blow up");
			
			e.printStackTrace();
			System.exit(0);
		}
		
		/*for ( int row = 0; row < this.rows; row++){
			for (int col = 0; col < this.columns; col++){
				cellData[row][col].Evaluate(this);
			}
		}*/
		
		
		
	}
	
	/**
	 * Does a topological sort of the cells and evaluates the cells 
	 * @throws CycleFound
	 */
	private void topSort() throws CycleFound{
		int numVertices = this.rows * this.columns;
		QueueAr q = new QueueAr(numVertices);
		int counter = 0;
		Cell cellV, cellW;
		try
        {
			for ( int row = 0; row < this.rows; row++){
				for (int col = 0; col < this.columns; col++){
					cellV = cellData[row][col];
					//reset temp indegree to original.
					cellV.indegreeTemp = cellV.indegree;
					if ( cellV.indegreeTemp == 0 ){
						q.enqueue(cellV);
					}
				}
			}
			
			while( !q.isEmpty() ){
				cellV = ( Cell )q.dequeue();
				cellV.Evaluate(this);
				counter++;
				LinkedList.Iterator iter = cellV.dependents.iterator();
				while( iter.hasNext() ){
					cellW = ( Cell )iter.next();
					if( --cellW.indegreeTemp == 0){
						q.enqueue(cellW);
					}
				}
			}
			if( counter != numVertices){
				throw new CycleFound();
			}
		}catch( Overflow e ) { 
			System.out.println( "Unexpected overflow" ); 
			}
	}
	
	/**
	 * Returns the value of the requested cell.
	 * @param cellToken the cell for which value is requested 
	 * @return cell value
	 */
	public int getCellValue(CellToken cellToken){
		return cellData[cellToken.getRow()][cellToken.getColumn()].getValue();
	}
	
	/**
	 * Prints all the cells of the spreadsheet to the console.
	 */
	public void printValues(){
		StringBuffer sb = new StringBuffer();
		sb.append("Spreadsheet values: \n");

		for ( int row = 0; row < this.rows; row++){
			for (int col = 0; col < this.columns; col++){
				String nextValue = cellData[row][col].printValue();
				sb.append(nextValue);
				//for proper spacing
				for (int spaces = nextValue.length(); spaces < 10; spaces++)
					sb.append(" ");
				
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
			
	}
	
	public void printAllFormulas(){
		for ( int row = 0; row < this.rows; row++){
			for (int col = 0; col < this.columns; col++){
				System.out.println(cellData[row][col].printFormula());
			}
		}
	}
	
	public void printCellFormula(CellToken cellToken){
		System.out.println(cellData[cellToken.getRow()]
		                            [cellToken.getColumn()].printFormula());
	}
	
	public String cellformulaToString(CellToken cellToken){
		return cellData[cellToken.getRow()]
                        [cellToken.getColumn()].printFormula();
	}
	
	public String cellValueToString(CellToken cellToken){
		return cellData[cellToken.getRow()]
                        [cellToken.getColumn()].printValue();
	}
	public int getNumRows(){
		return this.rows;
	}
	public int getNumColumns(){
		return this.columns;
	}

}
