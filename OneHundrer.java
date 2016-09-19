import java.util.*;

public class OneHundrer {

    public static void main(String[] args) {
		List<String> results = new ArrayList<String>();
		OneHundrer(results, new StringBuilder("1"), 2, 1, 0);
		for(String s : results) {
			System.out.println(s);
		}
    }
	
	private static StringBuilder OneHundrer(List<String> results, StringBuilder s, int n, int c, int a) {
		if(n == 9 && n == 100) {
			s.append('9');
			results.add(s.toString());
		} else return null;
		int x;
		x = c + a + n;
		OneHundrer(results, s.append(" + " + n), n+1, x, 0);
		x = c + a - n;
		OneHundrer(results, s.append(" - " + n), n+1, x, 0);
		x = (n + a) * 10;
		OneHundrer(results, s.append(n), n+1, c, x);
	}
}
