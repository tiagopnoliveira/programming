import java.lang.Math;

public class SnakeSequence {
	
	private static class ProcessedCellInfo {
		int maxFound = 0;
		String visitedCells = "";
		
		public ProcessedCellInfo() {}
		
		public ProcessedCellInfo(int maxFound, String visitedCells) {
			this.maxFound = maxFound;
			this.visitedCells = visitedCells;
		}
	}
	
	private static int[][] m = {{1, 2, 1, 2},
								{7, 7, 2, 5},
								{6, 4, 3, 4},
								{1, 2, 2, 5}};
	
	public static ProcessedCellInfo snakeSequence(int[][] m) {
		ProcessedCellInfo res[][] = new ProcessedCellInfo[m.length][m[0].length];
		
		for(int r = 0; r < m.length; r++) {
			for(int c = 0; c < m[0].length; c++) {
				processCell(m, res, r, c);
			}
		}
		
		ProcessedCellInfo lastCell = res[m.length-1][m[0].length-1];
		lastCell.maxFound++;
		return lastCell;
	} 
	
	private static void processCell(int[][] m, ProcessedCellInfo[][] res, int r, int c) {
		ProcessedCellInfo currentCell = new ProcessedCellInfo();
		
		//test upper cell
		if(r > 0 && Math.abs(m[r][c]-m[r-1][c]) == 1) {
			currentCell.visitedCells = res[r-1][c].visitedCells + "(r: " + (r-1) + ", c: " + c + ") ";
			currentCell.maxFound = res[r-1][c].maxFound + 1;
		}
		//test left cell, if bigger replace MaxFound
		if(c > 0 && Math.abs(m[r][c]-m[r][c-1]) == 1 && (res[r][c-1].maxFound + 1) > currentCell.maxFound) {
			currentCell.visitedCells = res[r][c-1].visitedCells + "(r: " + r + ", c: " + (c-1) + ") ";
			currentCell.maxFound = res[r][c-1].maxFound + 1;
		}
		res[r][c] = currentCell;
	}

    public static void main(String[] args) {
		ProcessedCellInfo result = snakeSequence(m);
		System.out.println("Steps: " + result.maxFound);
		System.out.println("Visited: " + result.visitedCells);
    }

}
