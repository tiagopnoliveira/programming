import java.lang.Math;

public class CountTwos {
	private static int n = 6553514;
	
	private static class CountTwoAnalyzer {
		public int mostSignificantNumber;
		public int remainder;
		public int magnitude;
		
		public CountTwoAnalyzer(int n) {
			this.remainder = 0;
			this.magnitude = 1;
			int mult = 1;
			while(n >= 10) {
				this.remainder += (n % 10) * mult;
				mult *= 10;
				n /= 10; 
				this.magnitude++;
			}
			this.mostSignificantNumber = n;
		}
	}

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println("Using brute force, for n = " + n +" found " + countTwosBruteStrings(n) + " instances of 2.");
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

		startTime = System.currentTimeMillis();
		System.out.println("Using best system, for n = " + n +" found " + countTwos(n) + " instances of 2.");
		duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static int countTwos(int n) {
		if(n < 2) return 0;
		if(n < 10) return 1;
		
		CountTwoAnalyzer c2a = new CountTwoAnalyzer(n);
		
		// First we check how many 2´s occur at that order of magnitude.
		// If n=300, we can assume we saw 200 instances of 2s while n was 2xx.
		int add = 0;
		if(c2a.mostSignificantNumber == 2) {
			add = c2a.remainder;
		} else if(c2a.mostSignificantNumber > 2) {
			add = (int) Math.pow(10, c2a.magnitude - 1);
		}
		
		// Now we add the discrete cases of the smaller order of magnitude.
		// If n was 500, we know there were 50 times there were x2x.
		add += ((int) Math.pow(10, c2a.magnitude - 2)) * c2a.mostSignificantNumber;
		
		// After adding all the discrete cases on int add, we process a smaller n
		// and multiply its output against the MSN x 10 -> n(521) = 50*n(10) + discrete cases + n(21) 
		return c2a.mostSignificantNumber*10*countTwos((int) Math.pow(10, c2a.magnitude - 2)) + add + countTwos(c2a.remainder);
	}
    
    public static int countTwosBruteStrings(int n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i < n; i++) {
			sb.append(i);
		}
		String s = sb.toString();
		int size = s.length();
		s = s.replace("2", "");
		return size - s.length();
	}
}
