public class BitwiseSum {

	private static final int a = 740;
	private static final int b = 60;

    public static void main(String[] args) {
		System.out.println("Sum result of " + a + " and " + b + " is " + bitwiseSum(a,b));
		System.out.println("Sub result of " + a + " and " + b + " is " + bitwiseSub(a,b));
    }
	
	private static int bitwiseSum(int a, int b) {
		while((a & b) > 0) {
			int tmp = (a & b) << 1;
			a ^= b;
			b = tmp;
		}
		return (a ^ b);
	}
	
	private static int bitwiseSub(int a, int b) {
		return bitwiseSum(a, bitwiseSum(~b, 1));
	}

}
