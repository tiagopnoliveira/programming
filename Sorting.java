import java.util.Arrays;

public class Sorting {

//	private static int[] a = {2,1};

	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println("Before: " + Arrays.toString(a));
		insertionSort(a);
		System.out.println("After : " + Arrays.toString(a));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static void swapElementsArray(int[] a, int p1, int p2) {
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
		
	}
}
