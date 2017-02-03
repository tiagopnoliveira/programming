import java.lang.Math;
import java.util.Arrays;

public class MazeConstruct {
	private static final int MAX_N = 50;
	private static final int MAX_M = 50;
	private static final int MAX_K = 1000;

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		mazeConstruct(1005);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void mazeConstruct(int k) {
		boolean solutionFound[] = new boolean[k+1];
		for(int n = 1; n <= MAX_N; n++) {
			for(int m = 1; m <= MAX_M; m++) {
				for(int ml = 0; ml <= m-1; ml++) {
					if(ml > 0 && ml % 4 != 0) {
						continue;
					}
					for(int nl = 0; nl <= n+1; nl++) {
						if(nl == 1 || nl == 2) {
							continue;
						}
						int r = ((ml/4)*(nl*2)) + (n - 1) + (m - ml - 1);
						if(r == k) {
							printMaze(n,m,nl,ml);
							return;
						}
						if(r <= k) {
							solutionFound[r] = true;
						}
					}
				}
			}
		}
		System.out.println(Arrays.toString(solutionFound));
	}
	
	private static void printMaze(int n, int m, int nl, int ml) {
		String maze[] = new String[m];
		boolean isPassageRight = true;
		for(int r = 0; r < m; r++) {
			StringBuilder rStringb = new StringBuilder();
			boolean inWalledSection = r < ml;
			boolean isWallRow = inWalledSection && (r%2 == 1);
			for(int c = 0; c < n; c++) {
				if(isWallRow) {
					if((c == 0 && !isPassageRight) || ((c == nl - 2) && isPassageRight)) {
						rStringb.append(".");
					} else {
						rStringb.append("#");
					}
					continue;
				}
				if(inWalledSection && (c == nl-1 || r == ml-1)) {
					rStringb.append("#");
				} else {
					rStringb.append(".");
				}
			}
			if(isWallRow) {
				isPassageRight = !isPassageRight;
			}
			maze[r] = rStringb.toString();
		}
		System.out.println("n: " + n + " m: " + m + " nl: " + nl + " ml: " + ml);
		System.out.println();
		for(int i = 0; i < maze.length; i++) {
			System.out.println(maze[i]);
		}
	}

}
