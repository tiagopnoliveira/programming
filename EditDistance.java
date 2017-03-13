import java.util.*;

public class EditDistance {
	protected static class EditDistanceInterm {
		public String a;
		public String b;
		public int n;
		
		public EditDistanceInterm(String a, String b, int n) {
			this.a = a;
			this.b = b;
			this.n = n;
		}
	}

	private static final String a = "strial";
	private static final String b = "trials";

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(editDistanceRec(a,b));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int editDistanceIterative(String a, String b) {
		Queue<EditDistanceInterm> results = new LinkedList<EditDistanceInterm>();
		results.add(new EditDistanceInterm(a,b,0));
		int minFound = Integer.MAX_VALUE;
		while(!results.isEmpty()) {
			EditDistanceInterm res = results.poll();
			a = res.a;
			b = res.b;
			int n = res.n;
			if(a.equals(b)) {
				minFound = Math.min(minFound, n);
				continue;
			}
			if(a.length() == 0) {
				minFound = Math.min(minFound, n+b.length());
				continue;
			}
			if(b.length() == 0) {
				minFound = Math.min(minFound, n+a.length());
				continue;
			}
			if(a.charAt(a.length()-1) == b.charAt(b.length()-1)) {
				results.add(new EditDistanceInterm(a.substring(0,a.length()-1), b.substring(0,b.length()-1),n));
				continue;
			}
			results.add(new EditDistanceInterm(a.substring(0,a.length()-1),b,n+1));
			results.add(new EditDistanceInterm(a,b.substring(0,b.length()-1),n+1));
			results.add(new EditDistanceInterm(a.substring(0,a.length()-1), b.substring(0,b.length()-1),n+1));
		}
		return minFound;
	}

	public static int editDistanceRec(String a, String b) {
		return editDistanceRec(a,b,0);
	}

	
	private static int editDistanceRec(String a, String b, int n) {
		if(a.equals(b)) {
			return n;
		}
		if(a.length() == 0) {
			return n+b.length();
		}
		if(b.length() == 0) {
			return n+a.length();
		}
		if(a.charAt(a.length()-1) == b.charAt(b.length()-1)) {
			return editDistanceRec(a.substring(0,a.length()-1), b.substring(0,b.length()-1),n);
		}
		int removeCharA = editDistanceRec(a.substring(0,a.length()-1),b,n+1);
		int removeCharB = editDistanceRec(a,b.substring(0,b.length()-1),n+1);
		int editChar = editDistanceRec(a.substring(0,a.length()-1), b.substring(0,b.length()-1),n+1);
		return Math.min(Math.min(removeCharA,removeCharB),editChar);
	}
}
