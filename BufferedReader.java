public class BufferedReader {

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int read4K(int fd, char[] buf) {
		return 4096;
	}
	
	public static int read(int fd, char[] buf, int size) {
	}

}
