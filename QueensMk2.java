import java.lang.Math;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class QueensMk2 {
	public static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Pos>> solutions = new ArrayList<ArrayList<Pos>>();
		int s = putQueens(12,12,solutions);
		System.out.println(s);
		int si = 1;
		//~ for(ArrayList<Pos> sol : solutions) {
			//~ int qi = 1;
			//~ System.out.println("Solution #" + si++);
			//~ for(Pos q : sol) {
				//~ System.out.println("Queen #" + qi++ + " ; r: " + q.r + ", c: " + q.c);
			//~ }
			//~ System.out.println();
		//~ }
		
	}
	
	public static int putQueens(int s, int q, ArrayList<ArrayList<Pos>> solutions) {
		ArrayList<Pos> queens = new ArrayList<Pos>();
		return putQueens(s,0,q,queens,solutions);
	}
	
	public static int putQueens(int s, int r, int q, ArrayList<Pos> queens, ArrayList<ArrayList<Pos>> solutions) {
		if(q == 0) {
//			solutions.add(new ArrayList<Pos>(queens));
			return 1;
		}
		if(r >= s || q > s-r) {
			return 0;
		}
		
		int res = 0;
		for(int c = 0; c < s; c++) {
			if(isPositionValid(s,r,c,queens)) {
				ArrayList<Pos> newQueens = new ArrayList<Pos>(queens);
				newQueens.add(new Pos(r,c));
				res += putQueens(s,r+1,q-1,newQueens,solutions);
			}
		}
		return res + putQueens(s,r+1,q,queens,solutions);
	}
	
	private static boolean isPositionValid(int s, int r, int c, ArrayList<Pos> queens) {
		if(r < 0 || c < 0 || r >= s || c >= s) {
			return false;
		}
		for(Pos q : queens) {
			if( q.r == r || q.c == c ||
				Math.abs(r - q.r) == Math.abs(c - q.c)) {
				return false;
			}
		}
		return true;
	}
}
