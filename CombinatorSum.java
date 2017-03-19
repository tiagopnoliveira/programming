import java.util.HashSet;

// https://careercup.com/question?id=5662358396469248
public class CombinatorSum {

	private static int[] a = {6,9,20};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(combinatorSum(a,47));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }

    public static boolean combinatorSum(int[] a, int n) {
		HashSet<Integer> memo = new HashSet<Integer>();
		return combinatorSum(a,n,memo);
	}

    // TODO: Find a more efficient way of doing this. Worst time is exponencial
    public static boolean combinatorSum(int[] a, int n, HashSet<Integer> memo) {
		if(n < 0 || memo.contains(n)) {
			return false;
		}
		if(n == 0) {
			return true;
		}
		boolean res = false;
		for(int i = 0; (i < a.length) && !res ; i++) {
			res |= combinatorSum(a,n-a[i]);
		}
		if(!res) {
			memo.add(n);
		}
		return res;
	}

}
