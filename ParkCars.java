import java.util.Arrays;

public class ParkCars {

	private static int[] in  = {0,1,2,4,5,8,9,3,6,7};
	private static int[] out = {1,2,3,4,5,6,7,8,9,0};
	
	// 0 is empty

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		parkCars(in, out);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void parkCars(int[] in, int[] out) {
		int emptySpot = findItemPos(in,0);
		if(out[emptySpot] == 0) {
			for(int i = 0; i < in.length; i++) {
				if(in[i] != out[i]) {
					exchangePos(in, emptySpot, i);
					System.out.println(emptySpot + " -> " + i + " :: " + Arrays.toString(in));
					emptySpot = i;
					break;
				}
				
			}
		}
		while(out[emptySpot] != 0) {
			int p = findItemPos(in, out[emptySpot]);
			exchangePos(in, emptySpot, p);
			System.out.println(emptySpot + " -> " + p + " : " + Arrays.toString(in));
			emptySpot = p;
		}
	}
	
	private static int findItemPos(int[] a, int item) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] == item) {
				return i;
			}
		}
		return -1;
	}
	
	private static void exchangePos(int[] a, int p1, int p2) {
		a[p1] ^= a[p2];
		a[p2] ^= a[p1];
		a[p1] ^= a[p2];
	}

}
