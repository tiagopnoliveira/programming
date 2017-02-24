import java.util.Stack;

// https://careercup.com/question?id=5749516234915840
public class FindLeafsBST {

//	private static int[] a = {5,4,7,1};
//	private static int[] a = {5,4,3,2,1,6};
//	private static int[] a = {5,3,2,4,8,7,9};
	private static int[] a = {6,2,5,3,8,7,9};


    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		findLeafsPreorderHitsBST(a);
		System.out.println();
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }

	public static void findLeafsPreorderHitsBST(int[] a) {
		Stack<Integer> p = new Stack<Integer>();
		for(int i = 1; i < a.length; i++) {
			if(a[i] < a[i-1]) {
				p.add(a[i-1]);
			} else {
				boolean foundLarger = false;
				while(!p.isEmpty()) {
					if(a[i] > p.peek()) {
						p.pop();
						foundLarger = true;
					} else {
						break;
					}
				}
				if(foundLarger) {
					System.out.println(a[i-1]);
				}
			}
		}
		System.out.println(a[a.length-1]);
	}
}
