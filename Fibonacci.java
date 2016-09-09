import java.util.Date;


public class Fibonacci {
	static int ITERATIONS = 80;

	public static void main(String[] args) {
		for(int i = 0; i < ITERATIONS; i++) {
			long start = (new Date()).getTime();
			long result = recursiveFibonacci(i);
			long end = (new Date()).getTime();
			System.out.println("For n = " + i + ", fibonacci = " + result + ". Took " + (end - start) + " milliseconds to process");
		}
	}
	
	public static long recursiveFibonacci(int n) {
		// Seeds
		if(n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		// Recursive function
		return recursiveFibonacci(n-1) + recursiveFibonacci(n-2);
	}
}