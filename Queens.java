import java.lang.Math;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Queens {
	
	private static final int GRID_SIZE = 12;
	
	private static class BoardState {
		int size;
		int columns[];
		
		public BoardState(int size, int columns[]) {
			this.size = size;
			this.columns = new int[size];
			for(int i = 0; i < size; i++) {
				this.columns[i] = columns[i];
			}
		}
		
		public BoardState(int size) {
			this.size = size;
			this.columns = new int[size];
			Arrays.fill(columns, -1);
		}
		
		public BoardState(BoardState s) {
			this(s.size, s.columns);
		}

		public boolean tryQueen(int row, int col) {
			if(row >= size || col >= size || columns[row] >= 0) {
				return false; // invalid state
			}
			
			for(int i = 0; i < row; i++) {
				if(columns[i] == col || (columns[i] >= 0 && (Math.abs(columns[i] - col) == row - i))) {
					return false;
				}
			}
			
			return true;
		}
		
		public void putQueen(int row, int col) {
			if(row >= size || col >= size) {
				return; // invalid state
			}
			columns[row] = col;
		}
		
		private boolean testConflictDiagonals(int row, int col) {
			return false;
		}

		public void print() {
			System.out.print("    ");
			for(int i = 0; i < size; i++) {
				System.out.print(" " + i%10);
			}
			System.out.println();
			System.out.print("    ");
			for(int i = 0; i < size; i++) {
				System.out.print(" ̲ ̲");
			}
			System.out.println();
			for(int r = 0; r < size; r++) {
				System.out.format("%02d |", r);
				for(int c = 0; c < size; c++) {
					if(columns[r] == c) {
						System.out.print(" Q");
					} else {
						System.out.print(" .");
					}
				}
				System.out.println("|");
			}
			System.out.print("    ");
			for(int i = 0; i < size; i++) {
				System.out.print("‾‾");
			}
			System.out.println();
		}
		
	}
	
	// My solution runs slower than the book solution, but allows for different numbers of queens to board sizes
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		List<BoardState> states = positionQueens(12,12);
		for(BoardState s : states) {
			s.print();
		}
		System.out.println("Total amount of states: " + states.size());
		List<Integer[]> res = new ArrayList<Integer[]>();
		placeQueensByBook(0, new Integer[GRID_SIZE], res);
		System.out.println("Total amount of states: " + res.size());
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static List<BoardState> positionQueens(int queens, int size) {
		List<BoardState> initStates = new ArrayList<BoardState>();
		List<BoardState> finishedStates = new ArrayList<BoardState>();
		initStates.add(new BoardState(size));
		positionQueens(queens, 0, size, initStates,finishedStates);
		return finishedStates;
	}
    
    public static void positionQueens(int queens, int row, int maxRow, List<BoardState> workingStates, List<BoardState> finishedStates) {
		if(queens == 0) {
			finishedStates.addAll(workingStates);
			return;
		}
		if(row > maxRow) {
			return;
		}
		List<BoardState> nextWorkingStates = new ArrayList<BoardState>();
		for(BoardState s : workingStates) {
			for(int col = 0; col < maxRow; col++) {
				if(s.tryQueen(row, col)) {
					BoardState ns = new BoardState(s);
					ns.putQueen(row, col);
					nextWorkingStates.add(ns);
				}
			}
		}
		if(maxRow - row > queens) {
			positionQueens(queens, row+1, maxRow, workingStates, finishedStates);
		}
		positionQueens(queens-1, row+1, maxRow, nextWorkingStates, finishedStates);
	}
	
	public static void placeQueensByBook(int row, Integer[] columns, List<Integer[]> results) {
		if(row == GRID_SIZE) {
			results.add(columns.clone());
		} else {
			for(int col = 0; col < GRID_SIZE; col++) {
				if(checkValid(columns, row, col)) {
					columns[row] = col; // place queen
					placeQueensByBook(row + 1, columns, results);
				}
			}
		}
	}
	
	private static boolean checkValid(Integer[] columns, int row1, int column1) {
		for(int row2 = 0; row2 < row1; row2++) {
			int column2 = columns[row2];
			
			
			if(column1 == column2) {
				return false;
			}
			
			int columnDist = Math.abs(column2 - column1);
			int rowDist = row1 - row2;
			if(columnDist == rowDist) {
				return false;
			}
		}
		return true;
	}
}
