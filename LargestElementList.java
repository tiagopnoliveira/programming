public class LargestElementList {
	
	private static int[] a = {8, 3, 121, 12, 75, 99, 51, 15};

    public static void main(String[] args) {
		if(a.length == 0) {
			System.out.println("Array was empty");
		}
		
		int max = a[0];
		for(int i = 0; i < a.length; i++) {
			if(max < a[i]) {
				max = a[i];
			}
		}
		
		System.out.println(max);
    }

}
