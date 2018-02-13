import java.util.ArrayList;
import java.util.HashMap;

public class BooleanEval {

	private static final HashMap<String,Result> memo = new HashMap<String,Result>();
	private static final HashMap<String,Integer> memo2 = new HashMap<String,Integer>();
	
	private static class Result {
		String s;
		int v;
		
		public Result(String s, int v) {
			this.s = s;
			this.v = v;
		}
	}
		
	public static class Expression {
		String l;
		String r;
		char op; // &, ^, |
		
		public Expression(char op) {
			this.op = op;
		}
	}
    
    private static ArrayList<Expression> splitStringIntoExpressions(String s) {
		ArrayList<Expression> res = new ArrayList<Expression>();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '&' || c == '|' || c == '^') {
				Expression exp = new Expression(c);
				exp.l = s.substring(0,i);
				exp.r = s.substring(i+1,s.length());
				res.add(exp);
			}
		}
		return res;
	}
	
	public static int calcExpressions(String s, boolean e) {
		if(s.length() == 0) {
			return 0;
		}
		if(memo2.containsKey(s + e)) {
			return memo2.get(s + e);
		}
		if(s.length() == 1) {
			int v = Integer.parseInt(s);
			if((v == 0 && !e) || (v > 0 && e)) {
				return 1;
			} else return 0;
		}
		int r = 0;
		ArrayList<Expression> expressions = splitStringIntoExpressions(s);
		for(Expression exp : expressions) {
			int l1 = calcExpressions(exp.l,true);
			int r1 = calcExpressions(exp.r,true);
			int l0 = calcExpressions(exp.l,false);
			int r0 = calcExpressions(exp.r,false);
			if(exp.op == '&') {
				if(e) {
					r += l1 * r1;
				} else {
					r += l0 * r0 + (l1 * r0) + (l0 * r1);
				}
			} else if(exp.op == '^') {
				if(e) {
					r += (l1 * r0) + (l0 * r1);
				} else {
					r += (l1 * r1) + (l0 * r0);
				}
			} else {
				if(e) {
					r += (l1 * r1) + (l1 * r0) + (l0 * r1);
				} else {
					r += (l0 * r0);
				}
			}
		}
		memo2.put(s + e,r);
		return r;
	}
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		//ArrayList<Result> res = evaluate("1^0&1|1", 0);
		ArrayList<Result> res = evaluate("1^0&1|1&0^1|1|0^0&1^0", 1);
		//~ for(Result r : res) {
			//~ System.out.print(r.s);
			//~ System.out.print(" -> " + r.v);
			//~ System.out.println();
		//~ }
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
