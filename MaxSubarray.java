import java.util.Arrays;

public class MaxSubarray {

	private static int[] a = {-2,1,-3,4,-1,2,1,-5,4};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println("Base array: " + Arrays.toString(a));
		int[] maxSubarray = maxSubarray(a);
		int sum = calculateArraySum(maxSubarray);
		System.out.println("Max array found: " + Arrays.toString(maxSubarray));
		System.out.println("Total sum: " + sum);
		double duration = (System.currentTimeMillis() - startTime) / 1000;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration);
		System.out.println(" seconds.");
    }
    
    public static int[] maxSubarray(int[] a) {
		if(a.length == 0) {
			int[] res = new int[0];
			return res;
		}
		int minValueIndex = 0;
		int maxValueIndex = 0;
		int acc[] = new int[a.length];
		acc[0] = a[0];
		for(int i = 1; i < a.length; i++) {
			acc[i] = acc[i-1] + a[i];
			if(acc[i] < acc[minValueIndex]) {
				minValueIndex = i;
			}
			if(acc[i] > acc[maxValueIndex]) {
				maxValueIndex = i;
			}
		}
		if(minValueIndex >= maxValueIndex) {
			minValueIndex = 0;
		}
		// Why -1 + 1? Just to rememember we dont want to include the minValueIndex on the result, but the item right after
		int[] res = new int[maxValueIndex - minValueIndex - 1 + 1];
		for(int j = 0, i = minValueIndex+1; i < maxValueIndex+1; j++,i++) {
			res[j] = a[i];
		}
		return res;
	}
	
	private static int calculateArraySum(int[] a) {
		int sum = 0;
		for(int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

}
