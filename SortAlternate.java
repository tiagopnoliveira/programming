import java.util.Arrays;

public class SortAlternate {

	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(Arrays.toString(a));
		sortAlternate(a);
		System.out.println(Arrays.toString(a));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void sortAlternate(int[] a) {
		for(int i = 0; i < a.length; i++) {
			int p = i;
			for(int j = i+1; j < a.length; j++) {
				if((i % 2 == 0 && a[j] > a[p]) || (i % 2 == 1 && a[j] < a[p])) {
					p = j;
				}
			}
			swap(a,i,p);
		}
	}
	
	private static void swap(int[] a, int p1, int p2) {
		if(p1 == p2) {
			return;
		}
		int t = a[p1];
		a[p1] = a[p2];
		a[p2] = t;
	}
}
