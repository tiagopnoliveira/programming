public class Joao {

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		joao();
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void joao() {
		for(int i = 0; i < Integer.MAX_VALUE; i++) {
			System.out.println(i);
			Thread.sleep(1000);
		}
	}

}
