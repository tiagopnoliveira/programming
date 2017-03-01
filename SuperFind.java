https://careercup.com/question?id=5172758498508800
public class SuperFind {

	private static int[] a = {3,15};
	private static int[] b = {13,15,16};
	private static int[] c = {40};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		double duration = System.currentTimeMillis() - startTime;
		System.out.println(findCombinations(a,b,c));
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int findCombinations(int[] a, int[] b, int[] c) {
		int res = 0;
		for(int ai = a.length-1; ai >= 0; ai--) {
			for(int ci = 0; ci < c.length; ci++) {
				int k = 0;
				for(int bi = 0; bi < b.length; bi++) {
					if(b[bi] < c[ci] && b[bi] > a[ai]) {
						k++;
					}
				}
				k = countCombinations(k);
				int acNeg = 0;
				if(ai < a.length - 1) {
					acNeg = countCombinations(a.length - ai - 1);
				}
				int ac = countCombinations(a.length - ai);
				int ccNeg = 0;
				if(ci > 0) {
					ccNeg = countCombinations(ci);
				}
				int cc = countCombinations(ci+1);
				res += k * (cc - ccNeg) * (ac - acNeg);
			}
		}
		return res;
	}
    	
	private static int countCombinations(int n) {
		return countCombinations(n,0);
	}
	
	private static int countCombinations(int n, int min) {
		int res = 0;
		for(int i = min+1; i <= n; i++) {
			res += 1+countCombinations(n,i);
		}
		return res;
	}

}
