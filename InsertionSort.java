import java.util.Arrays;

public class InsertionSort {

	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};

    public static void main(String[] args) {
		System.out.println("Before: " + Arrays.toString(a));
		insertionSort(a);
		System.out.println("After: " + Arrays.toString(a));
	}
	
	private static void insertionSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0 && a[j-1] > a[j]; j--) {
				swap(a, j, j-1);
			}
		}
	}
	
	private static void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
}
