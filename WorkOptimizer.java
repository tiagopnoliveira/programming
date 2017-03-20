// https://careercup.com/question?id=6303130607157248
public class WorkOptimizer {

	private static int[] a = {2,2,3,7,1};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(getMini(a,2));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static void descendingSort(int[] a) {
		descendingQuickSort(a,0,a.length-1);
	}
	
	private static void descendingQuickSort(int[] a, int s, int e) {
		if(s >= e) {
			return;
		}
		int p = a[e];
		int l = s;
		int r = e;
		while(l <= r) {
			while(a[l] > p) {
				l++;				
			}
			while(a[r] < p) {
				r--;
			}
			if(l <= r) {
				int t = a[l];
				a[l++] = a[r];
				a[r--] = t;
			}
		}
		descendingQuickSort(a,s,r);
		descendingQuickSort(a,l,e);
	}
	
	public static int getMini(int[] a, int k) {
		descendingSort(a);
		int[] workAssigned = new int[k];
		for(int i = 0; i < a.length; i++) {
			int min = getMinPosArray(workAssigned);
			workAssigned[min] += a[i];
		}
		int max = workAssigned[0];
		for(int i = 1; i < k; i++) {
			if(a[i] > max) {
				max = a[i];
			}
		}
		return max;
	}
	
	private static int getMinPosArray(int[] a) {
		int min = 0;
		for(int i = 1; i < a.length; i++) {
			if(a[i] < a[min]) {
				min = i;
			}
		}
		return min;
	}
}
