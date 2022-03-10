package sudoku;

import java.util.*;

/**
 * Class that represents a Sudoku 9x9 grid with
 * 2d arrays. It can check if the grid is full, 
 * legal and if the contents equals another grid.
 * 
 * @author William Nguyen
 *
 */

public class Grid 
{
	private int[][]						values;

	/**
	 * Constructor for grid.
	 * Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	 * See TestGridSupplier for examples of input.
	 * 
	 * Dots in input strings represent 0s in values[][].
	 * 
	 * @param rows		nums of rows
	 */
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}

	/**
	 * Turns grid into String format.
	 * 
	 * @return String representing grid
	 */
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	// Duplicates its source. You gets called 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}

	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Donâ€™t change
	// â€œthisâ€� grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		if (this.isFull()) {
			return null;
		}

		// Find x,y of an empty cell.
		Outer:
			for (int i = 0; i < values.length; i++) {
				for (int j = 0; j < values[0].length; j++) {
					if (values[i][j] == 0) {
						xOfNextEmptyCell = i;
						yOfNextEmptyCell = j;
						break Outer;
					}
				}
			}

		// Construct array list to contain 9 new grids.
		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.
		for (int i = 1; i <= 9; i++){
			Grid dupe = new Grid(this);
			dupe.values[xOfNextEmptyCell][yOfNextEmptyCell] = i;
			grids.add(dupe);
		}
		return grids;
	}


	//

	//
	
	/**
	 * Returns true if this grid is legal. A grid is legal if no row, column, or
	 * 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	 * @return boolean 
	 */
	public boolean isLegal()
	{

		// Check every row. If you find an illegal row, return false.
		for (int r = 0; r < values.length; r++) {
			int temp;
			for (int c = 0; c < values[0].length; c++) {	
				temp = values[r][c];
				if (temp != 0) {
					for (int dupeCheck = 0; dupeCheck < values[0].length; dupeCheck++) {
						if (temp == values[r][dupeCheck] && dupeCheck != c) {
							return false;
						}
					}
				}
			}

		}
		// Check every column. If you find an illegal column, return false.
		for (int c = 0; c < values[0].length; c++) {
			int temp;
			for (int r = 0; r < values.length; r++) {
				temp = (values[r][c]);
				if (temp != 0) {
					for (int dupeCheck = 0; dupeCheck < values.length; dupeCheck++) {
						if (temp == values[dupeCheck][c] && dupeCheck != r) {
							return false;
						}
					}
				}

			}
		}
		// Check every block. If you find an illegal block, return false.
		for (int i = 0; i < values.length; i += 3) {			//Visits the blocks in the grid.
			for (int j = 0; j < values[0].length; j += 3) {

				for(int r = i; r < values.length / 3 + i; r++) {		//Checks each block
					int temp;
					for (int c = j; c < values[0].length / 3 + j; c++) {
						temp = values[r][c];
						if (temp != 0) {
							for (int dupeCheck = 0; dupeCheck < values.length; dupeCheck++) {
								if (temp == values[r][dupeCheck] && dupeCheck != c) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		// All rows/cols/blocks are legal.
		return true;
	}

	
	/**
	 * Returns true if every cell member of values[][] is a digit from 1-9.
	 * @return	boolean
	 */
	public boolean isFull()
	{
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				if (values[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}


	//

	//
	/**
	 * Returns true if x is a Grid and, for every (i,j), 
	 * x.values[i][j] == this.values[i][j].
	 * False otherwise.
	 * 
	 * @return boolean
	 */
	public boolean equals(Object x)
	{
		Grid s = (Grid) x;
		for (int i = 0; i < this.values.length; i++) {
			for (int j = 0; j < this.values[0].length; j++) {
				if (s.values[i][j] != this.values[i][j]) {
					return false;
				}				
			}
		}
		return true;
	}



}
