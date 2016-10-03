import java.util.*;
import java.util.stream.Collectors;

public class PowerSet {
	
	private static int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		PowerSet ps = new PowerSet();
		Set<Set<Integer>> result = ps.powerSet(a);
		double duration = (System.currentTimeMillis() - startTime) / 1000;
		String listString = result.stream().map(Object::toString).collect(Collectors.joining(", "));
		System.out.println("Size: " + result.size());
		System.out.println(listString);
		System.out.print("Processing time: ");
		System.out.format("%.3f%n", duration);
		System.out.println(" seconds.");
    }
    
    public Set<Set<Integer>> powerSet(int[] a) {
		Set<Set<Integer>> result = new HashSet<Set<Integer>>();
		powerSetRecursive(a, result);
		return result;
	}
	
	private void powerSetRecursive(int[] a, Set<Set<Integer>> s) {
		Set<Integer> newSet;
		if(a.length == 0) {
			newSet = new HashSet<Integer>();
			if(!s.contains(newSet)) s.add(newSet);
			return;
		}
		
		for(int i = 0; i < a.length; i++) {
			int[] tmp = removeItem(a, i);
			newSet = new HashSet<Integer>();
			for(int j = 0; j < tmp.length; j++) {
				newSet.add(tmp[j]);
			}
			if(s.contains(newSet)) continue;
			s.add(newSet);
			powerSetRecursive(tmp, s);
		}
	}
	
	private int[] removeItem(int[] a, int pos) {
		int res[] = new int[a.length-1];
		int j = 0;
		for(int i = 0; i < a.length; i++) {
			if(i == pos) continue;
			res[j++] = a[i];
		}
		return res;
	}

}
