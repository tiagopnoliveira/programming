public class PairwiseBitSwap {
	
	//private static int[] a = {1,2,3,4};
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		printNumberAsBit(38735);
		printNumberAsBit(pairwiseBitSwap(38735));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static void printNumberAsBit(int n) {
		int i = 0;
		while(n != 0) {
			if((n & 1) > 0) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
			if(++i % 2 == 0) {
				System.out.print(" ");
			}
			n >>= 1;
		}
		System.out.println();
	}
    
    public static int pairwiseBitSwap(int n) {
		int em = 0;
		int om = 0;
		int n1 = n;
		int n2 = n;
		for(int i = 0; n != 0; i++) {
			if(i % 2 == 0) {
				om |= (1 << i);
			} else {
				em |= (1 << i);
			}
			n >>= 1;
		}
		n1 &= om;
		n1 <<= 1;
		n2 &= em;
		n2 >>= 1;
		return n1 | n2;
	}
    
}
