public class BiggestSumSubMatrix {
	
	private static class Solution {
		int sR;
		int sC;
		int eR;
		int eC;
		int sum;
		
		public Solution() {
			this.sR = -1;
			this.sR = -1;
			this.sC = -1;
			this.eR = -1;
			this.eC = -1;
			this.sum = 0;
		}
		
		public void update(int sR, int sC, int eR, int eC, int sum) {
			if(sum > this.sum) {
				this.sR = sR;
				this.sC = sC;
				this.eR = eR;
				this.eC = eC;
				this.sum = sum;
			}
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Starting Row: " + this.sR);
			sb.append("\nStarting Col: " + this.sC);
			sb.append("\nEnding Row: " + this.eR);
			sb.append("\nEnding Col: " + this.eC);
			sb.append("\nTotal sum: " + this.sum);
			return sb.toString();
		}
	}

	private static int[][] M = { {3,-1, 5}, {2, 1, -4}, {1, -6, 21} };

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		Solution s = biggestSumSubMatrix(M);
		System.out.println(s.toString());
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    // O(n^4)
    private static Solution biggestSumSubMatrix(int[][] M) {
		Solution solution = new Solution();
		if(M.length == 0) {
			return solution;
		}
		
		int n = Math.max(M.length,M[0].length);
		int[][][][] dp = new int[M.length][M[0].length][M.length][M[0].length];

		// startup DP with 
		for(int r = 0; r < M.length; r++) {
			for(int c = 0; c < M[r].length; c++) {
				dp[r][c][r][c] = M[r][c];
			}
		}		
		
		for(int dist = 1; dist < n; dist++) {
			for(int sR = 0; sR < n; sR++) {
				for(int sC = 0; sC < n; sC++) {
					int accRow = 0;
					int accCol = 0;
					for(int i = 0; i < dist; i++) {
						if(sR+i < M.length && sC+dist < M[sR+i].length) {
							accCol += M[sR+i][sC+dist];							
							dp[sR][sC][sR+i][sC+dist] = dp[sR][sC][sR+i][sC+dist-1] + accCol;
							solution.update(sR,sC,sR+i,sC+dist,dp[sR][sC][sR+i][sC+dist]);
						}
						if(sR+dist < M.length && sC+i < M[sR+i].length) {
							accRow += M[sR+dist][sC+i];							
							dp[sR][sC][sR+dist][sC+i] = dp[sR][sC][sR+dist-1][sC+i] + accRow;
							solution.update(sR,sC,sR+dist,sC+i,dp[sR][sC][sR+dist][sC+i]);
						}						
					}
					if(sR+dist < M.length && sC+dist < M[sR+dist].length) {
						dp[sR][sC][sR+dist][sC+dist] = 	dp[sR][sC][sR+dist-1][sC+dist-1] +
														accRow + accCol + M[sR+dist][sC+dist];
						solution.update(sR,sC,sR+dist,sC+dist,dp[sR][sC][sR+dist][sC+dist]);
					}
				}
			}
		}
		
		return solution;
	}

}
