import java.lang.Math;
import java.util.Arrays;

// https://community.topcoder.com/stat?c=problem_statement&pm=14518

//~ Problem Statement
//~ You may have solved the following classic task before: 

//~ Given is a rectangular board divided into n rows by m columns of cells. Each cell is either empty or it contains an obstacle. You start in the cell (0, 0). In each step you can move to an adjacent cell (up, down, left, or right). Obviously, you may not leave the board and you may not enter a cell with an obstacle. You want to reach the cell (n-1, m-1). What is the smallest number of steps you need? 

//~ In this task, you are going to solve the above task in reverse. You are given an int k. Design any rectangular board with the following properties: 

//~ The number of rows (n) must be between 1 and 50, inclusive.
//~ The number of columns (m) must be between 1 and 50, inclusive.
//~ The corner cells (0, 0) and (n-1, m-1) must both be empty.
//~ It must be possible to reach the cell (n-1, m-1) from the cell (0, 0). Additionally, the shortest way of doing so must have exactly k steps.
//~ For the constraints used in this task a solution always exists. If there are multiple solutions, you may choose any of them. Return the board you constructed as a String[] with n elements, each containing m characters. Use the character '#' to represent an obstacle and the character '.' to represent an empty cell.
 
//~ Definition
    	
//~ Class:	MazeConstruct
//~ Method:	construct
//~ Parameters:	int
//~ Returns:	String[]
//~ Method signature:	String[] construct(int k)
//~ (be sure your method is public)
    
//~ Constraints
//~ -	k will be between 2 and 1,000, inclusive.
 
//~ Examples
    	
//~ 3 - Returns: {"...." }
//~ There is more than one solution. Any valid solution will be accepted. For example, you may also return {"..", "..", ".."} or {"..", ".#", ".."}.
//~ 4 - Returns: {"...", "...", "..." }
//~ 10 - Returns: {"..#..", "#.#..", "..#..", ".#...", "....." }


public class MazeConstruct {
	private static final int MAX_N = 50;
	private static final int MAX_M = 50;
	private static final int MAX_K = 1000;

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		mazeConstruct(1005);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void mazeConstruct(int k) {
		//~ boolean solutionFound[] = new boolean[k+1];
		for(int n = 1; n <= MAX_N; n++) {
			for(int m = 1; m <= MAX_M; m++) {
				for(int ml = 0; ml <= m-1; ml++) {
					// ml <= m-1 to leave at least a free corridor to reach the opposing corner
					if(ml > 0 && ml % 4 != 0) {
						continue;
					}
					for(int nl = 0; nl <= n+1; nl++) {
						// nl <= n+1 because the maze width can go beyond the board, making the end of the board the natural wall
						if(nl == 1 || nl == 2) {
							// nl >= 3 because a maze needs to have at least width of 3
							continue;
						}
						// Formula explained:
						// ((ml/4)*(nl*2)) calculates number of steps to take in a serpenting maze from beginning corner to the exit of the maze
						// (n - 1) calculates number of horizontal steps to take from a beginning corner or maze exit (both on far left of the board) to the right wall.
						// (m - ml - 1) calculates number of vertical steps to take from a beginning corner or maze exit to the bottom of the board.
						int r = ((ml/4)*(nl*2)) + (n - 1) + (m - ml - 1);
						if(r == k) {
							printMaze(n,m,nl,ml);
							return;
						}
						//~ if(r == k) {
							//~ solutionFound[r] = true;
						//~ }
					}
				}
			}
		}
		//~ System.out.println(Arrays.toString(solutionFound));
	}
	
	// takes n and m as size of the board, nl and ml as size of the serpenting maze
	private static void printMaze(int n, int m, int nl, int ml) {
		String maze[] = new String[m];
		boolean isPassageRight = true;
		for(int r = 0; r < m; r++) {
			StringBuilder rStringb = new StringBuilder();
			boolean inWalledSection = r < ml;
			boolean isWallRow = inWalledSection && (r%2 == 1);
			for(int c = 0; c < n; c++) {
				if(isWallRow) {
					if((c == 0 && !isPassageRight) || ((c == nl - 2) && isPassageRight) || c >= nl) {
						rStringb.append(".");
					} else {
						rStringb.append("#");
					}
					continue;
				}
				if(inWalledSection && (c == nl-1 || r == ml-1)) {
					rStringb.append("#");
				} else {
					rStringb.append(".");
				}
			}
			if(isWallRow) {
				isPassageRight = !isPassageRight;
			}
			maze[r] = rStringb.toString();
		}
		System.out.println("n: " + n + " m: " + m + " nl: " + nl + " ml: " + ml);
		System.out.println();
		for(int i = 0; i < maze.length; i++) {
			System.out.println(maze[i]);
		}
	}

}
