public class RodCutting {

	private static int[] values = {2, 3, 7, 8, 9, 11, 11, 15, 16, 15, 18, 18, 24, 24, 25, 26, 29, 33, 35, 37, 37, 38, 38, 39, 39, 41, 43};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println(rodCutting(values, 27));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int rodCutting(int[] values, int len) {
		int[] bestValuePerLen = new int[len+1];
		bestValuePerLen[0] = 0; //otherwise, maxProfit would be infinite.
		for(int i = 1; i <= len; i++) {
			int maxValue = values[i-1];
			for(int j = 1; j <= i/2; j++) {
				maxValue = Math.max(maxValue, bestValuePerLen[j] + bestValuePerLen[i-j]);
			}
			bestValuePerLen[i] = maxValue;
		}
		return bestValuePerLen[len];
	}

}
