import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CoinCalc {

	private static final int[] v = {25,10,5,1};
	private static final int n = 1000;

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Set<Integer[]> res = coinCalcIt(n);
		System.out.println("Total results using iterative method: " + res.size());
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time for iterative method: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
		System.out.println();
		startTime = System.currentTimeMillis();
		res = calcCoinsRec(n);
		duration = System.currentTimeMillis() - startTime;
		System.out.println("Total results using recursive method: " + res.size());
		System.out.print("Processing time for recursive method: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
		System.out.println();
    }
    
    public static Set<Integer[]> coinCalcIt(int n) {
		Set<Integer[]> res = new HashSet<Integer[]>();
		Integer[] c = new Integer[v.length];
		Arrays.fill(c,0);
		res.add(c);
		for(int i = 0; i < v.length; i++) {
			Set<Integer[]> newRes = new HashSet<Integer[]>();
			for(Integer[] cIt : res) {
				int r = n - calcCoinStack(cIt);
				for(int j = 0; j <= (r/v[i]); j++) {
					Integer[] nc = copyIntegers(cIt);
					nc[i] = j;
					if(i == (v.length - 1)) {
						if(r == j*v[i]) {
							newRes.add(nc);
							break;
						} else {
							continue;
						}
					}
					newRes.add(nc);
				}
			}
			res = newRes;
		}
		
		return res;
	}
	
	private static int calcCoinStack(Integer[] coinStack) {
		int r = 0;
		for(int i = 0; i < v.length; i++) {
			r += coinStack[i] * v[i];
		}
		return r;
	}
	
	private static Integer[] copyIntegers(Integer[] source) {
		Integer[] copy = new Integer[source.length];
		for(int i = 0; i < source.length; i++) {
			copy[i] = source[i];
		}
		return copy;
	}
	
    public static Set<Integer[]> calcCoinsRec(int n) {
		Set<Integer[]> result = new HashSet<Integer[]>();
		Integer[] c = new Integer[v.length];
		Arrays.fill(c,0);
		calcCoinsRec(n,c,result);
		return result;
	}
	
	public static void calcCoinsRec(int n, Integer[] c, Set<Integer[]> result) {
		if(n == 0) {
			result.add(c);
			return;
		}
		if(n < 0) {
			return;
		}
		for(int i = 0; i < v.length; i++) {
			boolean noSmallerCoins = true;
			for(int j = i+1; j < v.length; j++) {
				noSmallerCoins &= c[j] == 0;
			}
			if(noSmallerCoins) {
				Integer[] newC = new Integer[v.length];
				for(int j = 0; j < v.length; j++) {
					newC[j] = c[j];
				}
				newC[i]++;
				calcCoinsRec(n-v[i],newC,result);
			}
		}
	}
	
}
