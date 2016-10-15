import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CoinCalc {

	private static final int[] v = {10,8};
	private static final int n = 100;

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
		res = coinCalcRec(n);
		//~ for(Integer[] i : res) {
			//~ System.out.println(Arrays.toString(i));
		//~ }
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
	
	public static Set<Integer[]> coinCalcRec(int n) {
		Integer[] c = new Integer[v.length];
		Arrays.fill(c,0);
		Set<Integer[]> res = new HashSet<Integer[]>();
		int maxVal = 0;
		for(int i = 0; i < v.length; i++) {
			if(v[i] > maxVal) {
				maxVal = v[i];
			}
		}
		coinCalcRecAux(res, n, c, maxVal);
		return res;
	}
	
	private static void coinCalcRecAux(Set<Integer[]> res, int n, Integer[] c, int maxVal) {
		if(n < 0) {
			return;
		}
		if(n == 0) {
			res.add(c);
			return;
		}
		for(int i = 0; i < v.length; i++) {
			//~ if(v[i] > maxVal) {
				//~ continue;
			//~ }
			Integer[] newC = copyIntegers(c);
			newC[i]++;
			coinCalcRecAux(res, n-v[i], newC, v[i]);
		}
	}
	
}
