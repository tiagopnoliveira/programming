import java.util.Set;
import java.util.HashSet;

public class GenerateParentheses {
	private static final int n = 5;

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Set<String> res = generateParentheses(n);
		double duration = System.currentTimeMillis() - startTime;
		for(String s : res) {
			System.out.println(s);
		}
		System.out.println("Total results: " + res.size());
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static Set<String> generateParentheses(int n) {
		Set<String> res = new HashSet<String>();
		generateParentheses(res, n, n, "");
		return res;
	}
	
	private static void generateParentheses(Set<String> res, int l, int r, String current) {
		if(r < l || l < 0) {
			return;
		}
		if(r == 0 && l == 0) {
			res.add(current);
			return;
		}
		generateParentheses(res, l-1, r, current.concat("("));
		generateParentheses(res, l, r-1, current.concat(")"));
	}

}
