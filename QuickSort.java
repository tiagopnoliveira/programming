import java.util.Arrays;

public class QuickSort {

//	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
//	private static int[] a = {259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
	private static int[] a = {7,18,4,9,1,6};

    public static void main(String[] args) {
//		System.out.println("Before: " + Arrays.toString(a));
//		a = mergeSortRecursive(a);
//		System.out.println("After rec: " + Arrays.toString(a));
		System.out.println("Before: " + Arrays.toString(a));
		quickSortClassic(a, 0, a.length-1);
		System.out.println("After iter: " + Arrays.toString(a));
	}

	public static void quickSortClassic(int[] a, int start, int end) {
		if(start < end) {
			int p = partition(a, start, end);
			quickSortClassic(a, start, p-1);
			quickSortClassic(a, p+1, end);
		}
	}
	
	private static int partition(int[] a, int start, int end) {
		System.out.println("Analyzing partition: " + Arrays.toString(a) + ". Start is " + start + ". End is: " + end + ".");
		int pivot = a[end];
		int i = start;
		for(int j = start; j < end; j++) {
			if(a[j] <= pivot) {
				swapItems(a,i++,j);
			}
		}
		swapItems(a,i,end);
		return i;
	}
	
	private static void swapItems(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
}
