import java.util.*;
import java.lang.Math;

public class LettersAndNumbers {

	private static char[] a = {'a','a','a','a','1','1','a','1','a','1','a','1','a','a','a','a'};

	public static class Pair {
		int s;
		int e;

		public Pair(int s, int e) {
			this.s = s;
			this.e = e;
		}

		public Pair(int s) {
			this(s,-1);
		}
		
		public int getDistance() {
			if(e < 0) {
				return -1;
			}
			return e-s;
		}
	}

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		System.out.println(lettersAndNumbers(a));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static int lettersAndNumbers(char[] a) {
		int[] score = getScoreArray(a);
		HashMap<Integer,Pair> result = new HashMap<Integer,Pair>();
		for(int i = 0; i < score.length; i++) {
			int s = score[i];
			Pair p = result.get(s);
			if(p == null) {
				result.put(s,new Pair(i));
			} else {
				p.e = i;
			}
		}
		int maxDistance = -1;
		for(Integer i : result.keySet()) {
			Pair p = result.get(i);
			if(p.e >= 0) {
				maxDistance = Math.max(maxDistance,p.getDistance());
			}
		}
		return maxDistance;
	}
	
	public static int[] getScoreArray(char[] a) {
		int[] score = new int[a.length+1];
		score[0] = 0;
		for(int i = 0; i < a.length; i++) {
			int v = 0;
			if(isNumber(a[i])) {
				v = 1;
			} else {
				v = -1;
			}
			score[i+1] = score[i] + v;
		}
		return score;
	}
	
	private static boolean isNumber(char c) {
		if(c >= '0' && c <= '9') {
			return true;
		} else return false;
	}
}
