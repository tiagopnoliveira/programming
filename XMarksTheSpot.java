import java.lang.Math;

public class XMarksTheSpot {
	
	// https://community.topcoder.com/stat?c=problem_statement&pm=14416
	
	public static enum Square {
		EMPTY ('.'),
		UNNKOWN ('?'),
		LANDMARK ('O');
		
		private final char c;
		
		private Square(final char c) {
			this.c = c;
		}
		
		@Override
		public String toString() {
			return String.valueOf(c);
		}

		public char get() {
			return c;
		}
	}
	
	private static class Area {
		int top;
		int bottom;
		int left;
		int right;
		
		public Area(int t, int b, int l, int r) {
			this.top = t;
			this.bottom = b;
			this.left = l;
			this.right = r;
		}
		
		public Area(char[][] board) {
			this(Area.findTopLandmark(board),
				 Area.findBottomLandmark(board),
				 Area.findLeftLandmark(board),
				 Area.findRightLandmark(board));
		}

		public int calculateArea() {
			return (bottom - top + 1) * (right - left + 1);
		}
		
		public boolean isPosInsideArea(int r, int c) {
			return r >= top && r <= bottom && c >= left && c <= right;
		}
		
		private static int findTopLandmark(char[][] board) {
			for(int r = 0; r < board.length; r++) {
				for(int c = 0; c < board.length; c++) {
					if(board[r][c] == Square.LANDMARK.get()) {
						return r;
					}
				}
			}
			return -1;
		}

		private static int findLeftLandmark(char[][] board) {
			int r = 0;
			for(int c = 0; c < board[r].length; c++) {
				while(r < board.length) {
					if(board[r++][c] == Square.LANDMARK.get()) {
						return c;
					}
				}
				r = 0;
			}
			return -1;
		}
		
		private static int findRightLandmark(char[][] board) {
			int r = board.length-1;
			for(int c = board[r].length-1; c >= 0; c--) {
				while(r >= 0) {
					if(board[r--][c] == Square.LANDMARK.get()) {
						return c;
					}
				}
				r = board.length-1;
			}
			return -1;
		}
		
		private static int findBottomLandmark(char[][] board) {
			for(int r = board.length-1; r >= 0; r--) {
				for(int c = board[r].length-1; c >= 0; c--) {
					if(board[r][c] == Square.LANDMARK.get()) {
						return r;
					}
				}
			}
			return -1;
		}
	}

	private static String[] board1 = {".O.",
									  "...",
									  "O.."};

	private static String[] board2 = {".O...",
									  ".....",
									  "O...?"};
	
	// 243200
	private static String[] board3 = {".O.O.OO....OO.O.OOO....OOO.OO..O.O.OO.", "O.O.OO.OO.O.......OO...O..O....O.OO..O", ".OOO.OO.O..O.O.OO.....O.O.O......OOOOO", ".O...O.OOO.O...OO..OOOOOOOOOO.O.OO.O.O", "O..O.OOOOO..OOOO.O.O?OOO.OOO..OOOO.OO.", "...O....O..OO.OOO..O.O.OOOOOO.OOO.OOO.", "OOOO.OOOO..OOOOO.OO.OO..OOOOO.O..OOO.O", ".O.O.OOO...O.OOOOOO.OOOO.OOOOOO...O.O.", ".OO.O...O.O.OO.OO..O..OO..OO.OO...O.OO", "..OO.OO...OOOO.OOOOO....OOO.O.OOOOOOOO", "O.OOO...OOO.OO..O..OOO..O...O...OOOOO.", "OO.OOOO..OOO.OO....OOOOOOOOOO.OOOO.O..", "OO...OOO.OOO.O.O.OO.OO.O.OO..O.O.OO.O.", "OO....OO.OOOOOOOOO...OO.O.O..OO..OOOO.", "..O..OOO..OO....OO.....O.O.OO.OOOO.OO.", ".OOO.OOOOOOO..OOOO.O.OOO.O..OO.O.OOOO.", "OOOO..O.O.OOOO.O..OO..O.O.OOO..OO..OOO", ".OOO.O..OOOOOO.OO.OOOOOOO.O..O...OO...", ".OOOO.O....OO..OOOO...?OO..OOOO..OO...", "OOOOOOOOOOO.OOOOOOOO.OOOO.....OO....OO", "O..O..O..O.OOO.OO.OOO..O.OO....OO..O.O", "O..O..O.OOO.OOO.O.O.OOOOO...O.OOO.O...", "OOO.O..OOO.OOO.OO.....OO.OOO..OO.O.OOO", "..OOOO.OO.O....OOO.OOOOOOOOOOO..O.O..O", ".?O.?.O.OO..OOOO..OO...O..OOOOOOO.O...", "?O..O..O.O.....?O.OOOOO.O.OOOO.O...OOO", "....OOOOOO..O.OO.OO.....OO.OOOO..OO.OO", "O.OOOOO.O.O.OO.........OO.....OOO.OOOO", "O.OO..OOOOOOO.OOOO.O..OOO.OO..OO.OOOOO", ".OOOOOOOO...O.O..OOOO.OOO...O.OOOOOOO.", "OO...OOOO.O.O..O..O.....OOOOOO.O..OOO.", ".O.OOO.O.O..O.OOOOO...O.OOO...O..O.OO.", "O..O..OO.OOOO.OOOO..OOOOO.OOOO.OO.O.O.", ".OOOO..OOOOOO.OO..O.OOO....OO.O..OO.O.", "O.OO..OO.OOO.O.OOO...OOOO.O.O..O..O.OO", "..OOO.OOOOOOO...OOOOOOO.O...OO..O.OOO.", ".OOOOO.OO....OO.OO..O.OO.O....O.OO.OO.", "OOOOO.O.OO.O.....OOO..OOOO.OO.O.......", "O.OOOOO..O...OOOO...O...O.O.O...OOOOO.", "O..OOOOO.OO..OOO.OO.OOOO..OO.O.O..O.OO", "O..OOOOO..O..O....O.O.O..OOOOO..OOO..O", "O.OOO.O.OOO....OOOOO....OOOOO....OO..O", "OOO.OO..OOOOOOO.O..O.O.OOO.O.O.O.O.OOO", "....O.OOO.O...O.OO.O.O.OOO...O.OOOOOO.", "..OOOO.O.OO.O.O..OOOO...O.OOO.OOOOOO.O", ".OO.OOOOO..OOOO...OOOO.OOOOO.O.OOO.O.O", "...OO...O.O..OO..OOO.OO.OOO.O.O...OOOO", "OOOOOOO..OOOOO..O.O.OOOOO.O.OO.O.OOO.O", "..OOOOOO.OOO...OOO.O..OO..OOOOO.OOOO?.", "O.O.O..OOOOOOOO.O.O.O.....OOOOO.OO...O"};
	
	// 60555264
	private static String[] board4 = {"...OO........O", ".OO.....O.O...", ".O.O...O......", ".O?..OOOO.O?..", ".O.O....O..O.O", "..OOO........O", "O...O..O...O..", "O...O....OOOOO", "OO.O.OO?....O.", "OO...?...OO?..", "..........OOOO", "O.O.O.........", ".......O.O..O.", ".O...?.OOOOOO.", ".O.O?OO...O..O", ".OOOOOOO....O.", "OO....O?O.....", "...O.O.O......", "..OO.O.....OOO", "OO.O...O..O...", "..?OO.?O.....O", "O.O.OO.O.....O", "......OOO.O...", ".O.OOO.O....O.", "O.OO....OO....", "OOO..O..O?O..O", ".O..O.?.?O.O.O", "OO.OO.....?...", "..OO....O....O", "OO.?.....?O...", "OO.........OO.", "..?...O.OO.O.O", "OO..OOO.....OO"};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(xMarksTheSpot(board4));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static int xMarksTheSpot(String[] board) {
		if(board.length == 0 || board[0].length() == 0) {
			return 0;
		}
		char[][] b = makeCharMatrix(board);
		Area a = new Area(b);
		return xMarksTheSpotAux(0,0,a,b,a.calculateArea());
	}
    
    private static int xMarksTheSpotAux(int r, int c, Area a, char[][] b, int acc) {
		if(r >= b.length) {
			return acc;
		}
		if(c >= b[r].length) {
			return xMarksTheSpotAux(r+1, 0, a, b, acc);
		}
		int mult = 1;
		if(b[r][c] == Square.UNNKOWN.get()) {
			if(a.isPosInsideArea(r,c)) {
				mult = 2;
			} else {
				char tmp = b[r][c];
				b[r][c] = Square.LANDMARK.get();
				Area newArea = new Area(b);
				b[r][c] = tmp;
				acc += newArea.calculateArea();
			}
		}
		return mult*xMarksTheSpotAux(r, c+1, a, b, acc);
	}
    
    private static char[][] makeCharMatrix(String[] board) {
		if(board.length == 0) {
			return null;
		}
		char[][] res = new char[board.length][0];
		for(int r = 0; r < board.length; r++) {
			res[r] = board[r].toCharArray();
		}
		return res;
	}
    
}
