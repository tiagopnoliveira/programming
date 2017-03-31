import java.util.Arrays;

// https://careercup.com/question?id=5682803648757760
public class NonZeroesToLeft {

	private static int[] a = {1,0,2,0,0,3,4};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		double duration = System.currentTimeMillis() - startTime;
		System.out.println(Arrays.toString(a));
		System.out.println(nonZeroesToLeft(a));
		System.out.println(Arrays.toString(a));
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int nonZeroesToLeft(int[] a) {
		int l = 0;
		int r = a.length-1;
		while(l < r) {
			while(l < a.length && a[l] != 0) {
				l++;
			}
			while(r >= 0 && a[r] == 0) {
				r--;
			}
			if(l < r) {
				swap(a,l++,r--);
			}
		}
		return a.length-l;
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
