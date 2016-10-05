public class BitwiseMult {

    public static void main(String[] args) {
		System.out.println("Enter a: ");
		int a = Integer.parseInt(System.console().readLine());
		System.out.println();
		System.out.println("Enter b: ");
		int b = Integer.parseInt(System.console().readLine());
		System.out.println();
		System.out.println("Result: " + bitwiseMult(a,b));
    }
    
    public static int bitwiseMult(int a, int b) {
		int res = 0;
		if(b > a) {
			a ^= b;
			b ^= a;
			a ^= b;
		}
		while(b > 0) {
			while((b & 1) == 0) {
				b = b >> 1;
				a = a << 1;
			}
			res += a;
			a = a << 1;
			b = b >> 1;
		}
		return res;
	}

}
