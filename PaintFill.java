import java.util.*;
import java.lang.Math;

public class PaintFill {
	private static int[] r0 = {1,0,1,1,0,0,1};
	private static int[] r1 = {0,0,1,1,1,1,1};
	private static int[] r2 = {0,0,1,0,0,0,0};
	private static int[] r3 = {1,1,1,1,1,1,1};
	private static int[] r4 = {1,1,1,1,1,1,1};
	private static int[] r5 = {1,0,0,0,0,0,1};
	private static int[] r6 = {0,1,1,1,0,0,1};
	
	private static int[][] a = {r0,r1,r2,r3,r4,r5,r6};
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println("Before: ");
		printArray(a);
		//~ fillPaint(a,2,3,3);
		fillPaint(a,2,1,0);
		System.out.println("After: ");
		printArray(a);
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static void printArray(int[][] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
	}
    
    public static void fillPaint(int[][] a, int n, int r, int c) {
		if(!inBound(a,r,c) || a[r][c] == n) {
			return;
		}
		int o = a[r][c];
		fillPaint(a,n,o,r,c);
	}
	
	private static void fillPaint(int[][] a, int n, int o, int r, int c) {
		if(!inBound(a,r,c) || a[r][c] != o) {
			return;
		}
		a[r][c] = n;
		fillPaint(a,n,o,r+1,c);
		fillPaint(a,n,o,r-1,c);
		fillPaint(a,n,o,r,c+1);
		fillPaint(a,n,o,r,c-1);
	}
	
	private static boolean inBound(int[][] a, int r, int c) {
		return r >= 0 && c >= 0 && r < a.length && c < a[0].length;
	}
}
