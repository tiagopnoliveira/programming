import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class Sorting {

//	private static int[] a = {1,2,1};

	//~ private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7};

//	private static int[] a = {7,3,4,1,2};

    public static void main(String[] args) {
		//int l = 1024;
		int l = Integer.MAX_VALUE/8;
		int a[] = new int[l];
		for(int i = 0; i < l; i++) {
			int rand = randInt(1, 20);
			a[i] = rand;			
		}
		
		//int b[] = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7};

		long startTime = System.currentTimeMillis();
		// Core Function here
		//System.out.println("Before: " + Arrays.toString(a));
		quickSortDualPivot(a);
		//System.out.println("After : " + Arrays.toString(a));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static void swapElementsArray(int[] a, int p1, int p2) {
		if(p1 == p2) {
			return;
		}
		a[p1] ^= a[p2];
		a[p2] ^= a[p1];
		a[p1] ^= a[p2];
	}
    
    // https://en.wikipedia.org/wiki/Bubble_sort
    public static void bubbleSort(int[] a) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length - i - 1; j++) {
				if(a[j] > a[j+1]) {
					swapElementsArray(a,j,j+1);
				}
			}
		}
	}
	
	// https://en.wikipedia.org/wiki/Selection_sort
	public static void selectionSort(int[] a) {
		for(int i = 0; i < a.length; i++) {
			int minFound = i;
			for(int j = i; j < a.length; j++) {
				if(a[j] < a[minFound]) {
					minFound = j;
				}
			}
			if(minFound > i) {
				swapElementsArray(a,i,minFound);
			}
		}
	}

	// https://en.wikipedia.org/wiki/Insertion_sort
	public static void insertionSort(int[] a) {
		for(int i = 1; i < a.length; i++) {
			int val = a[i];
			if(val > a[i-1]) {
				continue;
			}
			int j = i-1;
			while(j >= 0 && val < a[j]) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = val;
		}		
	}

	public static void mergeSort(int[] a) {
		int t[] = new int[a.length];
		mergeSort(a,t,0,a.length-1);
	}
	
	private static void mergeSort(int[] a, int[] t, int s, int e) {
		if(e <= s) {
			return;
		}
		int p = ((e-s)/2) + s;
		
		mergeSort(a,t,s,p);
		
		if(p+1 <= e) {
			mergeSort(a,t,p+1,e);
		}				
		
		int l = s;
		int r = p+1;
		int c = s;
		for(int i = s; i <= e; i++) {
			t[i] = a[i];
		}
		
		while(l <= p && r <= e) {
			if(t[l] > t[r]) {
				a[c] = t[r];
				r++;
			} else {
				a[c] = t[l];
				l++;
			}
			c++;
		}
		
		while(l <= p) {
			a[c] = t[l];
			l++;
			c++;
		}
	}

	public static void quickSort(int[] a) {
		quickSort(a,0,a.length-1);
	}
	
	// http://algs4.cs.princeton.edu/23quicksort/
	public static void quickSortDualPivot(int[] a) {
		quickSortDualPivot(a,0,a.length-1);
	}
	
	private static int quickSortPivotSelect(int[] a, int s, int e) {
		return e + ((s-e) / 2);
	}
	
	private static void quickSort(int[] a, int s, int e) {
		int l = s;
		int r = e;
		int p = a[quickSortPivotSelect(a,s,e)];
		
		while(l <= r) {
			while(a[l] < p) {
				l++;
			}
			while(a[r] > p) {
				r--;
			}
			if(l <= r) {
				swapElementsArray(a,l,r);
				l++;
				r--;
			}
		}
		if(s < r) {
			quickSort(a,s,r);
		}
		if(l < e) {
			quickSort(a,l,e);
		}
	}
	

	private static void quickSortDualPivot(int[] a, int s, int e) {
		if(s >= e) {
			return;
		}
		int p1 = a[s];
		int p2 = a[e];
		if(p1 > p2) {
			swapElementsArray(a,s,e);
			p1 = a[s];
			p2 = a[e];
		}
		
		int l = s+1;
		int i = s+1;
		int r = e-1;

		while(i <= r) {
			if(a[i] < p1) {
				swapElementsArray(a,i++,l++);
			} else if(p2 < a[i]) {
				swapElementsArray(a,i,r--);
			} else {
				i++;
			}
		}
		
		swapElementsArray(a, s, --l);
        swapElementsArray(a, e, ++r);

		quickSortDualPivot(a,s,l-1);
		if(a[l] < a[r]) {
			quickSortDualPivot(a,l+1,r-1);
		}
		quickSortDualPivot(a,r+1,e);
	}
	
	private static int randInt(int min, int max) {
		int rand = ThreadLocalRandom.current().nextInt(min,max);
		return rand;
	}
}
