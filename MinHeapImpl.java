import java.util.Arrays;

public class MinHeapImpl {
	
	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
	
	private static int size;
	private static int position;
	private static int[] minHeap;
	
//	Start stor­ing from index 1, not 0.
//	For any given node at posi­tion i:
//		Its Left Child is at [2*i] if available.
//		Its Right Child is at [2*i+1] if available.
//		Its Par­ent Node is at [i/2]if avail­able.

	public static void initMinHeap(int[] a) {
		minHeap = new int[a.length + 1];
		for(int i = 0; i < a.length; i++) {
			insert(a[i]);
		}
	}
	
	public static void insert(int item) {
		minHeap[++position] = item;
		bubbleUp();
	}
	
	public static int removeMin() {
		int min = minHeap[1];		
		minHeap[1] = minHeap[position];
		minHeap[position--] = 0;
		sinkDown();
		return min;
	}
	
	private static void swap(int p1, int p2) {
			int tmp = minHeap[p1];
			minHeap[p1] = minHeap[p2];
			minHeap[p2] = tmp;
	}
	
	private static void bubbleUp() {
		int p = position;
		while(minHeap[p] < minHeap[p/2]) {
			swap(p, p/2);
			p /= 2;
		}
	}
	
	private static void sinkDown() {
		int p = 2;
		while (p <= position) {
			int minPos = p;
			if(p+1 <= position && minHeap[p+1] < minHeap[minPos]) {
				minPos = p+1;
			}
			if(minHeap[minPos] < minHeap[p/2]) {
				swap(p/2, minPos);
				p = minPos * 2;
			} else return;
		}
	}

    public static void main(String[] args) {
		System.out.println("Before: " + Arrays.toString(a));
		initMinHeap(a);
		System.out.println("After: " + Arrays.toString(minHeap));
		for(int i = 0; i < 50; i++) {
			System.out.println("Min #" + i + ": " + removeMin());
		}
		insert(57); insert(17); insert(499);
		System.out.println("End: " + Arrays.toString(minHeap));
	}
}
