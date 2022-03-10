package sudoku;

/**
 * Static variables for different puzzles.
 * Has get method for these static variables.
 */

import java.util.ArrayList;
public class TestGridSupplier 
{
	private final static String[]		PUZZLE_1 =
	{
		"..3.1.5..",
		"8..395..1",
		"15.....27",
		".8..7..5.",
		"62.9.4.13",
		".9..2..7.",
		"91.....34",
		"2..748..9",
		"..6.3.2.."		
	};    
	
	
	private final static String[]       SOLUTION_1 =
    {
        "463217598",
        "872395641",
        "159486327",
        "384671952",
        "627954813",
        "591823476",
        "918562734",
        "235748169",
        "746139285"
    };
	
	
	static Grid getPuzzle1()		{ return new Grid(PUZZLE_1); }
	static Grid getSolution1()		{ return new Grid(SOLUTION_1); }

	

	private final static String[]		 PUZZLE_2 =
	{
		".........",
		"8.1...9.7",
		"..75493..",
		"7..9.2..8",
		"....1....",
		"1..3.8..5",
		"..84312..",
		"2.5...1.9",
		"........."
	};     
	
	
	private final static String[]       SOLUTION_2 =
    {
        "439187562",
        "851623947",
        "627549381",
        "763952418",
        "582714693",
        "194368725",
        "978431256",
        "245876139",
        "316295874"
    };

	static Grid getPuzzle2()		{ return new Grid(PUZZLE_2); }
	static Grid getSolution2()		{ return new Grid(SOLUTION_2); }
	

	

	private final static String[]		 PUZZLE_3 =
	{
		".97..1.6.",
		"2....7..5",
		"....9...3",
		"85.......",
		"..9...5..",
		".......32",
		"3...7....",
		"5..6....1",
		".4.1..37."
	}; 
	
	
    private final static String[]       SOLUTION_3 =
    {
        "497351268",
        "236847195",
        "185296743",
        "853924617",
        "629713584",
        "714568932",
        "361472859",
        "578639421",
        "942185376"
    };

    
	static Grid getPuzzle3()		{ return new Grid(PUZZLE_3); }
	static Grid getSolution3()		{ return new Grid(SOLUTION_3); }
	
	
	//
	// You can use these to test your Grid's evaluate() method.
	//
	private final static String[]		REJECT_1 =
	{
		"11.......",
		".........",
		".........",
		".........",
		".........",
		".........",
		".........",
		".........",
		"........."
	};
	
	private final static String[]		REJECT_2 =
	{
		"2........",
		"2........",
		".........",
		".........",
		".........",
		".........",
		".........",
		".........",
		"........."
	};
	
	private final static String[]		REJECT_3 =
	{
		"3........",
		"..3......",
		".........",
		".........",
		".........",
		".........",
		".........",
		".........",
		"........."
	};
	
	private final static String[]		REJECT_4 =
	{
		".........",
		".........",
		".........",
		"....4....",
		".....4...",
		".........",
		".........",
		".........",
		"........."
	};
	
	private final static String[]		CONTINUE =
	{
		"123456789",
		".........",
		".........",
		".........",
		".........",
		".........",
		".........",
		".........",
		"........."
	};
	
	private final static String[]		TEST_9GRID =
	{
		".........",
		".........",
		"........5",
		".........",
		".........",
		".........",
		".........",
		".........",
		"........."
	};

	static Grid getReject1()		{ return new Grid(REJECT_1); }
	static Grid getReject2()		{ return new Grid(REJECT_2); }
	static Grid getReject3()		{ return new Grid(REJECT_3); }
	static Grid getReject4()		{ return new Grid(REJECT_4); }
	static Grid getContinue()		{ return new Grid(CONTINUE); }
	static Grid getAccept()			{ return getSolution1(); }
	
	public static void main(String[] args) {
		Grid p1 = new Grid(PUZZLE_1);
		Grid p2 = new Grid(PUZZLE_2);
		Grid s1 = new Grid(SOLUTION_1);
		Grid s2 = new Grid(SOLUTION_2);
		Grid t = new Grid(TEST_9GRID);
		System.out.println(p1.isFull());
		System.out.println(p1.equals(p2));
		
		System.out.println("");
		System.out.println("Legal:");
		System.out.println(p1.isLegal());
		System.out.println(p2.isLegal());
		System.out.println(s1.isLegal());
		System.out.println(s2.isLegal());
		
		ArrayList<Grid> list = t.next9Grids();
		for (Grid x: list) {
			System.out.println(x.toString());
		}
	}
}

