import java.util.*;

// https://careercup.com/question?id=5707346257903616
public class UsageTV {
	protected static class Usage implements Comparable<Usage> {
		public int s;
		public int e;
		
		public Usage(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		public int compareTo(Usage u) {
			return this.e - u.e;
		}
	}

	//private static int[][] a = { {2,4} , {1,4} , {3,7} , {7,8} , {1,9} };
	//private static Usage[] u = { new Usage(2,4), new Usage(1,4), new Usage(6,7) };
	// private static Usage[] u = { new Usage(1,4), new Usage(6,8), new Usage(2,4), new Usage(7,9), new Usage(10,15) };
	// private static Usage[] u = { new Usage(2,2), new Usage(4,4), new Usage(65535,65536), new Usage((Integer.MAX_VALUE/4)-5,Integer.MAX_VALUE/4) };
	//private static Usage[] u = { new Usage(1,2), new Usage(2,3), new Usage(8,9), new Usage(7,9) };
	private static Usage[] u = { new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,Integer.MAX_VALUE/4), new Usage(2,4), new Usage(65535,65536) };
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(calcUsageTV(u));
		
		// System.out.println(calcUsageTVMethod2(u));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int calcUsageTV(Usage[] usageArray) {
		ArrayList<Usage> uList = new ArrayList<Usage>(Arrays.asList(usageArray));
		Collections.sort(uList);
		int minS = Integer.MAX_VALUE;
		int maxE = Integer.MIN_VALUE;
		for(Usage u : uList) {
			if(u.s < minS) {
				minS = u.s;
			}
			if(u.e > maxE) {
				maxE = u.e;
			}
		}
		int[] b = new int[maxE - minS + 1];
		for(Usage u : uList) {
			for(int i = u.s; i > minS; i--) {
				if(b[i-minS] > 0) {
					b[u.s-minS] = b[i-minS];
					break;
				}
			}
			
			if(b[u.e - minS] < (u.e - u.s + b[u.s - minS])) {
				for(int i = u.s + 1; i <= u.e; i++) {
					b[i-minS] = b[i-minS-1] + 1;
				}
			}
		}
		return b[maxE-minS];
	}
	
	public static int calcUsageTVMethod2(Usage[] usageArray) {
		ArrayList<Usage> uList = new ArrayList<Usage>(Arrays.asList(usageArray));
		int minS = Integer.MAX_VALUE;
		int maxE = Integer.MIN_VALUE;
		for(Usage u : uList) {
			if(u.s < minS) {
				minS = u.s;
			}
			if(u.e > maxE) {
				maxE = u.e;
			}
		}
		boolean[] b = new boolean[maxE - minS + 1];
		for(Usage u : uList) {
			for(int i = u.s - minS + 1; i <= u.e - minS; i++) {
				b[i] = true;
			}
		}
		
		int c = 0;
		for(int i = 0; i < b.length; i++) {
			if(b[i]) {
				c++;
			}
		}
		return c;
	}

}
