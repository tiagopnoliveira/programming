import java.util.*;

public class Jigsaw {
	// boolean fitsWith(Edge e1, Edge e2); <-- returns true if puzzle edges e1 and e2 fit together.
	
	public static class JigsawPuzzle {
		int h;
		int w;
		
		PuzzlePiece[][] solvedPieces;
		
		public JigsawPuzzle(int h, int w) {
			solvedPieces = new PuzzlePiece[h][w];
			for(int r = 0; r < h; r++) {
				for(int c = 0; c < w; c++) {
					PuzzleEdge up, down, left, right;
					if(r == 0) {
						up = new PuzzleEdge(true);
					} else {
						up = new PuzzleEdge(false);
					}
					if(r == h-1) {
						down = new PuzzleEdge(true);
					} else {
						down = new PuzzleEdge(false);
					}
					if(c == 0) {
						left = new PuzzleEdge(true);
					} else {
						left = new PuzzleEdge(false);
					}
					if(c == w-1) {
						right = new PuzzleEdge(true);
					} else {
						right = new PuzzleEdge(false);
					}
					solvedPieces[r][c] = new PuzzlePiece(up,down,left,right);
				}
			}
		}
	}
	
	public static class PuzzlePiece {
		private PuzzleEdge[] edges;
		private static final UP = 0;
		private static final DOWN = 1;
		private static final LEFT = 2;
		private static final RIGHT = 3;
		
		int flatSides;
		
		public PuzzlePiece(PuzzleEdge up, PuzzleEdge down, PuzzleEdge left, PuzzleEdge right) {
			this.edges = new PuzzleEdge[4];
			this.edges[UP] = up;
			this.edges[DOWN] = down;
			this.edges[LEFT] = left;
			this.edges[RIGHT] = right;
			for(int i = 0; i < edges.length; i++) {
				if(edges[i].type == EdgeType.FLAT) {
					flatSides++;
				}
			}
		}
	}
	
	public static class PuzzleEdge {
		private EdgeType type;
		
		public PuzzleEdge(boolean isFlat) {
			if(isFlat) {
				this.type = EdgeType.FLAT;
			} else {
				this.type = EdgeType.OTHER;
			}
		}
	}
	
	public static enum EdgeType {
		FLAT, OTHER;
	}
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here

		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
}
