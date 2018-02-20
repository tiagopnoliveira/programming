import java.util.*;
import java.lang.Math;

public class ContiguousSequence {
	static int[] a = {-8,-6,3,-10,-9,4,-9,4,-9,2,-1,-14,-10};
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here

		int[] r = getLargestContigousSumSequence(a);
		System.out.println(Arrays.toString(r));
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static int[] getLargestContigousSumSequence(int[] a) {
		ArrayList<Integer> positiveIndexes = new ArrayList<Integer>();
		int maxFound = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] > 0) {
				positiveIndexes.add(i);
			}
			if(a[i] > a[maxFound]) {
				maxFound = i;
			}
		}
		if(positiveIndexes.size() <= 1) {
			int[] r = new int[1];
			r[0] = a[maxFound];
			return r;
		}
		
		int bestStart = maxFound;
		int bestEnd = maxFound;
		int bestSum = a[maxFound];
		for(int i = 0; i < positiveIndexes.size(); i++) {
			int s = positiveIndexes.get(i);
			int sum = a[s];
			for(int j = i+1; j < positiveIndexes.size(); j++) {
				int e = positiveIndexes.get(j);
				for(int k = s; k <= e; k++) {
					sum += a[k];
				}
				if(sum > bestSum) {
					bestSum = sum;
					bestStart = s;
					bestEnd = e;
				}
			}
		}
		int[] r = new int[bestEnd-bestStart+1];
		for(int i = bestStart; i <= bestEnd; i++) {
			r[i-bestStart] = a[i];
		}
		return r;
	}

}
