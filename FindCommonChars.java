import java.util.*;

public class FindCommonChars {

	private static String[] dict = {"May", "student", "students", "dog", "studentssess", "god", "Cat", "act", "tab", "bat", "flow", "wolf", "lambs", "Amy", "Yam", "balms", "looped", "poodle", "john", "alice"};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		printFindCommonChars(dict);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void printFindCommonChars(String[] dict) {
		HashMap<BitSet,ArrayList<String>> results = new HashMap<BitSet,ArrayList<String>>();
		for(String s : dict) {
			BitSet bs = new BitSet(26);
			s = s.toLowerCase();
			for(int i = 0; i < s.length(); i++) {
				char c = (char) (s.charAt(i) - 'a');
				bs.set(c);				
			}
			if(results.get(bs) == null) {
				ArrayList<String> newArrayList = new ArrayList<String>();
				results.put(bs, newArrayList);
			}
			results.get(bs).add(s);
		}
		for(BitSet bs : results.keySet()) {
			for(String s : results.get(bs)) {
				System.out.print(s + ' ');
			}
			System.out.println();
		}
	}

}
