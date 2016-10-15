import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class Queens {
	
	private static class ChessBoard {
		int board;
		List<Queen> queens;
		
		public ChessBoard(int n, List<Queen> queens) {
			this.board = n;
			this.queens = queens;
		}
		
		public boolean isConflicting() {
			for(int i = 0; i < queens.size(); i++) {
				for(int j = i+1; j < queens.size(); j++) {
					if(queens.get(i).isConflicting(queens.get(j))) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	private static class Queen {
		int r;
		int c;
		
		public Queen(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean isConflicting(Queen q) {
			return q.r == r || q.c == c || Math.abs(q.r-r) == Math.abs(q.c-c);
		}
		
	}
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		ChessBoard startingBoard = new ChessBoard(12, new ArrayList<Queen>());
		List<List<Queen>> res = positionQueens(startingBoard, 12);
		for(List<Queen> combo : res) {
			System.out.print("Combination: ");
			for(Queen q : combo) {
				System.out.print("(" + q.r + "," + q.c + "), ");
			}
			System.out.println();
		}
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static List<List<Queen>> positionQueens(ChessBoard cb, int n_queens) {
		List<List<Queen>> res = new ArrayList<List<Queen>>();
		for(int r = 0; r < cb.board; r++) {
			for(int c = 0; c < cb.board; c++) {
				// boards are symetrical, so let's just try half of it (plus the central positions if board is uneven)
				if(r+c >= cb.board) {
					break;
				}
				Queen startingQueen = new Queen(r,c);
				List<Queen> resCandidate = new ArrayList<Queen>();
				resCandidate.add(startingQueen);
				res.add(resCandidate);
			}
		}
		n_queens--;
		while(n_queens-- > 0) {
			List<List<Queen>> nextResCandidate = new ArrayList<List<Queen>>();
			for(List<Queen> resCandidate : res) {
				List<Queen> possibleNextQueens = positionNextQueen(resCandidate.get(resCandidate.size()-1), cb.board);
				for(Queen q : possibleNextQueens) {
					List<Queen> nextUntestedCandidate = new ArrayList<Queen>();
					nextUntestedCandidate.addAll(resCandidate);
					nextUntestedCandidate.add(q);
					cb.queens = nextUntestedCandidate;
					if(!cb.isConflicting()) {
						nextResCandidate.add(nextUntestedCandidate);
					}
				}
			}
			System.out.println("res: " + nextResCandidate.size());
			res = nextResCandidate;
		}
		return res;
	}
	
	private static List<Queen> positionNextQueen(Queen q, int board) {
			int r = q.r;
			int c = q.c;
			List<Queen> res = new ArrayList<Queen>();
			if(r+2 < board && c+1 < board) {
				res.add(new Queen(r+2, c+1));
			}
			if(r+1 < board && c+2 < board) {
				res.add(new Queen(r+1, c+2));
			}

			if(r+2 < board && c-1 >= 0) {
				res.add(new Queen(r+2, c-1));
			}
			if(r+1 < board && c-2 >= 0) {
				res.add(new Queen(r+1, c-2));
			}

			if(r-2 >= 0 && c+1 < board) {
				res.add(new Queen(r-2, c+1));
			}
			if(r-1 >= 0 && c+2 < board) {
				res.add(new Queen(r-1, c+2));
			}
			
			if(r-2 >= 0 && c-1 >= 0) {
				res.add(new Queen(r-2, c-1));
			}
			if(r-1 >= 0 && c-2 >= 0) {
				res.add(new Queen(r-1, c-2));
			}
			return res;
		}


}
