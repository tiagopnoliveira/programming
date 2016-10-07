public class InjectBitStream {

    public static void main(String[] args) {
		System.out.println("Enter n: ");
		int n = Integer.parseInt(System.console().readLine());
		System.out.println();
		System.out.println("Enter m: ");
		int m = Integer.parseInt(System.console().readLine());
		System.out.println();
		System.out.println("Enter i: ");
		int i = Integer.parseInt(System.console().readLine());
		System.out.println();
		System.out.println("Enter j: ");
		int j = Integer.parseInt(System.console().readLine());
		System.out.println();
		System.out.println("N in bit format: " + Integer.toBinaryString(n));
		System.out.println("M in bit format: " + Integer.toBinaryString(m));
		System.out.println("Result in bit format: " + Integer.toBinaryString(injectBitStream(n,m,i,j)));
    }
    
    public static int injectBitStream(int n, int m, int i, int j) {
		n = clearBitRange(n,i,j);
		m <<= i;
		return n | m;
	}
	
	private static int clearBitRange(int n, int start, int end) {
		int mask = ~0;
		int subMask = ~1;
		for(int i = 0; i <= end; i++) {
			if(i >= start) {
				mask &= subMask;
			}
			subMask = (subMask << 1) | 1;
						
		}
		return mask & n;
	}
}
