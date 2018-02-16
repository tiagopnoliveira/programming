import java.util.*;
import java.lang.Math;

public class SumPairsArray {

	private static int[] a = {4,7,3,1,8,2,6,12,-2};
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		returnSumPairs(a,10);

		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static void returnSumPairs(int[] a, int t) {
		HashSet<Integer> foundItems = new HashSet<Integer>();
		for(int i = 0; i < a.length; i++) {
			int v = a[i];
			if(foundItems.contains(t-v)) {
				foundItems.remove(t-v);
				System.out.println("(" + v + "," + (t-v) + ")");
			} else {
				foundItems.add(v);
			}
		}
	}
    
}

