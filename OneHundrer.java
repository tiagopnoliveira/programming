import java.util.*;

public class OneHundrer {
	
	private static final int target = 100;
	private static final int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	private static final List<String> results = new ArrayList<String>();
	private static long counter = 0;
	
	private class OneHundrerInterm {
		public StringBuilder s;
		public int total;
		public int acc;
		
		public OneHundrerInterm(StringBuilder s, int total, int acc) {
			this.s = s;
			this.total = total;
			this.acc = acc;
		}
	}
	

    public static void main(String[] args) {
		OneHundrer("" + numbers[0], 1, 0, numbers[0]);
		for(String s : results) {
			System.out.println(s);
		}
		System.out.println("-------------------------------------");
		System.out.println("results found: " + results.size());
		System.out.println("counter: " + counter);
    }
	
	private static void OneHundrer(String s, int index, int total, int acc) {
		counter++;
		if(index == numbers.length) {
			total += acc;
			if(total == target) {
				results.add(s.toString());
			}
			return;
		}
		int n = numbers[index];
		OneHundrer(s + "+" + n, index+1, total + acc, n);
		OneHundrer(s + "-" + n, index+1, total + acc, -n);
		int n_tmp = n;
		while(n_tmp / 10 > 0 && acc != 0) {
			acc *= 10;
			n_tmp /= 10;
		}
		acc *= 10;
		if(acc < 0) {
			acc -= n;
		} else {
			acc += n;
		}
		OneHundrer(s + n, index+1, total, acc);
	}
	
}

