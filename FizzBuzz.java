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
			boolean gotFizzedOrBuzzedBro = false;
			if(i % 3 == 0) {
				gotFizzedOrBuzzedBro = true;
				System.out.print("Fizz");
			}
			if(i % 5 == 0) {
				gotFizzedOrBuzzedBro = true;
				System.out.print("Buzz");
			}
			if(gotFizzedOrBuzzedBro) {
				System.out.println();
			} else {
				System.out.println(i);
			}
		}
	}

}
