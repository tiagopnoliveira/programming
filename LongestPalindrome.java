public class LongestPalindrome {

	private static String input = "cbab";

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(longestPalindrome(input));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static int longestPalindrome(String S) {
		int[][] dp = new int[S.length()][S.length()];
		for(int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}
		
		for(int dist = 1; dist < dp.length; dist++) {
			for(int l = 0; l < dp.length - dist; l++) {
				int r = l + dist;
				if(S.charAt(l) == S.charAt(r)) {
					dp[l][r] = 2 + dp[l+1][r-1];
				} else {
					dp[l][r] = Math.max(dp[l+1][r],dp[l][r-1]);
				}
			}
		}
		return dp[0][dp.length-1];
	}

}
