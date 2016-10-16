import java.lang.Math;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Queens {
	
	private static class Queen implements Comparable<Queen> {
		int r;
		int c;
		
		public Queen(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Queen other){
			// compareTo should return < 0 if this is supposed to be
			// less than other, > 0 if this is supposed to be greater than 
			// other and 0 if they are supposed to be equal
			if(r < other.r) {
				return -1;
			}
			if(r > other.r || c > other.c) {
				return 1;
			}
			if(c < other.c) {
				return -1;
			}
			return 0;
		}
		
		@Override
		public int hashCode(){
			return (r << 16) ^ c;
		}
		
		@Override
		public boolean equals(Object o) {
			return (o instanceof Queen) && ((Queen)o).r == r && ((Queen)o).c == c;
		}
		
	}
	
	private static class AlreadyTestedException extends Exception {}
	private static class RowUsedException extends Exception {}
	private static class ColUsedException extends Exception {}
	private static class DiagUsedException extends Exception {}
	
	private static class BoardState {
		int size;
		int rows;
		boolean[][] collisions;
		int cols;
		Set<Queen> queens;
		
		public BoardState(int rows, int cols, int size, boolean[][] collisions, Set<Queen> queens) {
			this.rows = rows;
			this.cols = cols;
			this.size = size;
			this.collisions = collisions;
			this.queens = queens;
		}


		public BoardState(int rows, int cols, int size) {
			this(rows,cols,size,new boolean[size][size], new TreeSet<Queen>());
		}
		
		public BoardState(int size) {
			this(0,0,size);
		}
		
		public BoardState() {
			this(8);
		}
		
		public BoardState(BoardState s) {
			this(s.rows, s.cols, s.size, new boolean[s.size][s.size], new TreeSet<Queen>());
			this.copyCollisions(s.collisions);
			this.queens.addAll(s.queens);
		}
		
		public void copyCollisions(boolean[][] collisions) {
			for(int r = 0; r < size; r++) {
				for(int c = 0; c < size; c++) {
					this.collisions[r][c] = collisions[r][c];
				}
			}
		}
		
		public void tryQueen(int row, int col) throws AlreadyTestedException, RowUsedException, ColUsedException, DiagUsedException {
			if(row >= size || col >= size) {
				return;
			}
			if(collisions[row][col]) {
				throw new AlreadyTestedException();
			}
			int r, c;
			r = setBit(rows, row);
			c = setBit(cols, col);
			if(r == rows) {
				collisions[row][col] = true;
				throw new RowUsedException();
			}
			if(c == cols) {
				collisions[row][col] = true;
				throw new ColUsedException();
			}
			if(testConflictDiagonals(row, col)) {
				collisions[row][col] = true;
				throw new DiagUsedException();
			}
		}
		
		public void putQueen(int row, int col) {
			rows = setBit(rows, row);
			cols = setBit(cols, col);
			collisions[row][col] = true;
			queens.add(new Queen(row,col));
		}
		
		private boolean testConflictDiagonals(int row, int col) {
			for(Queen q : queens) {
				if(Math.abs(q.r-row) == Math.abs(q.c-col)) {
					return true;
				}
			}
			
			return false;
		}

		private int setBit(int array, int pos) {
			int mask = 1 << pos;
			return array | mask;
		}
		
		public void print() {
			boolean[][] queensMatrix = new boolean[size][size];
			for(Queen q : queens) {
				queensMatrix[q.r][q.c] = true;
			}
			
			System.out.print("    ");
			for(int i = 0; i < size; i++) {
				System.out.print(" " + i%10 + " ");
			}
			System.out.println();
			System.out.print("    ");
			for(int i = 0; i < size; i++) {
				System.out.print("---");
			}
			System.out.println();
			for(int r = 0; r < size; r++) {
				System.out.format("%02d |", r);
				for(int c = 0; c < size; c++) {
					if(queensMatrix[r][c]) {
						System.out.print(" Q ");
					} else {
						System.out.print(" . ");
					}
				}
				System.out.println("|");
			}
			System.out.print("    ");
			for(int i = 0; i < size; i++) {
				System.out.print("---");
			}
			System.out.println();
		}
		
		@Override
		public boolean equals(Object o) {
			BoardState bs = null;
			if(o instanceof BoardState) {
				bs = (BoardState) o;
			} else {
				return false;
			}
			if(!(bs.rows == rows && bs.cols == cols)) {
				return false;
			}
			return this.queens.equals(bs.queens);
		}
		
		@Override
		public int hashCode() {
			return rows ^ cols;
		}

	}
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		Set<BoardState> states = positionQueens(7,7);
		//~ for(BoardState s : states) {
			//~ s.print();
		//~ }
		System.out.println("Total amount of states: " + states.size());
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static Set<BoardState> positionQueens(int queens, int size) {
		Set<BoardState> initStates = new HashSet<BoardState>();
		initStates.add(new BoardState(size));
		return positionQueens(queens, initStates);
	}
    
    public static Set<BoardState> positionQueens(int n_queens, Set<BoardState> states) {
		if(n_queens == 0) {
			return states;
		}
		Set<BoardState> nextStates = new HashSet<BoardState>();
		for(BoardState s : states) {
			Set<Queen> queens = s.queens;
			rowCycle:
			for(int r = 0; r < s.size; r++) {
				for(int c = 0; c < s.size; c++) {
					try {
						s.tryQueen(r,c);
					} catch (AlreadyTestedException ate) {
						continue;
					} catch (RowUsedException rue) {
						break;
					} catch (ColUsedException cue) {
						continue;
					} catch (DiagUsedException due) {
						continue;
					}
					BoardState ns = new BoardState(s);
					ns.putQueen(r,c);
					nextStates.add(ns);
				}
			}
		}
		return positionQueens(n_queens-1, nextStates);
	}
}
