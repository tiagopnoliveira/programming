public class BitwiseSum {

	private static final int a = 740;
	private static final int b = 60;

    public static void main(String[] args) {
		System.out.println("Sum result of " + a + " and " + b + " is " + bitwiseSum(a,b));
    }
	
	private static int bitwiseSum(int a, int b) {
		while((a & b) > 0) {
			int tmp = (a & b) << 1;
			a ^= b;
			b = tmp;
		}
		return (a | b);
	}

}
