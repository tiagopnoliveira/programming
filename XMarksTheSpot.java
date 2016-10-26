import java.lang.Math;

public class XMarksTheSpot {
	
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
	
	private static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static String[] board = {".O.",
									 "...",
									 "O.."};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(calculateOptionsArea(board));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static int calculateOptionsArea(String[] board) {
		Position topLeftLandmark = findTopLeftLandmark(board);
		Position bottomRightLandmark = findBottomRightLandmark(board);
		return (Math.abs(bottomRightLandmark.y - topLeftLandmark.y)+1) * (Math.abs(bottomRightLandmark.x - topLeftLandmark.x)+1);
	}
	
	private static Position findTopLeftLandmark(String[] board) {
		for(int i = 0; i < board.length; i++) {
			char[] l = board[i].toCharArray();
			for(int j = 0; j < board.length; j++) {
				if(l[j] == Square.LANDMARK.get()) {
					return new Position(i,j);
				}
			}
		}
		return null;
	}
	
	private static Position findBottomRightLandmark(String[] board) {
		for(int i = board.length-1; i >= 0; i--) {
			char[] l = board[i].toCharArray();
			for(int j = l.length-1; j >= 0; j--) {
				if(l[j] == Square.LANDMARK.get()) {
					return new Position(i,j);
				}
			}
		}
		return null;
	}

}
