public class FizzBuzz {

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		fizzBuzz(100);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void fizzBuzz(int n) {
		for(int i = 1; i <= n; i++) {
			System.out.print(i + " ");
			if(i % 3 == 0) {
				System.out.print("Fizz");
			}
			if(i % 5 == 0) {
				System.out.print("Buzz");
			}
			System.out.println();
		}
	}

}
