import java.util.Arrays;

public class SparseMatrixMultiply {

	private static double[][] a = {{1,0,0,1,0}, {0,0,1,0,0}, {1,0,1,0,1}, {0,0,0,1,0}, {0,1,1,0,0} };
	private static double[][] b = {{1,0,0,0,0}, {0,0,0,1,0}, {0,1,0,0,0}, {1,0,1,1,0}, {1,1,1,1,1} };

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		double res[][] = sparseMatrixMultiply(a,b);
		for(int i = 0; i < res.length; i++) {
			System.out.println(Arrays.toString(res[i]));
		}
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static double[][] sparseMatrixMultiply(double[][] a, double[][] b) {
		if(a.length == 0 || b.length == 0 || b.length != a[0].length) {
			return null;
		}
		double res[][] = new double[b.length][a[0].length];
		for(int r = 0; r < b.length; r++) {
			for(int c = 0; c < a[0].length; c++) {
				if(a[r][c] == 0) {
					continue;
				}
				injectVector(res,a,b,r,c);
			}
		}
		return res;
	}
	
	public static void injectVector(double[][] res, double[][] a, double[][] b, int r, int c) {
		for(int i = 0; i < a[c].length; i++) {
			res[r][i] += a[r][c] * b[c][i];
		}
	}

}
