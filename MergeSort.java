import java.util.Arrays;

public class MergeSort {

	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
//	private static int[] a = {259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
//	private static int[] b = {12, 8, 7, 16, 5, 41, 1, 0, 2};

    public static void main(String[] args) {
//		System.out.println("Before: " + Arrays.toString(a));
//		a = mergeSortRecursive(a);
//		System.out.println("After rec: " + Arrays.toString(a));
		System.out.println("Before: " + Arrays.toString(a));
		a = mergeSortIter(a);
		System.out.println("After iter: " + Arrays.toString(a));
	}
	
	private static int[] mergeSort(int a[]) {
		return null;
	}
	
	private static int[] mergeSortRecursive(int a[]) {
		return mergeSortRecursive(a, new int[0]);
	}

	private static int[] mergeSortRecursive(int a[], int b[]) {
		if(a.length > 1) {
			int result[][] = splitArrays(a);
			a = mergeSortRecursive(result[0], result[1]);
		}
		
		if(b.length > 1) {
			int result[][] = splitArrays(b);
			b = mergeSortRecursive(result[0], result[1]);
		}
		
		return mergeArrays(a,b);
	}
	
	private static int[][] splitArrays(int a[]) {
		int result[][] = new int[2][];
		int halfPoint = a.length/2 + a.length%2;
		result[0] = new int[halfPoint];
		result[1] = new int[a.length/2];
		
		for(int i = 0; i < halfPoint; i++) {
			result[0][i] = a[i];
			if(i+halfPoint < a.length) {
				result[1][i] = a[i+halfPoint];
			}
		}
		
		return result;
	}
	
	private static int[] mergeArrays(int a[], int b[]) {
		int result[] = new int[a.length+b.length];
		int i = 0, j = 0;

		while(i < a.length || j < b.length) {
			if(i >= a.length || (j < b.length && a[i] > b[j])) {
				result[i+j] = b[j];
				j++;
			} else {
				result[i+j] = a[i];
				i++;
			}
		}

		return result;
	}
	
	
	private static int[] mergeSortIter(int[] a) {
		int[] tmp = new int[a.length];
		int size = 1;
		while(size < a.length) {
			for(int i = 0; i < a.length; i += 2*size) {
				int j = 0;
				int k = 0;
				while((j < size || k < size) && j+k+i < a.length) {
					// if both markers are beyond the array, not much more to do
					if((k + i + size) >= a.length && (j + i) >= a.length) {
						break;
					}
					// if the 2nd subarray (k) is beyond, or if we have consumed
					// all the alloted size for this subarray, default to j
					if((k + i + size) >= a.length || k >= size) {
						tmp[j+k+i] = a[j+i];
						j++;
					// if the 1st subarray (j) is beyond, or if we have consumed
					// all the alloted size for this subarray, default to k
					// If none of the conditions above are met, then we do a proper
					// compare to see if a[k] < a[j].
					} else if(j >= a.length || j >= size || a[k+i+size] < a[j+i]) {
						tmp[j+k+i] = a[k+i+size];
						k++;
					// if all conditions above have failed, we grab a[j]
					} else {
						tmp[j+k+i] = a[j+i];
						j++;
					}
				}
				for(j = 0; j < 2*size && j+i < a.length; j++) {
					a[j+i] = tmp[j+i];
				}
			}
			size *= 2;
		}
		return a;
	}
	
}
