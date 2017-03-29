import java.util.HashSet;

https://careercup.com/question?id=5669183904808960
public class FindNumbersFrequencyOverQuarter {

	private static int[] a = {1,1,1,1,1,2,2,2,3,3,4,5};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		HashSet<Integer> results = findNumbersFrequencyOverQuarter(a);
		for(Integer i : results) {
			System.out.println(i);
		}
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static HashSet<Integer> findNumbersFrequencyOverQuarter(int[] a) {
		HashSet<Integer> results = new HashSet<Integer>();
		int minOccurrences = a.length/4 + 1;
		int i = 0;
		while(i+minOccurrences-1 < a.length) {
			if(!results.contains(a[i]) && a[i+minOccurrences-1] == a[i]) {
				results.add(a[i]);
				i += minOccurrences;
			} else {
				i++;				
			}
		}
		return results;
	}

}
