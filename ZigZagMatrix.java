import java.lang.Math;

public class ZigZagMatrix {

	private static int[][] a = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16} };
	private static int[][] b = { {1,2}, {3,4}, {5,6}, {7,8}, {9,10}};
	private static int[][] c = { {1,2,3,4,5,6,7,8}, {9,10,11,12,13,14,15,16} };
	
	private static class Cursor {
		int row;
		int col;
		int rows;
		int cols;
		
		public Cursor(int rows, int cols) {
			this.rows = rows;
			this.cols = cols;
			this.row = 0;
			this.col = 0;
		}
		
		public boolean next() {
			if(col+1 < cols) {
				col++;
				return true;
			}
			if(row+1 < rows) {
				row++;
				return true;
			}
			return false;
		}
	}

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		zigzagMatrix(b);
		zigzagMatrix(c);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void zigzagMatrix(int[][] m) {
		Cursor cursor = new Cursor(m.length,m[0].length);
		while(true) {
			int r = cursor.row;
			int c = cursor.col;
			while(r < m.length && c >= 0) {
				System.out.print(m[r][c] + " ");
				r++;
				c--;
			}
			if(!cursor.next()) {
				break;
			}
		}
		System.out.println();
	}
}
