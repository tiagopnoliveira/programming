import java.util.ArrayList;
import java.util.HashMap;

public class BooleanEval {

	private static final HashMap<String,Result> memo = new HashMap<String,Result>();
	
	private static class Result {
		String s;
		int v;
		
		public Result(String s, int v) {
			this.s = s;
			this.v = v;
		}
	}
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		//ArrayList<Result> res = evaluate("1^0&1|1", 0);
		ArrayList<Result> res = evaluate("1^0&1|1&0^1|1|0^0&1^0", 1);
		for(Result r : res) {
			System.out.print(r.s);
			System.out.print(" -> " + r.v);
			System.out.println();
		}
		System.out.println("Number of results: " + res.size());
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static ArrayList<Result> evaluate(String s, int e) {
		ArrayList<Result> res = new ArrayList<Result>();
		if(s.length() == 1) {
			res.add(new Result(s,Integer.valueOf(s)));
			return res;
		}
		if(memo.containsKey(s)) {
			//System.out.println("Cache hit on: " + s);
			res.add(memo.get(s));
			return res;
		}
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c != '&' && c != '|' && c != '^') {
				continue;
			}
			String ls = s.substring(0,i);
			String rs = s.substring(i+1);
			int nextE = -1;
			ArrayList<Result> leftExpressions = evaluate(ls, -1);
			ArrayList<Result> rightExpressions = evaluate(rs, -1);
			for(Result l : leftExpressions) {
				for(Result r : rightExpressions) {
					String newS = l.s + c + r.s;
					int newV = -1;
					if(c == '&') {
						newV = l.v & r.v;
					} else if(c == '|') {
						newV = l.v | r.v;
					} else if(c == '^') {
						newV = l.v ^ r.v;
					} else {
						//shouldn't really happen 
					}
					Result newRes = new Result("(" +newS + ")", newV);
					memo.put(newS, newRes);
					if(e < 0 || newRes.v == e) {
						res.add(newRes);
					}
				}
			}
		}
		return res;
	}
}
