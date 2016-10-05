import java.util.Arrays;

public class TripleStep {

	private static int[] a = {1,2,3};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(countSteps(12, a));
		double duration = (System.currentTimeMillis() - startTime);
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int countSteps(int n, int[] a) {
		int memo[] = new int[n+1];
		Arrays.fill(memo, -1);
		return countSteps(n,a,memo);
	}
    
    private static int countSteps(int n, int[] a, int[] memo) {
		if(n < 0) {
			return 0;
		}
		if(n == 0) {
			return 1;
		}
		int sum = 0;
		for(int i = 0; i < a.length; i++) {
			int pos = n-a[i];
			if(pos < 0) {
				break;
			}
			int res = memo[pos];
			if(res < 0) {
				res = countSteps(pos, a, memo);
				memo[pos] = res;
			}
			sum += res;
		}
		return sum;
	}

}
