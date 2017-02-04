import java.util.Stack;
import java.util.HashSet;

//~ Problem Statement

//~ You have the number s. You may change this number. In each step you can apply one of two possible changes: 

//~ Add a to your current number.
//~ Multiply your current number by b.
//~ Your goal is to change s into t using the minimal number of steps. 

//~ You are given the longs s, t, a, and b. Compute and return the smallest number of steps in which we can turn s into t, or -1 if changing s into t is not possible.
 
//~ Definition
    	
//~ Class:	MultiplyAddPuzzle
//~ Method:	minimalSteps
//~ Parameters:	long, long, long, long
//~ Returns:	long
//~ Method signature:	long minimalSteps(long s, long t, long a, long b)
//~ (be sure your method is public)
 
//~ Constraints
//~ -	s will be between 0 and 1,000,000,000,000,000,000 (10^18), inclusive.
//~ -	t will be between 0 and 1,000,000,000,000,000,000 (10^18), inclusive.
//~ -	a will be between 0 and 1,000,000,000,000,000,000 (10^18), inclusive.
//~ -	b will be between 0 and 1,000,000,000,000,000,000 (10^18), inclusive.

public class MultiplyAddPuzzle {
	
	private static final long MAX = 1000000000000000000L; // 60 bits

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		//~ long s = ;
		//~ long t = ;
		//~ long a = ;
		//~ long b = ;
		//~ long s = 345;
		//~ long t = 12345;
		//~ long a = 1;
		//~ long b = 10;
		//~ long s = 10;
		//~ long t = 99;
		//~ long a = 4;
		//~ long b = 2;

		//~ long s = 0;
		//~ long t = 1000000000000000000L;
		//~ long a = 5;
		//~ long b = 10;

		//~ long s = 5;
		//~ long t = 10;
		//~ long a = 2;
		//~ long b = 0;

		//~ long s = 0;
		//~ long t = 7227448402L;
		//~ long a = 2;
		//~ long b = 1;
		
		long s = 45;
		long t = 835824775649847932L;
		long a = 8;
		long b = 84;
		
		long res = minimalStepsTopCoder(s, t, a, b);
		System.out.println(res);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    // keeping this one around to help me cry myself to sleep
    public static long minimalSteps(long s, long t, long a, long b) {
		if(s == t) {
			return 0;
		}
		if(b == 1) {
			t -= s;
			if(t % a == 0) {
				return t / a;
			} else {
				return -1;
			}
		}
		HashSet<Long> cache = new HashSet<Long>();
		Stack<Long> stack = new Stack<Long>();
		stack.push(s);
		long c = 0;
		while(true) {
			Stack<Long> newStack = new Stack<Long>();
			c++;
			if(c % 10 == 0) {
				System.out.println(c + " - " + stack.size());
			}
			while(!stack.isEmpty()) {
				s = stack.pop();
				long sa = s + a;
				long sb = s * b;		
				if(buildStack(newStack, cache, sa, t) || buildStack(newStack, cache, sb, t)) {
					return c;
				}
			}
			stack = newStack;
			if(stack.isEmpty()) {
				return -1;
			}

		}
	}
	
	private static boolean buildStack(Stack<Long> stack, HashSet<Long> cache, long s, long t) {
				if(t == s) {
					return true;
				}
				if(s < t && !cache.contains(s)) {
					cache.add(s);
					stack.push(s);
				}
				//~ if(s < t) {
					//~ stack.push(s);
				//~ }
				return false;
	}
	
	public static long minimalStepsTopCoder(long s, long t, long a, long b) { 
    if(s == t)return 0L; 
    if(a == 0 && b == 0){ 
      return t == 0 ? 1 : -1; 
    } 
    if(a == 0){ 
      if(b == 1){ 
        return -1; 
      }else if(s == 0){ 
        return -1; 
      }else{ 
        int step = 0; 
        while(true){ 
          double ds = (double)s*b; 
          if(ds > 2e18)break; 
          step++; 
          s *= b; 
          if(s == t)return step; 
        } 
        return -1; 
      } 
    } 
    if(b == 0){ 
      long step = Long.MAX_VALUE; 
      if(t % a == 0)step = Math.min(step, t/a+1); 
      if(s <= t && (t-s)%a == 0){ 
        step = Math.min(step, (t-s)/a); 
      } 
      if(step == Long.MAX_VALUE)return -1; 
      return step; 
    } 
    if(b == 1){ 
      long step = Long.MAX_VALUE; 
      if(s <= t && (t-s)%a == 0){ 
        step = Math.min(step, (t-s)/a); 
      } 
      if(step == Long.MAX_VALUE)return -1; 
      return step; 
    } 
    if(s == 0){ 
      long ret = Long.MAX_VALUE; 
      if(s <= t && (t-s) % a == 0){ 
        long num = (t-s)/a; 
        long st = 0; 
        int mul = -1; 
        while(num > 0){ 
          st += num % b; 
          num /= b; 
          mul++; 
        } 
        mul = Math.max(mul, 0); 
        st += num; 
        ret = Math.min(ret, mul + st); 
      } 
      return ret == Long.MAX_VALUE ? -1 : ret; 
    } 
     
    int base = 0; 
    long ret = Long.MAX_VALUE; 
    while(true){ 
      if(s <= t && (t-s) % a == 0){ 
        long num = (t-s)/a; 
        long st = 0; 
        int rem = base; 
        while(num > 0 && rem > 0){ 
          st += num % b; 
          num /= b; 
          rem--; 
        } 
        st += num; 
        ret = Math.min(ret, base + st); 
      } 
       
      double ds = (double)s*b; 
      if(ds > 2e18)break; 
      base++; 
      s *= b; 
    } 
    return ret == Long.MAX_VALUE ? -1 : ret; 
  } 
	
}
