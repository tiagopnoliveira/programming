import java.lang.Math;

// https://www.careercup.com/question?id=5746456070717440

public class HigestNumDivisibleByThree {

	private static int[] a = {3,1,4,1,5,9,3,1,4,1,5,5,5};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(getHighestDiv3(a));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static long getHighestDiv3(int[] a) {
		int t[] = new int[10];
		int i = 0;
		while(i < a.length) {
			t[a[i++]]++;
		}
		return getHighestDiv3(t,0,i);
	}
	
	private static long getHighestDiv3(int[] a, long v, int n) {
		if(n == 0) {
			if(v%3==0) {
				return v;
			}
			return 0;
		}
		v *= 10;
		n--;
		long maxFound = 0;
		for(int i = 9; i >= 0; i--) {
			if(a[i] > 0) {
				int t[] = copyArray(a);
				t[i]--;
				long res = getHighestDiv3(t,v+i,n);
				if(res > 0 && res > maxFound) {
					maxFound = res;
				}
			}
		}
		if(maxFound >= Math.pow(10,n)) {
			return maxFound;
		}
		return getHighestDiv3(a,v/10,n);
	}
	
	private static int[] copyArray(int[] a) {
		int t[] = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			t[i] = a[i];
		}
		return t;
	}
}
