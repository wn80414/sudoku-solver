package sudoku;

import java.util.*;

/**
 * Class that takes a Sudoku grid and solves it.
 * @author William Nguyen
 *
 */

public class Solver 
{
	private Grid						problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	/**
	 * Backtracking recursive solver.
	 * @param grid		Grid object
	 */
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			// Abandon evaluation of this illegal board.
			return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			// A complete and legal solution. Add it to solutions.
			solutions.add(grid);
		}
		else
		{
			// Here if eval == Evaluation.CONTINUE. Generate all 9 possible next grids. Recursively 
			// call solveRecurse() on those grids.
			
			ArrayList<Grid> temp = grid.next9Grids();
			for (Grid recurseGrid: temp) {
			solveRecurse(recurseGrid);
			}
		}
	}
	
	/**
	 * Evaluates the grid.
	 * 
	 * @param grid		Grid object
	 * @return	Evaluation.ABANDON if the grid is illegal. 
	 * @return 	ACCEPT if the grid is legal and complete.
	 * @return	CONTINUE if the grid is legal and incomplete.
	 */
	public Evaluation evaluate(Grid grid)
	{
		if (grid.isLegal() && grid.isFull()) {
			return Evaluation.ACCEPT;
		}
		else if (!grid.isLegal()) {
			return Evaluation.ABANDON;
		}
		return Evaluation.CONTINUE;
	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle1();		// or any other puzzle
		Solver solver = new Solver(g);
		System.out.println("Will solve\n" + g);
		solver.solve();
		System.out.println(solver.getSolutions());
		
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
		
	}
}
