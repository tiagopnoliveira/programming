import java.util.*;
import java.lang.Math;

public class FlipBit {
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		System.out.println(flipBit(1775)); // exp 8

		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static int flipBit(int n) {
		if(n == 0) {
			return 1;
		}
		int[] a = createIntBitArray(n);
		int[] bestResultArea = getBestResultArea(a);
		if(bestResultArea[1] - bestResultArea[0] < a.length) {
			if(bestResultArea[0] > 0) {
				a[bestResultArea[0]-1] = 1;
			} else {
				a[bestResultArea[1]+1] = 1;
			}
			bestResultArea = getBestResultArea(a);
		}
		return bestResultArea[1] - bestResultArea[0] + 1;
	}
	
	private static int[] getBestResultArea(int[] a) {
		int bestResult = 0;
		int curResult = 0;
		int[] bestArea = new int[2];
		int curStart = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] == 1) {
				if(curResult == 0) {
					curStart = i;
				}
				curResult++;
			} else {
				if(curResult > bestResult) {
					bestArea[0] = curStart;
					bestArea[1] = i-1;
					bestResult = curResult;
				}
				curResult = 0;
			}
		}
		if(curResult > bestResult) {
			bestArea[0] = curStart;
			bestArea[1] = a.length-1;
		}
		return bestArea;
	}
	
	private static int[] createIntBitArray(int n) {
		ArrayList<Integer> resultArray = new ArrayList<Integer>();
		while(n != 0) {
			resultArray.add(n & 1);
			n >>= 1;
		}
		int[] r = new int[resultArray.size()];
		for(int i = 0; i < r.length; i++) {
			r[i] = resultArray.get(i);			
		}
		return r;
	}
}
